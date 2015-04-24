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
class Deal //股票类，包括操作等
{
	private String name;
	private String num;
	private String date;
	private String type;
	private int amount;
	private float Taxrate;
	private float commission;
	public Deal(String name,String num,String date,String type,int amount,float Taxrate,float commission)
	{
		this.name=name;
		this.num=num;
		this.date=date;
		this.type=type;
		this.amount=amount;
		this.Taxrate=Taxrate;
		this.commission=commission;
	}
}
class User		//用户类
{
	private String user;
	private String password;
	public User()
	{
		user="";
		password="";
	}
	public User(String na,String nu)
	{
		user = na;
		password = nu;	
	}
	public String getUser()
	{
		return this.user;
	}
	public void setUser(String str)
	{
		user = str;
	}
	public void setPassword(String str)
	{
		password = str;
	}
	public String getpassword()
	{
		return this.password;
	}
}

class GetPanel extends JPanel       //登录面板
{
	 private static final long serialVersionUID = 1L;
	 JLabel user,password,register1,nullcaution,usercaution;
	 JTextField user1;
	 JPasswordField password1;
	 JButton login,register;
	 int width = 0, hight = 0;
	 String imgpath = "";
	 
	 
	 //////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 static class BarThread extends Thread 
	    {
		    private static int DELAY = 100;
		    JProgressBar progressBar;    
		
		    public BarThread(JProgressBar bar) 
		    {
		      progressBar = bar;
		    }
		
		    public void run() 
		    {
		      int minimum = progressBar.getMinimum();
		      int maximum = progressBar.getMaximum();
		      Runnable runner = new Runnable() 
		      {
			        public void run() 
			        {
			          int value = progressBar.getValue();
			          progressBar.setValue(value+5);
			        }
		      };
		      for (int i=minimum; i<maximum; i++) 
		      {
			        try {
				          SwingUtilities.invokeAndWait(runner);
				          // Our task for each step is to just sleep
				          Thread.sleep(DELAY);
			        } catch (InterruptedException ignoredException) 
			        {
			        } catch (InvocationTargetException ignoredException) 
			        {
			        }
		      }
		    }
	    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 public GetPanel(int width, int hight, String file)
	 {
		  this.width = width;
		  this.hight = hight;
		  imgpath = file;
		  this.setLayout(null);
		  //this.add(new JLabel("用户名"));
		  //this.add(new JTextField("Time to be displayed here"),BorderLayout.SOUTH);
		  //jp.setLayout(new GridBagLayout()); 
		  user=new JLabel("用户名");
		  user.setFont(new Font("", Font.PLAIN, 20));
		  password=new JLabel("密码");
		  password.setFont(new Font("", Font.PLAIN, 20));
		  login = new JButton("登  录");
		  register = new JButton("创建用户");
		  register1 = new JLabel("创建用户");
		  
		  nullcaution = new JLabel("用户名或密码不能为空");
		  usercaution = new JLabel("用户名或密码错误");
		  
		  user1=new JTextField(30);
		  password1=new JPasswordField(30);
		  
		  //////////////////////////////////////////////                                                           4.12更新
		  Registerpane rp = new Registerpane(750,500);
		  
		  this.add(user);
		  this.add(user1);
		  this.add(password);
		  this.add(password1);
		  this.add(login);
		  this.add(register1);	
		  this.add(nullcaution);
		  this.add(usercaution);
		  nullcaution.setVisible(false);
		  usercaution.setVisible(false);
		  
		  nullcaution.setForeground(Color.red);
		  usercaution.setForeground(Color.red);
		  
		  user.setForeground(Color.DARK_GRAY); 
		  password.setForeground(Color.DARK_GRAY); 
		  register1.setForeground(Color.BLUE);
		  user.setBounds(240,340,80,20);
		  user1.setBounds(320,340,140,25);
		  password.setBounds(240,375,80,20);
		  password1.setBounds(320,370,140,25);
		  login.setBounds(310,430,100,25);
		  register1.setBounds(480,340,120,25);
		  
		  nullcaution.setBounds(300,400,120,20);
		  usercaution.setBounds(300,400,120,20);
		  
		  
		  user1.getDocument().addDocumentListener
		  (
				  new DocumentListener() 
				  { 
				  	public void changedUpdate(DocumentEvent e) 
				  	{ 
				  		nullcaution.setVisible(false);
				  		usercaution.setVisible(false);
				  	} 
				  	public void insertUpdate(DocumentEvent e) 
				  	{ 
				  		nullcaution.setVisible(false);
				  		usercaution.setVisible(false);
				  	} 
				  	public void removeUpdate(DocumentEvent e) 
				  	{
				  		nullcaution.setVisible(false);
				  		usercaution.setVisible(false);
				  	}
				  	
				  } 
		  ); 
		  password1.getDocument().addDocumentListener
		  (
				  new DocumentListener() 
				  { 
				  	public void changedUpdate(DocumentEvent e) 
				  	{ 
				  		nullcaution.setVisible(false);
				  		usercaution.setVisible(false);
				  	} 
				  	public void insertUpdate(DocumentEvent e) 
				  	{ 
				  		nullcaution.setVisible(false);
				  		usercaution.setVisible(false);
				  	} 
				  	public void removeUpdate(DocumentEvent e) 
				  	{
				  		nullcaution.setVisible(false);
				  		usercaution.setVisible(false);
				  	}
				  	
				  } 
		  ); 
		  
		  
		  //////////////////////////////////////////////////////////////////////////////////////////////////
		  /*final JProgressBar aJProgressBar = new JProgressBar(0, 100);
		    //final JButton aJButton = new JButton("Start");

		    aJProgressBar.setStringPainted(true); // 显示百分比字符
		    aJProgressBar.setIndeterminate(false); // 不确定的进度条*/
		    
		    
		    
		    
		    //////////////////////////////                                            4.12
		   
		
		
		
		
		    /*ActionListener actionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        login.setEnabled(false);
		        user1.setEnabled(false);
		        password1.setEnabled(false);
		        Thread stepper = new BarThread(aJProgressBar);
		        stepper.start();
		      }
		    };*/
		    //JPanel panel1=new JPanel();
		    //this.setOpaque(false);
		    //this.add(aJProgressBar);
		   /* login.addActionListener(new ActionListener()	                    
			 {
					public void actionPerformed(ActionEvent event)
					{
						//int in;
						String na = String.valueOf(password1.getPassword());
						//String nu = String.valueOf(passwordnd1.getPassword());
						String u = user1.getText();
						String s = new String();
						if(na.equals("")||u.equals(""))
						{	
							nullcaution.setVisible(true);
						}
						else
						{
							
			  					//caution1.setVisible(false);
			  				try { 
						  			BufferedReader input = new BufferedReader(new FileReader("J:\\user.txt")); //读取流
						  			int user = 0;
						  			int count = 0;
						  			while((s = input.readLine())!=null)
						  			{ //判断是否读到了最后一行
						  				//System.out.println(s);
						  				if(s.equals(na))
								  		{
								  			//caution.setVisible(true);
								  			//userstate=0;
						  					user = count;
								  		}
						  				if(s.equals(u))
						  				{
						  					if(count==(user-1))
						  					{
						  						
						  						break;
						  					}
						  						
						  				}
						  				else
						  				{
						  					//caution.setVisible(false);
						  					//userstate = 1;
						  				}
						  				count++;
						  			} 
		
						  			input.close(); 

					  			}
					  			catch (Exception et) { 
					  			} 
					  	}
							
						
					}
			}
			);*/
		   //login.addActionListener(actionListener);
		    //this.add(panel1);
		    //aJProgressBar.setBounds(260,200,120,20);
		    //////////////////////////////////////////////////////////////////////////////////////////////
		  //user.setFont(new Font("",1,30));//字体大小
		 
		  
		  //this.add(user1);
		  //user1.setLocation(350,250);
			//container.add(aField);
		  //this.add(password);
		 
	 }
	 
	 protected void paintComponent(Graphics g) 
	 {
		  ImageIcon icon = new ImageIcon(imgpath);
		  Image img = icon.getImage();
		  g.drawImage(img, 0, 0, width, hight, this);
	 }
}

class Registerpane extends JPanel //注册面板
{
	 private static final long serialVersionUID = 1L;
	 private JLabel user,password,passwordnd,register1,caution,caution1,usercaution,passwordcaution,passwordcaution1,passwordndcaution;
	 JTextField user1;
	 JPasswordField password1,passwordnd1;
	 private int userstate = 0;
	 int passwordstate = 0;
	 int passwordndstate = 0;
	 
	 JButton confirm;
	 int width = 0, hight = 0;
	 public Registerpane(int width, int hight)
	 {
		  this.width = width;
		  this.hight = hight;
		  //imgpath = file;
		  this.setLayout(null);
		  user=new JLabel("用户名");
		  caution=new JLabel("此用户名已存在 ，请重新输入");
		  caution1=new JLabel("用户名格式不符合要求");
		  
		  usercaution=new JLabel("（用户名由数字、字母或下划线组成）");
		  passwordcaution=new JLabel("（密码由6~16位组成）");
		  passwordcaution1=new JLabel("密码格式不符合要求");
		  
		  passwordndcaution=new JLabel("前后密码不一致");
		  user.setFont(new Font("", Font.PLAIN, 30));
		  password=new JLabel("密码");
		  passwordnd=new JLabel("确认密码");
		  password.setFont(new Font("", Font.PLAIN, 30));
		  confirm = new JButton("下一步");
		  passwordnd.setFont(new Font("", Font.PLAIN, 30));
		  user1=new JTextField(40);
		  user1.setFont(new Font("", Font.PLAIN, 20));
		  //zhuce.setFont(new Font("宋体",Font.BOLD,20));
		  password1=new JPasswordField(40);
		  password1.setFont(new Font("", Font.PLAIN, 20));
		  passwordnd1=new JPasswordField(40);
		  passwordnd1.setFont(new Font("", Font.PLAIN, 20));
		  this.add(user);
		  this.add(user1);
		  this.add(caution);
		  this.add(caution1);
		  this.add(password);
		  this.add(password1);
		  this.add(passwordnd);
		  this.add(passwordnd1);
		  this.add(passwordcaution);
		  this.add(passwordcaution1);
		  this.add(usercaution);
		  this.add(passwordndcaution);
		  
		  //this.add(login);
		  this.add(confirm);	
		  confirm.setEnabled(false);
		  //user.setFont(new Font("",1,30));//字体大小
		  user.setForeground(Color.DARK_GRAY); 
		  
		  caution.setForeground(Color.red);
		  caution1.setForeground(Color.red);
		  password.setForeground(Color.DARK_GRAY); 
		  passwordnd.setForeground(Color.DARK_GRAY); 
		  user.setBounds(180,160,100,30);
		  usercaution.setBounds(180,200,210,20);
		  usercaution.setForeground(Color.DARK_GRAY);
		  passwordcaution1.setForeground(Color.red);
		  
		  caution.setVisible(false);
		  caution1.setVisible(false);
		  passwordndcaution.setVisible(false);
		  passwordcaution1.setVisible(false);
		  
		  user1.setBounds(320,160,200,35);
		  
		  caution.setBounds(525,165,190,20);
		  caution1.setBounds(525,165,190,20);
		  
		  password.setBounds(180,240,100,30);
		  passwordcaution.setBounds(180,280,200,20);
		  passwordcaution.setForeground(Color.DARK_GRAY);
		  
		  password1.setBounds(320,240,200,30);
		  passwordcaution1.setBounds(525,245,190,20);
		  
		  passwordnd.setBounds(180,320,130,30);
		  passwordnd1.setBounds(320,320,200,30);
		  passwordndcaution.setBounds(525,325,190,20);
		  passwordndcaution.setForeground(Color.red);
		  
		  
		  confirm.setBounds(280,400,100,25);
		  user1.getDocument().addDocumentListener
		  (
				  new DocumentListener() 
				  { 
					public Vector<User> users = new Vector<User>();
					
				  	public void changedUpdate(DocumentEvent e) 
				  	{ 
				  		//int in;	
				  		String na = user1.getText();
				  		String s = new String();
				  		
				  		if(!na.matches("[A-Za-z0-9_]+"))
				  		{
				  			caution1.setVisible(true);
				  			userstate=0;
				  		}
				  		else
				  		{
		  					caution1.setVisible(false);
		  					try { 
					  			BufferedReader input = new BufferedReader(new FileReader("J:\\user.txt")); //读取流
					  			BufferedReader input1 = new BufferedReader(new FileReader("J:\\user.txt")); //读取流
					  			if(input1.readLine()==null)
					  			{
					  				userstate = 1;
					  			}
					  			else
					  			{
						  			while((s = input.readLine())!=null)
						  			{ //判断是否读到了最后一行
						  				//System.out.println(s);
						  				if(s.equals(na))
								  		{
								  			caution.setVisible(true);
								  			userstate=0;
								  			break;
								  		}
						  				else
						  				{
						  					caution.setVisible(false);
						  					userstate = 1;
						  				}
						  			} 
					  			}
					  			input.close(); 
					  			input1.close();

				  			}
				  			catch (Exception et) { 
				  			} 
				  		}
				  		if(userstate==1&&passwordstate==1&&passwordndstate==1)
						{
							confirm.setEnabled(true);
						}
						else
						{
							confirm.setEnabled(false);
						}
				  	} 
				  	public void insertUpdate(DocumentEvent e) 
				  	{ 
				  		
				  		String na = user1.getText();
				  		String s = new String();
				  		
				  		if(!na.matches("[A-Za-z0-9_]+"))
				  		{
				  			caution1.setVisible(true);
				  			userstate=0;
				  		}
				  		else
				  		{
		  					caution1.setVisible(false);
		  					try { 
					  			BufferedReader input = new BufferedReader(new FileReader("J:\\user.txt")); //读取流
					  			BufferedReader input1 = new BufferedReader(new FileReader("J:\\user.txt")); //读取流
					  			if(input1.readLine()==null)
					  			{
					  				userstate = 1;
					  			}
					  			else
					  			{
						  			while((s = input.readLine())!=null)
						  			{ //判断是否读到了最后一行
						  				//System.out.println(s);
						  				if(s.equals(na))
								  		{
								  			caution.setVisible(true);
								  			userstate=0;
								  			break;
								  		}
						  				else
						  				{
						  					caution.setVisible(false);
						  					userstate = 1;
						  				}
						  			} 
					  			}
					  			input.close(); 
					  			input1.close();

				  			}
				  			catch (Exception et) { 
				  			} 
				  		}
				  		if(userstate==1&&passwordstate==1&&passwordndstate==1)
						{
							confirm.setEnabled(true);
						}
						else
						{
							confirm.setEnabled(false);
						}
				  	} 
				  	public void removeUpdate(DocumentEvent e) 
				  	{
				  		String na = user1.getText();
				  		String s = new String();
				  		
				  		if(!na.matches("[A-Za-z0-9_]+"))
				  		{
				  			caution1.setVisible(true);
				  			userstate=0;
				  		}
				  		else
				  		{
		  					caution1.setVisible(false);
		  					try { 
					  			BufferedReader input = new BufferedReader(new FileReader("J:\\user.txt")); //读取流
					  			BufferedReader input1 = new BufferedReader(new FileReader("J:\\user.txt")); //读取流
					  			if(input1.readLine()==null)
					  			{
					  				userstate = 1;
					  			}
					  			else
					  			{
						  			while((s = input.readLine())!=null)
						  			{ //判断是否读到了最后一行
						  				//System.out.println(s);
						  				if(s.equals(na))
								  		{
								  			caution.setVisible(true);
								  			userstate=0;
								  			break;
								  		}
						  				else
						  				{
						  					caution.setVisible(false);
						  					userstate = 1;
						  				}
						  			} 
					  			}
					  			input.close(); 
					  			input1.close();

				  			}
				  			catch (Exception et) { 
				  			} 
				  		}
				  		if(userstate==1&&passwordstate==1&&passwordndstate==1)
						{
							confirm.setEnabled(true);
						}
						else
						{
							confirm.setEnabled(false);
						}
				  	}
				  	
				  } 
		  ); 
		  
		  password1.getDocument().addDocumentListener
		  (
				  new DocumentListener() 
				  { 
					public Vector<User> users = new Vector<User>();
					
				  	public void changedUpdate(DocumentEvent e) 
				  	{ 
				  		String na = String.valueOf(password1.getPassword());
						//String nu = String.valueOf(passwordnd1.getPassword());
						if(na.length()<6||na.length()>16)
						{
							passwordcaution1.setVisible(true);
							passwordstate = 0;
						}
						else
						{
							passwordcaution1.setVisible(false);
							passwordstate = 1;
						}
						String nu = String.valueOf(passwordnd1.getPassword());
						if(!nu.equals(""))
						{
							if(!(na.equals(nu)))
							{
								passwordndcaution.setVisible(true);
								passwordndstate = 0;
								confirm.setEnabled(false);
							}
							else
							{
								passwordndcaution.setVisible(false);
								passwordndstate = 1;
								if(userstate==1&&passwordstate==1&&passwordndstate==1)
								{
									confirm.setEnabled(true);
								}
								else
								{
									confirm.setEnabled(false);
								}
							}
						}
						if(userstate==1&&passwordstate==1&&passwordndstate==1)
						{
							confirm.setEnabled(true);
						}
						else
						{
							confirm.setEnabled(false);
						}
						 			
				  	} 
				  	public void insertUpdate(DocumentEvent e) 
				  	{ 
				  		String na = String.valueOf(password1.getPassword());
						//String nu = String.valueOf(passwordnd1.getPassword());
						if(na.length()<6||na.length()>16)
						{
							passwordcaution1.setVisible(true);
							passwordstate = 0;
						}
						else
						{
							passwordcaution1.setVisible(false);
							passwordstate = 1;
						}
						String nu = String.valueOf(passwordnd1.getPassword());
						if(!nu.equals(""))
						{
							if(!(na.equals(nu)))
							{
								passwordndcaution.setVisible(true);
								passwordndstate = 0;
								confirm.setEnabled(false);
							}
							else
							{
								passwordndcaution.setVisible(false);
								passwordndstate = 1;
								if(userstate==1&&passwordstate==1&&passwordndstate==1)
								{
									confirm.setEnabled(true);
								}
								else
								{
									confirm.setEnabled(false);
								}
							}
						}
						if(userstate==1&&passwordstate==1&&passwordndstate==1)
						{
							confirm.setEnabled(true);
						}
						else
						{
							confirm.setEnabled(false);
						}
				  	} 
				  	public void removeUpdate(DocumentEvent e) 
				  	{
				  		String na = String.valueOf(password1.getPassword());
						//String nu = String.valueOf(passwordnd1.getPassword());
						if(na.length()<6||na.length()>16)
						{
							passwordcaution1.setVisible(true);
							passwordstate = 0;
						}
						else
						{
							passwordcaution1.setVisible(false);
							passwordstate = 1;
						}
						
						//String na = String.valueOf(password1.getPassword());
						String nu = String.valueOf(passwordnd1.getPassword());
						if(!nu.equals(""))
						{
							if(!(na.equals(nu)))
							{
								passwordndcaution.setVisible(true);
								passwordndstate = 0;
								confirm.setEnabled(false);
							}
							else
							{
								passwordndcaution.setVisible(false);
								passwordndstate = 1;
								if(userstate==1&&passwordstate==1&&passwordndstate==1)
								{
									confirm.setEnabled(true);
								}
								else
								{
									confirm.setEnabled(false);
								}
							}
						}
						if(userstate==1&&passwordstate==1&&passwordndstate==1)
						{
							confirm.setEnabled(true);
						}
						else
						{
							confirm.setEnabled(false);
						}
				  	}
				  } 
		  ); 
		 /* password1.getDocument().addDocumentListener
		  (
				  new DocumentListener() 
				  { 
					public Vector<User> users = new Vector<User>();
					
				  	public void changedUpdate(DocumentEvent e) 
				  	{ 
				  		String na = String.valueOf(password1.getPassword());
						//String nu = String.valueOf(passwordnd1.getPassword());
						if(na.length()<6||na.length()>16)
							passwordcaution1.setVisible(true);
						else
							passwordcaution1.setVisible(false);
						 			
				  	} 
				  	public void insertUpdate(DocumentEvent e) 
				  	{ 
				  		String na = String.valueOf(password1.getPassword());
						//String nu = String.valueOf(passwordnd1.getPassword());
						if(na.length()<6||na.length()>16)
							passwordcaution1.setVisible(true);
						else
							passwordcaution1.setVisible(false);
				  	} 
				  	public void removeUpdate(DocumentEvent e) 
				  	{
				  		String na = String.valueOf(password1.getPassword());
						//String nu = String.valueOf(passwordnd1.getPassword());
						if(na.length()<6||na.length()>16)
							passwordcaution1.setVisible(true);
						else
							passwordcaution1.setVisible(false);
				  	}
				  } 
		  ); */
		  passwordnd1.getDocument().addDocumentListener
		  (
				  new DocumentListener() 
				  { 
					//public Vector<User> users = new Vector<User>();
					
				  	public void changedUpdate(DocumentEvent e) 
				  	{
				  		String na = String.valueOf(password1.getPassword());
						String nu = String.valueOf(passwordnd1.getPassword());
						if(!(na.equals(nu)&&!nu.equals("")))
						{
							passwordndcaution.setVisible(true);
							passwordndstate = 0;
							confirm.setEnabled(false);
						}
						else
						{
							passwordndcaution.setVisible(false);
							passwordndstate = 1;
							if(userstate==1&&passwordstate==1&&passwordndstate==1)
							{
								confirm.setEnabled(true);
							}
							else
							{
								confirm.setEnabled(false);
							}
						}
				  	}
				  	public void insertUpdate(DocumentEvent e) 
				  	{
				  		String na = String.valueOf(password1.getPassword());
						String nu = String.valueOf(passwordnd1.getPassword());
						if(!(na.equals(nu)&&!nu.equals("")))
						{
							passwordndcaution.setVisible(true);
							passwordndstate = 0;
							confirm.setEnabled(false);
						}
						else
						{
							passwordndcaution.setVisible(false);
							passwordndstate = 1;
							if(userstate==1&&passwordstate==1&&passwordndstate==1)
							{
								confirm.setEnabled(true);
							}
							else
							{
								confirm.setEnabled(false);
							}
						}
				  	}
				  	public void removeUpdate(DocumentEvent e) 
				  	{
				  		String na = String.valueOf(password1.getPassword());
						String nu = String.valueOf(passwordnd1.getPassword());
						if(!(na.equals(nu)&&!nu.equals("")))
						{
							passwordndcaution.setVisible(true);
							passwordndstate = 0;
							confirm.setEnabled(false);
						}
						else
						{
							passwordndcaution.setVisible(false);
							passwordndstate = 1;
							if(userstate==1&&passwordstate==1&&passwordndstate==1)
							{
								confirm.setEnabled(true);
							}
							else
							{
								confirm.setEnabled(false);
							}
						}
				  	}
				  } 
		  ); 
		  
	 /*confirm.addActionListener(new ActionListener()	                    
	 {
			public void actionPerformed(ActionEvent event)
			{
				//int in;
				String na = String.valueOf(password1.getPassword());
				//String nu = String.valueOf(passwordnd1.getPassword());
				String u = user1.getText();
				if(userstate==1&&passwordstate==1&&passwordndstate==1)
				{	
					//File file = new File("J:\\user.txt");
					try{
					     BufferedWriter writer = new BufferedWriter(new FileWriter(new File("J:\\user.txt"),true));
					     writer.newLine();
					     writer.write(u);
					     writer.newLine();
					     writer.write(na);
					     writer.close();
					    // System.out.print(na);

					}catch(Exception e){

					     }
				}
			}
	}
	);*/
	 /*protected void paintComponent(Graphics g) 
	 {
		  ImageIcon icon = new ImageIcon(imgpath);
		  Image img = icon.getImage();
		  g.drawImage(img, 0, 0, width, hight, this);
	 }*/
}
}


class Zhanghuzongzhi extends JPanel //注册面板
{
	 private static final long serialVersionUID = 1L;
	 private JLabel sc,ryke,fdyk,yk,zhzzc,sz,xj,bj,sc1,ryke1,fdyk1,fdyk2,yk1,zhzzc1,sz1,xj1,bj1;
	 private JTextField user1;
	 private JPasswordField password1,passwordnd1;
	 JButton confirm;
	 int width = 0, hight = 0;
	 public Zhanghuzongzhi(int width, int hight)
	 {
		  this.width = width;
		  this.hight = hight;
		  //imgpath = file;
		  this.setLayout(null);
		  sc=new JLabel("市场");
		  ryke=new JLabel("日盈亏额");
		  fdyk=new JLabel("浮动盈亏");
		  yk=new JLabel("盈亏");
		  zhzzc=new JLabel("账户总资产");
		  sz=new JLabel("市值");
		  xj=new JLabel("现金");
		  bj=new JLabel("本金");
		  sc1=new JLabel("A股");
		  ryke1=new JLabel("0.00");
		  fdyk1=new JLabel("-23.3");
		  yk1=new JLabel("17632");
		  zhzzc1=new JLabel("500000");
		  sz1=new JLabel("1000000");
		  xj1=new JLabel("600000");
		  bj1=new JLabel("500000");
		  //caution=new JLabel("此用户名已存在 ，请重新输入");
		  sc.setFont(new Font("", Font.PLAIN, 18));
		  ryke.setFont(new Font("", Font.PLAIN, 18));
		  fdyk.setFont(new Font("", Font.PLAIN, 18));
		  yk.setFont(new Font("", Font.PLAIN, 18));
		  zhzzc.setFont(new Font("", Font.PLAIN, 18));
		  sz.setFont(new Font("", Font.PLAIN, 18));
		  xj.setFont(new Font("", Font.PLAIN, 18));
		  bj.setFont(new Font("", Font.PLAIN, 18));
		  sc1.setFont(new Font("", Font.PLAIN, 18));
		  ryke1.setFont(new Font("", Font.PLAIN, 18));
		  fdyk1.setFont(new Font("", Font.PLAIN, 18));
		  yk1.setFont(new Font("", Font.PLAIN, 18));
		  zhzzc1.setFont(new Font("", Font.PLAIN, 18));
		  sz1.setFont(new Font("", Font.PLAIN, 18));
		  xj1.setFont(new Font("", Font.PLAIN, 18));
		  bj1.setFont(new Font("", Font.PLAIN, 18));
		  
		  
		  this.add(sc);
		  this.add(ryke);
		  this.add(fdyk);
		  this.add(yk);
		  this.add(zhzzc);
		  this.add(sz);
		  this.add(xj);
		  this.add(bj);
		  this.add(sc1);
		  this.add(ryke1);
		  this.add(fdyk1);
		  this.add(yk1);
		  this.add(zhzzc1);
		  this.add(sz1);
		  //this.add(login);
		  this.add(xj1);
		  this.add(bj1);
		  //user.setFont(new Font("",1,30));//字体大小
		  
		  
		  sc.setBounds(20,200,80,20);
		  sc1.setBounds(20,220,80,20);
		  ryke.setBounds(100,200,80,20);
		  ryke1.setBounds(100,220,80,20);
		  fdyk.setBounds(180,200,80,20);
		  fdyk1.setBounds(180,220,80,20);
		  yk.setBounds(260,200,80,20);
		  yk1.setBounds(260,220,80,20);
		  zhzzc.setBounds(340,200,80,20);
		  zhzzc1.setBounds(340,220,80,20);
		  sz.setBounds(420,200,80,20);
		  sz1.setBounds(420,220,80,20);
		  xj.setBounds(500,200,80,20);
		  xj1.setBounds(500,220,80,20);
		  bj.setBounds(580,200,80,20);
		  bj1.setBounds(580,220,80,20);
		 
}
}

class Daoruye extends JPanel //注册面板
{
	 private static final long serialVersionUID = 1L;
	 private JLabel caution;
	 //private JTextField user1;
	 //private JPasswordField password1,passwordnd1;
	 JButton daoru,next;
	 int width = 0, hight = 0;
	 public Daoruye(int width, int hight)
	 {
		  this.width = width;
		  this.hight = hight;
		  //imgpath = file;
		  this.setLayout(null);
		  //user=new JLabel("用户名");
		  caution=new JLabel("是否有旧交易记录需要导入");
		  caution.setFont(new Font("", Font.PLAIN, 25));
		  //password=new JLabel("密码");
		  /*passwordnd=new JLabel("确认密码");
		  password.setFont(new Font("", Font.PLAIN, 18));
		  confirm = new JButton("下一步");
		  user1=new JTextField(20);
		  password1=new JPasswordField(20);
		  passwordnd1=new JPasswordField(20);*/
		  daoru = new JButton("是，导入数据");
		  next = new JButton("无，直接进入主页");
		  this.add(daoru);
		  this.add(next);
		  this.add(caution);
		  
		  //this.add(login);
		  //this.add(confirm);		  
		  //user.setFont(new Font("",1,30));//字体大小
		  caution.setForeground(Color.DARK_GRAY);
		  caution.setBounds(240,180,330,40);
		  daoru.setBounds(200,270,130,30);
		  next.setBounds(350,270,130,30);
		 /* user1.getDocument().addDocumentListener
		  (
				  new DocumentListener() 
				  { 
					public Vector<User> users = new Vector<User>();
					
				  	public void changedUpdate(DocumentEvent e) 
				  	{ 
				  		int in;	
				  		String na = user1.getText();
				  		String s = new String();
				  		try { 

					  			BufferedReader input = new BufferedReader(new FileReader("J:\\user.txt")); //读取流
	
					  			while((s = input.readLine())!=null)
					  			{ 
					  				if(s.equals(na))
							  		{
							  			caution.setVisible(true);
							  			break;
							  		}
					  				else
					  					caution.setVisible(false);
					  			} 
	
					  			input.close(); 

				  			}
				  			catch (Exception et) { 
				  			} 			  			
				  	} 
				  	public void insertUpdate(DocumentEvent e) 
				  	{ 
				  		String na = user1.getText();
				  		String s = new String();
				  		try { 

					  			BufferedReader input = new BufferedReader(new FileReader("J:\\user.txt")); //读取流
	
					  			while((s = input.readLine())!=null)
					  			{ 
					  			
					  				if(s.equals(na))
							  		{
							  			caution.setVisible(true);
							  			break;
							  		}
					  				else
					  					caution.setVisible(false);
					  			} 
	
					  			input.close(); 

				  			}
				  			catch (Exception et) { 
				  			} 		
				  	} 
				  	public void removeUpdate(DocumentEvent e) 
				  	{
				  		String na = user1.getText();
				  		String s = new String();
				  		try { 

					  			BufferedReader input = new BufferedReader(new FileReader("J:\\user.txt")); //读取流
	
					  			while((s = input.readLine())!=null)
					  			{ 
					  				
					  				if(s.equals(na))
							  		{
							  			caution.setVisible(true);
							  			break;
							  		}
					  				else
					  					caution.setVisible(false);
					  			} 
	
					  			input.close(); 

				  			}
				  			catch (Exception et) { 
				  			} 	
				  	} 
				  } 
		  ); */
		  
	 /*confirm.addActionListener(new ActionListener()		//按扭按下在txt加入用户信息
	 {
			public void actionPerformed(ActionEvent event)
			{
				//int in;
				String na = String.valueOf(password1.getPassword());
				String nu = String.valueOf(passwordnd1.getPassword());
				String u = user1.getText();
				if((!(u.equals("")))&&na.equals(nu)&&!na.equals(""))
				{	
					//File file = new File("J:\\user.txt");
					try{
					     BufferedWriter writer = new BufferedWriter(new FileWriter(new File("J:\\user.txt"),true));
					     writer.newLine();
					     writer.write(u);
					     writer.newLine();
					     writer.write(na);
					     writer.close();
					    // System.out.print(na);

					}catch(Exception e){

					     }
				}
			}
	}
	);*/
	 /*protected void paintComponent(Graphics g) 
	 {
		  ImageIcon icon = new ImageIcon(imgpath);
		  Image img = icon.getImage();
		  g.drawImage(img, 0, 0, width, hight, this);
	 }*/
}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class Zhuxiao extends JPanel //注册面板
{
	 private static final long serialVersionUID = 1L;
	 private JLabel caution;
	 //private JTextField user1;
	 //private JPasswordField password1,passwordnd1;
	 JButton zhuxiao,tuichu;
	 int width = 0, hight = 0;
	 public Zhuxiao(int width, int hight)
	 {
		  this.width = width;
		  this.hight = hight;
		  //imgpath = file;
		  this.setLayout(null);
		  //user=new JLabel("用户名");
		  caution=new JLabel("是否导入旧操作记录（若否，请点击下一步）");
		  //user.setFont(new Font("", Font.PLAIN, 18));
		  //password=new JLabel("密码");
		  /*passwordnd=new JLabel("确认密码");
		  password.setFont(new Font("", Font.PLAIN, 18));
		  confirm = new JButton("下一步");
		  user1=new JTextField(20);
		  password1=new JPasswordField(20);
		  passwordnd1=new JPasswordField(20);*/
		  zhuxiao = new JButton("注销");
		  tuichu = new JButton("退出");
		  this.add(zhuxiao);
		  this.add(tuichu);
		  //this.add(caution);
		  
		  //this.add(login);
		  //this.add(confirm);		  
		  //user.setFont(new Font("",1,30));//字体大小
		  //caution.setForeground(Color.DARK_GRAY);
		  //caution.setBounds(240,240,240,20);
		  zhuxiao.setBounds(260,270,80,80);
		  tuichu.setBounds(360,270,80,80);
		 /* user1.getDocument().addDocumentListener
		  (
				  new DocumentListener() 
				  { 
					public Vector<User> users = new Vector<User>();
					
				  	public void changedUpdate(DocumentEvent e) 
				  	{ 
				  		int in;	
				  		String na = user1.getText();
				  		String s = new String();
				  		try { 

					  			BufferedReader input = new BufferedReader(new FileReader("J:\\user.txt")); //读取流
	
					  			while((s = input.readLine())!=null)
					  			{ 
					  				if(s.equals(na))
							  		{
							  			caution.setVisible(true);
							  			break;
							  		}
					  				else
					  					caution.setVisible(false);
					  			} 
	
					  			input.close(); 

				  			}
				  			catch (Exception et) { 
				  			} 			  			
				  	} 
				  	public void insertUpdate(DocumentEvent e) 
				  	{ 
				  		String na = user1.getText();
				  		String s = new String();
				  		try { 

					  			BufferedReader input = new BufferedReader(new FileReader("J:\\user.txt")); //读取流
	
					  			while((s = input.readLine())!=null)
					  			{ 
					  			
					  				if(s.equals(na))
							  		{
							  			caution.setVisible(true);
							  			break;
							  		}
					  				else
					  					caution.setVisible(false);
					  			} 
	
					  			input.close(); 

				  			}
				  			catch (Exception et) { 
				  			} 		
				  	} 
				  	public void removeUpdate(DocumentEvent e) 
				  	{
				  		String na = user1.getText();
				  		String s = new String();
				  		try { 

					  			BufferedReader input = new BufferedReader(new FileReader("J:\\user.txt")); //读取流
	
					  			while((s = input.readLine())!=null)
					  			{ 
					  				
					  				if(s.equals(na))
							  		{
							  			caution.setVisible(true);
							  			break;
							  		}
					  				else
					  					caution.setVisible(false);
					  			} 
	
					  			input.close(); 

				  			}
				  			catch (Exception et) { 
				  			} 	
				  	} 
				  } 
		  ); */
		  
	 /*confirm.addActionListener(new ActionListener()		//按扭按下在txt加入用户信息
	 {
			public void actionPerformed(ActionEvent event)
			{
				//int in;
				String na = String.valueOf(password1.getPassword());
				String nu = String.valueOf(passwordnd1.getPassword());
				String u = user1.getText();
				if((!(u.equals("")))&&na.equals(nu)&&!na.equals(""))
				{	
					//File file = new File("J:\\user.txt");
					try{
					     BufferedWriter writer = new BufferedWriter(new FileWriter(new File("J:\\user.txt"),true));
					     writer.newLine();
					     writer.write(u);
					     writer.newLine();
					     writer.write(na);
					     writer.close();
					    // System.out.print(na);

					}catch(Exception e){

					     }
				}
			}
	}
	);*/
	 /*protected void paintComponent(Graphics g) 
	 {
		  ImageIcon icon = new ImageIcon(imgpath);
		  Image img = icon.getImage();
		  g.drawImage(img, 0, 0, width, hight, this);
	 }*/
}
}

class Adddeal extends JPanel //注册面板
{
	 private static final long serialVersionUID = 1L;
	 private JLabel namenum,date,price,amount,taxrate,commission,type,password,passwordnd,register1,caution,caution1,caution2;
	 private JTextField namenum1,date1,price1,amount1,taxrate1,commission1,type1,user1;
	 private JPasswordField password1,passwordnd1;
	 JButton confirm,next;
	 int width = 0, hight = 0;
	 public Adddeal(int width, int hight)
	 {
		  this.width = width;
		  this.hight = hight;
		  //imgpath = file;
		  this.setLayout(null);
		  namenum=new JLabel("股票名称或代码");
		  caution=new JLabel("此股票不存在，请重新输入");
		  caution1=new JLabel("正在搜索此股票，请稍候...");
		  caution2=new JLabel("√");
		  
		  //user.setFont(new Font("", Font.PLAIN, 18));
		  //password=new JLabel("密码");
		  date=new JLabel("操作日期");
		  price=new JLabel("价格");
		  amount=new JLabel("数量");
		  taxrate=new JLabel("税率");
		  commission=new JLabel("佣金");
		  type=new JLabel("操作类型");
		  //passwordnd=new JLabel("确认密码");
		  namenum.setFont(new Font("", Font.PLAIN, 18));
		  date.setFont(new Font("", Font.PLAIN, 18));
		  amount.setFont(new Font("", Font.PLAIN, 18));
		  taxrate.setFont(new Font("", Font.PLAIN, 18));
		  commission.setFont(new Font("", Font.PLAIN, 18));
		  type.setFont(new Font("", Font.PLAIN, 18));
		  confirm = new JButton("确定添加");
		  next = new JButton("进入主页");
		  user1=new JTextField(20);
		  namenum1=new JTextField(20);
		  date1=new JTextField(20);
		  price1=new JTextField(20);
		  amount1=new JTextField(20);
		  taxrate1=new JTextField(20);
		  commission1=new JTextField(20);
		  type1=new JTextField(20);
		  
		  password1=new JPasswordField(20);
		  passwordnd1=new JPasswordField(20);
		 // this.add(user);
		  this.add(namenum);
		  this.add(namenum1);
		  this.add(date);
		  this.add(date1);
		  this.add(price);
		  this.add(price1);
		  this.add(amount);
		  this.add(amount1);
		  this.add(taxrate);
		  this.add(taxrate1);
		  this.add(commission);
		  this.add(commission1);
		  this.add(type);
		  this.add(type1);
		  this.add(next);
		  
		  //this.add(login);
		  this.add(confirm);		  
		  //user.setFont(new Font("",1,30));//字体大小
		  //user.setForeground(Color.DARK_GRAY); 
		  caution.setForeground(Color.red);
		  namenum.setForeground(Color.DARK_GRAY); 
		  date.setForeground(Color.DARK_GRAY); 
		  price.setForeground(Color.DARK_GRAY); 
		  amount.setForeground(Color.DARK_GRAY); 
		  taxrate.setForeground(Color.DARK_GRAY); 
		  commission.setForeground(Color.DARK_GRAY); 
		  type.setForeground(Color.DARK_GRAY); 
		  //passwordnd.setForeground(Color.DARK_GRAY); 
		  //user.setBounds(260,240,80,20);
		  namenum.setBounds(260,150,100,20);
		  namenum1.setBounds(380,150,80,20);
		  date.setBounds(260,180,80,20);
		  date1.setBounds(380,180,80,20);
		  price.setBounds(260,210,80,20);
		  price1.setBounds(380,210,80,20);
		  amount.setBounds(260,240,80,20);
		  amount1.setBounds(380,240,80,20);
		  taxrate.setBounds(260,270,80,20);
		  taxrate1.setBounds(380,270,80,20);
		  commission.setBounds(260,300,80,20);
		  commission1.setBounds(380,300,80,20);
		  type.setBounds(260,330,80,20);
		  type1.setBounds(380,330,80,20);
		  
		  confirm.setBounds(300,370,100,25);
		  next.setBounds(300,400,100,25);
		  caution.setBounds(480,150,200,20);
		  caution1.setBounds(480,150,200,20);
		  caution2.setBounds(480,150,200,20);
		  
		  
		  caution.setVisible(false);
		  caution1.setVisible(false);
		  caution2.setVisible(false);
		  
		  this.confirm.addActionListener(new ActionListener()
		  	 {
			  public void actionPerformed(ActionEvent e){
				  JOptionPane.showMessageDialog(null,"添加成功","",JOptionPane.INFORMATION_MESSAGE);
		        }
			 }
		     );
		  
		  //user1.setBounds(340,240,120,20);
		  /*namenum1.getDocument().addDocumentListener
		  (
				  new DocumentListener() 
				  { 
					public Vector<User> users = new Vector<User>();
					
				  	public void changedUpdate(DocumentEvent e) 
				  	{ 
				  		int in;	
				  		String na = user1.getText();
				  		if(!na.equals(""))
				  		{
				  			caution.setVisible(false);
				  			caution1.setVisible(true);
				  		}
				  		String s = new String();
				  		try { 

					  			BufferedReader input = new BufferedReader(new FileReader("J:\\search.txt")); //读取流
					  			//String s;
					  			while((s = input.readLine())!=null)
					  			{ //判断是否读到了最后一行
					  				//System.out.println(s);
					  				String[] str=s.split(" ");
					  				str[1]=str[1].trim();
					  				if(str[0].equals(na)||str[1].equals(na))
					  				{
					  					caution1.setVisible(false);
					  					caution2.setVisible(true);
					  					break;
					  				}
					  			} 
					  			if(s==null)
					  			{
					  				caution1.setVisible(false);
					  				caution.setVisible(true);
					  			}
					  			input.close(); 

				  			}
				  			catch (Exception et) { 
				  			} 			  			
				  	} 
				  	public void insertUpdate(DocumentEvent e) 
				  	{ 
				  		String na = user1.getText();
				  		String s = new String();
				  		try { 

					  			BufferedReader input = new BufferedReader(new FileReader("J:\\user.txt")); //读取流
	
					  			while((s = input.readLine())!=null)
					  			{ //判断是否读到了最后一行
					  				//System.out.println(s);
					  				if(s.equals(na))
							  		{
							  			caution.setVisible(true);
							  			break;
							  		}
					  				else
					  					caution.setVisible(false);
					  			} 
	
					  			input.close(); 

				  			}
				  			catch (Exception et) { 
				  			} 		
				  	} 
				  	public void removeUpdate(DocumentEvent e) 
				  	{
				  		String na = user1.getText();
				  		String s = new String();
				  		try { 

					  			BufferedReader input = new BufferedReader(new FileReader("J:\\user.txt")); //读取流
	
					  			while((s = input.readLine())!=null)
					  			{ //判断是否读到了最后一行
					  				//System.out.println(s);
					  				if(s.equals(na))
							  		{
							  			caution.setVisible(true);
							  			break;
							  		}
					  				else
					  					caution.setVisible(false);
					  			} 
	
					  			input.close(); 

				  			}
				  			catch (Exception et) { 
				  			} 	
				  	} 
				  } 
		  ); */
		  
		  
		 // password.setBounds(260,270,80,20);
		 // password1.setBounds(340,270,120,20);
		  //passwordnd.setBounds(260,300,120,20);
		  //passwordnd1.setBounds(340,300,120,20);
		  
	/* confirm.addActionListener(new ActionListener()		//按扭按下在txt加入用户信息
	 {
			public void actionPerformed(ActionEvent event)
			{
				//int in;
				String na = String.valueOf(password1.getPassword());
				String nu = String.valueOf(passwordnd1.getPassword());
				String u = user1.getText();
				if((!(u.equals("")))&&na.equals(nu)&&!na.equals(""))
				{	
					//File file = new File("J:\\user.txt");
					try{
					     BufferedWriter writer = new BufferedWriter(new FileWriter(new File("J:\\user.txt"),true));
					     writer.newLine();
					     writer.write(u);
					     writer.newLine();
					     writer.write(na);
					     writer.close();
					    // System.out.print(na);

					}catch(Exception e){

					     }
				}
			}
	}
	);*/
	 /*protected void paintComponent(Graphics g) 
	 {
		  ImageIcon icon = new ImageIcon(imgpath);
		  Image img = icon.getImage();
		  g.drawImage(img, 0, 0, width, hight, this);
	 }*/
}
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class Dinglan extends JPanel
{
	 JLabel sc,ryke,fdyk,yk,zhzzc,sz,xj,bj,sc1,ryke1,fdyk1,fdyk2,yk1,zhzzc1,sz1,xj1,bj1;
	
	public Dinglan()
	{
	
	  sc=new JLabel("市场");
	  ryke=new JLabel("日盈亏额");
	  fdyk=new JLabel("浮动盈亏");
	  yk=new JLabel("盈亏");
	  zhzzc=new JLabel("账户总资产");
	  sz=new JLabel("市值");
	  xj=new JLabel("现金");
	  bj=new JLabel("本金");
	  sc1=new JLabel("A股");
	  ryke1=new JLabel("0.00");
	  fdyk1=new JLabel("-23.3");
	  yk1=new JLabel("17632");
	  zhzzc1=new JLabel("500000");
	  sz1=new JLabel("1000000");
	  xj1=new JLabel("600000");
	  bj1=new JLabel("500000");
	  sc.setForeground(Color.DARK_GRAY);
	  ryke.setForeground(Color.DARK_GRAY);
	  fdyk.setForeground(Color.DARK_GRAY);
	  yk.setForeground(Color.DARK_GRAY);
	  zhzzc.setForeground(Color.DARK_GRAY);
	  sz.setForeground(Color.DARK_GRAY);
	  xj.setForeground(Color.DARK_GRAY);
	  bj.setForeground(Color.DARK_GRAY);
	  sc1.setForeground(Color.DARK_GRAY);
	  ryke1.setForeground(Color.DARK_GRAY);
	  fdyk1.setForeground(Color.DARK_GRAY);
	  yk1.setForeground(Color.DARK_GRAY);
	  zhzzc1.setForeground(Color.DARK_GRAY);
	  sz1.setForeground(Color.DARK_GRAY);
	  xj1.setForeground(Color.DARK_GRAY);
	  bj1.setForeground(Color.DARK_GRAY);
	  //caution=new JLabel("此用户名已存在 ，请重新输入");
	  /*sc.setFont(new Font("", Font.PLAIN, 18));
	  ryke.setFont(new Font("", Font.PLAIN, 18));
	  fdyk.setFont(new Font("", Font.PLAIN, 18));
	  yk.setFont(new Font("", Font.PLAIN, 18));
	  zhzzc.setFont(new Font("", Font.PLAIN, 18));
	  sz.setFont(new Font("", Font.PLAIN, 18));
	  xj.setFont(new Font("", Font.PLAIN, 18));
	  bj.setFont(new Font("", Font.PLAIN, 18));
	  sc1.setFont(new Font("", Font.PLAIN, 18));
	  ryke1.setFont(new Font("", Font.PLAIN, 18));
	  fdyk1.setFont(new Font("", Font.PLAIN, 18));
	  yk1.setFont(new Font("", Font.PLAIN, 18));
	  zhzzc1.setFont(new Font("", Font.PLAIN, 18));
	  sz1.setFont(new Font("", Font.PLAIN, 18));
	  xj1.setFont(new Font("", Font.PLAIN, 18));
	  bj1.setFont(new Font("", Font.PLAIN, 18));*/
	  
	  this.setLayout(new GridLayout(2, 8));
	  
	  this.add(sc);
	  this.add(ryke);
	  this.add(fdyk);
	  this.add(yk);
	  this.add(zhzzc);
	  this.add(sz);
	  this.add(xj);
	  this.add(bj);
	  this.add(sc1);
	  this.add(ryke1);
	  this.add(fdyk1);
	  this.add(yk1);
	  this.add(zhzzc1);
	  this.add(sz1);
	  //this.add(login);
	  this.add(xj1);
	  this.add(bj1);
	  //user.setFont(new Font("",1,30));//字体大小
	  //user.setForeground(Color.DARK_GRAY); 
	  
	  /* sc.setBounds(20,200,80,20);
	  sc1.setBounds(20,220,80,20);
	  ryke.setBounds(100,200,80,20);
	  ryke1.setBounds(100,220,80,20);
	  fdyk.setBounds(180,200,80,20);
	  fdyk1.setBounds(180,220,80,20);
	  yk.setBounds(260,200,80,20);
	  yk1.setBounds(260,220,80,20);
	  zhzzc.setBounds(340,200,80,20);
	  zhzzc1.setBounds(340,220,80,20);
	  sz.setBounds(420,200,80,20);
	  sz1.setBounds(420,220,80,20);
	  xj.setBounds(500,200,80,20);
	  xj1.setBounds(500,220,80,20);
	  bj.setBounds(580,200,80,20);
	  bj1.setBounds(580,220,80,20);*/
	}
	 
}

public class Showstock extends JApplet 
{
	String username;
	JPanel p2 = new JPanel(new BorderLayout());
	//GetPanel pp = new GetPanel(750,500,"J:/background1.jpg");
	Registerpane rp = new Registerpane(750,500);
	
	
	JTextField aField=new JTextField(10);
	int news;
	//JLabel username = new JLabel("mouse");
	//JLabel username;
	JMenuBar jmb = new JMenuBar();
	JMenu toolMenu = new JMenu("工具");
	JMenu helpMenu = new JMenu("帮助");
	JMenuItem daorumenu, daochumenu,help,about;
	
	
	//pp.setLayout(new GridBagLayout());
	//
	  //add(new JButton("Food to be placed here"),
	  //BorderLayout.CENTER);
	
	  
  JTabbedPane jtpFigures = new JTabbedPane();
  /*private FigurePanel squarePanel = new FigurePanel();
  private FigurePanel rectanglePanel = new FigurePanel();
  private FigurePanel circlePanel = new FigurePanel();
  private FigurePanel ovalPanel = new FigurePanel();*/
  //JButton zx = new JButton("注销");
  //JButton tuichu = new JButton("退出");
  //zx.setSize(50,30);
  Zhuxiao zp = new Zhuxiao(750,500);
  Dinglan dl = new Dinglan();
  
  public void Replaceusername(String uname)
  {
	  JLabel username = new JLabel(uname);
	  username.setFont(new Font("",1,30));
	  add(username);
	  username.setBounds(10, 10, 120, 30);
  }
  public Showstock() 
  {
	
	//zx.setSize(50,30);
	//p2.add(new JTextField("Time to be displayed here"),BorderLayout.NORTH);
	
    /*squarePanel.setType(FigurePanel.LINE);
    rectanglePanel.setType(FigurePanel.RECTANGLE);
    circlePanel.setType(FigurePanel.ROUND_RECTANGLE);
    ovalPanel.setType(FigurePanel.OVAL);*/

    //GetPanel pp = new GetPanel(750,500,"J:/background1.jpg");
    //Zhanghuzongzhi zhzz = new Zhanghuzongzhi(750,500);
    
    jmb.add(toolMenu);
    jmb.add(helpMenu);
    daorumenu = new JMenuItem("导入数据");
    daochumenu = new JMenuItem("导出数据");
    help = new JMenuItem("帮助");
    about = new JMenuItem("关于");
    //toolMenu.add(arg0)
    toolMenu.add(daorumenu);
    toolMenu.add(daochumenu);
    helpMenu.add(help);
    helpMenu.add(about);
    
    this.setJMenuBar(jmb);
    this.setLayout(null);
   // zx.setBounds(r);
    //add(jtpFigures, BorderLayout.CENTER);
    //add(zx,BorderLayout.NORTH);
    
  }
  public void Createtable()
  {
	  	add(jtpFigures);
	    add(dl);
	    //add(zx);
	   // add(tuichu);
	    jtpFigures.setBounds(0, 45, 690, 400);
	    dl.setBounds(190, 6, 510, 42);
	    //zx.setBounds(640,1,50,20);
	    //tuichu.setBounds(640,5,50,20);
	    //jtpFigures.add(zhzz, "账户总值");
	    //JPanel j2 = new JPanel();
	    JPanel j3 = new JPanel();
	    JPanel j4 = new JPanel();
	    
	    final ModifyTable applet2 = new ModifyTable(username,news);//////////////////////////////////////////////////////////////////////////////////////
	    
	    //applet2.setSize(600, 300);
	    //applet2.setSize(650, 100);
	    //applet2.WIDTH=750;
	    //applet2.HEIGHT=400;
	    //JFrame frame = new JFrame();
	    //EXIT_ON_CLOSE == 3
	    //frame.setDefaultCloseOperation(3);
	    //frame.setTitle("ModifyTable");
	    //frame.getContentPane().add(applet, java.awt.BorderLayout.CENTER);
	    //applet2.init();
	    //applet2.start();
	    jtpFigures.add(applet2, "持仓盈亏");
	    jtpFigures.add(j3, "收益率");
	    jtpFigures.add(j4, "持股构成");      
	    
	    /*jtpFigures.add(rectanglePanel, "持仓盈亏");
	    jtpFigures.add(circlePanel, "收益率");
	    jtpFigures.add(pp, "持股构成");
	    //jtpFigures.add(zp, "注销");*/
	    

	    /*jtpFigures.setToolTipTextAt(0, "Square");
	    jtpFigures.setToolTipTextAt(1, "Rectangle");
	    jtpFigures.setToolTipTextAt(2, "Circle");
	    jtpFigures.setToolTipTextAt(3, "Oval");*/
	    applet2.jTable1.addMouseListener(new MouseAdapter() //////////////////////////////////这里是添加点击买卖时弹出frame,需要用到username,不过username在登录时才能get到
		  { 
		        public void mouseClicked(MouseEvent e) 
		        { 
		                //int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); //获得行位置 
		                //int col=((JTable)e.getSource()).columnAtPoint(e.getPoint()); //获得列位置
		        	///int r = jTable1.getSelectedRow();
		        		int r=applet2.jTable1.getSelectedRow();
		        		int c= applet2.jTable1.getSelectedColumn();
		        		//System.out.print(r+"    ");
		        		System.out.print(c);
		        		String number="";
		                if(c==8)
		                {
		                	System.out.print("kaka "+applet2.username+" kkakaka");
		                	
		                	File file = new File("j://"+applet2.username+".xls");
		                	 //File file = new File("j://"+username+".xls");
			        		String[] columnNames1={"日期", "类型", "价格", "数量","税率","佣金"};
				        	Object[][] rowData1={};
				        	System.out.print("kaka "+applet2.username+" ddddddd-----------");///////////////////////////////////////////////
				        	
				        	//String number = "";
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
				        		
				        		System.out.print(search+"-------------");
				        		//getValueAt(r, 0);
				        		
				        		
				        		
								Workbook book = Workbook.getWorkbook(file);
								Sheet sheet = book.getSheet(search);
								
								number = sheet.getCell(1, 1).getContents();
								
								try
								{
									System.out.print(sheet.getName()+" uuuuuu");
									int m = Integer.parseInt(sheet.getCell(11,0).getContents());
									System.out.print(m);
									System.out.print("kaka "+applet2.username+" fffff");//////////////////////////////////////////////////////
									
									//int m = Integer.parseInt(sheet.getCell(1, 11).getContents());
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
									System.out.print("kaka "+applet2.username+" zzzzz");
								}finally
								{
									if(book != null)
									{
										book.close();
									}
								}
				  	  		} catch (BiffException er) {
				  				System.err.println("");
							} catch (IOException er) {
								System.err.println("文件读取错误");
							}
		                	//System.out.print("jaja");
		                	final JTable jTable1 = new JTable(tableModel1);
		                	
		                	final JFrame frame = new JFrame(String.valueOf(applet2.jTable1.getValueAt(r, 0)));
		                	
		                	JLabel title = new JLabel();///////////////////////
		                	//frame.add(title,BorderLayout.NORTH);
		                	//JPanel jp = new JPanel();
		                	
		                	JPanel jpl = new JPanel();
		                	JPanel jpr = new JPanel();
		                	JPanel jps = new JPanel();
		                	frame.add(jpl);
		                	jpl.setBounds(5,50,170,220);
		                	//frame.add(jpr,BorderLayout.NORTH);
		                	
		                	
		                	
		                	jpl.setLayout(new GridLayout(9, 2));
		                	
		                	/////         此处有循环体处理标签，标签字符串从网络API获取
		                	try{
		            			URL gis = new URL("http://hq.sinajs.cn/list="+number);//你要报错的网页
		            			BufferedReader in = new BufferedReader( new InputStreamReader( gis.openStream() ) );
		            			//PrintWriter pw=new PrintWriter( new FileOutputStream("api.htm"));//保存的路径
		            			String line="";
		            			String[] data;
		            			System.out.print(number);
		            			while( (line = in.readLine()) != null )
		            			{
		            				
		            			//pw.println(line);
		            			//PrintWriter pfp = new PrintWriter(fp);
		            			//pfp.print(line);
		            			//pfp.close();
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
		                	//final JPanel rikp = new JPanel(new BorderLayout());
		            
		                	//rikp.add(rik);
		                	
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
		                   // rik.setVisible(false);
		                    //rik.setVisible(true);
		                    
		                    jtpFigures.add(zhouk, "周K");
		                    //zhouk.setVisible(false);
		                    //zhouk.setVisible(true);
		                    
		                    jtpFigures.add(yuekp, "月K");
		                    //yuekp.setVisible(false);
		                    //yuekp.setVisible(true);
		                    
		                    
		                    //jtpFigures.setSelectedIndex(1);
		                    //fenshi.repaint();
		                    //jtpFigures.sh
		                    //jtpFigures.setBounds(0, 0, 200, 150);
		                    jtpFigures.addChangeListener(new ChangeListener() 
		                    {
		                    	public void stateChanged(ChangeEvent e) 
		                    	{
			                    	JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
			                    	int selectedIndex = tabbedPane.getSelectedIndex();
			                    	switch (selectedIndex) 
			                    	{
			                    		case 0:   
			                    			//frame.setSize(700, 580);
			                    			/*JFrame frame1 = new JFrame("");
			                    			frame1.setVisible(true);
			    		                    frame1.setResizable(false);
			    		                    frame1.setSize(1, 1);
			                    			frame1.dispose();
			                    			frame1 = null;*/
			                    			break;
			                    			
			                    		case 1:			                    		
			                    			//frame.setSize(700, 581);
			                    			//frame.setSize(700, 582);
			                    			JFrame frame2 = new JFrame("");
			                    			frame2.setVisible(true);
			    		                    frame2.setResizable(false);
			    		                    frame2.setSize(1, 1);
			                    			frame2.dispose();
			                    			frame2 = null;
			                    			break;
			                    		case 2:
			                    			//frame.setSize(700, 583);
			                    			//frame.setSize(700, 584);
			                    			frame.setSize(700, 581);
			                    			JFrame frame3 = new JFrame("");
			                    			frame3.setVisible(true);
			    		                    frame3.setResizable(false);
			    		                    frame3.setSize(1, 1);
			                    			frame3.dispose();
			                    			frame3 = null;
			                    			break;
			                    		case 3:
			                    			//System.out.print("aaaaaaaaaaa");
			                    			
			                    			//frame.setSize(700, 586);
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
		                    //jps.setSize(400,200);
		                    //frame.add(new JScrollPane(jTable1),BorderLayout.SOUTH);
		                    //jtpFigures.setBounds(0, 0, 200, 200);////////////
		                    frame.add(jps);
		                    jps.setBounds(40, 350, 620, 140);
		                    JPanel jpss = new JPanel();
		                    //jpss.setLayout(new BorderLayout());
		                    //frame
		                	//JPanel gif = new JPanel();
		                	JButton jbtAddRow1 = new JButton("增加新的交易");
		                	JButton jbtDeleteRow1 = new JButton("删除所选交易");
		                	jpss.add(jbtAddRow1);
		                	jpss.add(jbtDeleteRow1);
		                	frame.add(jpss);
		                	jpss.setBounds(10, 500, 620, 30);
		                	
		                	//frame.add(jbtAddRow1);
		                	//frame.add(jbtDeleteRow1);
		                	
		                	//jbtAddRow1.setBounds(280, 400, 50, 20);
		                	//jbtDeleteRow1.setBounds(350, 400, 50, 20);
		                	
		                    jbtAddRow1.addActionListener(new ActionListener() {
		                        public void actionPerformed(ActionEvent e) {
		                          if (jTable1.getSelectedRow() >= 0)
		                            //tableModel.insertRow(jTable1.getSelectedRow(),
		                              //new java.util.Vector());
		                          tableModel1.addRow(new java.util.Vector());
		                          else
		                          tableModel1.addRow(new java.util.Vector());
		                        }
		                        
		                      });

		                      /*jbtAddColumn.addActionListener(new ActionListener() {
		                        public void actionPerformed(ActionEvent e) {
		                          String name = JOptionPane.showInputDialog("New Column Name");
		                          tableModel.addColumn(name, new java.util.Vector());
		                        }
		                      });*/

		                      jbtDeleteRow1.addActionListener(new ActionListener() {
		                        public void actionPerformed(ActionEvent e) {
		                          if (jTable1.getSelectedRow() >= 0)
		                            tableModel1.removeRow(jTable1.getSelectedRow());
		                        }
		                      });
		                	
		                    frame.setLayout(null);
		                    
		                    frame.setSize(700, 580);
		                    frame.setLocationRelativeTo(null);
		                    frame.setVisible(true);
		                    frame.setResizable(false);
		                }
		                
		                //String cellVal=(String)(tbModel.getValueAt(row,col)); //获得点击单元格数据 
		                //txtboxRow.setText((row+1)+""); txtboxCol.setText((col+1)+""); 
		                //txtboxContent.setText(cellVal); 

		           // } 
		        	//else return; 
		        } 
		  });
  }

  /** Main method */
  public static void main(String[] args) 
  {
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
      } 
	  catch (Exception e) 
	  {  
          System.err.println("Something went wrong!");  
      }  
	//JPanel login = new JPanel(new BorderLayout());
    final JFrame frame = new JFrame("乾道量行");

    // Create an instance of the applet
    final Showstock applet = new Showstock();
    
    ///////////////////////////////////////
    ////////////////////////4.14
    
    //public static void main(String[] args) {
   
    //frame.setSize(400,320);
    //frame.setLocationRelativeTo(null);
    //frame.setVisible(true);*/
    
    
    //applet.init();

    /////////////////////////////////////////////                          4.12
    final JPanel jp = new JPanel();
    final CardLayout out = new CardLayout();
    jp.setLayout(out);
    
    frame.add(jp);
    
    final Registerpane rp = new Registerpane(750,500);
    
    //frame.add(rp);
    //rp.setVisible(false);
    
    final GetPanel pp = new GetPanel(750,500,"J:/background1.jpg");
    
    final Daoruye daorup = new Daoruye(750,500);
    
    Adddeal adddealp = new Adddeal(750,500);
    
    jp.add(pp,"1");
    jp.add(rp,"2");
    jp.add(daorup,"3");
    //jp.add(adddealp,"4");
    
    out.show(jp, "1");
    
    /*pp.login.addActionListener(new ActionListener()		//按扭按下在txt加入用户信息
	 {
			public void actionPerformed(ActionEvent event)
			{
				//out.show(jp, "4");
				jp.setVisible(false);
				
				frame.add(applet);
				applet.show();
				
				
			}
	}
	);*/
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
				  			BufferedReader input = new BufferedReader(new FileReader("J:\\user.txt")); //读取流
				  			BufferedReader input1 = new BufferedReader(new FileReader("J:\\password.txt")); //读取流
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
    
    
    
    /*rp.confirm.addActionListener(new ActionListener()	
	 {
			public void actionPerformed(ActionEvent event)
			{
				//rp.setVisible(false);
				//frame.add(daorup);
				//daorup.show();
				out.show(jp, "3");
				
			}
	}
	);*/
    
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
					     BufferedWriter writer = new BufferedWriter(new FileWriter(new File("J:\\user.txt"),true));
					     BufferedWriter writer1 = new BufferedWriter(new FileWriter(new File("J:\\password.txt"),true));
					     writer.newLine();
					     writer1.newLine();
					     
					     writer.write(u);
					     writer1.write(na);
					     
					     //writer.newLine();
					     //writer.write(na);
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
				/*jp.setVisible(false);				
				frame.add(applet);
				applet.show();	*/
				
				
					
					
					//System.out.print("haha");
					

  					//daorup.setVisible(false);
  					jp.setVisible(false);
  					//applet.Replaceusername(u);
  					//applet.username=u;
  					
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
				System.out.println(fDialog.getSelectedFile());
				jp.setVisible(false);				
				frame.add(applet);
				applet.show();
			}
		}
	 }
    	);
    
    adddealp.next.addActionListener(new ActionListener()		//按扭按下在txt加入用户信息
	 {
			public void actionPerformed(ActionEvent event)
			{
				//out.show(jp, "4");
				jp.setVisible(false);
				frame.add(applet);
				applet.show();
				
				
			}
	}
	);
    
    
    

//然后在你的JLabel实例上,作用这个监听器,如:
   // JLabel lab = new JLabel("点我");
   // lab.addMouseListener(new MouseAdp());

    class MouseAdp implements MouseListener
    {
        public MouseAdp(){}
        public void mouseClicked(MouseEvent e) 
        {
/**鼠标点击事件(包括按下和弹起两个动作)处理方法.**/
        	//System.out.println("你点了我!");
        	//pp.setVisible(false);
        	
        	//frame.add(rp);
        	//rp.show();
        	out.show(jp, "2");
        	
        	
        }

        public void mouseEntered(MouseEvent e) 
        {
/**鼠标移到组件上方法时事件处理方法.**/
        	
        }
        public void mouseExited(MouseEvent e) 
        {
        	
/**鼠标移开组件时事件处理方法.**/
        }

        public void mousePressed(MouseEvent e) 
        {
/**鼠标在组件上按下(但没弹起)时事件处理方法.**/
        }

        public void mouseReleased(MouseEvent e) 
        {
/**鼠标在组件上弹起事件处理方法.**/
        }

    }
    applet.zp.zhuxiao.addActionListener(new ActionListener()		//按扭按下在txt加入用户信息
	 {
		public void actionPerformed(ActionEvent event)
		{
			//applet.setVisible(false);
			//pp.setVisible(true);
			frame.remove(applet);
			
			//pp.show();
			
			jp.show();
			out.show(jp, "1");
		}
	 }
     );
         
   /* querymenu = new JMenuItem("修改信息");/////////////////////////////////////////////////////////*******************
    // 菜单添加事件
    querymenu.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            Menu.this.t.setText("修改信息菜单");
        }
    });////////////////////////////////////////////////////////////////////////////////////////////////////*/
   
    pp.register1.addMouseListener(new MouseAdp());
    // Add the applet instance to the frame
    
    //frame.add(rp);
    //frame.add(daorup);
    //daorup.setVisible(false);
    //rp.setVisible(false);
    //frame.remove(rp);
    //frame.remove(daorup);
   // pp.show();
    //frame.add(applet, java.awt.BorderLayout.CENTER);
    frame.setDefaultCloseOperation(0);
    //frame.setDefaultCloseOperation();
    frame.setLocationRelativeTo(null);
    
    // Display the frame
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

