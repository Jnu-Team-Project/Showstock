import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

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
class KJPanel extends JPanel       //登录面板
{
	 private static final long serialVersionUID = 1L;
	 int width = 0, hight = 0;
	 String imgpath = "";
	 int news;
	 
	 //////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //////////////////////////////////////////////////////////////
	 public KJPanel(int width, int hight, String file)
	 {
		  this.width = width;
		  this.hight = hight;
		  imgpath = file;
		  this.setLayout(null);
		  Registerpane rp = new Registerpane(750,500);
		    //////////////////////////////   
	 }
	 
	 protected void paintComponent(Graphics g) 
	 {
		  ImageIcon icon = new ImageIcon(imgpath);
		  Image img = icon.getImage();
		  g.drawImage(img, 0, 0, width, hight, this);
	 }
}

class ModifyTable extends JPanel {
	//int width =750;
	//int hight=480;
  // Create table column names
  String username="";
  private String[] columnNames={"股票", "当前价", "涨跌", "持仓成本","持有量","持有市值","浮动盈亏","盈亏","操作"};
  Object[][] rowData=
	  {
	  };
  // Create table data
  //Object[][] rowData;
  /*= 
{
    {"工商银行","","","","","","","","买卖"},
    {"伊利股份","","","","","","","","买卖"},
    {"北京银行", "","","","","","","","买卖"},
    {"以岭药业","","","","","","","","买卖"},
    {"哥尔声学","","","","","","","","买卖"},
    {"复星医药","","","","","","","","买卖"},
    {"上海家化","","","","","","","","买卖"}
  };
  */
    
  
  // Create a table model
  //private DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);
  
  
  
  DefaultTableModel tableModel = new DefaultTableModel(rowData,columnNames){  
      @Override  
      public boolean isCellEditable(int row,int column){  
          return false;  
      }  
  }; 
  // Create a table
  JTable jTable1 = new JTable(tableModel);
  

 
  //backGroundColor.setBackground(Color.yellow);   
  //tableColumn.setCellRenderer(backGroundColor);  
  // Create buttons
  private JButton jbtAddRow = new JButton("添加新的股票");
  // private JButton jbtAddColumn = new JButton("Add New Column");
  private JButton jbtDeleteRow = new JButton("删除所选股票");
  
  private JButton jbtSave = new JButton("Save");
  private JButton jbtClear = new JButton("Clear");
  private JButton jbtRestore = new JButton("Restore");
  // Create a combo box for selection modes
  private JComboBox jcboSelectionMode =
    new JComboBox(new String[] {"SINGLE_SELECTION",
      "SINGLE_INTERVAL_SELECTION", "MULTIPLE_INTERVAL_SELECTION"});

  // Create check boxes
  private JCheckBox jchkRowSelectionAllowed =
    new JCheckBox("RowSelectionAllowed", true);
  private JCheckBox jchkColumnSelectionAllowed =
    new JCheckBox("ColumnSelectionAllowed", false);

  JLabel daorujian = new JLabel("导入数据");
	JLabel daochujian = new JLabel("导出数据");
	
  public ModifyTable(String uname,int n) 
  {
	 if(n!=1)
	 {
	  username=uname;
	  //if(username.eu)
	  File file = new File("j://"+username+".xls");
	 
  		//StringBuffer sb = new StringBuffer();
  			try {
  				
  				System.out.print(username+"haha");////////////////////////////////////////////////////////////////////////////////////////////
				
				Workbook book = Workbook.getWorkbook(file);
				try
				{
					for(int i=0;i<book.getNumberOfSheets();i++)
					{
						String[] row={book.getSheet(i).getName(),"","","","","","","","买卖"};
						tableModel.addRow(row);	
					}
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
  
		
	
	
	//witeToFile
	
		/*File file = new File("j://data55.xls");
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
		}*/
		
	  TableColumn OpColumn = jTable1.getColumn("操作"); 
	  
	  //System.out.print(jTable1.getValueAt(0, 0));
	 // DefaultTableCellRenderer backGroundColor = new DefaultTableCellRenderer(); 
	  /*DefaultTableCellRenderer fontColor = new DefaultTableCellRenderer() {   

          public void setValue(Object value) { //重写setValue方法，从而可以动态设置列单元字体颜色   

             

              double a = (value instanceof Double) ? ((Double) value).doubleValue() : 0.0; //获取月薪列中的值   

                 

              setForeground((a  > 3099.0) ? Color.red : Color.black); //如果月薪大于3099元，就将字体设置为红色   

                 

              setText((value == null) ? "" : value.toString());   

          }   

      };   */
	  DefaultTableCellRenderer fontColor = new DefaultTableCellRenderer();
	  fontColor.setForeground(Color.blue); 
	  OpColumn.setCellRenderer(fontColor);
	  //this.WIDTH=750;
	//this.WIDTH=740;
	//jTable1.getTableHeader().setReorderingAllowed(false);                                              4.18改了，可能是设置可编辑阙状态
	
	this.setLayout(new BorderLayout());
    JPanel panel1 = new JPanel();
    //panel1.setLayout(new GridLayout(1, 2));
    //panel1.setLayout(new BorderLayout());
    panel1.add(jbtAddRow);
    //panel1.add(jbtAddColumn);
    panel1.add(jbtDeleteRow);
  

    JPanel panel6 = new JPanel();
    panel6.setLayout(new BorderLayout());
    panel6.add(panel1, BorderLayout.SOUTH);
    //panel6.add(panel2, BorderLayout.CENTER);

    //add(panel5, BorderLayout.NORTH);
    
    //jTable1.setSize(400, 100);
    add(new JScrollPane(jTable1),
      BorderLayout.CENTER);
    add(panel1, BorderLayout.SOUTH);

    // Initialize table selection mode
    jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    jbtAddRow.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (jTable1.getSelectedRow() >= 0)
          //tableModel.insertRow(jTable1.getSelectedRow(),
            //new java.util.Vector());
        tableModel.addRow(new java.util.Vector());
        else
          tableModel.addRow(new java.util.Vector());
      }
      
    });

    /*jbtAddColumn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String name = JOptionPane.showInputDialog("New Column Name");
        tableModel.addColumn(name, new java.util.Vector());
      }
    });*/

    jbtDeleteRow.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (jTable1.getSelectedRow() >= 0)
          tableModel.removeRow(jTable1.getSelectedRow());
      }
    });

    /*jbtDeleteColumn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (jTable1.getSelectedColumn() >= 0) {
          TableColumnModel columnModel = jTable1.getColumnModel();
          TableColumn tableColumn =
              columnModel.getColumn(jTable1.getSelectedColumn());
          columnModel.removeColumn(tableColumn);
        }
      }
    });*/

    jbtSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream("tablemodel.dat"));
          out.writeObject(tableModel.getDataVector());
          out.writeObject(getColumnNames());
          out.close();
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });

    /*jbtClear.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        tableModel.setRowCount(0);
      }
    });*/

    /*jbtRestore.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          ObjectInputStream in = new ObjectInputStream(
            new FileInputStream("tablemodel.dat"));
          Vector rowData = (Vector)in.readObject();
          Vector columnNames = (Vector)in.readObject();
          tableModel.setDataVector(rowData, columnNames);
          in.close();
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });*/

    /*jchkRowSelectionAllowed.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jTable1.setRowSelectionAllowed(
          jchkRowSelectionAllowed.isSelected());
      }
    });*/

    /*jchkColumnSelectionAllowed.addActionListener(
      new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jTable1.setColumnSelectionAllowed(
          jchkColumnSelectionAllowed.isSelected());
      }
    });*/

    /*jcboSelectionMode.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String selectedItem =
          (String) jcboSelectionMode.getSelectedItem();

        if (selectedItem.equals("SINGLE_SELECTION"))
          jTable1.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION);
        else if (selectedItem.equals("SINGLE_INTERVAL_SELECTION"))
          jTable1.setSelectionMode(
            ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        else if (selectedItem.equals("MULTIPLE_INTERVAL_SELECTION"))
          jTable1.setSelectionMode(
            ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      }
    });*/
  
  }
  else
  {
	  System.out.print("这个用户没用记录");/////////////////////////////////////////////////////////////////////////////////////////////
	  username=uname;
	this.setLayout(new BorderLayout());
    JPanel panel1 = new JPanel();
    //panel1.setLayout(new GridLayout(1, 2));
    //panel1.setLayout(new BorderLayout());
    panel1.add(jbtAddRow);
    //panel1.add(jbtAddColumn);
    panel1.add(jbtDeleteRow);
  

    JPanel panel6 = new JPanel();
    panel6.setLayout(new BorderLayout());
    panel6.add(panel1, BorderLayout.SOUTH);
    //panel6.add(panel2, BorderLayout.CENTER);

    //add(panel5, BorderLayout.NORTH);
    
    //jTable1.setSize(400, 100);
    add(new JScrollPane(jTable1),
      BorderLayout.CENTER);
    add(panel1, BorderLayout.SOUTH);

    // Initialize table selection mode
    jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    jbtAddRow.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (jTable1.getSelectedRow() >= 0)
          //tableModel.insertRow(jTable1.getSelectedRow(),
            //new java.util.Vector());
        tableModel.addRow(new java.util.Vector());
        else
          tableModel.addRow(new java.util.Vector());
      }
      
    });

 
    jbtDeleteRow.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (jTable1.getSelectedRow() >= 0)
          tableModel.removeRow(jTable1.getSelectedRow());
      }
    });

 

    jbtSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream("tablemodel.dat"));
          out.writeObject(tableModel.getDataVector());
          out.writeObject(getColumnNames());
          out.close();
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });
  }
  }
  private Vector getColumnNames() {
    Vector<String> columnNames = new Vector<String>();

    for (int i = 0; i < jTable1.getColumnCount(); i++)
      columnNames.add(jTable1.getColumnName(i));

    return columnNames;
  }

  //Main method
  /*public static void main(String[] args) {
    ModifyTable applet = new ModifyTable();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("ModifyTable");
    frame.getContentPane().add(applet, java.awt.BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);*/
  
}
