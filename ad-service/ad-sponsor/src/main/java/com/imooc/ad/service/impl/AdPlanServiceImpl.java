package com.imooc.ad.service.impl;

import com.imooc.ad.constant.Constants;
import com.imooc.ad.dao.AdPlanRepository;
import com.imooc.ad.dao.AdUserRepository;
import com.imooc.ad.entity.AdPlan;
import com.imooc.ad.entity.AdUser;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.service.IAdPlanService;
import com.imooc.ad.service.IUserService;
import com.imooc.ad.vo.AdPlanGetRequest;
import com.imooc.ad.vo.AdPlanRequest;
import com.imooc.ad.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class AdPlanServiceImpl implements IAdPlanService {

    @Autowired
    private AdUserRepository userRepository;

    @Autowired
    private AdPlanRepository planRepository;

    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        if(!request.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        // 确保关联的对象是存在的

        Optional<AdUser> adUser = userRepository.findById(request.getUserId());
        if(!adUser.isPresent()){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        AdPlan oldPlan = planRepository.findByUserIdAndPlanName(request.getUserId(),request.getPlanName());
        return null;
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {
        return null;
    }

    @Override
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        return null;
    }

    @Override
    public void deleteAdPlan(AdPlanRequest request) throws AdException {

    }
}
