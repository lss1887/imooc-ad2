package com.oppo.demo.writer;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ParseExcel {
    //  if(!wb.isSheetHidden(sheetnum)){}//判断工作表sheet是否被隐藏，隐藏返回true，未被隐藏返回false
    //  if(!row.getZeroHeight()){}//判断行是否隐藏，隐藏返回true
    public static void mainParseExcel() {
        //把所有文件种交易集合到一个list集合中
        String xlsxpath = "C:/check.xlsx";
        int startNum = 0;//从第几个sheet页开始解析
        /*解Excel*/
        Workbook wb = null;       //创建工作簿对象
        try{
            FileInputStream fi = new FileInputStream(new File(xlsxpath));
            wb = new XSSFWorkbook(fi);    //操作Excel2007的版本，扩展名是.xlsx//xlsm文件也可以兼容
            fi.close();//关闭输入流
        } catch (Exception e){
            System.out.println(e);
        }
        int num = wb.getNumberOfSheets();//得到sheet的个数
        for(int sheetnum=startNum; sheetnum<num; sheetnum++){//行,列和sheet索引都是从0开始
            if(!wb.isSheetHidden(sheetnum)){//判断工作表是否被隐藏，隐藏返回true，未被隐藏返回false
                /**解sheet页*/
                Sheet sheet = wb.getSheetAt(sheetnum);    //获取给定索引处的Sheet对象。
                FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();    //先返回XSSF和HSSF对象，再创建一个用于计算公式单元格的对象
                int rowNum = sheet.getLastRowNum() + 1;    //取最后一行的行号
                /*双for循环遍历工作簿中单元格*/
                for(int i=0; i<rowNum; i++){    //行循环
                    Row row = sheet.getRow(i);    //行对象
                    int cellNum = row.getLastCellNum();     //取最后一列列号

                    for(int j=0; j<cellNum; j++){    //列循环
                        Cell cell = row.getCell(Short.parseShort(j + ""));    //指定单元格
                        CellValue c = evaluator.evaluate(cell);    //单元格值对象
                        if(c != null){    //判断单元格是否有值
                            System.out.println(c.getCellType());
                            System.out.println(c.getStringValue());
                            switch(c.getCellType()) {
                                case STRING:
                                    String value = c.getStringValue();    //得到单元格值
                                    System.out.println("String:" + value);
                                    break;
                                case NUMERIC:
                                    double  dvalue = c.getNumberValue(); //得到单元格内数字
                                    System.out.println("Double:" + dvalue);
                                    break;
                                default:
                                    System.out.println("---");
                            }
                        }
                    }
                }

            }
        }
    }


    public static void main(String[] args) {

        mainParseExcel();
    }

}