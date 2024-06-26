package space.atnibam.steamedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import space.atnibam.api.ums.RemoteUserInfoService;
import space.atnibam.steamedu.mapper.CourseTeacherRelMapper;
import space.atnibam.steamedu.model.dto.CourseDetailDTO;
import space.atnibam.steamedu.model.dto.UserBaseInfoDTO;
import space.atnibam.steamedu.model.entity.CourseTeacherRel;
import space.atnibam.steamedu.service.CourseTeacherRelService;
import space.atnibam.steamedu.utils.CourseInfoUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
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
    @Resource
    private CourseInfoUtils courseInfoUtils;

    /**
     * 根据课程ID查询所有教师的信息（含职位）
     *
     * @param courseId 课程ID
     * @return 教师信息列表
     */
    @Override
    public List<CourseDetailDTO.TeacherDTO> getCourseDetailTeacherInfoByCourseId(Integer courseId) {
        // 查询指定课程ID的所有教师关系
        List<CourseTeacherRel> courseTeacherRels = courseTeacherRelMapper.selectCourseTeacherRelByCourseId(courseId);

        // 获取用户ID和角色映射
        Map<Integer, String> userIdToRoleMap = getTeacherRolesMapping(courseTeacherRels);

        // 提取并转换用户信息为TeacherDTO列表
        return extractAndConvertCourseDetailDTOTeachersInfo(courseInfoUtils.extractFieldFromList(courseTeacherRels, CourseTeacherRel::getTeacherUserId), userIdToRoleMap);
    }

    /**
     * 根据课程ID查询所有教师的信息（不含职位）
     *
     * @param courseId 课程ID
     * @return 教师信息列表
     */
    @Override
    public List<UserBaseInfoDTO> getNearbyCourseTeacherInfoByCourseId(Integer courseId) {
        // 查询指定课程ID的所有教师关系
        List<CourseTeacherRel> courseTeacherRels = courseTeacherRelMapper.selectCourseTeacherRelByCourseId(courseId);

        // 提取教师ID列表
        List<Integer> teacherUserIds = courseInfoUtils.extractFieldFromList(courseTeacherRels, CourseTeacherRel::getTeacherUserId);

        // 提取并转换用户信息为TeacherDTO列表
        return extractAndConvertNearbyCourseDTOTeachersInfo(teacherUserIds);
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
     * 从远程服务获取用户基本信息并转换为指定类型的TeacherDTO列表
     *
     * @param userIds  用户ID列表
     * @param dtoClass 目标TeacherDTO类的Class对象
     * @param roleMap  可选参数，用于设置教师角色映射（如果dto需要该字段）
     * @return TeacherDTO列表
     */
    private <T extends UserBaseInfoDTO> List<T> extractAndConvertTeachersInfo(List<Integer> userIds, Class<T> dtoClass, Map<Integer, String> roleMap) {
        // 从远程服务获取用户基本信息
        Object teacherUserInfoList = remoteUserInfoService.getBasicUserInfo(userIds).getData();
        List<Map<String, Object>> teachersListDataMap = (List<Map<String, Object>>) teacherUserInfoList;

        // 将处理后的DTO对象放入列表中
        // TODO:这里需要做非空判断，否则会出现空指针异常
        return teachersListDataMap.stream()
                .map(teacherMap -> {
                    // 获取用户ID
                    Integer userId = (Integer) teacherMap.get("userId");
                    // 将用户信息转换为目标DTO对象
                    T teacherDTO = objectMapper.convertValue(teacherMap, dtoClass);

                    // 如果roleMap不为空且目标DTO类有TeacherRole属性，则设置教师角色
                    try {
                        if (roleMap != null && teacherDTO.getClass().getDeclaredField("teacherRole") != null) {
                            try {
                                Field teacherRoleField = teacherDTO.getClass().getDeclaredField("teacherRole");
                                teacherRoleField.setAccessible(true);
                                teacherRoleField.set(teacherDTO, roleMap.getOrDefault(userId, ""));
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                log.error("Failed to set teacher role due to ", e);
                            }
                        }
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }

                    return teacherDTO;
                })
                .collect(Collectors.toList());
    }

    /**
     * 从远程服务获取课程详情DTO的教师信息并转换为指定类型的TeacherDTO列表
     *
     * @param userIds 用户ID列表
     * @param roleMap 可选参数，用于设置教师角色映射（如果dto需要该字段）
     * @return TeacherDTO列表
     */
    private List<CourseDetailDTO.TeacherDTO> extractAndConvertCourseDetailDTOTeachersInfo(List<Integer> userIds, Map<Integer, String> roleMap) {
        return extractAndConvertTeachersInfo(userIds, CourseDetailDTO.TeacherDTO.class, roleMap);
    }

    /**
     * 从远程服务获取附近课程DTO的教师信息并转换为指定类型的TeacherDTO列表
     *
     * @param userIds 用户ID列表
     * @return TeacherDTO列表
     */
    private List<UserBaseInfoDTO> extractAndConvertNearbyCourseDTOTeachersInfo(List<Integer> userIds) {
        return extractAndConvertTeachersInfo(userIds, UserBaseInfoDTO.class, null);
    }

}