package com.sbjava.cat.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * description: User
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
    private String name;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 昵称
     */
    private String nickname;
}
