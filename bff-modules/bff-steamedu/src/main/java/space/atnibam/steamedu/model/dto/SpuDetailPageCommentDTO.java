package space.atnibam.steamedu.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class SpuDetailPageCommentDTO implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 评论ID
     */
    private Integer id;
    /**
     * 评论的用户的信息
     */
    private CommentUserInfoDTO userInfo;
    /**
     * 内容
     */
    private String content;

    /**
     * 评论的用户的信息
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentUserInfoDTO implements Serializable {
        private static final long serialVersionUID = 1L;
        /**
         * 用户名
         */
        private String userName;
        /**
         * 头像
         */
        private String userAvatar;
    }
}