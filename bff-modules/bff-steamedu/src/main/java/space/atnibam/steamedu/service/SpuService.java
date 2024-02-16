package space.atnibam.steamedu.service;

import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.model.dto.AdaptiveCourseDTO;
import space.atnibam.steamedu.model.dto.NearbyCourseDTO;

import java.util.List;

/**
 * @InterfaceName: SpuService
 * @Description: 商品服务
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-06 19:38
 **/
public interface SpuService {
    /**
     * 根据课程ID获取商品信息
     *
     * @param courseId 课程ID
     * @return 课程信息
     */
    R getCourseDetailByCourseId(Integer courseId);

    /**
     * 根据位置获取用户附近的课程列表
     *
     * @param lat 纬度
     * @param lng 经度
     * @return 课程列表
     */
    List<NearbyCourseDTO> getNearbyCourseList(Double lat, Double lng);

    /**
     * 获取自适应课程列表
     *
     * @return 自适应课程列表
     */
    List<AdaptiveCourseDTO> getAdaptiveCourseList();
}