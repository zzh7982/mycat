package com.sbjava.cat.controller;

import com.sbjava.cat.model.CatUser;
import com.sbjava.cat.service.impl.UserService;
import com.sbjava.cat.utils.RepObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * description: UserController
 *
 * @author ralf
 * @version [1.0, 2018/6/7]
 */
@Api(value = "/user", tags = "用户接口模块")
@RestController
@RequestMapping("/user")
public class UserController extends AbstractController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "插入用户", notes = "插入用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id,不需要传", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "nickname", value = "用户名昵称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "birthday", value = "生日yyyy-mm-dd", required = true, dataType = "String"),
            @ApiImplicitParam(name = "score", value = "积分", dataType = "Integer"),
            @ApiImplicitParam(name = "msgFlag", value = "设置开关 0关闭 1开启", dataType = "Integer")
    })
    @PostMapping
    public RepObj user(@Valid CatUser user) {
        userService.insert(user);
        return success(user);
    }

    @ApiOperation(value = "查询用户", notes = "查询用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String")
    @GetMapping("/{id}")
    public RepObj getUser(@PathVariable("id") String id) {
        return success(userService.findOne(id));
    }

    @ApiOperation(value = "更新用户", notes = "更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "用户名", dataType = "String"),
            @ApiImplicitParam(name = "nickname", value = "用户名昵称", dataType = "String"),
            @ApiImplicitParam(name = "birthday", value = "生日yyyy-mm-dd", dataType = "String"),
            @ApiImplicitParam(name = "score", value = "积分", dataType = "Integer"),
            @ApiImplicitParam(name = "msgFlag", value = "设置开关 0关闭 1开启", dataType = "Integer")
    })
    @PatchMapping
    public RepObj updateUser(CatUser user) {
        return success(userService.update(user, user.getId()));
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String")
    @DeleteMapping("/{id}")
    public RepObj delete(@PathVariable("id") String id) {
        CatUser catUser = new CatUser();
        catUser.setId(id);
        return success(userService.delete(catUser));
    }
}
