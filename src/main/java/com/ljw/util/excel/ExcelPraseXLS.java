package com.ljw.util.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelPraseXLS {
	
	
	public static void main(String[] args) throws Exception {
		ExcelPraseXLS parse = new ExcelPraseXLS();

		String path = "C:\\Users\\PC\\Desktop\\ip.xls";
		
		List<List<String>> readXlsx = parse.readXls(path);
		int i = 1;
		if (readXlsx == null) {
			System.out.println("文件解析错误: "+path);
			return ;
		}
		for (List<String> list : readXlsx) {
			System.out.println(list + ":" + (i++));
		}
	}
	
	private List<List<String>> readXls(String filePath) throws Exception {
		
		InputStream is = new FileInputStream(filePath);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is); //HSSFWorkbook 表示整个excel
		
		
		List<List<String>> result = new ArrayList<List<String>>();
		
		//获取页数
		int numberOfSheets = hssfWorkbook.getNumberOfSheets();
		
		//循环每一页，并处理当前页
		for (int numSheet = 0; numSheet < numberOfSheets; numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);//HSSFSheet 表示某一页
			
			if (hssfSheet == null) {
				continue;
			}
			
			//获取行数字
			int lastRowNum = hssfSheet.getLastRowNum();
			
			//处理当前页
			for (int rowNum = 0; rowNum < lastRowNum; rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);//HSSFSheet 表示某一行
				if (hssfRow == null) {//当前页为空，跳过
					continue;
				}
				int minColIx = hssfRow.getFirstCellNum();
				int maxColIx = hssfRow.getLastCellNum();
				
				List<String> rowList = new ArrayList<String>();
				for (int colIx = minColIx; colIx < maxColIx; colIx++) {
					HSSFCell cell = hssfRow.getCell(colIx);
					if (cell == null) {//当前行为空，跳过
						continue;
					}
					String cellValue = cell.getStringCellValue();
					rowList.add(cellValue);
				}
				
				result.add(rowList);
			}
		}
		
		
		return result;
	}

}
