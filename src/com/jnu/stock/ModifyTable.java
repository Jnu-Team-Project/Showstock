package com.jnu.stock;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import com.jnu.stock.util.HoldingAmount;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Vector;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ModifyTable extends JPanel {
	int[] total = new int[20];
	String username = "";
	Double dinglanriyingkui = 0.0;
	Double dinglanyingkui = 0.0;
	Double dinglanshizhi = 0.0;
	Double dinglanxianjin = 0.0;

	public Double getDinglanshizhi() {
		return dinglanshizhi;
	}

	public void setDinglanshizhi(Double dinglanshizhi) {
		this.dinglanshizhi = dinglanshizhi;
	}

	public Double getDinglanxianjin() {
		return dinglanxianjin;
	}

	public void setDinglanxianjin(Double dinglanxianjin) {
		this.dinglanxianjin = dinglanxianjin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getDinglanriyingkui() {
		return dinglanriyingkui;
	}

	public void setDinglanriyingkui(Double dinglanriyingkui) {
		this.dinglanriyingkui = dinglanriyingkui;
	}

	public Double getDinglanyingkui() {
		return dinglanyingkui;
	}

	public void setDinglanyingkui(Double dinglanyingkui) {
		this.dinglanyingkui = dinglanyingkui;
	}

	private String[] columnNames = { "股票", "当前价", "涨跌", "持有量", "持有市值", "盈亏",
			"操作" };
	Object[][] rowData = {};
	DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames) {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	// Create a table
	JTable jTable1 = new JTable(tableModel);

	public JTable getjTable1() {
		return jTable1;
	}

	public void setjTable1(JTable jTable1) {
		this.jTable1 = jTable1;
	}

	private JTextField bianhao = new JTextField(8);
	private JLabel add = new JLabel("股票编号：");
	private JButton jbtAddRow = new JButton("添加");

	public int[] SetTable() {
		return total;
	}

	public ModifyTable(String uname, int n) {

		if (n != 1) {
			username = uname;
			try {
				Workbook book = Workbook.getWorkbook(new File(username + ".xls"));
				try {
					Sheet sheet1 = book.getSheet(0);
					String xianjin = sheet1.getCell(12, 0).getContents();
					dinglanxianjin = Double.parseDouble(xianjin);
					for (int i = 0; i < book.getNumberOfSheets(); i++) {
						String search = book.getSheet(i).getName();
						Sheet sheet = book.getSheet(search);

						String number = sheet.getCell(1, 1).getContents();
						int amountru = 0;
						int amountchu = 0;
						int amount = 0;
						Double riyingkui = 0.0;
						Double chengben = 0.0;
						Double shouru = 0.0;
						try {
							URL gis = new URL("http://hq.sinajs.cn/list="
									+ number);// 你要报错的网页
							BufferedReader in = new BufferedReader(
									new InputStreamReader(gis.openStream()));
							String line = "";
							String[] data;
							while ((line = in.readLine()) != null) {
								data = line.split(",");
								String nowprice = data[3];
								Double zhangdie1 = Double.parseDouble(nowprice)
										- Double.parseDouble(data[2]);
								String zhangdie = String.valueOf(zhangdie1);
								int recordCount = Integer.parseInt(sheet.getCell(11, 0).getContents());

								if (recordCount > 0) {
									for (int j = 0; j < recordCount; j++) {
										if (sheet.getCell(3, j + 1).getContents().equals("买入")||
												sheet.getCell(3, j + 1).getContents().equals("补仓")) {
											
											int num = Integer.parseInt(sheet.getCell(5, j + 1).getContents());
											
											amountru = amountru + num;
											chengben = chengben+ (num * Double.parseDouble(sheet.getCell(4, j + 1).getContents()))* 1.0013;
										}
										if (sheet.getCell(3, j + 1).getContents().equals("卖出") || sheet.getCell(3, j + 1)
														.getContents()
														.equals("卖空")) {
											int num = Integer.parseInt(sheet
													.getCell(5, j + 1)
													.getContents());
											amountchu = amountchu + num;
											shouru = shouru+ (num * Double.parseDouble(sheet
																	.getCell(
																			4,
																			j + 1)
																	.getContents()))
													* (1 - 0.0013);
										}
									}
									amount = amountru - amountchu;
//									System.out.println(amount);
//									System.out.println(HoldingAmount.holdingAmountCalculation("16-1-10", "工商银行", book));
									total[i] = amount;

									Double chiyoushizhi = amount
											* Double.parseDouble(nowprice);

									riyingkui = amount * zhangdie1;// ////////////////////////////////////////////////////////////////////////////
									dinglanriyingkui = dinglanriyingkui
											+ riyingkui;
									String chiyouliang = String.valueOf(amount);
									String chiyoushizhistr = String
											.valueOf(chiyoushizhi);// ///持有股票数*当前价格

									Double yingkui = chiyoushizhi + shouru
											- chengben;
									dinglanyingkui = dinglanyingkui + yingkui;
									dinglanshizhi += chiyoushizhi;
									String yingkuistr = String.valueOf(yingkui);
									String[] row = {
											book.getSheet(i).getName(),
											nowprice, zhangdie, chiyouliang,
											chiyoushizhistr, yingkuistr, "买卖" };// /////////////////////netnetnetn
									tableModel.addRow(row);
								} else {
									String[] row = {
											book.getSheet(i).getName(),
											nowprice, zhangdie, "", "", "",
											"买卖" };
									tableModel.addRow(row);
								}
							}
							in.close();
							// pw.close();
						} catch (Exception er) {
							System.out.println(er);
						}
					}
				} finally {
					if (book != null) {
						book.close();
					}
				}
			} catch (BiffException e) {
				System.err.println(e + "");
			} catch (IOException e) {
				System.err.println(e + "文件读取错误");
			}

			TableColumn OpColumn = jTable1.getColumn("操作");
			DefaultTableCellRenderer fontColor = new DefaultTableCellRenderer();
			fontColor.setForeground(Color.blue);
			OpColumn.setCellRenderer(fontColor);
			this.setLayout(new BorderLayout());
			JPanel panel1 = new JPanel();
			panel1.add(add);
			panel1.add(bianhao);
			panel1.add(jbtAddRow);

			JPanel panel6 = new JPanel();
			panel6.setLayout(new BorderLayout());
			panel6.add(panel1, BorderLayout.SOUTH);

			add(new JScrollPane(jTable1), BorderLayout.CENTER);
			add(panel1, BorderLayout.SOUTH);

			jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			jbtAddRow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Addnewstock addns = new Addnewstock();
					String bianhaostr = bianhao.getText();
					String stockname = addns.Searchstock(bianhaostr);

					if (!stockname.equals("")) {
						String nowprice = addns.data[3];
						Double zhangdie1 = Double.parseDouble(nowprice)
								- Double.parseDouble(addns.data[2]);
						String zhangdie = String.valueOf(zhangdie1);
						String[] row = { stockname, nowprice, zhangdie, "", "",
								"", "", "", "买卖" };// /////////////////////netnetnetn
						tableModel.addRow(row);
						addns.Addsheet(username);
						bianhao.setText("");
					}

					else {
						JOptionPane.showMessageDialog(null, "该股票不存在", "",
								JOptionPane.INFORMATION_MESSAGE);
						bianhao.setText("");
					}

				}
			});

		} else {
			System.out.print("这个用户没用记录");// ///////////////////////////////////////////////////////////////////////////////////////////
			username = uname;
			this.setLayout(new BorderLayout());
			JPanel panel1 = new JPanel();
			panel1.add(jbtAddRow);
			JPanel panel6 = new JPanel();
			panel6.setLayout(new BorderLayout());
			panel6.add(panel1, BorderLayout.SOUTH);
			add(new JScrollPane(jTable1), BorderLayout.CENTER);
			add(panel1, BorderLayout.SOUTH);
			jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			jbtAddRow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (jTable1.getSelectedRow() >= 0)
						tableModel.addRow(new java.util.Vector());
					else
						tableModel.addRow(new java.util.Vector());
				}

			});
		}

	}

}
