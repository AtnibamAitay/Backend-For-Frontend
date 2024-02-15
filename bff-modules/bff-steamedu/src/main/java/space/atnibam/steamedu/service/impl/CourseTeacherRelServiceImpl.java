package space.atnibam.steamedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import space.atnibam.api.ums.RemoteUserInfoService;
import space.atnibam.steamedu.mapper.CourseTeacherRelMapper;
import space.atnibam.steamedu.model.dto.CourseDetailDTO;
import space.atnibam.steamedu.model.entity.CourseTeacherRel;
import space.atnibam.steamedu.service.CourseTeacherRelService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: CourseTeacherRelServiceImpl
 * @Description: 课程教师关系Service实现
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-15 15:23
 **/
@Service
public class CourseTeacherRelServiceImpl extends ServiceImpl<CourseTeacherRelMapper, CourseTeacherRel> implements CourseTeacherRelService {
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private CourseTeacherRelMapper courseTeacherRelMapper;
    @Resource
    private RemoteUserInfoService remoteUserInfoService;

    /**
     * 根据课程ID查询所有教师的信息
     *
     * @param courseId 课程ID
     * @return 教师信息列表
     */
    @Override
    public List<CourseDetailDTO.TeacherDTO> getCourseTeacherInfoByCourseId(Integer courseId) {
        // 查询指定课程ID的所有教师关系
        List<CourseTeacherRel> courseTeacherRels = courseTeacherRelMapper.selectCourseTeacherRelByCourseId(courseId);

        // 获取用户ID和角色映射
        Map<Integer, String> userIdToRoleMap = getTeacherRolesMapping(courseTeacherRels);

        // 提取并转换用户信息为TeacherDTO列表
        return extractAndConvertTeachersInfo(userIdsFromRels(courseTeacherRels), userIdToRoleMap);
    }

    /**
     * 从课程教师关系中提取用户ID集合
     *
     * @param courseTeacherRels 课程教师关系列表
     * @return 用户ID集合
     */
    private List<Integer> userIdsFromRels(List<CourseTeacherRel> courseTeacherRels) {
        return courseTeacherRels.stream()
                .map(CourseTeacherRel::getTeacherUserId)
                .collect(Collectors.toList());
    }

    /**
     * 获取教师角色映射
     *
     * @param courseTeacherRels 课程教师关系列表
     * @return 用户ID到角色的映射
     */
    private Map<Integer, String> getTeacherRolesMapping(List<CourseTeacherRel> courseTeacherRels) {
        return courseTeacherRels.stream()
                .collect(Collectors.toMap(
                        CourseTeacherRel::getTeacherUserId,
                        this::getTeacherRoleDescription));
    }

    /**
     * 获取教师角色描述
     *
     * @param courseTeacherRel 课程教师关系对象
     * @return 角色描述
     */
    private String getTeacherRoleDescription(CourseTeacherRel courseTeacherRel) {
        switch (courseTeacherRel.getTeacherRole()) {
            case 0:
                return "主讲";
            case 1:
                return "助教";
            default:
                // TODO: 改为抛出自定义异常
                return null;
        }
    }

    /**
     * 从远程服务获取用户基本信息并转换为TeacherDTO列表
     *
     * @param userIds 用户ID列表
     * @param roleMap 用户ID到角色的映射
     * @return TeacherDTO列表
     */
    private List<CourseDetailDTO.TeacherDTO> extractAndConvertTeachersInfo(List<Integer> userIds, Map<Integer, String> roleMap) {
        // 从远程服务获取用户基本信息
        Object teacherUserInfoList = remoteUserInfoService.getBasicUserInfo(userIds).getData();
        List<Map<String, Object>> teachersListDataMap = (List<Map<String, Object>>) teacherUserInfoList;

        // 将处理后的DTO对象放入列表中
        return teachersListDataMap.stream()
                .map(teacherMap -> {
                    // 获取用户ID
                    Integer userId = (Integer) teacherMap.get("userId");
                    // 将用户信息转换为DTO对象
                    CourseDetailDTO.TeacherDTO teacherDTO = objectMapper.convertValue(teacherMap, CourseDetailDTO.TeacherDTO.class);
                    // 设置教师角色
                    teacherDTO.setTeacherRole(roleMap.getOrDefault(userId, ""));
                    return teacherDTO;
                })
                .collect(Collectors.toList());
    }

}