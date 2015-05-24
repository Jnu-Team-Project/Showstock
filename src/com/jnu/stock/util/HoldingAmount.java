package com.jnu.stock.util;

import com.jnu.stock.Oldestdate;

import jxl.Sheet;
import jxl.Workbook;

public class HoldingAmount {
	public static int holdingAmountCalculation(String date, String stockName, Workbook book){
		Sheet stockSheet = book.getSheet(stockName);
		int recordCount = Integer.parseInt(stockSheet.getCell(11, 0).getContents());
		int buy = 0;  //ÂòÈë
		int sell = 0; //Âô³ö
		if (recordCount > 0) {
			System.out.println(stockSheet.getCell(2, 1).getContents()); 
			for (int j = 0; j < recordCount && Oldestdate.isOldestdate(stockSheet.getCell(2, j + 1).getContents(), date); j++) {
				
				if ("ÂòÈë".equals(stockSheet.getCell(3, j + 1).getContents())||
						"²¹²Ö".equals(stockSheet.getCell(3, j + 1).getContents())) {
					buy += Integer.parseInt(stockSheet.getCell(5, j + 1).getContents());
				}
				
				if (stockSheet.getCell(3, j + 1).getContents().equals("Âô³ö") || stockSheet.getCell(3, j + 1)
								.getContents()
								.equals("Âô¿Õ")) {
					sell += Integer.parseInt(stockSheet.getCell(5, j + 1).getContents());
				}
			}
		}
		return buy - sell;
	}
}

