package space.atnibam.steamedu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 课程信息表
 *
 * @TableName course
 */
@TableName(value = "course")
@Data
public class Course implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 课程id
     */
    @TableId(type = IdType.AUTO)
    private Integer courseId;
    /**
     * 商品id
     */
    private Integer spuId;
    /**
     * 上课地点
     */
    private String location;
    /**
     * 经度
     */
    private BigDecimal longitude;
    /**
     * 纬度
     */
    private BigDecimal latitude;
    /**
     * 课程类型（0代表线上课，1代表线下课）
     */
    private Integer courseType;
    /**
     * 课程开始时间
     */
    private LocalDateTime startTime;
    /**
     * 每节课时长
     */
    private Integer classDuration;
    /**
     * 课时数
     */
    private Integer totalClasses;
    /**
     * 课程结束时间
     */
    private LocalDateTime endTime;
    /**
     * 结课状态（0代表未结课、1代表已结课）
     */
    private Integer completionStatus;
    /**
     * 课程创建时间
     */
    private Date createTime;
    /**
     * 状态（0代表已删除、1代表正常）
     */
    private Integer status;
}