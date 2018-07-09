package com.ljw.util.excel;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 读取Excel表格：表格格式xlsx文件
 * 
 * @author PC
 *
 */
public class ExcelPraseXLSX {
	
	
	public static void main(String[] args) throws Exception {
		ExcelPraseXLSX parse = new ExcelPraseXLSX();

		List<List<String>> readXlsx = parse.readXlsx("C:\\Users\\PC\\Desktop\\ip.xlsx");
		int i = 1;
		for (List<String> list : readXlsx) {
			System.out.println(list + ":" + (i++));
		}
	}
	
	
	/**
	 * 
	 * @param filePath :xlsx文件所在的路径
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> readXlsx(String filePath) throws Exception {

		InputStream is = new FileInputStream(filePath);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);

		List<List<String>> result = new ArrayList<List<String>>();

		// 循环每一页，并处理当前页
		for (XSSFSheet xssFSheet : xssfWorkbook) {
			if (xssFSheet == null) {
				continue;
			}

			//获取最后一行
			int lastRowNum = xssFSheet.getLastRowNum();
			// 循环当前页，循环读取每一行
			for (int rowNum = 0; rowNum <= lastRowNum; rowNum++) {
				XSSFRow xssfRow = xssFSheet.getRow(rowNum);
				
				if (xssfRow == null) {//如果当前行为空，继续向下一次循环
					continue;
				}
				
				int minColIx = xssfRow.getFirstCellNum();
				int maxColIx = xssfRow.getLastCellNum();

				List<String> rowList = new ArrayList<String>();

				//遍历该行，获取处理每个元素
				for (int colIx = minColIx; colIx < maxColIx; colIx++) {
					XSSFCell cell = xssfRow.getCell(colIx);
					if (cell == null) {
						continue;
					}
					String cellValue = cell.toString().trim();
					if ("".equals(cellValue)) {
						continue;
					}
					rowList.add(cellValue);
				}

				result.add(rowList);

			}

		}

		return result;

	}

	

}
