package com.sbjava.cat.controller;

import com.mongodb.client.result.DeleteResult;
import com.sbjava.cat.model.CatUser;
import com.sbjava.cat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: UserController
 *
 * @author ralf
 * @version [1.0, 2018/6/7]
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("insert")
    public String user() {
        CatUser catUser = new CatUser();
        catUser.setBirthday("1993-02-03");
        catUser.setName("张三");
        catUser.setNickname("李白");
        userService.insert(catUser);
        return "success";
    }

    @GetMapping("user")
    public CatUser getUser() {
        return userService.getUserById("5b1902341e58a66b4ca6d23f");
    }

    @PostMapping("update")
    public String updateUser() {
        CatUser catUser = new CatUser();
        catUser.setId("5b1902341e58a66b4ca6d23f");
        catUser.setBirthday("1993-02-03");
        catUser.setName("张三");
        catUser.setNickname("dd");
        userService.update(catUser);
        return "success";
    }

    @DeleteMapping("delete")
    public DeleteResult delete() {
        CatUser catUser = new CatUser();
        catUser.setId("5b1902341e58a66b4ca6d23f");
        catUser.setBirthday("1993-02-03");
        catUser.setName("张三");
        catUser.setNickname("dd");
        return userService.delete(catUser);
    }
}
