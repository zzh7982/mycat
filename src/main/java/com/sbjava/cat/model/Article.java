package com.sbjava.cat.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * description: 时间轴
 *
 * @author ralf
 * @version [1.0, 2018/6/8]
 */
@Data
@Document(collection = "c_article")
public class Article {
    @Id
    private String id;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String userId;
    /**
     * 评论
     */
    private List<Comment> comments;
    /**
     * 点赞
     */
    private List<Praise> praises;
    /**
     * 新评论个数
     */
    private Integer newComment;
    /**
     * 新点赞个数
     */
    private Integer newPraises;

}
