package com.magicears.bpm.util;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ReadExcelUtil {


    public static List<List<String>> readExcel(String excelPath) throws IOException {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        File excelFile = new File(excelPath); //创建文件对象
        FileInputStream is = new FileInputStream(excelFile); //文件流
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(is);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        //存储数据容器
        List<List<String>> cells = new ArrayList<>();
        assert workbook != null;
        //遍历每个Sheet
        for (int s = 0; s < workbook.getNumberOfSheets(); s++) {
            Sheet sheet = workbook.getSheetAt(s);
            int rowNum = sheet.getLastRowNum();
            //遍历每一行
            for (int r = 0; r <= rowNum; r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;
                //用来存储每行数据的容器
                List<String> cellList = new ArrayList<>();
                //遍历每一列
                for (int c = 0; c < row.getLastCellNum(); c++) {
                    Cell cell = row.getCell(c);
                    int cellType;
                    if (cell == null) {
                        cellType = 3;
                    } else {
                        cellType = cell.getCellType();
                    }
                    String cellValue;
                    switch (cellType) {
                        case Cell.CELL_TYPE_STRING: //文本
                            cellValue = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_NUMERIC: //数字、日期
                            if (DateUtil.isCellDateFormatted(cell)) {
                                cellValue = fmt.format(cell.getDateCellValue()); //日期型
                            } else {
                                DecimalFormat df = new DecimalFormat("00");
                                cellValue = String.valueOf(df.format(cell.getNumericCellValue())); //数字
                            }
                            break;
                        case Cell.CELL_TYPE_BLANK: //空白
                            cellValue = "";
                            break;
                        default:
                            cellValue = "";
                    }
//                    if (cell != null && XSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
//                        cellValue = cell.getStringCellValue();
//                    } else if (cell != null && XSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
//                        if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
//                            SimpleDateFormat sdf = null;
//                            if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
//                                sdf = new SimpleDateFormat("HH:mm");
//                            } else {// 日期
//                                sdf = new SimpleDateFormat("yyyy-MM-dd");
//                            }
//                            Date date = cell.getDateCellValue();
//                            cellValue = sdf.format(date);
//                        } else {
//                            HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
//                            cellValue = dataFormatter.formatCellValue(cell);
//                        }
//                    } else if (cell != null && XSSFCell.CELL_TYPE_BLANK == cell.getCellType()) {//判断CELL_TYPE_BLANK是否为空值
//                        cellValue = "";
//                    }
                    cellList.add(cellValue);
                }
                cells.add(cellList);
            }
        }
        return cells;
    }
}