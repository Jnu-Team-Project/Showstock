package com.jnu.stock;
import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class Gethistoryprice 
{
	public String Getprice(String num,String searchdate)
	{
		String search[] = searchdate.split("/");
		String n = String.valueOf(Integer.parseInt(search[2]));
		String search1 = "15-3-"+n;
		String data="0";
		
		try{
			Workbook book = Workbook.getWorkbook(new File("stockhistory.xls"));	
			Sheet sheet = book.getSheet(num);
			for(int i=0;i<50;i++)
			{
				
				if(sheet.getCell(0, i).getContents().equals(search1))	
				{
					data = sheet.getCell(3, i).getContents();
					
					break;
				}
				
			}
			book.close();
		}
		 catch (BiffException e) {
				System.err.println(e+"");
		} catch (IOException e) {
			System.err.println(e+"ÎÄ¼þ¶ÁÈ¡´íÎó");
		}
		
			return data;
		
	}

}
