package com.sbjava.cat.utils;

import lombok.Data;

/**
 * description: 返回类
 *
 * @author ralf
 * @version [1.0, 2018/6/8]
 */
@Data
public class RepObj {
    private Integer code;
    private String  msg;
    private Object data;

    public RepObj(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RepObj(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    public RepObj(Object data) {
        this.code = 200;
        this.msg = "success";
        this.data = data;
    }
    public RepObj(String msg) {
        this.code = -1;
        this.msg = msg;
    }
}
