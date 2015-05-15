import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 提供搜索股票及修改股票的功能
 * @author MJ
 *
 */

public class Addnewstock 
{
	String stockname="";
	String stocknum="";
	String[] data;
	public String Searchstock(String stock)
	{
		String name="";
		try{
			URL gis = new URL("http://hq.sinajs.cn/list="+stock);//你要报错的网页
			BufferedReader in = new BufferedReader( new InputStreamReader( gis.openStream() ) );
			//PrintWriter pw=new PrintWriter( new FileOutputStream("api.htm"));//保存的路径
			String line="";
			
			
			//System.out.print(number);
			while( (line = in.readLine()) != null )
			{
				
				data = line.split(",");
				if(data.length>1)
				{
	            	String[] data1 = data[0].split("=\"");
	            	name=data1[1];	
				}
			}
			in.close();
			//pw.close();
			}
		catch (MalformedURLException e) {  
			//System.out.print("dede");
            //e.printStackTrace();  
        } catch (IOException e) {  
        	//System.out.print("dede");
            //e.printStackTrace();  
        }  
		stockname = name;
		stocknum = stock;
		return name;
	}
	public String Addsheet(String usename)
	{
		String newsheetname="";
		
		try
		{
			Workbook wb=Workbook.getWorkbook(new File(usename+".xls"));   
			WritableWorkbook book=  Workbook.createWorkbook(new File(usename+".xls"),wb);  
			int total = book.getNumberOfSheets();
			WritableSheet sheet=book.createSheet(stockname,total); 
			Label l;   	
			l=new Label(11,0,"0");
			sheet.addCell(l);
			l=new Label(1,1,stocknum);
			sheet.addCell(l);
			newsheetname = book.getSheet(total).getName();
			
			book.write();  
			book.close();  
			wb.close();
			
		
		}
		catch(Exception e)
		{
		}
		return newsheetname;
	}
}
