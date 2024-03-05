package space.atnibam.steamedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import space.atnibam.steamedu.model.dto.StudentInfoDTO;
import space.atnibam.steamedu.model.entity.StudentInfo;

/**
 * @author Atnibam Aitay
 * @description 针对表【student_info(学生信息表)】的数据库操作Service
 * @createDate 2024-03-05 13:55:41
 */
public interface StudentInfoService extends IService<StudentInfo> {

    /**
     * 获取当前用户的学生信息
     *
     * @return 当前用户的学生信息
     */
    StudentInfoDTO getStudentInfosByCurrentUser();
}
