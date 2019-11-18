package com.epam.AmazonTest.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	private static final Logger logger = Logger.getLogger(ExcelUtil.class);
	private static final String EXCELPATH = "src/test/resources/amazonlogindata.xlsx";
	private static XSSFSheet excelWSheet;
	private static XSSFWorkbook excelWBook;
	private static XSSFCell cell;

	private ExcelUtil() {
	}

	public static Object[][] getExcelDataAsTableArray(int sheetnum) {
		Object[][] tabArray = null;
		try {
			FileInputStream excelFile = new FileInputStream(EXCELPATH);
			excelWBook = new XSSFWorkbook(excelFile);
			excelWSheet = excelWBook.getSheetAt(sheetnum);
			int startRow = 1;
			int startCol = 1;
			int ci = 0;
			int cj = 0;
			int totalRows = excelWSheet.getLastRowNum();
			int totalCols = 2;
			tabArray = new String[totalRows][totalCols];
			ci = 0;
			for (int i = startRow; i <= totalRows; i++, ci++) {
				cj = 0;
				for (int j = startCol; j <= totalCols; j++, cj++) {
					tabArray[ci][cj] = readExcelCellData(i, j);
					logger.info(tabArray[ci][cj]);
				}
			}
		} catch (IOException e) {
			logger.error("Could not read the Excel sheet " + e);
		}
		return (tabArray);
	}

	public static void writeToExcelSheet(int sheetnum,int rownum,String msg) {
		try {
			FileInputStream excelFile = new FileInputStream(EXCELPATH);
			excelWBook = new XSSFWorkbook(excelFile);
			excelWSheet = excelWBook.getSheetAt(sheetnum);
			cell = excelWSheet.getRow(rownum).getCell(3);
			cell.setCellValue("");
			cell.setCellValue(msg);

			FileOutputStream outFile = new FileOutputStream(new File(EXCELPATH));
			excelWBook.write(outFile);
			outFile.close();

		} catch (IOException e) {
			logger.error("Could not read the Excel sheet " + e);
		}

	}

	public static String readExcelCellData(int rowNum, int colNum) {
		try {
			cell = excelWSheet.getRow(rowNum).getCell(colNum);
			int dataType = cell.getCellType();
			switch (dataType) {
			case XSSFCell.CELL_TYPE_NUMERIC:
				DecimalFormat decimalFormat = new DecimalFormat("#0");
				return decimalFormat.format(cell.getNumericCellValue());
			case XSSFCell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			default:
				return "";
			}
		} catch (Exception e) {
			logger.error("excel error " + e);
		}
		return null;
	}

}
