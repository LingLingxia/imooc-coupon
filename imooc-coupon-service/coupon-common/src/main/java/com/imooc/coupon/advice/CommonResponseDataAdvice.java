package com.imooc.coupon.advice;

import com.imooc.coupon.annotation.IgnoreResponseAdvice;
import com.imooc.coupon.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//advice后缀表示功能增强
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {
    //判断是否对响应进行处理
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //当前方法所在类如果标注了@IgnoreResponseAdvice，不需要处理
        if(methodParameter.getDeclaringClass().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )){
            return false;
        }
        //当前方法所在类如果标注了@IgnoreResponseAdvice，不需要处理
        if(methodParameter.getMethod().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )){
            return false;
        }
        //对相应进行处理
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        //定义最终的返回对象
        CommonResponse<Object> response = new CommonResponse<>(0,"");
        if(null==o){
            return  response;
        }else if (o instanceof CommonResponse){
            response = (CommonResponse<Object>) o;
        }else{
            response.setData(o);
        }
        return response;
    }
}
