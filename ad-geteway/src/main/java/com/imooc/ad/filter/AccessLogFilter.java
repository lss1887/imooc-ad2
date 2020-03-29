package com.imooc.ad.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Filter;

/**
 * Created by linshisheng on 2020/03/29.
 */
@Slf4j
@Component
public class AccessLogFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return  FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {

        return FilterConstants.SEND_RESPONSE_FILTER_ORDER -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
         RequestContext currentContext = RequestContext.getCurrentContext();
         HttpServletRequest request = currentContext.getRequest();
         Long startTime = (Long) currentContext.get("startTime");
         String url = request.getRequestURI();
         Long duration = System.currentTimeMillis() - startTime;
         log.info("url:"+url+",duration:"+duration/100+"ms");
        return null;
    }
}
