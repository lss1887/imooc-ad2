package com.oppo.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class MarkTemplateEntity {
    private Integer adId;
    private Integer ownerId;
    private List<String> ownerApps;
    private Integer questCount;
    private String  materialNum;
    private Long materialPrice;
    private String materialBrandType;
    private String industryTag;
    private Integer playTime;
    private String networkType;
    private List<String> landPageContents;
    private String materialFont;
    private List<String> materialFonts;
    private String materialBrand;
    private String materialContent;
    private List<String> materialContents;
    private String landPageRelevant;
    private String materialQuality;
    private List<String> materialQualitys;
    private String operator;
    private String operationTime;

}
