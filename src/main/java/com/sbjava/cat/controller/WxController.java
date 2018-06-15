package com.sbjava.cat.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.sbjava.cat.model.CatUser;
import com.sbjava.cat.service.impl.UserService;
import com.sbjava.cat.service.impl.UserSessionService;
import com.sbjava.cat.utils.RepObj;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * description: WxController
 *
 * @author ralf
 * @version [1.0, 2018/6/13]
 */
@RestController
@RequestMapping("/wx")
@Slf4j
public class WxController extends AbstractController {

    @Autowired
    private WxMaService wxService;
    @Autowired
    private UserSessionService sessionService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public RepObj login(@RequestBody String code, HttpServletRequest request) {
        try {
            String token = request.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                token = request.getSession().getId();
            }
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            sessionService.saveToken(token, session.getOpenid() + "#" + session.getSessionKey());
            return success(token);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return success(-1, "登录失败");
    }

    @PostMapping("/test")
    public RepObj test(HttpServletRequest request) {
        String token = request.getHeader("token");
        log.info(sessionService.getOpenId(token));
        log.info(sessionService.getSessionKey(token));

        return success("");
    }

    @GetMapping("/user/{openId}")
    public RepObj use(@PathVariable String openId) {
        Map<String,Object> map = new HashMap<>(1);
        map.put("openId",openId);
        return success(userService.findByPro(CatUser.class, map));
    }
}

