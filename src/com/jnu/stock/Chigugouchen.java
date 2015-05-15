package com.jnu.stock;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

public class Chigugouchen extends JPanel {
	static DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	String cggcusername = "";
	int width, hight;

	public Chigugouchen(int w, int h, String username) {
		this.width = w;
		this.hight = h;
		JFreeChart chart = ChartFactory.createStackedAreaChart("持股构成", // 图表标题
				"时间", // 目录轴的显示标签--横轴
				"", // 数值轴的显示标签--纵轴
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、
				true, // 是否显示图例(对于简单的柱状图必须
				false, // 是否生成工具
				false // 是否生成URL链接
				);
		// 3. 处理chart中文显示问题
		cggcusername = username;
		processChart(chart);
		Calculate("2015/03/14");
		// 4. chart输出图片
		writeChartAsImage(chart);

	}

	public void Calculate(String enddate)// date:2015/*/*
	{
		String[] sdate = enddate.split("/");
		Gethistorypri cat = new Gethistorypri();// 5.12
		int count = Integer.parseInt(sdate[2]) - 5 + 1;
		try {
			// System.out.print(cggcusername+"haha");////////////////////////////////////////////////////////////////////////////////////////////
			Workbook book = Workbook
					.getWorkbook(new File(cggcusername + ".xls"));

			for (int i = 0; i < book.getNumberOfSheets(); i++)// 第一次循环，遍历所有股票
			{
				// System.out.print("########1");//77777777777777777777777
				String search = book.getSheet(i).getName();
				Sheet sheet = book.getSheet(search);

				String number = sheet.getCell(1, 1).getContents();
				int amountru = 0;
				int amountchu = 0;
				int amount = 0;
				// System.out.print("########2");//77777777777777777777777
				Double chengben = 0.0;
				Double shouru = 0.0;
				int m = Integer.parseInt(sheet.getCell(11, 0).getContents());

				if (m > 0) {
					for (int d = 0; d < count; d++) // 第二层循环，遍历所有日期
					{
						String edate;
						int dd = 5 + d;
						if (dd > 9)
							edate = "2015/3/" + String.valueOf(dd);
						else
							edate = "2015/3/" + String.valueOf(dd);
						String price = cat.Getprice(number, edate);// ------------------------可替换成本地获取
						// String price =
						// "5.5";//------------------------可替换成本地获取
						for (int j = 0; j < m; j++) {

							String date = sheet.getCell(2, j + 1).getContents();
							String[] date1 = date.split("-");
							int idate = Integer.parseInt(date1[2]);
							if (idate == dd)// 如果是这个时间
							{
								if (sheet.getCell(3, j + 1).getContents()
										.equals("买入")) {
									int num = Integer.parseInt(sheet.getCell(5,
											j + 1).getContents());
									amountru = amountru + num;
									chengben = chengben
											+ (num * Double.parseDouble(sheet
													.getCell(4, j + 1)
													.getContents())) * 1.0013;
								}
								// System.out.print("########" + number);
								if (sheet.getCell(3, j + 1).getContents()
										.equals("卖出")) {
									// amountchu = amountchu +
									// Integer.parseInt(sheet.getCell(5,j+1).getContents());
									int num = Integer.parseInt(sheet.getCell(5,
											j + 1).getContents());
									amountchu = amountchu + num;
									shouru = shouru
											+ (num * Double.parseDouble(sheet
													.getCell(4, j + 1)
													.getContents()))
											* (1 - 0.0013);
								}
							}
						}
						int chiyoushu = amountru - amountchu;
						double chiyouzhi = chiyoushu
								* Double.parseDouble(price);
						if (!price.equals("0")) {
							dataset.addValue(chiyouzhi, search, edate);
						}
					}
					// amount = amountru - amountchu;
					// String chiyouliang = String.valueOf(amount);
				}
			}
			book.close();
		} catch (BiffException e) {
			System.err.println(e + "");
		} catch (IOException e) {
			System.err.println(e + "文件读取错误");
		}

	}

	private static void processChart(JFreeChart chart) {
		CategoryPlot plot = chart.getCategoryPlot();
		CategoryAxis domainAxis = plot.getDomainAxis();
		ValueAxis rAxis = plot.getRangeAxis();
		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("宋体", Font.PLAIN, 20));
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
		domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		rAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
		rAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
		// renderer.setItemLabelGenerator(new LabelGenerator(0.0));
		// renderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 12));
		// renderer.setItemLabelsVisible(true);
	}

	/**
	 * 输出图片
	 * 
	 * @param chart
	 */
	private static void writeChartAsImage(JFreeChart chart) {
		FileOutputStream fos_jpg = null;
		try {
			fos_jpg = new FileOutputStream("fruit.jpg");
			ChartUtilities.writeChartAsJPEG(fos_jpg, 1, chart, 690, 380, null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
			}
		}
	}

	protected void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon("fruit.jpg");
		Image img = icon.getImage();
		g.drawImage(img, 0, 0, width, hight, this);
	}
}