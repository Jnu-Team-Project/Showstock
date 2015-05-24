package com.jnu.stock.util;

public class DealOperationUtil {
//	public static void operation(DealDialog dealDialog){
//		String datestr = dealDialog.getDateTextField().getText();
//		String styletr = dealDialog.getStyleTextField().getText();
//		String pricestr = dealDialog.getPriceTextField().getText();
//		String amountstr = dealDialog.getAmountTextField().getText();
//		String ratestr = dealDialog.getRateTextField().getText();
//		String yongjinstr = dealDialog.getYongjinTextField().getText();
//		double pricestr1 = Double.parseDouble(pricestr);
//		double amountstr1 = Double.parseDouble(amountstr);
//		double cut = 0.0;
//		double add = pricestr1 * amountstr1;
//		double currentAccount = 0.0;    //（现金）目前账户
//		
//		if (styletr.equals("买入") || styletr.equals("补仓")) {
//			cut = pricestr1 * amountstr1;
//			currentAccount = applet2.getDinglanxianjin() - cut;
//		} else if (styletr.equals("卖出")|| styletr.equals("卖空"))
//			currentAccount = applet2.getDinglanxianjin() + add;
//		boolean full = (!datestr.equals(""))&& (!pricestr.equals(""))&& (!amountstr.equals(""));
//		
//		if (cut < applet2.getDinglanxianjin() && full) {
//			Workbook wb;
//			
//			try {
//				wb = Workbook.getWorkbook(new File(dealDialog.getDealdialogusername() + ".xls"));
//				WritableWorkbook book;
//				try {
//					book = Workbook.createWorkbook(new File(dealDialog.getDealdialogusername() + ".xls"), wb);
//					
//					WritableSheet sheet2 = book.getSheet(dealDialog.getDealdialogname());
//					WritableSheet sheet3 = book.getSheet(dealDialog.getDealdialogname());
//
//					int m = Integer.parseInt(sheet2.getCell(11, 0).getContents()) + 1;
//
//					String mindate = sheet2.getCell(13, 0).getContents();
//					
//					System.out.print(mindate);
//					Oldestdate cp = new Oldestdate();
//					if (cp.isOldestdate(datestr, mindate)
//							&& styletr.equals("卖出")) {
//						JOptionPane.showMessageDialog(
//										null,
//										"持有股数不足",
//										"停止操作",
//										JOptionPane.INFORMATION_MESSAGE);
//					}
//					else {
//						Label lb;
//						lb = new Label(13, 0, datestr);
//						sheet2.addCell(lb);
//						Label l;
//						l = new Label(2, m, datestr);
//						sheet2.addCell(l);
//
//						l = new Label(3, m, styletr);
//						sheet2.addCell(l);
//
//						l = new Label(4, m, pricestr);
//						sheet2.addCell(l);
//
//						l = new Label(5, m, amountstr);
//						sheet2.addCell(l);
//
//						l = new Label(6, m, ratestr);
//						sheet2.addCell(l);
//
//						l = new Label(7, m, yongjinstr);
//						sheet2.addCell(l);
//
//						l = new Label(11, 0, String
//								.valueOf(m));
//						sheet2.addCell(l);
//
//						l = new Label(12, 0, String
//								.valueOf(currentAccount));
//						sheet3.addCell(l);
//
//						book.write();
//						book.close();
//						framejioayi.dispose();
//						TableUtil.updatetable(showStcok,
//								username, news);
//					}
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				} catch (RowsExceededException e1) {
//					e1.printStackTrace();
//				} catch (WriteException e1) {
//					e1.printStackTrace();
//				}
//				wb.close();
//			} catch (BiffException e1) {
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		} else {
//			JOptionPane.showMessageDialog(null, "现金不足", "停止操作", JOptionPane.INFORMATION_MESSAGE);
//			dealDialog.setAmountTextFieldstr("");
//		}
//	}
}
