package com.sbjava.cat.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * description: 评论
 *
 * @author ralf
 * @version [1.0, 2018/6/8]
 */
@Data
public class Comment {
    @Id
    private String id;
    private String content;
    private String userId;
    private LocalDateTime createTime;
}
