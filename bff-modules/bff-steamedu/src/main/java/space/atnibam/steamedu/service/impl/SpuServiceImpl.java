package space.atnibam.steamedu.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import space.atnibam.api.pms.RemoteSpuService;
import space.atnibam.common.core.domain.R;
import space.atnibam.steamedu.model.dto.CourseDetailDTO;
import space.atnibam.steamedu.service.SpuService;

import javax.annotation.Resource;
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
    private RemoteSpuService remoteSpuService;

    /**
     * 根据课程ID获取商品信息
     *
     * @param courseId 课程ID
     * @return 课程信息
     */
    @Override
    public R getCourseDetailByCourseId(Integer courseId) {
        Object spuDetail = remoteSpuService.getSpuDetail(courseId).getData();
        Map<String, Object> spuDetailMap = (Map<String, Object>) spuDetail;
        CourseDetailDTO courseDetailDTO = objectMapper.convertValue(spuDetailMap, CourseDetailDTO.class);
        return R.ok(courseDetailDTO);
    }
}