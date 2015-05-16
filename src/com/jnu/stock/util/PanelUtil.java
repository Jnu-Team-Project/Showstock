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
 * ��幤����
 * 
 * @author Administrator
 *
 */
public class PanelUtil {
	
	/**
	 * ʵ������¼��Ľ����Լ�Ϊ�ؼ�����¼�
	 * 
	 * @param showStock
	 * @param username
	 */
	public static void initTheSecondePanel(final ShowStock showStock, final String username, final int news){
		JMenuBar jmb = new JMenuBar();
		JMenu toolMenu = new JMenu("����");
		JMenu helpMenu = new JMenu("����");
		JMenuItem importDataItem, exportDateItem, contactItem, aboutItem, refreshItem;
		jmb.add(toolMenu);
		jmb.add(helpMenu);
		importDataItem = new JMenuItem("��������");
		exportDateItem = new JMenuItem("��������");
		refreshItem = new JMenuItem("ˢ��");
		contactItem = new JMenuItem("��ϵ����");
		aboutItem = new JMenuItem("����");

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
						"�汾 1.0\n��Ȩ���� �Ŷ���Ŀ1�鱣������Ȩ��", "���ڡ�Ǭ�����С�",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		//����¼�
		refreshItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//showStock.Updatetable();
				TableUtil.updatetable(showStock, username, news);
			}
		});

		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// ��ʼ���ļ�ѡ���
				JFileChooser fDialog = new JFileChooser();
				// �����ļ�ѡ���ı���
				fDialog.setDialogTitle("��ѡ�����ļ�");
				// ����ѡ���
				int returnVal = fDialog.showOpenDialog(null);
				// �����ѡ�����ļ�
				if (JFileChooser.APPROVE_OPTION == returnVal) {
					JOptionPane.showMessageDialog(null, "����ɹ�", "",
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
				// ��ʼ���ļ�ѡ���
				JFileChooser fDialog = new JFileChooser();
				// �����ļ�ѡ���ı���
				fDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fDialog.setDialogTitle("��ѡ�����ļ�");
				// ����ѡ���
				int returnVal = fDialog.showOpenDialog(null);
				// �����ѡ�����ļ�
				if (JFileChooser.APPROVE_OPTION == returnVal) {

					JOptionPane.showMessageDialog(null, "�����ɹ�", "",
							JOptionPane.INFORMATION_MESSAGE);
					// ��ӡ���ļ���·����������޸�λ ��·��ֵ д�� textField��
					Copy copy = new Copy();
					copy.copyFile(username + ".xls", fDialog.getSelectedFile().toString() + "//" + username + ".xls");
					System.out.println(username);
					System.out.println(fDialog.getSelectedFile());
				}
			}
		});
	}
}
