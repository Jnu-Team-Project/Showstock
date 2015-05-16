package com.jnu.stock;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JApplet;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.theme.SubstanceTerracottaTheme;

import com.jnu.stock.util.PanelUtil;
import com.jnu.stock.util.TableUtil;
import com.jnu.stock.util.UsernameLabelUtil;

public class ShowStock extends JApplet {
	static Logger logger = Logger.getLogger(ShowStock.class.getName());

	static String username;
	static int news;
	
	/**
	 * ShowStock构造函数
	 */
	public ShowStock() {
		PropertyConfigurator.configure("src//log4j.properties");
	}

	/**
	 * 主方法main
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure("src//log4j.properties"); 
		
		//界面美化
		try {
			UIManager.setLookAndFeel(new SubstanceLookAndFeel());
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			SubstanceLookAndFeel
					.setCurrentTheme(new SubstanceTerracottaTheme());
			logger.info("界面美化成功");
		} catch (Exception e) {
			logger.error("Info ..."); 
			System.err.println("Something went wrong!");
		}
		
		//实例化一个JFrame并初始化
		final JFrame frame = new JFrame("乾道量行");
		frame.setDefaultCloseOperation(0);
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int selected = JOptionPane.showConfirmDialog(frame, "是否退出", "",
						JOptionPane.YES_NO_OPTION);
				if (JOptionPane.OK_OPTION == selected) {
					System.exit(0);
				}
			}
		});

		//实例化一个ShowStock对象
		final ShowStock applet = new ShowStock();
		
		final JPanel jp = new JPanel();
		final CardLayout cardLayout = new CardLayout();
		jp.setLayout(cardLayout);

		frame.add(jp);

		final RegisterPanel registerPanel = new RegisterPanel(750, 500);
		final LoginPanel loginPanel = new LoginPanel(750, 500, "background1.jpg");

		//注册用户，记录导入页面
		final DataImportPanel dataImportPanel = new DataImportPanel(750, 500);
		jp.add(loginPanel, "1");
		jp.add(registerPanel, "2");
		jp.add(dataImportPanel, "3");
		
		cardLayout.show(jp, "1");
		
		//为登录按钮添加响应
		loginPanel.login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String loginPassword = String.valueOf(loginPanel.password1.getPassword());
				String loginUserName = loginPanel.user1.getText();
				String txtUserName = new String();
				String txtPassword = new String();
				if (loginPassword.equals("") || loginUserName.equals("")) {
					loginPanel.nullcaution.setVisible(true);
				}
				else {
					BufferedReader input = null;
					BufferedReader input1 = null;
					try {
						input = new BufferedReader(new FileReader("user.txt")); // 读取流
						input1 = new BufferedReader(new FileReader("password.txt")); // 读取流
						
						while ((txtUserName = input.readLine()) != null && (txtPassword = input1.readLine()) != null) { // 判断是否读到了最后一行
							if (txtUserName.equals(loginUserName) && txtPassword.equals(loginPassword)) {
								jp.setVisible(false);
								UsernameLabelUtil.replaceUsername(applet, loginUserName);
								//applet.username = loginUserName;
								applet.init();
								//applet.Createtable();
								PanelUtil.initTheSecondePanel(applet, loginUserName, news);
								
								TableUtil.createTable(applet, loginUserName, news);
								frame.add(applet);
								applet.show();
								break;
							}
						}
						loginPanel.usercaution.setVisible(true);

					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						if(input != null){
							try {
								input.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if(input1 != null){
							try {
								input1.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}

			}
		});
		
		registerPanel.confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String password = String.valueOf(registerPanel.password1.getPassword());
				String user = registerPanel.user1.getText();
				
				//定义两个缓冲字符流，往文件中写入注册用户的信息
				BufferedWriter userWriter =null;
				BufferedWriter passwordWriter = null;
				try {
					userWriter = new BufferedWriter(new FileWriter(new File("user.txt"), true));
					passwordWriter = new BufferedWriter(new FileWriter(new File("password.txt"), true));
					userWriter.newLine();
					passwordWriter.newLine();
					userWriter.write(user);
					passwordWriter.write(password);
					
					applet.username = user;
					UsernameLabelUtil.replaceUsername(applet, user);
					//applet.replaceUsername(user);
					applet.news = 1;
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(userWriter != null){
						try {
							userWriter.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(passwordWriter != null){
						try {
							passwordWriter.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				cardLayout.show(jp, "3");
			}
		});
		
		dataImportPanel.next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				jp.setVisible(false);
				applet.init();
//				applet.Createtable();
				TableUtil.createTable(applet, username, news);
				frame.add(applet);

				applet.show();
			}
		});
		
		dataImportPanel.daoru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// 初始化文件选择框
				JFileChooser fDialog = new JFileChooser();
				// 设置文件选择框的标题
				fDialog.setDialogTitle("请选择导入文件");
				// 弹出选择框
				int returnVal = fDialog.showOpenDialog(null);
				// 如果是选择了文件
				if (JFileChooser.APPROVE_OPTION == returnVal) {

					JOptionPane.showMessageDialog(null, "导入成功", "",
							JOptionPane.INFORMATION_MESSAGE);

					// 打印出文件的路径，你可以修改位 把路径值 写到 textField中
					// System.out.println(fDialog.getSelectedFile());
					String name = fDialog.getSelectedFile().toString();
					Copy copy = new Copy();
					copy.copyFile(name, username + ".xls");
					applet.news = 0;
					//applet.Createtable();
					TableUtil.createTable(applet, username, news);
					jp.setVisible(false);

					frame.add(applet);
					applet.show();
				}
			}

		});
		
		//为“创建用户”标签添加点击事件
		loginPanel.register1.addMouseListener(new MouseAdp(cardLayout, jp));
	}
}
