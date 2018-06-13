package com.sbjava.cat.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description: WxController
 *
 * @author ralf
 * @version [1.0, 2018/6/13]
 */
@RestController
@RequestMapping("/wx")
@Slf4j
public class WxController {

    @Autowired
    private WxMaService wxService;

    @PostMapping("/login")
    public WxMaJscode2SessionResult login(@RequestBody String code) {
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            log.info("openid:"+session.getOpenid());
            log.info("sesskey:"+session.getSessionKey());
            log.info("unionid:"+session.getUnionid());
            return session;
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }
}

