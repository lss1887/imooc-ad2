package com.oppo.demo.controller;


import com.oppo.demo.entity.MarkTemplateEntity;
import com.oppo.demo.utils.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

    // 当前行
    private int curRow = 0;
    // 当前列
    private int curCol = 0;

    private int sheetIndex = -1;

    // 共享字符串表
    private SharedStringsTable sst;

    @RequestMapping("/first")
    public String getDemoFirst(){
        String a = "ddddddd";
        log.info("ad-sponsor: deleteAdPlan -> {}", a);
        return a;
    }
    @RequestMapping("/file")
    public String getFile(MultipartFile mFile) throws Exception {
        log.info("ad-demo: uploadFile name-> {}", mFile.getOriginalFilename());
        long size = mFile.getSize();
        if(StringUtils.isEmpty(mFile.getOriginalFilename()) || size == 0){
            return "上传失败";
        }
//        InputStream is = null;
        InputStream is = mFile.getInputStream();
        Workbook wb = WorkbookFactory.create(is);

        Sheet sheet = wb.getSheetAt(0);
        //excel 总行数
        int totalRows = sheet.getLastRowNum()+1;
        log.info("totalRows :{}",totalRows);
        //列数
        int totalCells = 0;
        //列数
        if(totalRows >=1 && sheet.getRow(0) != null){
            totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        log.info("totalRows :{}",totalCells);
        List<MarkTemplateEntity> list = new ArrayList<>();
        for (int i = 1; i < totalRows; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                MarkTemplateEntity entity = new MarkTemplateEntity();
                //打标内容为空数
                int count = 0;
                for (int c = 0; c < totalCells; c++) {
                    Cell cell = row.getCell(c);
                    switch (c) {
                        case 0:
                            if (cell == null) {
                                throw new RuntimeException("请填写广告ID");
                            }
                            cell.setCellType(CellType.STRING);
                            String adId = cell.getStringCellValue();
                            entity.setAdId(Integer.valueOf(adId.replace(" ","")));
                            break;
                        case 1:
                            if (cell == null) {
                                throw new RuntimeException("请填写广告主ID");
                            }
                            cell.setCellType(CellType.STRING);
                            String ownerId = cell.getStringCellValue();
                            entity.setOwnerId(Integer.valueOf(ownerId.replace(" ","")));
                            break;
                        case 2:
                            if(cell != null) {
                                List<String> ownerAppList = new ArrayList<>();
                                cell.setCellType(CellType.STRING);
                                String ownerAppStr = cell.getStringCellValue();
                                if(StringUtils.isNotEmpty(ownerAppStr)){
                                    String[] ownerAppSplit = ownerAppStr.split(",");
                                    for (String ownerApp : ownerAppSplit) {
                                        if (StringUtils.isNotEmpty(ownerApp)) {
                                            ownerAppList.add(ownerApp);
                                        }
                                    }
                                    entity.setOwnerApps(ownerAppList);
                                    break;
                                }else{
                                    count += 1;
                                    break;
                                }
                            }else{
                                count += 1;
                                break;
                            }
                        case 3:
                            if(cell != null){
                                cell.setCellType(CellType.STRING);
                                String questCount = cell.getStringCellValue();
                                if(StringUtils.isNotEmpty(questCount)){
                                    entity.setQuestCount(Integer.valueOf(questCount));
                                }else{
                                    count += 1;
                                    break;
                                }
                                break;
                            }else{
                                count += 1;
                                break;
                            }

                        case 4:
                            if(cell == null){
                                count += 1;
                                break;
                            }else {
                                cell.setCellType(CellType.STRING);
                                String materialNum = cell.getStringCellValue();
                                if(StringUtils.isNotEmpty(materialNum)){
                                    entity.setMaterialNum(materialNum);
                                    break;
                                }else{
                                    count += 1;
                                    break;
                                }
                            }

                        case 5:
                            if(cell == null){
                                count += 1;
                                break;
                            }else{
                                cell.setCellType(CellType.STRING);
                                String materialPrice = cell.getStringCellValue();
                                if(StringUtils.isNotEmpty(materialPrice)){
                                    entity.setMaterialPrice(Long.valueOf(materialPrice));
                                    break;
                                }else {
                                    count += 1;
                                    break;
                                }
                            }
                        case 6:
                            if(cell == null){
                                count += 1;
                                break;
                            }else{
                                cell.setCellType(CellType.STRING);
                                String materialBrandType = cell.getStringCellValue();
                                if(StringUtils.isNotEmpty(materialBrandType)){
                                    entity.setMaterialBrandType(materialBrandType);
                                    break;
                                }else{
                                    count += 1;
                                    break;
                                }
                            }

                        case 7:
                            if(cell == null){
                                count += 1;
                                break;
                            }else{
                                cell.setCellType(CellType.STRING);
                                String industryTag = cell.getStringCellValue();
                                if(StringUtils.isNotEmpty(industryTag)){
                                    entity.setIndustryTag(industryTag);
                                    break;
                                }else{
                                    count += 1;
                                    break;
                                }
                            }

                        case 8:
                            if(cell == null){
                                count += 1;
                                break;
                            }else{
                                cell.setCellType(CellType.STRING);
                                String playTime = cell.getStringCellValue();
                                if(StringUtils.isNotEmpty(playTime)){
                                    entity.setPlayTime(Integer.valueOf(playTime));
                                    break;
                                }else{
                                    count += 1;
                                    break;
                                }
                            }
                        case 9:
                            if(cell == null){
                                count += 1;
                                break;
                            }else{
                                cell.setCellType(CellType.STRING);
                                String networkType = cell.getStringCellValue();
                                if(StringUtils.isNotEmpty(networkType)){
                                    entity.setNetworkType(networkType);
                                    break;
                                }else{
                                    count += 1;
                                    break;
                                }
                            }

                        case 10:
                            if(cell == null){
                                count += 1;
                                break;
                            }else{

                                cell.setCellType(CellType.STRING);
                                String landPageContentStr = cell.getStringCellValue();
                                if(StringUtils.isNotEmpty(landPageContentStr)){
                                    List<String> landPageContentList = new ArrayList<>();
                                    String[] landPageContentSplit = landPageContentStr.split(",");
                                    if(landPageContentSplit.length > 7){
                                        throw new  RuntimeException("支持落地页内容不能超过7个");
                                    }
                                    for (String landPageContent : landPageContentSplit) {
                                        if(StringUtils.isNotEmpty(landPageContent)){
                                            landPageContentList.add(landPageContent);
                                        }
                                    }
                                    entity.setLandPageContents(landPageContentList);
                                    break;
                                }else{
                                    count += 1;
                                    break;
                                }
                            }

                        case 11:
                            if(cell == null){
                                count += 1;
                                break;
                            }else{

                                cell.setCellType(CellType.STRING);
                                String materialFontStr = cell.getStringCellValue();
                                if(StringUtils.isNotEmpty(materialFontStr)){
                                    List<String> materialFontList = new ArrayList<>();
                                    String[] materialFontSplit = materialFontStr.split(",");
                                    if(materialFontSplit.length > 7){
                                        throw new  RuntimeException("支持素材文字不能超过7个");
                                    }
                                    for (String materialFont : materialFontSplit) {
                                        if(StringUtils.isNotEmpty(materialFont)){
                                            materialFontList.add(materialFont);
                                        }
                                    }
                                    entity.setMaterialFonts(materialFontList);
                                    break;
                                }else{
                                    count += 1;
                                    break;
                                }
                            }
                        case 12:
                            if(cell == null){
                                count += 1;
                                break;
                            }else{
                                cell.setCellType(CellType.STRING);
                                String materialBrand = cell.getStringCellValue();
                                if(StringUtils.isNotEmpty(materialBrand)){
                                    entity.setMaterialBrand(materialBrand);
                                    break;
                                }else{
                                    count += 1;
                                    break;
                                }
                            }

                        case 13:
                            if(cell == null){
                                count += 1;
                                break;
                            }else{
                                cell.setCellType(CellType.STRING);
                                String materialContentStr = cell.getStringCellValue();
                                if(StringUtils.isNotEmpty(materialContentStr)){
                                    List<String> materialContentList = new ArrayList<>();
                                    String[] materialContentSplit = materialContentStr.split(",");
                                    if(materialContentSplit.length > 7){
                                        throw new  RuntimeException("支持素材内容不能超过7个");
                                    }
                                    for (String materialContent : materialContentSplit) {
                                        if(StringUtils.isNotEmpty(materialContent)){
                                            materialContentList.add(materialContent);
                                        }
                                    }
                                    entity.setMaterialContents(materialContentList);
                                    break;
                                }else{
                                    count += 1;
                                    break;
                                }
                            }

                        case 14:
                            if(cell == null){
                                count += 1;
                                break;
                            }
                            cell.setCellType(CellType.STRING);
                            String landPageRelevant = cell.getStringCellValue();
                            if(StringUtils.isNotEmpty(landPageRelevant)){
                                entity.setLandPageRelevant(landPageRelevant);
                                break;
                            }else{
                                count += 1;
                                break;
                            }

                        case 15:
                            if(cell == null){
                                count += 1;
                                break;
                            }else{

                                cell.setCellType(CellType.STRING);
                                String materialQuantityStr = cell.getStringCellValue();
                                if(StringUtils.isNotEmpty(materialQuantityStr)){
                                    List<String> materialQualityList = new ArrayList<>();
                                    String[] materialQuantitySplit = materialQuantityStr.split(",");
                                    if(materialQuantitySplit.length > 2){
                                        throw new  RuntimeException("支持素材质量度不能超过2个");
                                    }
                                    for (String materialQuantity : materialQuantitySplit) {
                                        if(StringUtils.isNotEmpty(materialQuantity)){
                                            materialQualityList.add(materialQuantity);
                                        }
                                    }
                                    entity.setMaterialQualitys(materialQualityList);
                                    break;
                                }else{
                                    count += 1;
                                    break;
                                }
                            }
                        case 16:
                            if(cell == null){
                                throw new RuntimeException("请填写操作人");
                            }
                            cell.setCellType(CellType.STRING);
                            String operator = cell.getStringCellValue();
                            entity.setOperator(operator);
                            break;
                        case 17:
                            if(cell == null){
                                throw new RuntimeException("请填写操作时间");
                            }
                            cell.setCellType(CellType.STRING);
                            String operationTime = cell.getStringCellValue();
                            entity.setOperationTime(operationTime);
                            break;
                    }
                }
                //一个广告不可所有打标内容都为空  14个打标内容
                log.info("count:{}",count);
                if(count == 14){
                    throw new RuntimeException("一个广告不可所有打标内容都为空");
                }
                log.info("entity:{}",entity);
                list.add(entity);
            }else{
                break;
            }
        }

        is.close();
        return null;
    }

    @RequestMapping("/download")
    @Transactional
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        IOUtils ioUtils = new IOUtils();
        ioUtils.down(request,response);
    }

}
