    package com.imooc.ad.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.annotation.IgnoreResponseAdvice;
import com.imooc.ad.client.vo.AdPlan;
import com.imooc.ad.client.vo.AdPlanRequest;
import com.imooc.ad.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * ribbon调用其他微服务
 */
@Slf4j
@RestController
public class SearchController {

    @Autowired
    private RestTemplate restTemplate;

    @SuppressWarnings("all")
    @IgnoreResponseAdvice
    @PostMapping("/getAdPlansByRibbon")
    public CommonResponse<List<AdPlan>> getAdPlanByRebbon(@RequestBody AdPlanRequest request){
        log.info("ad-search: getAdPlansByRibbon ====> {}", JSON.toJSONString(request));

        return restTemplate.postForEntity("http://euraka-client-ad-sponsor/ad-sponsor/ad-sponsor/get/adPlan",request,CommonResponse.class).getBody();
    }

}
