package com.magicears.bpm.controller.advice;

import com.magicears.bpm.core.mvc.ui.BaseResultStatus;
import com.magicears.bpm.core.mvc.ui.ResultData;
import com.magicears.bpm.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;


@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 用户不存在
     *
     * @param e        .
     * @param response .
     * @return .
     */
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResultData userNotFoundException(RuntimeException e, HttpServletResponse response) {
        return new ResultData(BaseResultStatus.ERROR_USERNAME.getCode(), BaseResultStatus.ERROR_USERNAME.getMessage());
    }

    /**
     * 用户密码错误
     *
     * @param e        .
     * @param response .
     * @return .
     */
    @ExceptionHandler(UserPasswordNotEqualException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResultData userPasswordNotEqualException(RuntimeException e, HttpServletResponse response) {
        return new ResultData(BaseResultStatus.ERROR_PASSWORD.getCode(), BaseResultStatus.ERROR_PASSWORD.getMessage());
    }


    /**
     * 用户过期
     *
     * @param e        .
     * @param response .
     * @return .
     */
    @ExceptionHandler(TokenExpiredException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResultData tokenExpiredException(RuntimeException e, HttpServletResponse response) {
        return new ResultData(BaseResultStatus.TOKEN_EXPIRED.getCode(), BaseResultStatus.TOKEN_EXPIRED.getMessage());
    }


    /**
     * 没有权限
     *
     * @param e        .
     * @param response .
     * @return .
     */
    @ExceptionHandler(ForbiddenException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResultData forbiddenException(RuntimeException e, HttpServletResponse response) {
        return new ResultData(BaseResultStatus.FORBIDDEN.getCode(), BaseResultStatus.FORBIDDEN.getMessage());
    }

    /**
     * 用户过期
     *
     * @param e        .
     * @param response .
     * @return .
     */
    @ExceptionHandler(com.auth0.jwt.exceptions.TokenExpiredException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResultData jwtTokenExpiredException(RuntimeException e, HttpServletResponse response) {
        return new ResultData(BaseResultStatus.TOKEN_EXPIRED.getCode(), BaseResultStatus.TOKEN_EXPIRED.getMessage());
    }

  /**
     * 用户不能允许试用
     *
     * @param e        .
     * @param response .
     * @return .
     */
    @ExceptionHandler(UserNotAvailableException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResultData userNotAvailableException(RuntimeException e, HttpServletResponse response) {
        return new ResultData(BaseResultStatus.USER_NOT_AVAILABLE.getCode(), BaseResultStatus.USER_NOT_AVAILABLE.getMessage());
    }

  /**
     * 用户信息未补全
     *
     * @param e        .
     * @param response .
     * @return .
     */
    @ExceptionHandler(UserNotFinishException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResultData userNotFinishException(RuntimeException e, HttpServletResponse response) {
        return new ResultData(BaseResultStatus.USER_NOT_FINISH.getCode(), BaseResultStatus.USER_NOT_FINISH.getMessage());
    }


    /**
     * 拦截错误
     *
     * @param e        .
     * @param response .
     * @return .
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData exceptionHandler(RuntimeException e, HttpServletResponse response) {
        log.info("error:{}", e);
        return new ResultData(BaseResultStatus.SERVER_ERROR.getCode(), BaseResultStatus.SERVER_ERROR.getMessage());
    }


    /**
     * 拦截错误
     *
     * @param e        .
     * @param response .
     * @return .
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData allExceptionHandler(Exception e, HttpServletResponse response) {
        log.info("error:{}", e);
        return new ResultData(BaseResultStatus.SERVER_ERROR.getCode(), BaseResultStatus.SERVER_ERROR.getMessage());
    }


}