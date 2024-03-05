package space.atnibam.steamedu.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import space.atnibam.api.auth.dto.SessionUserInfoDTO;
import space.atnibam.steamedu.enums.GenderEnum;
import space.atnibam.steamedu.mapper.StudentInfoMapper;
import space.atnibam.steamedu.model.dto.StudentInfoDTO;
import space.atnibam.steamedu.model.entity.StudentInfo;
import space.atnibam.steamedu.service.StudentInfoService;

import javax.annotation.Resource;

import static space.atnibam.api.auth.constants.AuthConstants.USER_INFO;

/**
 * @author Atnibam Aitay
 * @description 针对表【student_info(学生信息表)】的数据库操作Service实现
 * @createDate 2024-03-05 13:55:41
 */
@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo>
        implements StudentInfoService {
    @Resource
    private StudentInfoMapper studentInfoMapper;

    /**
     * 获取当前用户的学生信息
     *
     * @return 当前用户的学生信息
     */
    @Override
    public StudentInfoDTO getStudentInfosByCurrentUser() {
        StudentInfoDTO studentInfoDTO = new StudentInfoDTO();
        SessionUserInfoDTO sessionUserInfoDTO = (SessionUserInfoDTO) StpUtil.getSession().get(USER_INFO);
        Integer userId = sessionUserInfoDTO.getUserId();

        // 查询学生信息
        StudentInfo studentInfo = studentInfoMapper.selectStudentInfosByUserId(userId);

        // 将gender字段转换为文字描述
        if (studentInfo != null) {
            BeanUtils.copyProperties(studentInfo, studentInfoDTO);
            studentInfoDTO.setGender(GenderEnum.getByCode(studentInfo.getGender()).getDesc());
        }

        return studentInfoDTO;
    }
}




