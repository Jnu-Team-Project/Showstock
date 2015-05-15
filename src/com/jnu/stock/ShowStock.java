package com.jnu.stock;
//Showstock.java
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JApplet;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
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
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.theme.SubstanceTerracottaTheme;

public class ShowStock extends JApplet {
	
	static String username;
	int news;
	JMenuBar jmb = new JMenuBar();
	JMenu toolMenu = new JMenu("����");
	JMenu update = new JMenu("ˢ��");
	JMenu helpMenu = new JMenu("����");
	JMenuItem importDataItem, exportDateItem, helpItem, aboutItem, refreshItem;

	JTabbedPane jtpFigures = new JTabbedPane();
	StockInfoLayout stockInfo = new StockInfoLayout();

	/**
	 * ��¼������������Ͻǵı�ǩΪ�û���
	 * @param userName �û���
	 */
	public void replaceUsername(String userName) {
		JLabel username = new JLabel(userName);
		username.setFont(new Font("", 1, 30));
		add(username);
		username.setBounds(10, 10, 120, 30);
	}

	ModifyTable applet2;

	public ShowStock() {
		PropertyConfigurator.configure("src//log4j.properties");
		jmb.add(toolMenu);
		jmb.add(helpMenu);
		importDataItem = new JMenuItem("��������");
		exportDateItem = new JMenuItem("��������");
		refreshItem = new JMenuItem("ˢ��");
		helpItem = new JMenuItem("����");
		aboutItem = new JMenuItem("����");

		/*
		 * update.add(shuaxin1); update.add(shuaxin2); update.add(shuaxin3);
		 */

		toolMenu.add(importDataItem);
		toolMenu.add(exportDateItem);
		toolMenu.add(refreshItem);

		helpMenu.add(helpItem);
		helpMenu.add(aboutItem);
		this.setJMenuBar(jmb);
		this.setLayout(null);

		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null,
						"�汾 1.0\n��Ȩ���� �Ŷ���Ŀ1�鱣������Ȩ��", "���ڡ�Ǭ�����С�",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		refreshItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Updatetable();// ======================5.12
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
					Createtable();

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
					copy.copyFile(username + ".xls", fDialog.getSelectedFile()
							.toString() + "//" + username + ".xls");
					System.out.println(fDialog.getSelectedFile());
				}
			}
		});
	}

	public void Createtable() {
		add(jtpFigures);
		jtpFigures.setBounds(0, 45, 690, 400);
		stockInfo.setBounds(190, 6, 510, 42);
		Shouyilv j3 = new Shouyilv(690, 360, username);
		Chigugouchen j4 = new Chigugouchen(690, 360, username);
		applet2 = new ModifyTable(username, news);
		stockInfo.ryke1 = new JLabel(String.valueOf(applet2.dinglanriyingkui));
		stockInfo.yk1 = new JLabel(String.valueOf(applet2.dinglanyingkui));
		stockInfo.createStockInfoLayout();
		add(stockInfo);
		jtpFigures.add(applet2, "�ֲ�ӯ��");
		jtpFigures.add(j3, "������");
		jtpFigures.add(j4, "�ֹɹ���");
		PropertyConfigurator.configure("src//log4j.properties");// ================================
		applet2.jTable1.addMouseListener(new MouseAdapter() // ////////////////////////////////��������ӵ������ʱ����frame,��Ҫ�õ�username,����username�ڵ�¼ʱ����get��
				{
					public void mouseClicked(MouseEvent e) {
						int r = applet2.jTable1.getSelectedRow();
						int c = applet2.jTable1.getSelectedColumn();

						final JFrame framejioayi = new JFrame("���ӽ���");
						if (c == 8) {
							final Dealdialog dlog = new Dealdialog();
							dlog.dealdialogusername = applet2.username;// //////////////////////////////////////////////2015.5.3
							framejioayi.add(dlog);
							dlog.dealdialogname = String
									.valueOf(applet2.jTable1.getValueAt(r, 0));
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
									try {
										wb = Workbook.getWorkbook(new File(
												dlog.dealdialogusername
														+ ".xls"));
										WritableWorkbook book;
										try {
											book = Workbook
													.createWorkbook(
															new File(
																	dlog.dealdialogusername
																			+ ".xls"),
															wb);
											WritableSheet sheet2 = book
													.getSheet(dlog.dealdialogname);
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

											Updatetable();

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
							File file = new File(applet2.username + ".xls");
							String[] columnNames1 = { "����", "����", "�۸�", "����",
									"˰��", "Ӷ��" };
							Object[][] rowData1 = {};
							final DefaultTableModel tableModel1 = new DefaultTableModel(rowData1, columnNames1) {
								@Override
								public boolean isCellEditable(int row,
										int column) {
									return false;
								}
							};
							String search = String.valueOf(applet2.jTable1
									.getValueAt(r, 0));
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
								System.err.println("�ļ���ȡ����");
							}
							
							final JTable jTable1 = new JTable(tableModel1);

							final JFrame frame = new JFrame(String
									.valueOf(applet2.jTable1.getValueAt(r, 0)));
							JLabel title = new JLabel();
							JPanel jpl = new JPanel();
							JPanel jpr = new JPanel();
							JPanel jps = new JPanel();
							frame.add(jpl);
							jpl.setBounds(5, 50, 170, 220);
							jpl.setLayout(new GridLayout(9, 2));

							try {
								URL gis = new URL("http://hq.sinajs.cn/list="
										+ number);// ��Ҫ�������ҳ
								BufferedReader in = new BufferedReader(
										new InputStreamReader(gis.openStream()));
								String line = "";
								String[] data;
								// System.out.print(number);
								while ((line = in.readLine()) != null) {
									System.out.println(line);
									data = line.split(",");
									JLabel jl1 = new JLabel("���տ��̼� ");
									JLabel jl11 = new JLabel(data[1]);

									JLabel jl2 = new JLabel("�������̼� ");
									JLabel jl3 = new JLabel("��ǰ�۸� ");
									JLabel jl4 = new JLabel("������߼� ");
									JLabel jl5 = new JLabel("������ͼ� ");
									JLabel jl6 = new JLabel("����� ");
									JLabel jl7 = new JLabel("������ ");
									JLabel jl8 = new JLabel("�ɽ��Ĺ�Ʊ�� ");
									JLabel jl9 = new JLabel("�ɽ���� ");

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
							jtpFigures.add(fenshi, "��ʱ");
							jtpFigures.add(rik, "��K");
							jtpFigures.add(zhouk, "��K");
							jtpFigures.add(yuekp, "��K");
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

	public void Updatetable()// =======================================================
	{
		Shouyilv j3 = new Shouyilv(690, 360, username);
		Chigugouchen j4 = new Chigugouchen(690, 360, username);
		jtpFigures.removeAll();
		stockInfo.removeAll();
		this.remove(stockInfo);
		// add(jtpFigures);
		jtpFigures.setBounds(0, 45, 690, 400);
		stockInfo.setBounds(190, 6, 510, 42);
	
		applet2 = new ModifyTable(username, news);
		stockInfo.ryke1 = new JLabel(String.valueOf(applet2.dinglanriyingkui));
		stockInfo.yk1 = new JLabel(String.valueOf(applet2.dinglanyingkui));
		stockInfo.createStockInfoLayout();
		add(stockInfo);
		// j3.cggcusername=username;
		// j4.cggcusername=username;
		jtpFigures.add(applet2, "�ֲ�ӯ��");
		jtpFigures.add(j3, "������");
		jtpFigures.add(j4, "�ֹɹ���");
		PropertyConfigurator.configure("src//log4j.properties");// ================================
		applet2.jTable1.addMouseListener(new MouseAdapter() // ////////////////////////////////��������ӵ������ʱ����frame,��Ҫ�õ�username,����username�ڵ�¼ʱ����get��
				{
					public void mouseClicked(MouseEvent e) {
						int r = applet2.jTable1.getSelectedRow();
						int c = applet2.jTable1.getSelectedColumn();

						final JFrame framejioayi = new JFrame("���ӽ���");
						if (c == 8) {
							final Dealdialog dlog = new Dealdialog();
							dlog.dealdialogusername = applet2.username;// //////////////////////////////////////////////2015.5.3
							dlog.ok.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {

									String datestr = dlog.date1.getText();
									String styletr = dlog.style1.getText();
									String pricestr = dlog.price1.getText();
									String amountstr = dlog.amount1.getText();
									String ratestr = dlog.rate1.getText();
									String yongjinstr = dlog.yongjin1.getText();

									Workbook wb;
									try {
										wb = Workbook.getWorkbook(new File(
												dlog.dealdialogusername
														+ ".xls"));
										WritableWorkbook book;
										try {
											book = Workbook
													.createWorkbook(
															new File(
																	dlog.dealdialogusername
																			+ ".xls"),
															wb);
											WritableSheet sheet2 = book
													.getSheet(dlog.dealdialogname);

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
										// state = 1;
									}

									catch (BiffException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									// st.Updatetable();
								}
							});
							framejioayi.add(dlog);
							dlog.dealdialogname = String
									.valueOf(applet2.jTable1.getValueAt(r, 0));
							// System.out.print("uuuutttt"+dlog.dealdialogname+"------");
							framejioayi.setSize(680, 110);
							framejioayi.setLocationRelativeTo(null);
							framejioayi.setVisible(true);
							framejioayi.setResizable(false);
							// ------------------------------------------------

						}
						// System.out.print(r+"    ");
						// System.out.print(c);
						String number = "";
						if (e.getClickCount() == 2 && c != 8) {
							File file = new File(applet2.username + ".xls");
							String[] columnNames1 = { "����", "����", "�۸�", "����",
									"˰��", "Ӷ��" };
							Object[][] rowData1 = {};
							// System.out.print("kaka "+applet2.username+" ddddddd-----------");///////////////////////////////////////////////
							final DefaultTableModel tableModel1 = new DefaultTableModel(
									rowData1, columnNames1) {
								@Override
								public boolean isCellEditable(int row,
										int column) {
									return false;
								}
							};
							String search = String.valueOf(applet2.jTable1
									.getValueAt(r, 0));
							StringBuffer sb = new StringBuffer();
							try {
								Workbook book = Workbook.getWorkbook(file);
								Sheet sheet = book.getSheet(search);

								number = sheet.getCell(1, 1).getContents();

								try {
									// System.out.print(sheet.getName()+" uuuuuu");
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
									// System.out.print("kaka "+applet2.username+" zzzzz");
								} finally {
									if (book != null) {
										book.close();
									}
								}
							} catch (BiffException er) {
								logger.error(er); // ===================
								System.err.println("");
							} catch (IOException er) {
								logger.error(er); // ===================
								System.err.println("�ļ���ȡ����");
							}
							// System.out.print("jaja");
							final JTable jTable1 = new JTable(tableModel1);

							final JFrame frame = new JFrame(String
									.valueOf(applet2.jTable1.getValueAt(r, 0)));
							JLabel title = new JLabel();// /////////////////////
							JPanel jpl = new JPanel();
							JPanel jpr = new JPanel();
							JPanel jps = new JPanel();
							frame.add(jpl);
							jpl.setBounds(5, 50, 170, 220);
							jpl.setLayout(new GridLayout(9, 2));

							try {
								URL gis = new URL("http://hq.sinajs.cn/list="
										+ number);// ��Ҫ�������ҳ
								BufferedReader in = new BufferedReader(
										new InputStreamReader(gis.openStream()));
								// PrintWriter pw=new PrintWriter( new
								// FileOutputStream("api.htm"));//�����·��
								String line = "";
								String[] data;
								// System.out.print(number);
								while ((line = in.readLine()) != null) {
									System.out.println(line);
									data = line.split(",");
									JLabel jl1 = new JLabel("���տ��̼� ");
									JLabel jl11 = new JLabel(data[1]);

									JLabel jl2 = new JLabel("�������̼� ");
									JLabel jl3 = new JLabel("��ǰ�۸� ");
									JLabel jl4 = new JLabel("������߼� ");
									JLabel jl5 = new JLabel("������ͼ� ");
									JLabel jl6 = new JLabel("����� ");
									JLabel jl7 = new JLabel("������ ");
									JLabel jl8 = new JLabel("�ɽ��Ĺ�Ʊ�� ");
									JLabel jl9 = new JLabel("�ɽ���� ");

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
								// pw.close();
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
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								rik.Setimg("http://image.sinajs.cn/newchart/daily/n/"
										+ number + ".gif");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
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
							jtpFigures.add(fenshi, "��ʱ");
							jtpFigures.add(rik, "��K");
							jtpFigures.add(zhouk, "��K");

							jtpFigures.add(yuekp, "��K");

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

	static Logger logger = Logger.getLogger(ShowStock.class.getName());

	/**
	 * ������main
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure("src//log4j.properties"); 
		
		//��������
		try {
			UIManager.setLookAndFeel(new SubstanceLookAndFeel());
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			SubstanceLookAndFeel
					.setCurrentTheme(new SubstanceTerracottaTheme());
			logger.info("���������ɹ�");
		} catch (Exception e) {
			logger.error("Info ..."); 
			System.err.println("Something went wrong!");
		}
		
		//ʵ����һ��JFrame����ʼ��
		final JFrame frame = new JFrame("Ǭ������");
		frame.setDefaultCloseOperation(0);
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int selected = JOptionPane.showConfirmDialog(frame, "�Ƿ��˳�", "",
						JOptionPane.YES_NO_OPTION);
				if (JOptionPane.OK_OPTION == selected) {
					System.exit(0);
				}
			}
		});

		// Create an instance of the applet
		final ShowStock applet = new ShowStock();
		final JPanel jp = new JPanel();
		final CardLayout cardLayout = new CardLayout();
		jp.setLayout(cardLayout);

		frame.add(jp);

		final RegisterPanel registerPanel = new RegisterPanel(750, 500);
		final LoginPanel loginPanel = new LoginPanel(750, 500, "background1.jpg");

		//ע���û�����¼����ҳ��
		final DataImportPanel dataImportPanel = new DataImportPanel(750, 500);
		jp.add(loginPanel, "1");
		jp.add(registerPanel, "2");
		jp.add(dataImportPanel, "3");
		
		cardLayout.show(jp, "1");
		
		//Ϊ��¼��ť�����Ӧ
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
						input = new BufferedReader(new FileReader("user.txt")); // ��ȡ��
						input1 = new BufferedReader(new FileReader("password.txt")); // ��ȡ��
						
						while ((txtUserName = input.readLine()) != null && (txtPassword = input1.readLine()) != null) { // �ж��Ƿ���������һ��
							if (txtUserName.equals(loginUserName) && txtPassword.equals(loginPassword)) {
								jp.setVisible(false);
								applet.replaceUsername(loginUserName);
								applet.username = loginUserName;
								applet.init();
								applet.Createtable();
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
				
				//�������������ַ��������ļ���д��ע���û�����Ϣ
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
					applet.replaceUsername(user);
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
				applet.Createtable();
				frame.add(applet);

				applet.show();
			}
		});
		
		dataImportPanel.daoru.addActionListener(new ActionListener() {
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

					// ��ӡ���ļ���·����������޸�λ ��·��ֵ д�� textField��
					// System.out.println(fDialog.getSelectedFile());
					String name = fDialog.getSelectedFile().toString();
					Copy copy = new Copy();
					copy.copyFile(name, username + ".xls");
					applet.news = 0;
					applet.Createtable();

					jp.setVisible(false);

					frame.add(applet);
					applet.show();
				}
			}

		});
		
		class MouseAdp implements MouseListener {
			public MouseAdp() {
			}

			public void mouseClicked(MouseEvent e) {
				cardLayout.show(jp, "2");
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
		}
		//Ϊ�������û�����ǩ��ӵ���¼�
		loginPanel.register1.addMouseListener(new MouseAdp());
	}
}
