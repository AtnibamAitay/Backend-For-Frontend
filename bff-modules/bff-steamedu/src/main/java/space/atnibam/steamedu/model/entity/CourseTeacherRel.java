package space.atnibam.steamedu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName course_teacher_rel
 */
@TableName(value = "course_teacher_rel")
@Data
public class CourseTeacherRel implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer ctRelId;
    /**
     * 课程ID
     */
    private Integer courseId;
    /**
     * 教师ID
     */
    private Integer teacherUserId;
    /**
     * 教师在课程中的角色（0代表主讲，1表示助教）
     */
    private Integer teacherRole;
}