package com.sbjava.cat.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

/**
 * description: Article
 *
 * @author ralf
 * @version [1.0, 2018/6/8]
 */
@Data
public class Article {
    @Id
    private String id;
    private String content;
    private List<String> images;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String userId;

}
