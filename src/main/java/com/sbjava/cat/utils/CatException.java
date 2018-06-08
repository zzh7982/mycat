package com.sbjava.cat.utils;

import lombok.Data;

/**
 * description: 自定义异常
 *
 * @author ralf
 * @version [1.0, 2018/6/8]
 */
@Data
public class CatException extends RuntimeException {
    private String msg;
    private Integer code;


    public CatException(String msg, Integer code) {
        this.code = code;
        this.msg = msg;
    }
    public CatException(String msg) {
        this.code = 500;
        this.msg = msg;
    }
}
