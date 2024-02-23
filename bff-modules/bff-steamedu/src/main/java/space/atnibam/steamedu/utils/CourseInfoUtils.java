package space.atnibam.steamedu.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName: CourseInfoUtils
 * @Description: 课程信息工具类
 * @Author: AtnibamAitay
 * @CreateTime: 2024-02-23 14:16
 **/
@Component
public class CourseInfoUtils {
    /**
     * 格式化课程时间
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 格式化后的课程时间
     */
    public String formatCourseTime(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd");

        // 判断是否为同一年
        if (startTime.toLocalDate().getYear() == now.getYear() && endTime.toLocalDate().getYear() == now.getYear()) {
            // 格式化为"MM.dd"格式
            return startTime.toLocalDate().format(formatter) + " - " + endTime.toLocalDate().format(formatter);
        } else {
            // 格式化为"yyyy.MM.dd"格式
            formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            return startTime.format(formatter) + " - " + endTime.format(formatter);
        }
    }

    /**
     * 从列表中提取指定字段，并返回新的列表
     *
     * @param sourceList 原始列表
     * @param mapper     映射函数，将原始列表中的每个元素映射为新的元素
     * @return 新的列表，包含映射后的元素
     */
    public <T, R> List<R> extractFieldFromList(List<T> sourceList, Function<T, R> mapper) {
        return sourceList.stream().map(mapper).collect(Collectors.toList());
    }
}