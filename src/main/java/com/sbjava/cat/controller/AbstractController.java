package com.sbjava.cat.controller;

import com.sbjava.cat.utils.RepObj;

/**
 * description: AbstractController
 *
 * @author ralf
 * @version [1.0, 2018/6/8]
 */
public class AbstractController {
    RepObj success(Object t) {
        return new RepObj(t);
    }

    RepObj success(Integer code, String msg) {
        return new RepObj(code, msg);
    }


    RepObj success(Integer code, String msg, Object t) {
        return new RepObj(code, msg, t);
    }
}
