package space.atnibam.steamedu.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import space.atnibam.api.cms.RemoteCommentService;
import space.atnibam.api.pms.RemoteSpuService;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.mapper.CourseMapper;
import space.atnibam.steamedu.model.dto.AdaptiveCourseDTO;
import space.atnibam.steamedu.model.dto.CourseDetailDTO;
import space.atnibam.steamedu.model.dto.NearbyCourseDTO;
import space.atnibam.steamedu.model.entity.Course;
import space.atnibam.steamedu.service.CourseTeacherRelService;
import space.atnibam.steamedu.service.SpuService;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SpuServiceImpl
 * @Description: 商品服务实现类
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-06 19:38
 **/
@Service
public class SpuServiceImpl implements SpuService {
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private RemoteSpuService remoteSpuService;
    @Resource
    private RemoteCommentService remoteCommentService;
    @Resource
    private CourseTeacherRelService courseTeacherRelService;

    /**
     * 根据课程ID获取商品信息
     *
     * @param courseId 课程ID
     * @return 课程信息
     */
    @Override
    public R<CourseDetailDTO> getCourseDetailByCourseId(Integer courseId) {
        Course course = courseMapper.selectCourseById(courseId);

        // 同步调用远程服务并直接转换为CourseDetailDTO
        Object spuDetail = remoteSpuService.getSpuDetail(course.getSpuId()).getData();
        CourseDetailDTO courseDetailDTO = objectMapper.convertValue(spuDetail, CourseDetailDTO.class);

        // 设置从Course对象中获取的信息
        BeanUtils.copyProperties(course, courseDetailDTO, "spuId");
        // 获取综合评分
        courseDetailDTO.setTotalComprehensiveScore((Double) remoteCommentService.getAverageGrade(course.getSpuId(), "2").getData());

        // 获取教师信息
        courseDetailDTO.setTeacher(courseTeacherRelService.getCourseDetailTeacherInfoByCourseId(courseId));

        return R.ok(courseDetailDTO);
    }

    /**
     * 根据位置获取用户附近的课程列表
     *
     * @param lat 纬度
     * @param lng 经度
     * @return 课程列表
     */
    @Override
    public List<NearbyCourseDTO> getNearbyCourseList(Double lat, Double lng) {
        List<NearbyCourseDTO> nearbyCourseDTOList = new ArrayList<>();
        List<Course> courses = courseMapper.selectActiveCourses(1);

        for (Course course : courses) {
            // 同步调用远程服务并直接转换为CourseDetailDTO
            Object spuDetail = remoteSpuService.getSpuDetail(course.getSpuId()).getData();
            // 将远程服务返回的对象转换为NearbyCourseDTO对象
            NearbyCourseDTO nearbyCourseDTO = objectMapper.convertValue(spuDetail, NearbyCourseDTO.class);

            if (nearbyCourseDTO == null) {
                continue;
            }
            LocalDateTime courseStartTime = course.getStartTime();
            LocalDateTime courseEndTime = course.getEndTime();
            // 格式化课程时间
            String formattedSchoolTime = formatCourseTime(courseStartTime, courseEndTime);

            // TODO: 距离判断先搁置，等后面一起实现。
            nearbyCourseDTO.setDistanceFromUser("3.2km");
            nearbyCourseDTO.setSchoolTime(formattedSchoolTime);
            // 获取课程的教师信息
            nearbyCourseDTO.setTeacher(courseTeacherRelService.getNearbyCourseTeacherInfoByCourseId(course.getCourseId()));
            // 复制Course对象的属性到NearbyCourseDTO对象
            BeanUtils.copyProperties(course, nearbyCourseDTO);
            nearbyCourseDTOList.add(nearbyCourseDTO);
        }

        return nearbyCourseDTOList;
    }

    /**
     * 获取自适应课程列表
     *
     * @return 活跃课程实体列表
     */
    private List<Course> fetchActiveCourses() {
        return courseMapper.selectActiveCourses(2);
    }

    /**
     * 获取并解析SPU详情
     *
     * @param spuId 商品ID
     * @return 解析后的SPU详情Map
     */
    private Map<String, Object> fetchAndParseSpuDetail(Integer spuId) {
        Object spuDetailData = remoteSpuService.getSpuDetail(spuId).getData();
        if (spuDetailData == null) {
            return null;
        }
        return objectMapper.convertValue(spuDetailData, Map.class);
    }

    /**
     * 提取Merchant中的用户名
     *
     * @param spuDetailAsMap 解析后的SPU详情Map
     * @return 用户名
     */
    private String extractUserNameFromMerchant(Map<String, Object> spuDetailAsMap) {
        if (!spuDetailAsMap.containsKey("merchant")) {
            return null;
        }
        Map<String, Object> merchant = (Map<String, Object>) spuDetailAsMap.get("merchant");
        if (merchant != null) {
            return (String) merchant.get("userName");
        }
        return null;
    }

    /**
     * 获取自适应课程列表
     *
     * @return 自适应课程DTO列表
     */
    @Override
    public List<AdaptiveCourseDTO> getAdaptiveCourseList() {
        List<AdaptiveCourseDTO> adaptiveCourseDTOList = new ArrayList<>();
        List<Course> courses = fetchActiveCourses();

        for (Course course : courses) {
            // 解析Spu详情
            Map<String, Object> spuDetailAsMap = fetchAndParseSpuDetail(course.getSpuId());
            if (spuDetailAsMap == null) {
                continue;
            }

            // 从Spu详情中提取用户名
            String userName = extractUserNameFromMerchant(spuDetailAsMap);

            // 创建自适应课程DTO对象
            AdaptiveCourseDTO adaptiveCourseDTO = new AdaptiveCourseDTO();
            // 复制Course对象的属性到自适应课程DTO对象
            BeanUtils.copyProperties(course, adaptiveCourseDTO);
            // 设置自适应课程DTO对象的名称
            adaptiveCourseDTO.setName((String) spuDetailAsMap.get("name"));
            // 设置自适应课程DTO对象的用户名
            adaptiveCourseDTO.setUserName(userName);
            // 设置自适应课程DTO对象的销售量
            adaptiveCourseDTO.setSalesVolume((Integer) spuDetailAsMap.get("salesVolume"));

            // 将自适应课程DTO对象添加到列表中
            adaptiveCourseDTOList.add(adaptiveCourseDTO);
        }
        return adaptiveCourseDTOList;
    }

    /**
     * 格式化课程时间
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 格式化后的课程时间
     */
    private String formatCourseTime(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd");

        // 判断是否为同一年
        if (startTime.toLocalDate().getYear() == now.getYear() && endTime.toLocalDate().getYear() == now.getYear()) {
            // 格式化为"MM.dd"格式
            return startTime.toLocalDate().format(formatter) + " - " + endTime.toLocalDate().format(formatter);
        } else {
            // 格式化为"yyyy.MM.dd"格式
            formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            return startTime.format(formatter) + " - " + endTime.format(formatter);
        }
    }

}