package space.atnibam.steamedu.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import space.atnibam.api.cms.RemoteCommentService;
import space.atnibam.api.pms.RemoteSpuService;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.mapper.CourseMapper;
import space.atnibam.steamedu.model.dto.CourseDetailDTO;
import space.atnibam.steamedu.model.entity.Course;
import space.atnibam.steamedu.service.CourseTeacherRelService;
import space.atnibam.steamedu.service.SpuService;

import javax.annotation.Resource;

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
        courseDetailDTO.setTeacher(courseTeacherRelService.getCourseTeacherInfoByCourseId(courseId));

        return R.ok(courseDetailDTO);
    }
}