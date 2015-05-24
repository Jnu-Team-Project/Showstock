package com.jnu.stock;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StockInfoLayout extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel sc, ryke, fdyk, yk, zhzzc, sz, xj, bj, sc1, ryke1, fdyk1, fdyk2,
			yk1, zhzzc1, sz1, xj1, bj1;

	
	public JLabel getSc() {
		return sc;
	}

	public void setSc(JLabel sc) {
		this.sc = sc;
	}

	public JLabel getRyke() {
		return ryke;
	}

	public void setRyke(JLabel ryke) {
		this.ryke = ryke;
	}

	public JLabel getFdyk() {
		return fdyk;
	}

	public void setFdyk(JLabel fdyk) {
		this.fdyk = fdyk;
	}

	public JLabel getYk() {
		return yk;
	}

	public void setYk(JLabel yk) {
		this.yk = yk;
	}

	public JLabel getZhzzc() {
		return zhzzc;
	}

	public void setZhzzc(JLabel zhzzc) {
		this.zhzzc = zhzzc;
	}

	public JLabel getSz() {
		return sz;
	}

	public void setSz(JLabel sz) {
		this.sz = sz;
	}

	public JLabel getXj() {
		return xj;
	}

	public void setXj(JLabel xj) {
		this.xj = xj;
	}

	public JLabel getBj() {
		return bj;
	}

	public void setBj(JLabel bj) {
		this.bj = bj;
	}

	public JLabel getSc1() {
		return sc1;
	}

	public void setSc1(JLabel sc1) {
		this.sc1 = sc1;
	}

	public JLabel getRyke1() {
		return ryke1;
	}

	public void setRyke1(JLabel ryke1) {
		this.ryke1 = ryke1;
	}

	public JLabel getFdyk1() {
		return fdyk1;
	}

	public void setFdyk1(JLabel fdyk1) {
		this.fdyk1 = fdyk1;
	}

	public JLabel getFdyk2() {
		return fdyk2;
	}

	public void setFdyk2(JLabel fdyk2) {
		this.fdyk2 = fdyk2;
	}

	public JLabel getYk1() {
		return yk1;
	}

	public void setYk1(JLabel yk1) {
		this.yk1 = yk1;
	}

	public JLabel getZhzzc1() {
		return zhzzc1;
	}

	public void setZhzzc1(JLabel zhzzc1) {
		this.zhzzc1 = zhzzc1;
	}

	public JLabel getSz1() {
		return sz1;
	}

	public void setSz1(JLabel sz1) {
		this.sz1 = sz1;
	}

	public JLabel getXj1() {
		return xj1;
	}

	public void setXj1(JLabel xj1) {
		this.xj1 = xj1;
	}

	public JLabel getBj1() {
		return bj1;
	}

	public void setBj1(JLabel bj1) {
		this.bj1 = bj1;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public StockInfoLayout() {
		sc = new JLabel("市场");
		ryke = new JLabel("日盈亏额");
		yk = new JLabel("盈亏");
		zhzzc = new JLabel("账户总资产");
		sz = new JLabel("市值");
		xj = new JLabel("现金");
		
		sc1 = new JLabel("A股");
		ryke1 = new JLabel("");
		fdyk1 = new JLabel("-23.3");
		yk1 = new JLabel("");
		zhzzc1 = new JLabel("500000");
		sz1 = new JLabel("");
		xj1 = new JLabel("");
	}

	public void createStockInfoLayout() {
		this.setLayout(new GridLayout(2, 5));
		this.add(sc);
		this.add(ryke);
		this.add(yk);
		//this.add(zhzzc);
		this.add(sz);
		this.add(xj);
		
		this.add(sc1);
		this.add(ryke1);
		this.add(yk1);
		//this.add(zhzzc1);
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