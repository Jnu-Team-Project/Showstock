package com.jnu.stock.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.jnu.stock.Copy;
import com.jnu.stock.ShowStock;
/**
 * 
 * 面板工具类
 * 
 * @author Administrator
 *
 */
public class PanelUtil {
	
	/**
	 * 实例化登录后的界面以及为控件添加事件
	 * 
	 * @param showStock
	 * @param username
	 */
	public static void initTheSecondePanel(final ShowStock showStock, final String username, final int news){
		JMenuBar jmb = new JMenuBar();
		JMenu toolMenu = new JMenu("工具");
		JMenu helpMenu = new JMenu("帮助");
		JMenuItem importDataItem, exportDateItem, contactItem, aboutItem, refreshItem;
		jmb.add(toolMenu);
		jmb.add(helpMenu);
		importDataItem = new JMenuItem("导入数据");
		exportDateItem = new JMenuItem("导出数据");
		refreshItem = new JMenuItem("刷新");
		contactItem = new JMenuItem("联系我们");
		aboutItem = new JMenuItem("关于");

		toolMenu.add(importDataItem);
		toolMenu.add(exportDateItem);
		toolMenu.add(refreshItem);
		
		helpMenu.add(contactItem);
		helpMenu.add(aboutItem);
		showStock.setJMenuBar(jmb);
		showStock.setLayout(null);

		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null,
						"版本 1.0\n版权所有 团队项目1组保留所有权利", "关于“乾道量行”",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		//添加事件
		refreshItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//showStock.Updatetable();
				TableUtil.updatetable(showStock, username, news);
			}
		});

		importDataItem.addActionListener(new ActionListener() {
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

					String name = fDialog.getSelectedFile().toString();
					Copy copy = new Copy();
					copy.copyFile(name, username + ".xls");
					//showStock.Createtable();
					TableUtil.updatetable(showStock, username, news);
				}
			}
		});

		exportDateItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// 初始化文件选择框
				JFileChooser fDialog = new JFileChooser();
				// 设置文件选择框的标题
				fDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fDialog.setDialogTitle("请选择导入文件");
				// 弹出选择框
				int returnVal = fDialog.showOpenDialog(null);
				// 如果是选择了文件
				if (JFileChooser.APPROVE_OPTION == returnVal) {

					JOptionPane.showMessageDialog(null, "导出成功", "",
							JOptionPane.INFORMATION_MESSAGE);
					// 打印出文件的路径，你可以修改位 把路径值 写到 textField中
					Copy copy = new Copy();
					copy.copyFile(username + ".xls", fDialog.getSelectedFile().toString() + "//" + username + ".xls");
					System.out.println(username);
					System.out.println(fDialog.getSelectedFile());
				}
			}
		});
	}
}
