import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


class Zhuxiao extends JPanel //注册面板
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
		  //user=new JLabel("用户名");
		  caution=new JLabel("是否导入旧操作记录（若否，请点击下一步）");
		  zhuxiao = new JButton("注销");
		  tuichu = new JButton("退出");
		  this.add(zhuxiao);
		  this.add(tuichu);
		  zhuxiao.setBounds(260,270,80,80);
		  tuichu.setBounds(360,270,80,80);
	 }
}