package com.jnu.stock;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StockInfoLayout extends JPanel {

	JLabel sc, ryke, fdyk, yk, zhzzc, sz, xj, bj, sc1, ryke1, fdyk1, fdyk2,
			yk1, zhzzc1, sz1, xj1, bj1;

	public StockInfoLayout() {
		sc = new JLabel("�г�");
		ryke = new JLabel("��ӯ����");
		yk = new JLabel("ӯ��");
		zhzzc = new JLabel("�˻����ʲ�");
		sz = new JLabel("��ֵ");
		xj = new JLabel("�ֽ�");
		
		sc1 = new JLabel("A��");
		ryke1 = new JLabel("");
		fdyk1 = new JLabel("-23.3");
		yk1 = new JLabel("");
		zhzzc1 = new JLabel("500000");
		sz1 = new JLabel("1000000");
		xj1 = new JLabel("618814.43");
	}

	public void createStockInfoLayout() {
		this.setLayout(new GridLayout(2, 6));
		this.add(sc);
		this.add(ryke);
		this.add(yk);
		this.add(zhzzc);
		this.add(sz);
		this.add(xj);
		
		this.add(sc1);
		this.add(ryke1);
		this.add(yk1);
		this.add(zhzzc1);
		this.add(sz1);
		this.add(xj1);
	}

	public void update() {
		this.removeAll();
		createStockInfoLayout();
		this.setVisible(false);
		this.setVisible(true);
	}

}