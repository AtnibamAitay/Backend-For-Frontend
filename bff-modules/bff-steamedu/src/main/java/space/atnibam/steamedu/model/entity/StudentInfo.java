package space.atnibam.steamedu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生信息表
 *
 * @TableName student_info
 */
@TableName(value = "student_info")
@Data
public class StudentInfo implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 学生姓名
     */
    private String studentName;
    /**
     * 性别（0代表女，1代表男）
     */
    private Integer gender;
    /**
     * 学校
     */
    private String school;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 联系方式
     */
    private String contact;
    /**
     * 照片
     */
    private String photo;
    /**
     * 年级
     */
    private String grade;
}