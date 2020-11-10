package com.imooc.coupon.exception;

import com.imooc.coupon.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

//全局异常处理,RestControllerAdvice可以理解为拦截了全局响应，既可以对响应进行包装，也可以统一处理异常
@RestControllerAdvice
public class GlobalExceptionAdvice {
    //对CouponException进行统一处理,value代表要处理哪些异常，可以传数组
    @ExceptionHandler(value = CouponException.class)
    public CommonResponse<String> handlerCouponException(
            HttpServletRequest req, CouponException ex
    ) {
        CommonResponse<String> response = new CommonResponse<>(
                -1, "business error"
        );
        response.setData(ex.getMessage());
        return response;
    }
}
