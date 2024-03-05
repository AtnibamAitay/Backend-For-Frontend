package space.atnibam.steamedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import space.atnibam.steamedu.mapper.StudentInfoMapper;
import space.atnibam.steamedu.model.entity.StudentInfo;
import space.atnibam.steamedu.service.StudentInfoService;

/**
 * @author Atnibam Aitay
 * @description 针对表【student_info(学生信息表)】的数据库操作Service实现
 * @createDate 2024-03-05 13:55:41
 */
@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo>
        implements StudentInfoService {

}




