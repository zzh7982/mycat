package com.sbjava.cat.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.sbjava.cat.service.impl.UserSessionService;
import com.sbjava.cat.utils.RepObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: WxController
 *
 * @author ralf
 * @version [1.0, 2018/6/13]
 */
@Api(value = "/wx", tags = "微信接口")
@RestController
@RequestMapping("/wx")
@Slf4j
public class WxController extends AbstractController {

    @Autowired
    private WxMaService wxService;
    @Autowired
    private UserSessionService sessionService;

    @ApiOperation(value = "登陆", notes = "登陆接口,需要传入code,返回token,以后所有的请求都需要在header中携带header:{'token':token}")
    @PostMapping("/login")
    public RepObj login(@RequestBody String code) {
        try {
            String token = request.getSession().getId();
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            sessionService.saveToken(token, session.getOpenid() + "#" + session.getSessionKey());
            return success(token);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return success(-1, "登录失败");
    }

}

