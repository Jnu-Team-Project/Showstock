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
class KJPanel extends JPanel       //��¼���
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
  private String[] columnNames={"��Ʊ", "��ǰ��", "�ǵ�", "�ֲֳɱ�","������","������ֵ","����ӯ��","ӯ��","����"};
  Object[][] rowData=
	  {
	  };
  // Create table data
  //Object[][] rowData;
  /*= 
{
    {"��������","","","","","","","","����"},
    {"�����ɷ�","","","","","","","","����"},
    {"��������", "","","","","","","","����"},
    {"����ҩҵ","","","","","","","","����"},
    {"�����ѧ","","","","","","","","����"},
    {"����ҽҩ","","","","","","","","����"},
    {"�Ϻ��һ�","","","","","","","","����"}
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
  private JButton jbtAddRow = new JButton("����µĹ�Ʊ");
  // private JButton jbtAddColumn = new JButton("Add New Column");
  private JButton jbtDeleteRow = new JButton("ɾ����ѡ��Ʊ");
  
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

  JLabel daorujian = new JLabel("��������");
	JLabel daochujian = new JLabel("��������");
	
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
						String[] row={book.getSheet(i).getName(),"","","","","","","","����"};
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
				System.err.println(e+"�ļ���ȡ����");
			}
  
		
	
	
	//witeToFile
	
		/*File file = new File("j://data55.xls");
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
		}*/
		
	  TableColumn OpColumn = jTable1.getColumn("����"); 
	  
	  //System.out.print(jTable1.getValueAt(0, 0));
	 // DefaultTableCellRenderer backGroundColor = new DefaultTableCellRenderer(); 
	  /*DefaultTableCellRenderer fontColor = new DefaultTableCellRenderer() {   

          public void setValue(Object value) { //��дsetValue�������Ӷ����Զ�̬�����е�Ԫ������ɫ   

             

              double a = (value instanceof Double) ? ((Double) value).doubleValue() : 0.0; //��ȡ��н���е�ֵ   

                 

              setForeground((a  > 3099.0) ? Color.red : Color.black); //�����н����3099Ԫ���ͽ���������Ϊ��ɫ   

                 

              setText((value == null) ? "" : value.toString());   

          }   

      };   */
	  DefaultTableCellRenderer fontColor = new DefaultTableCellRenderer();
	  fontColor.setForeground(Color.blue); 
	  OpColumn.setCellRenderer(fontColor);
	  //this.WIDTH=750;
	//this.WIDTH=740;
	//jTable1.getTableHeader().setReorderingAllowed(false);                                              4.18���ˣ����������ÿɱ༭��״̬
	
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
	  System.out.print("����û�û�ü�¼");/////////////////////////////////////////////////////////////////////////////////////////////
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
