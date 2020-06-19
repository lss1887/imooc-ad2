package com.imooc.ad.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * Created by linshisheng on 2020/03/29.
 * 访问日志过滤器
 */
@Slf4j
//javaBean注册到容器中
@Component
public class PreRequestFilter extends ZuulFilter {

    //    相应的延迟 的计算
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    //执行顺序 越小越先被调用
    @Override
    public int filterOrder() {
        return 0;
    }
    //是否执行
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("startTime",System.currentTimeMillis());
        return null;
    }



}
