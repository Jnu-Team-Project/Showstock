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
            String fileName = "J:\\data.xls"; // Excel�ļ�����·��  
            File file = new File(fileName); // �����ļ�����  
            Workbook wb = Workbook.getWorkbook(file); // ���ļ����л�ȡExcel����������WorkBook��  
            Sheet sheet = wb.getSheet(0); // �ӹ�������ȡ��ҳ��Sheet��  
              
            for (int i = 0; i < sheet.getRows(); i++) { // ѭ����ӡExcel���е�����  
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
 * 2014��12��30�� ����7:16:14
 * @author sunfeilong1993
 * ��飺����jxlʵ�ּ򵥵Ĵ�excel�ļ��ж�ȡ���ݺ�������д�뵽excel�ļ���
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
						Sheet sheet = book.getSheet("��������");
						
						System.out.print(book.getSheet("��������").getName());
						System.out.print("  "+book.getNumberOfSheets()+"   ");
						for(int i = 0 ; i < 10 ; i++)
						{
							for(int j = 0 ; j < 10 ; j++)
							{
								//��һ�����������У��ڶ������������С�(Ĭ����ʼֵ��Ϊ0)
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
			System.err.println(e+"�ļ���ȡ����");
				}
	}//end readFromFile
	
	//witeToFile
	public static void writeToFile(){
		File file = new File("j://data55.xls");
		try {
			
			WritableWorkbook book = Workbook.createWorkbook(file);
			//����һ����������(Ĭ�ϵ�excel�ļ�������sheet,��excel�����½ǿ��Կ���sheet1/sheet2/sheet3��
			WritableSheet sheet = book.createSheet("��1ҳ", 4);
			//�ڹ����������������
			try {
				for(int i = 0; i < 10 ; i ++ ){
					for(int j = 0 ; j < 10 ; j++){
						Label newLabel;						
						if(0 == i){
							//��һ�����������У��ڶ�������������(Ĭ����ʼֵ��Ϊ0),������������Ҫ�ڵ�Ԫ��������д�����ݷ�
							newLabel = new Label(j,i,String.valueOf(j));
						}else if(0 == j){
							newLabel = new Label(j,i,String.valueOf(i));
						}else{
							newLabel = new Label(j,i,String.valueOf(i*j));
						}
						//�ڵ�Ԫ���������ע��
						//WritableCellFeatures cellFeatures = new WritableCellFeatures();
						//cellFeatures.setComment("������"+i+"*"+j+"��ֵ");
						//newLabel.setCellFeatures(cellFeatures);
						sheet.addCell(newLabel);
						
					}
				}
			} catch (RowsExceededException e) {
				System.err.println(e+"�л��в�������");
			} catch (WriteException e) {
				System.err.println(e+"д��ʧ��");
			}finally{
				if(book != null){
					book.write();
					try 
					{
						book.close();
					} 
					catch (WriteException e) 
					{
						System.err.println(e+"�ļ��ر�ʧ�ܣ�");
					}
				}
			}
			
		} catch (IOException e) {
			System.err.println(e+"�����ļ�ʧ�ܣ�");
		}
	}
	
}
