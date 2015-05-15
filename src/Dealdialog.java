import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import jxl.CellType;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Dealdialog extends JPanel
{
	int state = 0;
	//JLabel num = new JLabel("股票编号");
	//JTextField num1 = new JTextField(8);
	String dealdialogusername = new String("");
	String dealdialogname = new String("");
	private JLabel date = new JLabel("日期（如2008-8-8）");
	JTextField date1 = new JTextField(8);
	
	private JLabel style = new JLabel("操作类型");
	JTextField style1 = new JTextField(8);
	
	private JLabel price = new JLabel("价格（元）");
	JTextField price1 = new JTextField(8);
	
	private JLabel amount = new JLabel("数量");
	JTextField amount1 = new JTextField(8);
	
	private JLabel rate = new JLabel("税率（‰）");
	JTextField rate1 = new JTextField(8);
	
	private JLabel yongjin = new JLabel("佣金（‰）");
	JTextField yongjin1 = new JTextField(8);
	
	JButton ok = new JButton("确定");
	String[] stylestr = {"买入","卖出","补仓","卖空"};
	JComboBox jcb = new JComboBox(stylestr);
	public Dealdialog()//,final Showstock st
	{
		style1.setText(stylestr[0]);
		date1.addMouseListener(new MouseAdapter() 
		  { 
		        public void mouseClicked(MouseEvent e) 
		        { 
		        	new Datedialog(null,true,date1,300,400);
		        }
		  });
		jcb.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				style1.setText(stylestr[jcb.getSelectedIndex()]);
			}
		}
		);
		JPanel jpbutton = new JPanel();
		JPanel jiaoyi = new JPanel();
		jpbutton.add(ok);
		jiaoyi.setLayout(new GridLayout(2, 6));
		jiaoyi.add(date);
		jiaoyi.add(style);
		jiaoyi.add(price);
		jiaoyi.add(amount);
		jiaoyi.add(rate);
		jiaoyi.add(yongjin);
		jiaoyi.add(date1);
		//jiaoyi.add(style1);
		jiaoyi.add(jcb);
		jiaoyi.add(price1);
		jiaoyi.add(amount1);
		jiaoyi.add(rate1);
		jiaoyi.add(yongjin1);
		this.setLayout(new BorderLayout());
		add(jpbutton, BorderLayout.SOUTH);
	    add(jiaoyi, BorderLayout.CENTER);
	    
	    /*ok.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	String datestr = date1.getText();
	        	String styletr = style1.getText();
	        	String pricestr = price1.getText();
	        	String amountstr = amount1.getText();
	        	String ratestr = rate1.getText();
	        	String yongjinstr = yongjin1.getText();

	        	Workbook wb;
				try 
				{
					wb = Workbook.getWorkbook(new File("J://"+dealdialogusername+".xls"));
					WritableWorkbook book;
					try {
						book = Workbook.createWorkbook(new File("J://"+dealdialogusername+".xls"),wb);
						WritableSheet sheet2 = book.getSheet(dealdialogname);  
						
						int m = Integer.parseInt(sheet2.getCell(11,0).getContents())+1;
						  
					
							Label l;   	
							l=new Label(2,m,datestr);
							sheet2.addCell(l);
 	
							l=new Label(3,m,styletr);
							sheet2.addCell(l);
							  	
							l=new Label(4,m,pricestr);
							sheet2.addCell(l);
 	
							l=new Label(5,m,amountstr);
							sheet2.addCell(l);
	
							l=new Label(6,m,ratestr);
							sheet2.addCell(l);
							
							l=new Label(7,m,ratestr);
							sheet2.addCell(l);
							
							l=new Label(11,0,String.valueOf(m));
							sheet2.addCell(l);   					  
							
						 
							book.write();  
							book.close(); 
							jf.dispose();
							
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (RowsExceededException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (WriteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					wb.close();
					state = 1;
				} 

				catch (BiffException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
				//st.Updatetable();
	        }
	      });*/

	    	
	}
	
	public String Adddealtosheet(String a1,String a2,String a3,String a4,String a5,String a6)
	{
    	
    	String datestr = a1;
    	String styletr = a2;
    	String pricestr = a3;
    	String amountstr = a4;
    	String ratestr = a5;
    	String yongjinstr = a6;
    	String test="";

    	Workbook wb;
		try 
		{
			wb = Workbook.getWorkbook(new File(dealdialogusername+".xls"));
			WritableWorkbook book;
			try {
					book = Workbook.createWorkbook(new File(dealdialogusername+".xls"),wb);
					WritableSheet sheet2 = book.getSheet(dealdialogname);  
					
					int m = Integer.parseInt(sheet2.getCell(11,0).getContents())+1;			
					Label l;   	
					l=new Label(2,m,datestr);
					sheet2.addCell(l);

					l=new Label(3,m,styletr);
					sheet2.addCell(l);
					  	
					l=new Label(4,m,pricestr);
					sheet2.addCell(l);

					l=new Label(5,m,amountstr);
					sheet2.addCell(l);

					l=new Label(6,m,ratestr);
					sheet2.addCell(l);
					
					l=new Label(7,m,yongjinstr);
					sheet2.addCell(l);
					
					l=new Label(11,0,String.valueOf(m));
					sheet2.addCell(l);
					test = sheet2.getCell(11, 0).getContents();
					book.write();  
					book.close(); 
					//jf.dispose();
					
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (RowsExceededException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			wb.close();
			state = 1;
		} 

		catch (BiffException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	return test;
		//st.Updatetable();
    }
}
