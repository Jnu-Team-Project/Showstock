import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


class Zhuxiao extends JPanel //ע�����
{
	 private static final long serialVersionUID = 1L;
	 private JLabel caution;
	 JButton zhuxiao,tuichu;
	 int width = 0, hight = 0;
	 public Zhuxiao(int width, int hight)
	 {
		  this.width = width;
		  this.hight = hight;
		  //imgpath = file;
		  this.setLayout(null);
		  //user=new JLabel("�û���");
		  caution=new JLabel("�Ƿ���ɲ�����¼������������һ����");
		  zhuxiao = new JButton("ע��");
		  tuichu = new JButton("�˳�");
		  this.add(zhuxiao);
		  this.add(tuichu);
		  zhuxiao.setBounds(260,270,80,80);
		  tuichu.setBounds(360,270,80,80);
	 }
}