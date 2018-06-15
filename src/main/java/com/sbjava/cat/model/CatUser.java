package com.sbjava.cat.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * description: 用户
 *
 * @author ralf
 * @version [1.0, 2018/6/7]
 */
@Data
@Document(collection = "c_user")
public class CatUser {
    @Id
    private String id;
    /**
     * 姓名
     */
    @NotBlank
    private String name;
    /**
     * 生日
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    /**
     * 昵称
     */
    @NotBlank
    private String nickname;
    /**
     * 积分
     */
    private Integer score;
    /**
     * 消息开关
     * 0关闭 1开启
     */
    private Integer msgFlag = 0;

    private String openId;
}
