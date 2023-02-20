package com.aqiu.reggie.common;

/**
 * <p>Powered by Aqiu On 2023--02--20--12--28--02
 *
 * @author Aqiu
 * @version 1.0
 * 自定义业务异常类
 */
public class CustomException extends RuntimeException {
    public CustomException(String message){
        super(message);
    }
}
