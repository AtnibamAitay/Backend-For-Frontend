package space.atnibam.steamedu.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName: OrderInfoDTO
 * @Description: 订单信息
 * @Author: AtnibamAitay
 * @CreateTime: 2024-03-05 16:05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoDTO {
    @ApiModelProperty("学生信息")
    private OrderStudentInfoDTO studentInfo;

    @ApiModelProperty("课程信息")
    private List<OrderCourseInfoDTO> courseInfo;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderStudentInfoDTO implements Serializable {
        private static final long serialVersionUID = 1L;
        @ApiModelProperty("学生姓名")
        private String studentName;

        @ApiModelProperty("学校")
        private String contact;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderCourseInfoDTO implements Serializable {
        private static final long serialVersionUID = 1L;
        @ApiModelProperty("课程ID")
        private Integer courseId;

        @ApiModelProperty("课程类型")
        private Integer courseType;

        @ApiModelProperty("课程名称")
        private String name;

        // TODO:为了方便前端测试而临时设置的价格
        @ApiModelProperty("课程价格")
        private BigDecimal price = BigDecimal.valueOf(100);

        @ApiModelProperty("上课时间")
        private String schoolTime;
    }
}
