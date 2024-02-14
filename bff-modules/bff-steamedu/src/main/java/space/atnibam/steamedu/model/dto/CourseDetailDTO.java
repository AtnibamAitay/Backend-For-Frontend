package space.atnibam.steamedu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName: CourseDetailDTO
 * @Description: 课程详细信息
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-14 23:05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 课程id
     */
    private Integer spuId;
    /**
     * 封面列表
     */
    private List<String> cover;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 库存（剩余名额）
     */
    private Integer stock;
    /**
     * 课程总名额
     */
    private Integer totalStock;
    /**
     * 课程开始时间
     */
    private LocalDateTime startTime;
    /**
     * 课程名
     */
    private String name;
    /**
     * 综合评分
     */
    private Double totalComprehensiveScore;
    /**
     * 累计销量
     */
    private Integer salesVolume;
    /**
     * 课时
     */
    private Integer totalClasses;
    /**
     * 教师列表
     */
    private List<teacherDTO> teacher;
    /**
     * 机构信息
     */
    private MerchantDTO merchant;
    /**
     * 上课地点
     */
    private String location;
    /**
     * 介绍图列表
     */
    private List<String> detail;
    /**
     * 是否上架（0表示下架、1表示）
     */
    private Integer isListed;

    /**
     * 开课机构
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MerchantDTO implements Serializable {
        private static final long serialVersionUID = 1L;
        /**
         * 机构ID
         */
        private Integer userId;
        /**
         * 机构名
         */
        private String userName;
        /**
         * 头像
         */
        private String userAvatar;
        /**
         * 机构简介
         */
        private String userIntroduction;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class teacherDTO implements Serializable {
        private static final long serialVersionUID = 1L;
        /**
         * 头像
         */
        private String userAvatar;
        /**
         * 教师角色
         */
        private String teacherRole;
        /**
         * 教师名
         */
        private String userName;
    }
}
