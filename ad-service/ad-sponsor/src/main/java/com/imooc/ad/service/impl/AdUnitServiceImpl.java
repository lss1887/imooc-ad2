package com.imooc.ad.service.impl;

import com.imooc.ad.constant.Constants;
import com.imooc.ad.dao.AdPlanRepository;
import com.imooc.ad.dao.AdUnitRepository;
import com.imooc.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.imooc.ad.dao.unit_condition.AdUnitItReposity;
import com.imooc.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.imooc.ad.entity.AdPlan;
import com.imooc.ad.entity.AdUnit;
import com.imooc.ad.entity.unit_condition.AdUnitDistrict;
import com.imooc.ad.entity.unit_condition.AdUnitIt;
import com.imooc.ad.entity.unit_condition.AdUnitKeyword;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.service.IAdUnitService;
import com.imooc.ad.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class AdUnitServiceImpl implements IAdUnitService {

    @Autowired
    private AdPlanRepository planRepository;

    @Autowired
    private AdUnitRepository unitRepository;

    @Autowired
    private AdUnitKeywordRepository unitKeywordRepository;

    @Autowired
    private AdUnitItReposity unitItReposity;

    @Autowired
    private AdUnitDistrictRepository unitDistrictRepository;



    @Override
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {
        if(!request.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        Optional<AdPlan> adPlan= planRepository.findById(request.getPlanId());
                if(!adPlan.isPresent()){
                    throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
                }
        AdUnit adUnit = unitRepository.findByPlanIdAndUnitName(request.getPlanId(), request.getUnitName());
                if(adUnit != null){
                    throw new AdException(Constants.ErrorMsg.SAME_NAME_UNIT_ERROR);
                }
                AdUnit newAdUnit = unitRepository.save(new AdUnit(request.getPlanId(),request.getUnitName(),request.getPositionType(),request.getBudget()));

        return new AdUnitResponse(newAdUnit.getId(),newAdUnit.getUnitName());
    }

    @Override
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException {
        List<Long> unitIds = request.getUnitKeywords().stream().map(AdUnitKeywordRequest.UnitKeyword :: getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExist(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<Long> ids = Collections.emptyList();
        List<AdUnitKeyword> unitKeywords = new ArrayList<>();
        if(!CollectionUtils.isEmpty(request.getUnitKeywords())){
            request.getUnitKeywords().forEach(i -> unitKeywords.add(
                    new AdUnitKeyword(i.getUnitId(),i.getKeyword())
            ));
            ids = unitKeywordRepository.saveAll(unitKeywords).stream()
                    .map(AdUnitKeyword :: getId)
                    .collect(Collectors.toList());
        }
        return new AdUnitKeywordResponse(ids);
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {
        List<Long> unitIds = request.getUnitIts().stream()
                .map(AdUnitItRequest.UnitIt :: getUnitId)
                .collect(Collectors.toList());
        List<AdUnitIt> unitIts = new ArrayList<>();
        request.getUnitIts().forEach(i -> unitIts.add(
                new AdUnitIt(i.getUnitId(),i.getItTag())
        ));

        List<Long> ids = unitItReposity.saveAll(unitIts).stream()
                .map(AdUnitIt :: getId)
                .collect(Collectors.toList());

        return new AdUnitItResponse(ids);
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {
        List<Long> unitIds = request.getUnitDistricts().stream()
                .map(AdUnitDistrictRequest.UnitDistrict :: getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExist(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdUnitDistrict> unitDistricts = new ArrayList<>();
        request.getUnitDistricts().forEach(d ->
                new AdUnitDistrict(d.getUnitId(),d.getProvince(),d.getCity()
        ));

        List<Long> ids = unitDistrictRepository.saveAll(unitDistricts).stream()
                .map(AdUnitDistrict::getId)
                .collect(Collectors.toList());

        return new AdUnitDistrictResponse(ids);
    }

    @Override
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException {
        return null;
    }

    private boolean isRelatedUnitExist(List<Long> unitIds){
        if(CollectionUtils.isEmpty(unitIds)){
            return  false;
        }
        return unitRepository.findAllById(unitIds).size() == new HashSet<>(unitIds).size();
    }
}