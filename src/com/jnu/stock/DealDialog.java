package com.jnu.stock;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class DealDialog extends JPanel {
	int state = 0;

	String dealdialogusername = new String("");

	String dealdialogname = new String("");

	private JLabel date = new JLabel("日期（如2008-8-8）");
	JTextField dateTextField = new JTextField(8);

	private JLabel style = new JLabel("操作类型");
	JTextField styleTextField = new JTextField(8);

	private JLabel price = new JLabel("价格（元）");
	JTextField priceTextField = new JTextField(8);

	private JLabel amount = new JLabel("数量");
	JTextField amountTextField = new JTextField(8);

	private JLabel rate = new JLabel("税率（‰）");
	JTextField rateTextField = new JTextField(8);

	private JLabel yongjin = new JLabel("佣金（‰）");
	JTextField yongjinTextField = new JTextField(8);

	JButton okButton = new JButton("确定");

	String[] stylestr = { "买入", "卖出", "补仓", "卖空" };
	JComboBox jcb = new JComboBox(stylestr);

	public DealDialog(final ModifyTable applet,final int r,final JFrame jf)// ,final Showstock st
	{
		dateTextField.setText("15-5-22");
		rateTextField.setText("1");
		yongjinTextField.setText("0.3");
		styleTextField.setText(stylestr[0]);
		dateTextField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new Datedialog(jf, true, dateTextField, 300, 400);
			}
		});
		jcb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				styleTextField.setText(stylestr[jcb.getSelectedIndex()]);
				if(styleTextField.getText().equals("卖空"))
				{
					amountTextField.setText(String.valueOf(applet.getjTable1().getValueAt(r, 3)));
				}
				
			}
			
		});
		JPanel jpbutton = new JPanel();
		JPanel jiaoyi = new JPanel();
		jpbutton.add(okButton);
		jiaoyi.setLayout(new GridLayout(2, 6));
		jiaoyi.add(date);
		jiaoyi.add(style);
		jiaoyi.add(price);
		jiaoyi.add(amount);
		jiaoyi.add(rate);
		jiaoyi.add(yongjin);
		jiaoyi.add(dateTextField);
		jiaoyi.add(jcb);
		jiaoyi.add(priceTextField);
		jiaoyi.add(amountTextField);
		jiaoyi.add(rateTextField);
		jiaoyi.add(yongjinTextField);
		this.setLayout(new BorderLayout());
		add(jpbutton, BorderLayout.SOUTH);
		add(jiaoyi, BorderLayout.CENTER);

	}

	public String Adddealtosheet(String a1, String a2, String a3, String a4,
			String a5, String a6) {

		String datestr = a1;
		String styletr = a2;
		String pricestr = a3;
		String amountstr = a4;
		String ratestr = a5;
		String yongjinstr = a6;
		String test = "";

		Workbook wb;
		try {
			wb = Workbook.getWorkbook(new File(dealdialogusername + ".xls"));
			WritableWorkbook book;
			try {
				book = Workbook.createWorkbook(new File(dealdialogusername
						+ ".xls"), wb);
				WritableSheet sheet2 = book.getSheet(dealdialogname);

				int m = Integer.parseInt(sheet2.getCell(11, 0).getContents()) + 1;
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

				l = new Label(11, 0, String.valueOf(m));
				sheet2.addCell(l);
				test = sheet2.getCell(11, 0).getContents();
				book.write();
				book.close();
				// jf.dispose();

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
			state = 1;
		}

		catch (BiffException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return test;
		// st.Updatetable();
	}

	public String getDealdialogusername() {
		return dealdialogusername;
	}

	public void setDealdialogusername(String dealdialogusername) {
		this.dealdialogusername = dealdialogusername;
	}

	public String getDealdialogname() {
		return dealdialogname;
	}

	public void setDealdialogname(String dealdialogname) {
		this.dealdialogname = dealdialogname;
	}

	public JTextField getDateTextField() {
		return dateTextField;
	}

	public void setDateTextField(JTextField dateTextField) {
		this.dateTextField = dateTextField;
	}

	public JTextField getStyleTextField() {
		return styleTextField;
	}

	public void setStyleTextField(JTextField styleTextField) {
		this.styleTextField = styleTextField;
	}

	public JTextField getPriceTextField() {
		return priceTextField;
	}

	public void setPriceTextField(JTextField priceTextField) {
		this.priceTextField = priceTextField;
	}

	public JTextField getAmountTextField() {
		return amountTextField;
	}

	public void setAmountTextField(JTextField amountTextField) {
		this.amountTextField = amountTextField;
	}

	public JTextField getRateTextField() {
		return rateTextField;
	}

	public void setRateTextField(JTextField rateTextField) {
		this.rateTextField = rateTextField;
	}

	public JTextField getYongjinTextField() {
		return yongjinTextField;
	}

	public void setYongjinTextField(JTextField yongjinTextField) {
		this.yongjinTextField = yongjinTextField;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}
	public void setAmountTextFieldstr(String a)
	{
		this.amountTextField.setText(a);
	}
}
