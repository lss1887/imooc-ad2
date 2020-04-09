package com.imooc.ad.service;

import com.imooc.ad.entity.AdPlan;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.vo.AdPlanGetRequest;
import com.imooc.ad.vo.AdPlanRequest;
import com.imooc.ad.vo.AdPlanResponse;

import java.util.List;

/**
 * create By Lin ShiSheng
 * */
public interface IAdPlanService {
    /**
     * 创建推广计划
     * */
    AdPlanResponse createAdPlan(AdPlanRequest request)throws AdException;
    /**
     * 获取推广计划
     * */
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    /**
     * 更新推广计划
     * */
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    /**
     * 删除推广计划
     * */
    void deleteAdPlan(AdPlanRequest request)throws AdException;
}
