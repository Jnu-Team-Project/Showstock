import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


class Dinglan extends JPanel
{
	JLabel sc,ryke,fdyk,yk,zhzzc,sz,xj,bj,sc1,ryke1,fdyk1,fdyk2,yk1,zhzzc1,sz1,xj1,bj1;
	public Dinglan()
	{
	  sc=new JLabel("�г�");
	  ryke=new JLabel("��ӯ����");
	  //fdyk=new JLabel("����ӯ��");
	  yk=new JLabel("ӯ��");
	  zhzzc=new JLabel("�˻����ʲ�");
	  sz=new JLabel("��ֵ");
	  xj=new JLabel("�ֽ�");
	  //bj=new JLabel("����");
	  sc1=new JLabel("A��");
	  ryke1=new JLabel("");
	  fdyk1=new JLabel("-23.3");
	  yk1=new JLabel("");
	  zhzzc1=new JLabel("500000");
	  sz1=new JLabel("1000000");
	  xj1=new JLabel("618814.43");
	  //bj1=new JLabel("500000");
	  sc.setForeground(Color.DARK_GRAY);
	  ryke.setForeground(Color.DARK_GRAY);
	  //fdyk.setForeground(Color.DARK_GRAY);
	  yk.setForeground(Color.DARK_GRAY);
	  zhzzc.setForeground(Color.DARK_GRAY);
	  sz.setForeground(Color.DARK_GRAY);
	  xj.setForeground(Color.DARK_GRAY);
	  //bj.setForeground(Color.DARK_GRAY);
	  sc1.setForeground(Color.DARK_GRAY);
	  ryke1.setForeground(Color.DARK_GRAY);
	  //fdyk1.setForeground(Color.DARK_GRAY);
	  yk1.setForeground(Color.DARK_GRAY);
	  zhzzc1.setForeground(Color.DARK_GRAY);
	  sz1.setForeground(Color.DARK_GRAY);
	  xj1.setForeground(Color.DARK_GRAY);
	  //bj1.setForeground(Color.DARK_GRAY);
	}
	  public void createdinglan()
	  {
		  this.setLayout(new GridLayout(2, 6));	  
		  this.add(sc);
		  this.add(ryke);
		  //this.add(fdyk);
		  this.add(yk);
		  this.add(zhzzc);
		  this.add(sz);
		  this.add(xj);
		  //this.add(bj);
		  this.add(sc1);
		  this.add(ryke1);
		  //this.add(fdyk1);
		  this.add(yk1);
		  this.add(zhzzc1);
		  this.add(sz1);
		  //this.add(login);
		  this.add(xj1);
		  //this.add(bj1);
	}
	  public void update()
	  {
		  this.removeAll();
		  createdinglan();
		  this.setVisible(false);
		  this.setVisible(true);
	  }
	 
}