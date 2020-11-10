package com.imooc.coupon.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PreRequestFilter extends AbstractPreZuulFilter{
    @Override
    protected Object cRun() {
        //存储时间戳
        context.set("startTime",System.currentTimeMillis());
        return success();
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}
