package space.atnibam.steamedu.service;

import space.atnibam.common.core.domain.R;

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
}