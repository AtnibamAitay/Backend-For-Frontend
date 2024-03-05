package space.atnibam.steamedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import space.atnibam.steamedu.model.entity.StudentInfo;

/**
 * @author Atnibam Aitay
 * @description 针对表【student_info(学生信息表)】的数据库操作Mapper
 * @createDate 2024-03-05 13:55:41
 * @Entity space.atnibam.steamedu.model.entity.StudentInfo
 */
public interface StudentInfoMapper extends BaseMapper<StudentInfo> {
    /**
     * 根据用户ID获取学生信息
     *
     * @param userId 用户ID
     * @return 学生信息
     */
    @Select("SELECT * FROM student_info WHERE user_id = #{userId}")
    StudentInfo selectStudentInfosByUserId(Integer userId);
}
