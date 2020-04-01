package com.imooc.ad.exception;

import com.imooc.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExcetionAdvice {

    /**
     *  excetionHandler 指定类的异常
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerAdExcetion(HttpServletRequest req,AdException ex){
        CommonResponse<String> response = new CommonResponse(-1,"business error");
        response.setData(ex.getMessage());
        return response;
    }
}
