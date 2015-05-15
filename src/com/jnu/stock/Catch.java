package com.jnu.stock;

import java.net.*;
import java.util.Vector;
import java.io.*;
public class Catch
{ 
	public static String loadJson (String url) 
	{  
        StringBuilder json = new StringBuilder();  
        try {  
            URL urlObject = new URL(url);  
            URLConnection uc = urlObject.openConnection();  
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));  
            String inputLine = null;  
            while ( (inputLine = in.readLine()) != null) {  
                json.append(inputLine);  
            }  
            in.close();  
        } catch (MalformedURLException e) { 
            e.printStackTrace();  
        } catch (IOException e) { 
            e.printStackTrace();  
        }  
        return json.toString();  
    }  
	public String Gethistoryprice(String num,String searchdate)//begin ="2014-09-08"
	{
		String[] detaildate = searchdate.split("/");
		String a = "http://market.finance.sina.com.cn/transHis.php?date=";
		String b = detaildate[0]+"-";
		String c = detaildate[1]+"-";
		String d = detaildate[2]+"&symbol=";
		
		String gis =a+b+c+d+num;
		//System.out.print(gis);
		String searchbegin = "</th><td>";
		String searchend = "</td><td>";
		String line=loadJson(gis);	    
	    String[] str = line.split(searchbegin);
	    if(str.length>1)
	    {
			String[] str1 = str[1].split(searchend);
			String data = str1[0];
			return data;
	    }
	    else
	    	return "0";
	}
	/*public static void main (String args[]) 
	{
		String[] a = {"2015","03","05"};
		String[] b = {"2015","03","13"};
		String name = "sh601398.ss";
		Vector ab = new Vector();
		ab = Gethistorydata(name,a,b);
		System.out.print("\n"+ab.size());	
	}*/
}
