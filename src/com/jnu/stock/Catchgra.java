package com.jnu.stock;
import java.awt.AWTEvent;  
import java.awt.Frame;  
import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.event.WindowEvent;  
import java.awt.image.ImageProducer;  
import java.io.IOException;  
import java.net.URL;  

import javax.swing.JPanel;
  
class Catchgra extends JPanel
{  
    private Image img;
    
    public void Setimg(String path)throws IOException
    {
    	URL url = new URL(path);  
    	this.img  = this.createImage((ImageProducer)url.getContent()); 
    	this.setSize(425,300);
    }
    public void paint(Graphics g)
    {  
      g.drawImage(img, 0, 0,500,290, this);//ÏÔÊ¾Í¼Ïñ  
    }  
}  
