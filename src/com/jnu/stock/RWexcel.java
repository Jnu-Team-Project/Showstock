package com.jnu.stock;
/*import java.awt.Label;
import java.io.File;  
import java.io.IOException;  
  



import jxl.Cell;  
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.read.biff.BiffException;  
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
  
public class RWexcel {  
    public static void main(String[] args) 
    {      
        try {  
            String fileName = "J:\\data.xls"; // Excel文件所在路径  
            File file = new File(fileName); // 创建文件对象  
            Workbook wb = Workbook.getWorkbook(file); // 从文件流中获取Excel工作区对象（WorkBook）  
            Sheet sheet = wb.getSheet(0); // 从工作区中取得页（Sheet）  
              
            for (int i = 0; i < sheet.getRows(); i++) { // 循环打印Excel表中的内容  
                for (int j = 0; j < sheet.getColumns(); j++) {  
                    Cell cell = sheet.getCell(j, i);  
                    System.out.println(cell.getContents());  
                }  
                System.out.println();  
            }  
        } catch (BiffException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
    	
          
    }  
}*/
import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 
 * 2014年12月30日 下午7:16:14
 * @author sunfeilong1993
 * 简介：利用jxl实现简单的从excel文件中读取数据和那数据写入到excel文件中
 * 
 */

public class RWexcel {
	public static void main(String[] args) {
		writeToFile();
		readFromFile();
	}
	
	
	//readFromFile
	public static void readFromFile()
	{
		File file = new File("j://mouse.xls");
		StringBuffer sb = new StringBuffer();
		try {
				Workbook book = Workbook.getWorkbook(file);
				try{
						Sheet sheet = book.getSheet("工商银行");
						
						System.out.print(book.getSheet("工商银行").getName());
						System.out.print("  "+book.getNumberOfSheets()+"   ");
						for(int i = 0 ; i < 10 ; i++)
						{
							for(int j = 0 ; j < 10 ; j++)
							{
								//第一个参数代表列，第二个参数代表行。(默认起始值都为0)
								//sb.append(sheet.getCell(j, i).getContents()+"\t");
								//System.out.println(Integer.parseInt(sheet.getCell(j, i).getContents())+"\t");
								//System.out.println(sheet.getCell(j, i)+"\t");
							
							}
							System.out.println(Integer.parseInt(sheet.getCell(1, 1).getContents())+"\t");
							sb.append("\n");
						}
						//System.out.println(sb);
					}finally
					{
						if(book != null)
						{
							book.close();
						}
					}
			
			} catch (BiffException e) {
			System.err.println(e+"");
				} catch (IOException e) {
			System.err.println(e+"文件读取错误");
				}
	}//end readFromFile
	
	//witeToFile
	public static void writeToFile(){
		File file = new File("j://data55.xls");
		try {
			
			WritableWorkbook book = Workbook.createWorkbook(file);
			//创建一个工作区。(默认的excel文件有三个sheet,在excel的左下角可以看到sheet1/sheet2/sheet3）
			WritableSheet sheet = book.createSheet("第1页", 4);
			//在工作区上面添加内容
			try {
				for(int i = 0; i < 10 ; i ++ ){
					for(int j = 0 ; j < 10 ; j++){
						Label newLabel;						
						if(0 == i){
							//第一个参数代表列，第二个参数代表行(默认起始值都为0),第三个参数是要在单元格里面填写的内容发
							newLabel = new Label(j,i,String.valueOf(j));
						}else if(0 == j){
							newLabel = new Label(j,i,String.valueOf(i));
						}else{
							newLabel = new Label(j,i,String.valueOf(i*j));
						}
						//在单元格上面添加注释
						//WritableCellFeatures cellFeatures = new WritableCellFeatures();
						//cellFeatures.setComment("这里是"+i+"*"+j+"的值");
						//newLabel.setCellFeatures(cellFeatures);
						sheet.addCell(newLabel);
						
					}
				}
			} catch (RowsExceededException e) {
				System.err.println(e+"行或列参数错误！");
			} catch (WriteException e) {
				System.err.println(e+"写入失败");
			}finally{
				if(book != null){
					book.write();
					try 
					{
						book.close();
					} 
					catch (WriteException e) 
					{
						System.err.println(e+"文件关闭失败！");
					}
				}
			}
			
		} catch (IOException e) {
			System.err.println(e+"创建文件失败！");
		}
	}
	
}
