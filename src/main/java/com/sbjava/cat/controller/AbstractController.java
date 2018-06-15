package com.sbjava.cat.controller;

import com.sbjava.cat.model.CatUser;
import com.sbjava.cat.utils.RepObj;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * description: AbstractController
 *
 * @author ralf
 * @version [1.0, 2018/6/8]
 */
public class AbstractController {
    @Autowired
    HttpServletRequest request;

    RepObj success(Object t) {
        return new RepObj(t);
    }

    RepObj success(Integer code, String msg) {
        return new RepObj(code, msg);
    }

    RepObj success(Integer code, String msg, Object t) {
        return new RepObj(code, msg, t);
    }

    CatUser getUser() {
        return (CatUser) request.getAttribute("userInfo");
    }

    String getUserId() {
        return getUser().getId();
    }

}
