//Showstock.java
import java.awt.*;

import javax.swing.*;

import java.awt.*;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.theme.SubstanceTerracottaTheme;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;  
import org.apache.log4j.PropertyConfigurator;  


public class Showstock extends JApplet 
{
	static String username;
	JPanel p2 = new JPanel(new BorderLayout());
	Registerpane rp = new Registerpane(750,500);
	JTextField aField=new JTextField(10);
	int news;
	JMenuBar jmb = new JMenuBar();
	JMenu toolMenu = new JMenu("工具");
	JMenu update = new JMenu("刷新");
	JMenu helpMenu = new JMenu("帮助");
	JMenuItem daorumenu, daochumenu,help,about,shuaxin;

  JTabbedPane jtpFigures = new JTabbedPane();
  Zhuxiao zp = new Zhuxiao(750,500);
  Dinglan dl = new Dinglan();
  //static Logger logger = Logger.getLogger(Showstock.class.getName());
  public void Replaceusername(String uname)
  {
	  JLabel username = new JLabel(uname);
	  username.setFont(new Font("",1,30));
	  add(username);
	  username.setBounds(10, 10, 120, 30);
  }
  //Shouyilv j3;
  //Chigugouchen j4;
  ModifyTable applet2;
  
  public Showstock() 
  {
	PropertyConfigurator.configure ("src//log4j.properties");//================================
    jmb.add(toolMenu);
    //jmb.add(update);
    jmb.add(helpMenu);
    daorumenu = new JMenuItem("导入数据");
    daochumenu = new JMenuItem("导出数据");
    shuaxin = new JMenuItem("刷新");
    //shuaxin2 = new JMenuItem("收益率");
   // shuaxin3 = new JMenuItem("持股构成");
    help = new JMenuItem("帮助");
    about = new JMenuItem("关于");
    //toolMenu.add(arg0)
    
   /* update.add(shuaxin1);
    update.add(shuaxin2);
    update.add(shuaxin3);*/
    
    toolMenu.add(daorumenu);
    toolMenu.add(daochumenu);
    toolMenu.add(shuaxin);
    
    helpMenu.add(help);
    helpMenu.add(about);
    this.setJMenuBar(jmb);
    this.setLayout(null);
    
    about.addActionListener(new ActionListener()		
	 {
		public void actionPerformed(ActionEvent event)
		{
			JOptionPane.showMessageDialog(null,"版本 1.0\n版权所有 团队项目1组保留所有权利","关于“乾道量行”",JOptionPane.INFORMATION_MESSAGE);
		}
	 }
 	);
    
    shuaxin.addActionListener(new ActionListener()		
	 {
		public void actionPerformed(ActionEvent event)
		{
			Updatetable();//======================5.12
		}
	 }
  	);
  
    daorumenu.addActionListener(new ActionListener()		
	 {
		public void actionPerformed(ActionEvent event)
		{
			//初始化文件选择框
			JFileChooser fDialog = new JFileChooser();
			//设置文件选择框的标题 
			fDialog.setDialogTitle("请选择导入文件");
			//弹出选择框
			int returnVal = fDialog.showOpenDialog(null);
			// 如果是选择了文件
			if(JFileChooser.APPROVE_OPTION == returnVal)
			{				
				JOptionPane.showMessageDialog(null,"导入成功","",JOptionPane.INFORMATION_MESSAGE);
				
				String name = fDialog.getSelectedFile().toString();
				Copy copy = new Copy();
				copy.copyFile(name, username+".xls");
				Createtable();
				
				
			}
		}
	 }
   	);
    
    daochumenu.addActionListener(new ActionListener()		
	 {
		public void actionPerformed(ActionEvent event)
		{
			//初始化文件选择框
			JFileChooser fDialog = new JFileChooser();
			//设置文件选择框的标题 
			fDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fDialog.setDialogTitle("请选择导入文件");
			//弹出选择框
			int returnVal = fDialog.showOpenDialog(null);
			// 如果是选择了文件
			if(JFileChooser.APPROVE_OPTION == returnVal)
			{
				
				JOptionPane.showMessageDialog(null,"导出成功","",JOptionPane.INFORMATION_MESSAGE);		        
			    //打印出文件的路径，你可以修改位 把路径值 写到 textField中
				Copy copy = new Copy();
				copy.copyFile(username+".xls",fDialog.getSelectedFile().toString()+"//"+username+".xls");
				System.out.println(fDialog.getSelectedFile());
			}
		}
	 }
  	);
  }
  public void Createtable()
  {
	  	
	  	add(jtpFigures);
	    jtpFigures.setBounds(0, 45, 690, 400);
	    dl.setBounds(190, 6, 510, 42);
	    //JPanel j3=new JPanel();
	    //JPanel j4=new JPanel();
	    Shouyilv j3 = new Shouyilv(690,360,username);
	    Chigugouchen j4 = new Chigugouchen(690,360,username);
	   // Shouyilv j4 = j3;
	   // Shouyilv j3 = j4;
	   // j4 = new Chigugouchen(690,360,username);
	    //System.out.print("@@@@@@@@@@"+username+"@@@@@@@@---------");//------
	    applet2 = new ModifyTable(username,news);//////////////////////////////////////////////////////////////////////////////////////
	    dl.ryke1 = new JLabel(String.valueOf(applet2.dinglanriyingkui));
	    dl.yk1 = new JLabel(String.valueOf(applet2.dinglanyingkui));
	    dl.createdinglan();
	    add(dl);
	    jtpFigures.add(applet2, "持仓盈亏");
	    jtpFigures.add(j3, "收益率");
	    jtpFigures.add(j4, "持股构成");   
	    PropertyConfigurator.configure ("src//log4j.properties");//================================	    	    
	    applet2.jTable1.addMouseListener(new MouseAdapter() //////////////////////////////////这里是添加点击买卖时弹出frame,需要用到username,不过username在登录时才能get到
		  { 
		        public void mouseClicked(MouseEvent e) 
		        { 
		        		int r=applet2.jTable1.getSelectedRow();
		        		int c= applet2.jTable1.getSelectedColumn();
		        		
		        		final JFrame framejioayi = new JFrame("增加交易");
		        		if (c==8) 
		        		{
		        			final Dealdialog dlog = new Dealdialog();
		        			dlog.dealdialogusername = applet2.username;////////////////////////////////////////////////2015.5.3		        			
                        	framejioayi.add(dlog);                 
                        	dlog.dealdialogname = String.valueOf(applet2.jTable1.getValueAt(r, 0));    
                        	dlog.ok.addActionListener(new ActionListener() {
                     	        public void actionPerformed(ActionEvent e) {
                     	        	framejioayi.dispose();
                     	        	String datestr = dlog.date1.getText();
                     	        	String styletr = dlog.style1.getText();
                     	        	String pricestr = dlog.price1.getText();
                     	        	String amountstr = dlog.amount1.getText();
                     	        	String ratestr = dlog.rate1.getText();
                     	        	String yongjinstr = dlog.yongjin1.getText();

                     	        	Workbook wb;
                     				try 
                     				{
                     					wb = Workbook.getWorkbook(new File(dlog.dealdialogusername+".xls"));
                     					WritableWorkbook book;
                     					try {
	                     						book = Workbook.createWorkbook(new File(dlog.dealdialogusername+".xls"),wb);
	                     						WritableSheet sheet2 = book.getSheet(dlog.dealdialogname);                       						
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
                     							
                     						 
                     							book.write();  
                     							book.close(); 
                     							
                     							
                     							Updatetable();
                     							
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
                     					//state = 1;
                     				} 

                     				catch (BiffException e1) {
                     					// TODO Auto-generated catch block
                     					e1.printStackTrace();
                     				} catch (IOException e1) {
                     					// TODO Auto-generated catch block
                     					e1.printStackTrace();
                     				}  
                     	        }
                     	      });
                        	//System.out.print("uuuutttt"+dlog.dealdialogname+"------");
                        	framejioayi.setSize(680, 110);
                        	framejioayi.setLocationRelativeTo(null);
                        	framejioayi.setVisible(true);
                        	framejioayi.setResizable(false);
		        		}
		        		System.out.print(c);
		        		String number="";
		                if(e.getClickCount() == 2 && c!=8)
		                {	
		                	File file = new File(applet2.username+".xls");
			        		String[] columnNames1={"日期", "类型", "价格", "数量","税率","佣金"};
				        	Object[][] rowData1={};
				        	//System.out.print("kaka "+applet2.username+" ddddddd-----------");///////////////////////////////////////////////
				        	final DefaultTableModel tableModel1 = new DefaultTableModel(rowData1,columnNames1)
				        	{  
			        	      @Override  
			        	      public boolean isCellEditable(int row,int column)
			        	      {  
			        	          return false;  
			        	      }  
				        	};
				        	String search = String.valueOf(applet2.jTable1.getValueAt(r, 0));
				        	StringBuffer sb = new StringBuffer();
				        	try {
								Workbook book = Workbook.getWorkbook(file);
								Sheet sheet = book.getSheet(search);
								
								number = sheet.getCell(1, 1).getContents();
								
								try
								{
									int m = Integer.parseInt(sheet.getCell(11,0).getContents());
									for(int i=0;i<m;i++)
									{
										String[] row={
												sheet.getCell(2,i+1).getContents(),
												sheet.getCell(3,i+1).getContents(),
												sheet.getCell(4,i+1).getContents(),
												sheet.getCell(5,i+1).getContents(),
												sheet.getCell(6,i+1).getContents(),
												sheet.getCell(7,i+1).getContents(),
												};
												tableModel1.addRow(row);	
									}
									//System.out.print("kaka "+applet2.username+" zzzzz");
								}finally
								{
									if(book != null)
									{
										book.close();
									}
								}
				  	  		} catch (BiffException er) {
				  	  		logger.error(er); //===================
				  				System.err.println("");
							} catch (IOException er) {
							logger.error(er); //===================
								System.err.println("文件读取错误");
							}
		                	//System.out.print("jaja");
		                	final JTable jTable1 = new JTable(tableModel1);
		                	
		                	final JFrame frame = new JFrame(String.valueOf(applet2.jTable1.getValueAt(r, 0)));	                	
		                	JLabel title = new JLabel();///////////////////////
		                	JPanel jpl = new JPanel();
		                	JPanel jpr = new JPanel();
		                	JPanel jps = new JPanel();
		                	frame.add(jpl);
		                	jpl.setBounds(5,50,170,220);       	
		                	jpl.setLayout(new GridLayout(9, 2));

		                	try{
		            			URL gis = new URL("http://hq.sinajs.cn/list="+number);//你要报错的网页
		            			BufferedReader in = new BufferedReader( new InputStreamReader( gis.openStream() ) );
		            			String line="";
		            			String[] data;
		            			//System.out.print(number);
		            			while( (line = in.readLine()) != null )
		            			{
		            				System.out.println(line);
		            				data = line.split(",");
		            				JLabel jl1 = new JLabel("今日开盘价 ");
		            				JLabel jl11 = new JLabel(data[1]);
		            
				                	JLabel jl2 = new JLabel("昨日收盘价 ");
				                	JLabel jl3 = new JLabel("当前价格 ");
				                	JLabel jl4 = new JLabel("今日最高价 ");
				                	JLabel jl5 = new JLabel("今日最低价 ");
				                	JLabel jl6 = new JLabel("竞买价 ");
				                	JLabel jl7 = new JLabel("竞卖价 ");
				                	JLabel jl8 = new JLabel("成交的股票数 ");
				                	JLabel jl9 = new JLabel("成交金额 ");
				                	
				                	JLabel jl22 = new JLabel(data[2]);
				                	JLabel jl33 = new JLabel(data[3]);
				                	JLabel jl44 = new JLabel(data[4]);
				                	JLabel jl55 = new JLabel(data[5]);
				                	JLabel jl66 = new JLabel(data[6]);
				                	JLabel jl77 = new JLabel(data[7]);
				                	JLabel jl88 = new JLabel(data[8]);
				                	JLabel jl99 = new JLabel(data[9]);             	
				                	jpl.add(jl1);
				                	jpl.add(jl11);
				                	jpl.add(jl2);			    
				                	jpl.add(jl22);
				                	jpl.add(jl3);
				                	jpl.add(jl33);
				                	jpl.add(jl4);          	
				                	jpl.add(jl44);
				                	jpl.add(jl5);
				                	jpl.add(jl55);
				                	jpl.add(jl6);		                	
				                	jpl.add(jl66);
				                	jpl.add(jl7);
				                	jpl.add(jl77);
				                	jpl.add(jl8);
				                	jpl.add(jl88);
				                	jpl.add(jl9);
				                	jpl.add(jl99);		            				
		            			}
		            			in.close();
		            			}
		            			catch(Exception er){
		            				System.out.println(er); 
		            			}		                	
		                	final JTabbedPane jtpFigures = new JTabbedPane();	
		                	Catchgra fenshi = new Catchgra();
		                	final Catchgra rik = new Catchgra();
		                	Catchgra zhouk = new Catchgra();
		                	Catchgra yuekp = new Catchgra();         	
		                	try {
								fenshi.Setimg("http://image.sinajs.cn/newchart/min/n/"+number+".gif");
							} catch (IOException e1) {
								e1.printStackTrace();
							}   	
		                	try {
								rik.Setimg("http://image.sinajs.cn/newchart/daily/n/"+number+".gif");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
		                	
		                	try {
								zhouk.Setimg("http://image.sinajs.cn/newchart/weekly/n/"+number+".gif");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}	
		                	try {
								yuekp.Setimg("http://image.sinajs.cn/newchart/monthly/n/"+number+".gif");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		                	jtpFigures.add(fenshi, "分时");     
		                    jtpFigures.add(rik, "日K");
		                    jtpFigures.add(zhouk, "周K");
		                    jtpFigures.add(yuekp, "月K");
		                    jtpFigures.addChangeListener(new ChangeListener() 
		                    {
		                    	public void stateChanged(ChangeEvent e) 
		                    	{
			                    	JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
			                    	int selectedIndex = tabbedPane.getSelectedIndex();
			                    	switch (selectedIndex) 
			                    	{
			                    		case 0:   
			                    			break;
			                    			
			                    		case 1:			                    		
			                    			JFrame frame2 = new JFrame("");
			                    			frame2.setVisible(true);
			    		                    frame2.setResizable(false);
			    		                    frame2.setSize(1, 1);
			                    			frame2.dispose();
			                    			frame2 = null;
			                    			break;
			                    		case 2:
			                    			frame.setSize(700, 581);
			                    			JFrame frame3 = new JFrame("");
			                    			frame3.setVisible(true);
			    		                    frame3.setResizable(false);
			    		                    frame3.setSize(1, 1);
			                    			frame3.dispose();
			                    			frame3 = null;
			                    			break;
			                    		case 3:
			                    			JFrame frame4 = new JFrame("");
			                    			frame4.setVisible(true);
			    		                    frame4.setResizable(false);
			    		                    frame4.setSize(1, 1);
			                    			frame4.dispose();
			                    			frame4 = null;
			                    			break;
			                    	}
		                    	}
		                    });
		                    frame.add(jtpFigures);
		                    jtpFigures.setBounds(190, 10, 500, 330);            
		                    jps.setLayout(new BorderLayout());
		                    jps.add(new JScrollPane(jTable1));		  
		                    frame.add(jps);
		                    jps.setBounds(40, 350, 620, 140);         	
		                    frame.setLayout(null);        
		                    frame.setSize(700, 560);
		                    frame.setLocationRelativeTo(null);
		                    frame.setVisible(true);
		                    frame.setResizable(false);
		                    
		                }         
		        } 
		  });
  }
  /*public void Updatechigugoucheng()
  {
	  		j4 = new Chigugouchen(690,360,username);
		  	jtpFigures.removeAll();
		  	dl.removeAll();
		  	this.remove(dl);
		    jtpFigures.setBounds(0, 45, 690, 400);
		    dl.setBounds(190, 6, 510, 42);
		    Chigugouchen j4 = new Chigugouchen(690,360,username);
		    dl.ryke1 = new JLabel(String.valueOf(applet2.dinglanriyingkui));
		    dl.yk1 = new JLabel(String.valueOf(applet2.dinglanyingkui));
		    dl.createdinglan();
		    add(dl);
		    jtpFigures.add(applet2, "持仓盈亏");
		    jtpFigures.add(j3, "收益率");
		    jtpFigures.add(j4, "持股构成");      
		    PropertyConfigurator.configure ("src//log4j.properties");//================================
	  
  }
  public void Updateshouyilv()
  {
	  
		  	jtpFigures.removeAll();
		  	dl.removeAll();
		  	this.remove(dl);
		    jtpFigures.setBounds(0, 45, 690, 400);
		    dl.setBounds(190, 6, 510, 42);
		    j3 = new Shouyilv(690,360,username);
		    dl.ryke1 = new JLabel(String.valueOf(applet2.dinglanriyingkui));
		    dl.yk1 = new JLabel(String.valueOf(applet2.dinglanyingkui));
		    dl.createdinglan();
		    add(dl);
		    jtpFigures.add(applet2, "持仓盈亏");
		    jtpFigures.add(j3, "收益率");
		    jtpFigures.add(j4, "持股构成");      
		    PropertyConfigurator.configure ("src//log4j.properties");//================================
	  
  }*/
  public void Updatetable()//=======================================================
  {
	  	Shouyilv j3 = new Shouyilv(690,360,username);
	    Chigugouchen j4 = new Chigugouchen(690,360,username);
	  	jtpFigures.removeAll();
	  	dl.removeAll();
	  	this.remove(dl);
		//add(jtpFigures);
	    jtpFigures.setBounds(0, 45, 690, 400);
	    dl.setBounds(190, 6, 510, 42);
	    //JPanel j3 = new JPanel();
	    //JPanel j4 = new JPanel();
	    
	    //Shouyilv j3 = new Shouyilv(690,360,username);
	    //Chigugouchen j4 = new Chigugouchen(690,360,username);
	    //System.out.print("@@@@@@@@@@"+username+"@@@@@@@@---------");//------
	    applet2 = new ModifyTable(username,news);//////////////////////////////////////////////////////////////////////////////////////
	    dl.ryke1 = new JLabel(String.valueOf(applet2.dinglanriyingkui));
	    dl.yk1 = new JLabel(String.valueOf(applet2.dinglanyingkui));
	    dl.createdinglan();
	    add(dl);
	    //j3.cggcusername=username;
	    //j4.cggcusername=username;
	    jtpFigures.add(applet2, "持仓盈亏");
	    jtpFigures.add(j3, "收益率");
	    jtpFigures.add(j4, "持股构成");      
	    PropertyConfigurator.configure ("src//log4j.properties");//================================
	    applet2.jTable1.addMouseListener(new MouseAdapter() //////////////////////////////////这里是添加点击买卖时弹出frame,需要用到username,不过username在登录时才能get到
		  { 
		        public void mouseClicked(MouseEvent e) 
		        { 
		        		int r=applet2.jTable1.getSelectedRow();
		        		int c= applet2.jTable1.getSelectedColumn();
		        		
		        		final JFrame framejioayi = new JFrame("增加交易");
		        		if (c==8) 
		        		{
		        			final Dealdialog dlog = new Dealdialog();
		        			dlog.dealdialogusername = applet2.username;////////////////////////////////////////////////2015.5.3	
		        			dlog.ok.addActionListener(new ActionListener() {
                     	        public void actionPerformed(ActionEvent e) {
                     	        	
                     	        	String datestr = dlog.date1.getText();
                     	        	String styletr = dlog.style1.getText();
                     	        	String pricestr = dlog.price1.getText();
                     	        	String amountstr = dlog.amount1.getText();
                     	        	String ratestr = dlog.rate1.getText();
                     	        	String yongjinstr = dlog.yongjin1.getText();

                     	        	Workbook wb;
                     				try 
                     				{
                     					wb = Workbook.getWorkbook(new File(dlog.dealdialogusername+".xls"));
                     					WritableWorkbook book;
                     					try {
                     						book = Workbook.createWorkbook(new File(dlog.dealdialogusername+".xls"),wb);
                     						WritableSheet sheet2 = book.getSheet(dlog.dealdialogname);  
                     						
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
                     							framejioayi.dispose();
                     							Updatetable();	                     							
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
                     					//state = 1;
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
                     	      });
                        	framejioayi.add(dlog);                   	
                        	dlog.dealdialogname = String.valueOf(applet2.jTable1.getValueAt(r, 0));                        	
                        	//System.out.print("uuuutttt"+dlog.dealdialogname+"------");
                        	framejioayi.setSize(680, 110);
                        	framejioayi.setLocationRelativeTo(null);
                        	framejioayi.setVisible(true);
                        	framejioayi.setResizable(false);
                        	//------------------------------------------------
                        	
		        		}
		        		//System.out.print(r+"    ");
		        		//System.out.print(c);
		        		String number="";
		                if(e.getClickCount() == 2 && c!=8)
		                {	
		                	File file = new File(applet2.username+".xls");
			        		String[] columnNames1={"日期", "类型", "价格", "数量","税率","佣金"};
				        	Object[][] rowData1={};
				        	//System.out.print("kaka "+applet2.username+" ddddddd-----------");///////////////////////////////////////////////
				        	final DefaultTableModel tableModel1 = new DefaultTableModel(rowData1,columnNames1)
				        	{  
			        	      @Override  
			        	      public boolean isCellEditable(int row,int column)
			        	      {  
			        	          return false;  
			        	      }  
				        	};
				        	String search = String.valueOf(applet2.jTable1.getValueAt(r, 0));
				        	StringBuffer sb = new StringBuffer();
				        	try {
								Workbook book = Workbook.getWorkbook(file);
								Sheet sheet = book.getSheet(search);
								
								number = sheet.getCell(1, 1).getContents();
								
								try
								{
									//System.out.print(sheet.getName()+" uuuuuu");
									int m = Integer.parseInt(sheet.getCell(11,0).getContents());
									for(int i=0;i<m;i++)
									{
										String[] row={
												sheet.getCell(2,i+1).getContents(),
												sheet.getCell(3,i+1).getContents(),
												sheet.getCell(4,i+1).getContents(),
												sheet.getCell(5,i+1).getContents(),
												sheet.getCell(6,i+1).getContents(),
												sheet.getCell(7,i+1).getContents(),
												};
												tableModel1.addRow(row);	
									}
									//System.out.print("kaka "+applet2.username+" zzzzz");
								}finally
								{
									if(book != null)
									{
										book.close();
									}
								}
				  	  		} catch (BiffException er) {
				  	  		logger.error(er); //===================
				  				System.err.println("");
							} catch (IOException er) {
							logger.error(er); //===================
								System.err.println("文件读取错误");
							}
		                	//System.out.print("jaja");
		                	final JTable jTable1 = new JTable(tableModel1);
		                	
		                	final JFrame frame = new JFrame(String.valueOf(applet2.jTable1.getValueAt(r, 0)));	                	
		                	JLabel title = new JLabel();///////////////////////
		                	JPanel jpl = new JPanel();
		                	JPanel jpr = new JPanel();
		                	JPanel jps = new JPanel();
		                	frame.add(jpl);
		                	jpl.setBounds(5,50,170,220);       	
		                	jpl.setLayout(new GridLayout(9, 2));

		                	try{
		            			URL gis = new URL("http://hq.sinajs.cn/list="+number);//你要报错的网页
		            			BufferedReader in = new BufferedReader( new InputStreamReader( gis.openStream() ) );
		            			//PrintWriter pw=new PrintWriter( new FileOutputStream("api.htm"));//保存的路径
		            			String line="";
		            			String[] data;
		            			//System.out.print(number);
		            			while( (line = in.readLine()) != null )
		            			{
		            				System.out.println(line);
		            				data = line.split(",");
		            				JLabel jl1 = new JLabel("今日开盘价 ");
		            				JLabel jl11 = new JLabel(data[1]);
		            
				                	JLabel jl2 = new JLabel("昨日收盘价 ");
				                	JLabel jl3 = new JLabel("当前价格 ");
				                	JLabel jl4 = new JLabel("今日最高价 ");
				                	JLabel jl5 = new JLabel("今日最低价 ");
				                	JLabel jl6 = new JLabel("竞买价 ");
				                	JLabel jl7 = new JLabel("竞卖价 ");
				                	JLabel jl8 = new JLabel("成交的股票数 ");
				                	JLabel jl9 = new JLabel("成交金额 ");
				                	
				                	JLabel jl22 = new JLabel(data[2]);
				                	JLabel jl33 = new JLabel(data[3]);
				                	JLabel jl44 = new JLabel(data[4]);
				                	JLabel jl55 = new JLabel(data[5]);
				                	JLabel jl66 = new JLabel(data[6]);
				                	JLabel jl77 = new JLabel(data[7]);
				                	JLabel jl88 = new JLabel(data[8]);
				                	JLabel jl99 = new JLabel(data[9]);             	
				                	jpl.add(jl1);
				                	jpl.add(jl11);
				                	jpl.add(jl2);			    
				                	jpl.add(jl22);
				                	jpl.add(jl3);
				                	jpl.add(jl33);
				                	jpl.add(jl4);          	
				                	jpl.add(jl44);
				                	jpl.add(jl5);
				                	jpl.add(jl55);
				                	jpl.add(jl6);		                	
				                	jpl.add(jl66);
				                	jpl.add(jl7);
				                	jpl.add(jl77);
				                	jpl.add(jl8);
				                	jpl.add(jl88);
				                	jpl.add(jl9);
				                	jpl.add(jl99);		            				
		            			}
		            			in.close();
		            			//pw.close();
		            			}
		            			catch(Exception er){
		            				System.out.println(er); 
		            			}		                	
		                	final JTabbedPane jtpFigures = new JTabbedPane();	
		                	Catchgra fenshi = new Catchgra();
		                	final Catchgra rik = new Catchgra();
		                	Catchgra zhouk = new Catchgra();
		                	Catchgra yuekp = new Catchgra();         	
		                	try {
								fenshi.Setimg("http://image.sinajs.cn/newchart/min/n/"+number+".gif");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}   	
		                	try {
								rik.Setimg("http://image.sinajs.cn/newchart/daily/n/"+number+".gif");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		                	
		                	try {
								zhouk.Setimg("http://image.sinajs.cn/newchart/weekly/n/"+number+".gif");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}	
		                	try {
								yuekp.Setimg("http://image.sinajs.cn/newchart/monthly/n/"+number+".gif");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		                	jtpFigures.add(fenshi, "分时");     
		                    jtpFigures.add(rik, "日K");
		                    jtpFigures.add(zhouk, "周K");
	
		                    jtpFigures.add(yuekp, "月K");
		          
		                    jtpFigures.addChangeListener(new ChangeListener() 
		                    {
		                    	public void stateChanged(ChangeEvent e) 
		                    	{
			                    	JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
			                    	int selectedIndex = tabbedPane.getSelectedIndex();
			                    	switch (selectedIndex) 
			                    	{
			                    		case 0:   
			                    			break;
			                    			
			                    		case 1:			                    		
			                    			JFrame frame2 = new JFrame("");
			                    			frame2.setVisible(true);
			    		                    frame2.setResizable(false);
			    		                    frame2.setSize(1, 1);
			                    			frame2.dispose();
			                    			frame2 = null;
			                    			break;
			                    		case 2:
			                    			frame.setSize(700, 581);
			                    			JFrame frame3 = new JFrame("");
			                    			frame3.setVisible(true);
			    		                    frame3.setResizable(false);
			    		                    frame3.setSize(1, 1);
			                    			frame3.dispose();
			                    			frame3 = null;
			                    			break;
			                    		case 3:
			                    			JFrame frame4 = new JFrame("");
			                    			frame4.setVisible(true);
			    		                    frame4.setResizable(false);
			    		                    frame4.setSize(1, 1);
			                    			frame4.dispose();
			                    			frame4 = null;
			                    			break;
			                    	}
		                    	}
		                    });
		                    frame.add(jtpFigures);
		                    jtpFigures.setBounds(190, 10, 500, 330);            
		                    jps.setLayout(new BorderLayout());
		                    jps.add(new JScrollPane(jTable1));		  
		                    frame.add(jps);
		                    jps.setBounds(40, 350, 620, 140);         	
		                    frame.setLayout(null);        
		                    frame.setSize(700, 560);
		                    frame.setLocationRelativeTo(null);
		                    frame.setVisible(true);
		                    frame.setResizable(false);
		                }         
		        } 
		  });
  }
  static Logger logger = Logger.getLogger(Showstock.class.getName()); 
  /** Main method */
  public static void main(String[] args) 
  {
	  PropertyConfigurator.configure ("src//log4j.properties"); //============================================日志
	  try 
	  {  
          UIManager.setLookAndFeel(new SubstanceLookAndFeel());  
          JFrame.setDefaultLookAndFeelDecorated(true);  
          JDialog.setDefaultLookAndFeelDecorated(true);  
          SubstanceLookAndFeel.setCurrentTheme(new SubstanceTerracottaTheme());  
	       // SubstanceLookAndFeel.setSkin(new EmeraldDuskSkin());  
	     // SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());  
	      //SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());  
	        //SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter());  
	         // SubstanceLookAndFeel.setCurrentGradientPainter(new StandardGradientPainter());  
	          //SubstanceLookAndFeel.setCurrentTitlePainter(new FlatTitePainter());  
          logger.info("界面美化成功"); 
      } 
	  catch (Exception e) 
	  { 
		  logger.error("Info ..."); //==========================================
          System.err.println("Something went wrong!");  
      }  
    final JFrame frame = new JFrame("乾道量行");

    // Create an instance of the applet
    final Showstock applet = new Showstock();
    final JPanel jp = new JPanel();
    final CardLayout out = new CardLayout();
    jp.setLayout(out);
    
    frame.add(jp);
    
    final Registerpane rp = new Registerpane(750,500);
    final GetPanel pp = new GetPanel(750,500,"background1.jpg");
    
    final Daoruye daorup = new Daoruye(750,500);
    jp.add(pp,"1");
    jp.add(rp,"2");
    jp.add(daorup,"3");
    //jp.add(adddealp,"4");
    
    out.show(jp, "1");
  
    pp.login.addActionListener(new ActionListener()	                    
	 {
			public void actionPerformed(ActionEvent event)
			{
				//int in;
				String na = String.valueOf(pp.password1.getPassword());
				//String nu = String.valueOf(passwordnd1.getPassword());
				String u = pp.user1.getText();
				String s = new String();
				String s1 =  new String();
				if(na.equals("")||u.equals(""))
				{	
					pp.nullcaution.setVisible(true);
				}
				
				else
				{
					
	  					//caution1.setVisible(false);
	  				try { 
				  			BufferedReader input = new BufferedReader(new FileReader("user.txt")); //读取流
				  			BufferedReader input1 = new BufferedReader(new FileReader("password.txt")); //读取流
				  			int user = 0;
				  			int count = 0;
				  			while((s = input.readLine())!=null&&(s1 = input1.readLine())!=null)
				  			{ //判断是否读到了最后一行
				  				//System.out.println(s);
				  				//System.out.print("haha");
				  				if(s.equals(u)&&s1.equals(na))
						  		{				  								  					
				  					jp.setVisible(false);
				  					applet.Replaceusername(u);
				  					applet.username=u;
				  					 //j3 = new Shouyilv(690,360,username);
				  					 //j4 = new Chigugouchen(690,360,username);
				  					applet.init();
				  					applet.Createtable();
			  						frame.add(applet);			  						
			  						applet.show();
			  						//System.out.print("haha");
			  						break;
						  		}
				  			} 
				  			input.close(); 
				  			input1.close();
				  			pp.usercaution.setVisible(true);

			  			}
			  			catch (Exception et) { 
			  			} 
			  	}
					
				
			}
	}
	);
   
    rp.confirm.addActionListener(new ActionListener()	                    
	 {
			public void actionPerformed(ActionEvent event)
			{
				//int in;
				String na = String.valueOf(rp.password1.getPassword());
				//String nu = String.valueOf(passwordnd1.getPassword());
				String u = rp.user1.getText();
					//File file = new File("J:\\user.txt");
					try{
					     BufferedWriter writer = new BufferedWriter(new FileWriter(new File("user.txt"),true));
					     BufferedWriter writer1 = new BufferedWriter(new FileWriter(new File("password.txt"),true));
					     writer.newLine();
					     writer1.newLine();			     
					     writer.write(u);
					     writer1.write(na);
					     writer.close();
					     writer1.close();
					    // System.out.print(na);
					     applet.username=u;//////////////////////////////////////////////////////////////////////////////////////////
					     applet.Replaceusername(u);
					     applet.news = 1;
					}catch(Exception e){

					     }
					out.show(jp, "3");
				
			}
	}
	);
    daorup.next.addActionListener(new ActionListener()		
	 {
			public void actionPerformed(ActionEvent event)
			{
  					jp.setVisible(false);
  					applet.init();
  					applet.Createtable();
					frame.add(applet);
						
					applet.show();
						//System.out.print("haha");
			}
	}
	);
    daorup.daoru.addActionListener(new ActionListener()		
	 {
		public void actionPerformed(ActionEvent event)
		{
			//初始化文件选择框
			JFileChooser fDialog = new JFileChooser();
			//设置文件选择框的标题 
			fDialog.setDialogTitle("请选择导入文件");
			//弹出选择框
			int returnVal = fDialog.showOpenDialog(null);
			// 如果是选择了文件
			if(JFileChooser.APPROVE_OPTION == returnVal)
			{
				
				JOptionPane.showMessageDialog(null,"导入成功","",JOptionPane.INFORMATION_MESSAGE);
			        
			    //打印出文件的路径，你可以修改位 把路径值 写到 textField中
				//System.out.println(fDialog.getSelectedFile());
				String name = fDialog.getSelectedFile().toString();
				Copy copy = new Copy();
				copy.copyFile(name, username+".xls");
				applet.news = 0;
				applet.Createtable();
				
				jp.setVisible(false);	
				
				frame.add(applet);
				applet.show();
			}
		}
		
	 }
    	);
    class MouseAdp implements MouseListener
    {
        public MouseAdp(){}
        public void mouseClicked(MouseEvent e) 
        {
        	out.show(jp, "2");        	
        }

        public void mouseEntered(MouseEvent e) 
        {
        }
        public void mouseExited(MouseEvent e) 
        {
        }

        public void mousePressed(MouseEvent e) 
        {
        }

        public void mouseReleased(MouseEvent e) 
        {
        }
    }
    applet.zp.zhuxiao.addActionListener(new ActionListener()		//按扭按下在txt加入用户信息
	 {
		public void actionPerformed(ActionEvent event)
		{
			frame.remove(applet);		
			jp.show();
			out.show(jp, "1");
		}
	 }
     );
    pp.register1.addMouseListener(new MouseAdp());
    frame.setDefaultCloseOperation(0);
    frame.setSize(700, 500);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setResizable(false);
    frame.addWindowListener(new WindowAdapter(){
    	   public void windowClosing(WindowEvent e){
    	    int selected = JOptionPane.showConfirmDialog(frame, "是否退出","",JOptionPane.YES_NO_OPTION);
    	  if(JOptionPane.OK_OPTION == selected){
    	   System.exit(0);
    	  }
    	   }
    	  });
  }
}

