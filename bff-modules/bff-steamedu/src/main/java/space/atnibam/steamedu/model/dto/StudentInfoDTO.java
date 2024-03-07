package space.atnibam.steamedu.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: StudentInfoDTO
 * @Description: 学生信息DTO
 * @Author: AtnibamAitay
 * @CreateTime: 2024-03-05 14:39
 **/
@Data
public class StudentInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 学生姓名
     */
    private String studentName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 学校
     */
    private String school;
    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
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