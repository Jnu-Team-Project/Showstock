package com.jnu.stock;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


class DataImportPanel extends JPanel //注册面板
{
	 private static final long serialVersionUID = 1L;
	 private JLabel caution;
	 //private JTextField user1;
	 //private JPasswordField password1,passwordnd1;
	 JButton daoru,next;
	 int width = 0, hight = 0;
	 public DataImportPanel(int width, int hight)
	 {
		  this.width = width;
		  this.hight = hight;
		  //imgpath = file;
		  this.setLayout(null);
		  caution=new JLabel("是否有旧交易记录需要导入");
		  caution.setFont(new Font("", Font.PLAIN, 25));
		  daoru = new JButton("是，导入数据");
		  next = new JButton("无，直接进入主页");
		  this.add(daoru);
		  this.add(next);
		  this.add(caution);
		  caution.setForeground(Color.DARK_GRAY);
		  caution.setBounds(240,180,330,40);
		  daoru.setBounds(200,270,130,30);
		  next.setBounds(350,270,130,30);
	 }
}