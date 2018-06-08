package com.sbjava.cat.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * description: 统一异常处理
 *
 * @author ralf
 * @version [1.0, 2018/6/8]
 */
@Slf4j
@RestControllerAdvice
public class ExHandle {

    @ExceptionHandler(Exception.class)
    public RepObj error(Exception e) {
        log.error(e.getMessage(), e);
        return new RepObj(500, "服务器出错了");
    }

    @ExceptionHandler(CatException.class)
    public RepObj error(CatException e) {
        log.error(e.getMessage(), e);
        return new RepObj(e.getCode(), e.getMsg());
    }
}
