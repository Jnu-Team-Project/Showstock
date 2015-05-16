package com.jnu.stock.util;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.jnu.stock.Catchgra;
import com.jnu.stock.Chigugouchen;
import com.jnu.stock.Dealdialog;
import com.jnu.stock.ModifyTable;
import com.jnu.stock.Shouyilv;
import com.jnu.stock.ShowStock;
import com.jnu.stock.StockInfoLayout;

/**
 * createTable和updateTable
 * 
 * @author Administrator
 *
 */
public class TableUtil {
	
	static Logger logger = Logger.getLogger(TableUtil.class.getName());
	
	static JTabbedPane jtpFigures = new JTabbedPane();
	static StockInfoLayout stockInfoLayout = new StockInfoLayout();
	
	public static void updatetable(final ShowStock showStcok, final String username,final int news){
		
		Shouyilv j3 = new Shouyilv(690, 360, username);
		Chigugouchen j4 = new Chigugouchen(690, 360, username);
		jtpFigures.removeAll();
		stockInfoLayout.removeAll();
		showStcok.remove(stockInfoLayout);
		jtpFigures.setBounds(0, 45, 690, 400);
		stockInfoLayout.setBounds(190, 6, 510, 42);
	
		final ModifyTable applet2 = new ModifyTable(username, news);
		
		stockInfoLayout.setRyke1(new JLabel(String.valueOf(applet2.getDinglanriyingkui())));
		stockInfoLayout.setYk1(new JLabel(String.valueOf(applet2.getDinglanyingkui())));
		stockInfoLayout.createStockInfoLayout();
		
		showStcok.add(stockInfoLayout);
		jtpFigures.add(applet2, "持仓盈亏");
		jtpFigures.add(j3, "收益率");
		jtpFigures.add(j4, "持股构成");
		PropertyConfigurator.configure("src//log4j.properties");
		applet2.getjTable1().addMouseListener(new MouseAdapter() 
				{
					public void mouseClicked(MouseEvent e) {
						int r = applet2.getjTable1().getSelectedRow();
						int c = applet2.getjTable1().getSelectedColumn();

						final JFrame framejioayi = new JFrame("增加交易");
						if (c == 8) {
							final Dealdialog dlog = new Dealdialog();
							
							dlog.setDealdialogusername(applet2.getUsername());
							dlog.getOkButton().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {

									String datestr = dlog.getDateTextField().getText();
									String styletr = dlog.getStyleTextField().getText();
									String pricestr = dlog.getPriceTextField().getText();
									String amountstr = dlog.getAmountTextField().getText();
									String ratestr = dlog.getRateTextField().getText();
									String yongjinstr = dlog.getYongjinTextField().getText();

									Workbook wb;
									try {
										wb = Workbook.getWorkbook(new File(
												dlog.getDealdialogusername() + ".xls"));
										WritableWorkbook book;
										try {
											book = Workbook
													.createWorkbook(
															new File(
																	dlog.getDealdialogusername() + ".xls"),
															wb);
											WritableSheet sheet2 = book
													.getSheet(dlog.getDealdialogname());

											int m = Integer.parseInt(sheet2
													.getCell(11, 0)
													.getContents()) + 1;

											Label l;
											l = new Label(2, m, datestr);
											sheet2.addCell(l);

											l = new Label(3, m, styletr);
											sheet2.addCell(l);

											l = new Label(4, m, pricestr);
											sheet2.addCell(l);

											l = new Label(5, m, amountstr);
											sheet2.addCell(l);

											l = new Label(6, m, ratestr);
											sheet2.addCell(l);

											l = new Label(7, m, ratestr);
											sheet2.addCell(l);

											l = new Label(11, 0, String
													.valueOf(m));
											sheet2.addCell(l);

											book.write();
											book.close();
											framejioayi.dispose();
											TableUtil.updatetable(showStcok, username, news);
										} catch (IOException e1) {
											e1.printStackTrace();
										} catch (RowsExceededException e1) {
											e1.printStackTrace();
										} catch (WriteException e1) {
											e1.printStackTrace();
										}
										wb.close();
									}
									catch (BiffException e1) {
										e1.printStackTrace();
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
							});
							
							framejioayi.add(dlog);
							dlog.setDealdialogname(String.valueOf(applet2.getjTable1().getValueAt(r, 0)));
							framejioayi.setSize(680, 110);
							framejioayi.setLocationRelativeTo(null);
							framejioayi.setVisible(true);
							framejioayi.setResizable(false);
						}
						String number = "";
						if (e.getClickCount() == 2 && c != 8) {
							File file = new File(applet2.getUsername() + ".xls");
							String[] columnNames1 = { "日期", "类型", "价格", "数量",
									"税率", "佣金" };
							Object[][] rowData1 = {};
							final DefaultTableModel tableModel1 = new DefaultTableModel(rowData1, columnNames1) {
								@Override
								public boolean isCellEditable(int row,
										int column) {
									return false;
								}
							};
							String search = String.valueOf(applet2.getjTable1().getValueAt(r, 0));
							StringBuffer sb = new StringBuffer();
							try {
								Workbook book = Workbook.getWorkbook(file);
								Sheet sheet = book.getSheet(search);

								number = sheet.getCell(1, 1).getContents();

								try {
									int m = Integer.parseInt(sheet.getCell(11,
											0).getContents());
									for (int i = 0; i < m; i++) {
										String[] row = {
												sheet.getCell(2, i + 1)
														.getContents(),
												sheet.getCell(3, i + 1)
														.getContents(),
												sheet.getCell(4, i + 1)
														.getContents(),
												sheet.getCell(5, i + 1)
														.getContents(),
												sheet.getCell(6, i + 1)
														.getContents(),
												sheet.getCell(7, i + 1)
														.getContents(), };
										tableModel1.addRow(row);
									}
								} finally {
									if (book != null) {
										book.close();
									}
								}
							} catch (BiffException er) {
								logger.error(er);
								System.err.println("");
							} catch (IOException er) {
								logger.error(er); 
								System.err.println("文件读取错误");
							}
							final JTable jTable1 = new JTable(tableModel1);

							final JFrame frame = new JFrame(String.valueOf(applet2.getjTable1().getValueAt(r, 0)));
							JLabel title = new JLabel();
							JPanel jpl = new JPanel();
							JPanel jpr = new JPanel();
							JPanel jps = new JPanel();
							frame.add(jpl);
							jpl.setBounds(5, 50, 170, 220);
							jpl.setLayout(new GridLayout(9, 2));

							try {
								URL gis = new URL("http://hq.sinajs.cn/list=" + number);// 你要报错的网页
								BufferedReader in = new BufferedReader(
										new InputStreamReader(gis.openStream()));
							
								String line = "";
								String[] data;
								
								while ((line = in.readLine()) != null) {
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
							} catch (Exception er) {
								System.out.println(er);
							}
							final JTabbedPane jtpFigures = new JTabbedPane();
							Catchgra fenshi = new Catchgra();
							final Catchgra rik = new Catchgra();
							Catchgra zhouk = new Catchgra();
							Catchgra yuekp = new Catchgra();
							try {
								fenshi.Setimg("http://image.sinajs.cn/newchart/min/n/" + number + ".gif");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							try {
								rik.Setimg("http://image.sinajs.cn/newchart/daily/n/" + number + ".gif");
							} catch (IOException e1) {
								e1.printStackTrace();
							}

							try {
								zhouk.Setimg("http://image.sinajs.cn/newchart/weekly/n/" + number + ".gif");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							try {
								yuekp.Setimg("http://image.sinajs.cn/newchart/monthly/n/" + number + ".gif");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							jtpFigures.add(fenshi, "分时");
							jtpFigures.add(rik, "日K");
							jtpFigures.add(zhouk, "周K");
							jtpFigures.add(yuekp, "月K");

							jtpFigures.addChangeListener(new ChangeListener() {
								public void stateChanged(ChangeEvent e) {
									JTabbedPane tabbedPane = (JTabbedPane) e
											.getSource();
									int selectedIndex = tabbedPane
											.getSelectedIndex();
									switch (selectedIndex) {
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
	
	public static void createTable(final ShowStock showStcok, final String username,final int news) {
		showStcok.add(jtpFigures);
		jtpFigures.setBounds(0, 45, 690, 400);
		stockInfoLayout.setBounds(190, 6, 510, 42);
		Shouyilv j3 = new Shouyilv(690, 360, username);
		Chigugouchen j4 = new Chigugouchen(690, 360, username);
		final ModifyTable applet2 = new ModifyTable(username, news);
		stockInfoLayout.setRyke1(new JLabel(String.valueOf(applet2.getDinglanriyingkui())));
		stockInfoLayout.setYk1(new JLabel(String.valueOf(applet2.getDinglanyingkui())));
		stockInfoLayout.createStockInfoLayout();
		showStcok.add(stockInfoLayout);
		jtpFigures.add(applet2, "持仓盈亏");
		jtpFigures.add(j3, "收益率");
		jtpFigures.add(j4, "持股构成");
		PropertyConfigurator.configure("src//log4j.properties");
		applet2.getjTable1().addMouseListener(new MouseAdapter() 
				{
					public void mouseClicked(MouseEvent e) {
						int r = applet2.getjTable1().getSelectedRow();
						int c = applet2.getjTable1().getSelectedColumn();

						final JFrame framejioayi = new JFrame("增加交易");
						if (c == 8) {
							final Dealdialog dlog = new Dealdialog();
							dlog.setDealdialogusername(applet2.getUsername());
							framejioayi.add(dlog);
							dlog.setDealdialogname(String.valueOf(applet2.getjTable1().getValueAt(r, 0)));
							dlog.getOkButton().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									framejioayi.dispose();
									String datestr = dlog.getDateTextField().getText();
									String styletr = dlog.getStyleTextField().getText();
									String pricestr = dlog.getPriceTextField().getText();
									String amountstr = dlog.getAmountTextField().getText();
									String ratestr = dlog.getRateTextField().getText();
									String yongjinstr = dlog.getYongjinTextField().getText();

									Workbook wb;
									try {
										wb = Workbook.getWorkbook(new File(
												dlog.getDealdialogusername() + ".xls"));
										WritableWorkbook book;
										try {
											book = Workbook.createWorkbook(new File(dlog.getDealdialogusername() + ".xls"),wb);
											
											WritableSheet sheet2 = book.getSheet(dlog.getDealdialogname());
											int m = Integer.parseInt(sheet2
													.getCell(11, 0)
													.getContents()) + 1;
											Label l;
											l = new Label(2, m, datestr);
											sheet2.addCell(l);

											l = new Label(3, m, styletr);
											sheet2.addCell(l);

											l = new Label(4, m, pricestr);
											sheet2.addCell(l);

											l = new Label(5, m, amountstr);
											sheet2.addCell(l);

											l = new Label(6, m, ratestr);
											sheet2.addCell(l);

											l = new Label(7, m, yongjinstr);
											sheet2.addCell(l);

											l = new Label(11, 0, String
													.valueOf(m));
											sheet2.addCell(l);

											book.write();
											book.close();

											TableUtil.updatetable(showStcok, username, news);

										} catch (IOException e1) {
											e1.printStackTrace();
										} catch (RowsExceededException e1) {
											e1.printStackTrace();
										} catch (WriteException e1) {
											e1.printStackTrace();
										}
										wb.close();
									}
									catch (BiffException e1) {
										e1.printStackTrace();
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
							});
							framejioayi.setSize(680, 110);
							framejioayi.setLocationRelativeTo(null);
							framejioayi.setVisible(true);
							framejioayi.setResizable(false);
						}
						System.out.print(c);
						String number = "";
						if (e.getClickCount() == 2 && c != 8) {
							File file = new File(applet2.getUsername() + ".xls");
							String[] columnNames1 = { "日期", "类型", "价格", "数量",
									"税率", "佣金" };
							Object[][] rowData1 = {};
							final DefaultTableModel tableModel1 = new DefaultTableModel(rowData1, columnNames1) {
								@Override
								public boolean isCellEditable(int row,
										int column) {
									return false;
								}
							};
							String search = String.valueOf(applet2.getjTable1().getValueAt(r, 0));
							
							StringBuffer sb = new StringBuffer();
							try {
								Workbook book = Workbook.getWorkbook(file);
								Sheet sheet = book.getSheet(search);

								number = sheet.getCell(1, 1).getContents();

								try {
									int m = Integer.parseInt(sheet.getCell(11,
											0).getContents());
									for (int i = 0; i < m; i++) {
										String[] row = {
												sheet.getCell(2, i + 1)
														.getContents(),
												sheet.getCell(3, i + 1)
														.getContents(),
												sheet.getCell(4, i + 1)
														.getContents(),
												sheet.getCell(5, i + 1)
														.getContents(),
												sheet.getCell(6, i + 1)
														.getContents(),
												sheet.getCell(7, i + 1)
														.getContents(), };
										tableModel1.addRow(row);
									}
								} finally {
									if (book != null) {
										book.close();
									}
								}
							} catch (BiffException er) {
								logger.error(er);
								System.err.println("");
							} catch (IOException er) {
								logger.error(er);
								System.err.println("文件读取错误");
							}
							
							final JTable jTable1 = new JTable(tableModel1);

							final JFrame frame = new JFrame(String
									.valueOf(applet2.getjTable1().getValueAt(r, 0)));
							JLabel title = new JLabel();
							JPanel jpl = new JPanel();
							JPanel jpr = new JPanel();
							JPanel jps = new JPanel();
							frame.add(jpl);
							jpl.setBounds(5, 50, 170, 220);
							jpl.setLayout(new GridLayout(9, 2));

							try {
								URL gis = new URL("http://hq.sinajs.cn/list="
										+ number);// 你要报错的网页
								BufferedReader in = new BufferedReader(
										new InputStreamReader(gis.openStream()));
								String line = "";
								String[] data;
								// System.out.print(number);
								while ((line = in.readLine()) != null) {
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
							} catch (Exception er) {
								System.out.println(er);
							}
							final JTabbedPane jtpFigures = new JTabbedPane();
							Catchgra fenshi = new Catchgra();
							final Catchgra rik = new Catchgra();
							Catchgra zhouk = new Catchgra();
							Catchgra yuekp = new Catchgra();
							try {
								fenshi.Setimg("http://image.sinajs.cn/newchart/min/n/"
										+ number + ".gif");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							try {
								rik.Setimg("http://image.sinajs.cn/newchart/daily/n/"
										+ number + ".gif");
							} catch (IOException e1) {
								e1.printStackTrace();
							}

							try {
								zhouk.Setimg("http://image.sinajs.cn/newchart/weekly/n/"
										+ number + ".gif");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								yuekp.Setimg("http://image.sinajs.cn/newchart/monthly/n/"
										+ number + ".gif");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							jtpFigures.add(fenshi, "分时");
							jtpFigures.add(rik, "日K");
							jtpFigures.add(zhouk, "周K");
							jtpFigures.add(yuekp, "月K");
							jtpFigures.addChangeListener(new ChangeListener() {
								public void stateChanged(ChangeEvent e) {
									JTabbedPane tabbedPane = (JTabbedPane) e
											.getSource();
									int selectedIndex = tabbedPane
											.getSelectedIndex();
									switch (selectedIndex) {
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
}
