package com.qtr.core.dataprovider;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelDataProvider {
    private static final String currentDir = System.getProperty("user.dir");
    private static String testDataExcelPath = null;
    private static XSSFWorkbook excelWBook;
    private static XSSFSheet excelWSheet;
    private static XSSFCell cell;
    private static XSSFRow row;
    private static int rowNumber;
    private static int columnNumber;
    private static final String defaultTestDataPath = "\\src\\test\\resources\\";

    public static void setRowNumber(int pRowNumber) { rowNumber = pRowNumber; }

    public static int getRowNumber() { return rowNumber; }

    public static void setColumnNumber(int pColumnNumber) { columnNumber = pColumnNumber; }

    public static int getColumnNumber() { return columnNumber; }

    public static void getExcelFileSheet(String testDataExcelFileName, String sheetName) {
        testDataExcelPath = currentDir + defaultTestDataPath;
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(testDataExcelPath + testDataExcelFileName);
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
        } catch (Exception e) {
            try {
                throw (e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static String getCellData(int RowNum, int ColNum) {
        try {
            cell = excelWSheet.getRow(RowNum).getCell(ColNum);
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        } catch (Exception e) {
            throw (e);
        }
    }

    public static XSSFRow getRowData(int RowNum) {
        try {
            row = excelWSheet.getRow(RowNum);
            return row;
        } catch (Exception e) {
            throw (e);
        }
    }

    public static void setCellData(String testDataExcelFileName, int RowNum, int ColNum, String value) {
        try {
            row = excelWSheet.getRow(RowNum);
            cell = row.getCell(ColNum);
            if (cell == null) {
                cell = row.createCell(ColNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + testDataExcelFileName);
            excelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            try {
                throw (e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
