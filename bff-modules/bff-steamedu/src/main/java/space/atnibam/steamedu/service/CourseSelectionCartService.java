package space.atnibam.steamedu.service;

import space.atnibam.steamedu.model.dto.CourseBriefInfoDTO;

import java.util.List;

/**
 * @InterfaceName: CourseSelectionCartService
 * @Description:
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-27 14:55
 **/
public interface CourseSelectionCartService {
    /**
     * 获取用户的选课单列表
     *
     * @param userId 用户ID
     * @return 选课单列表
     */
    List<CourseBriefInfoDTO> getCourseSelectionCartByUserId(Integer userId);
}