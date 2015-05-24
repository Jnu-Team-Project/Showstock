package com.jnu.stock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Oldestdate {
	/**
	 * 
	 * @param inputDate
	 * @param olddate
	 * @return true ��������ڱȣ��ȶԵ����ڣ�Ҫ��
	 */
	public static boolean isOldestdate(String inputDate, String olddate) {
		boolean bl = false;
		try {
//			String[] a = inputDate.split("-");
//			String changedDate = a[0] + '-' + a[1] + '-' + a[2];
//			System.out.println(changedDate);
			SimpleDateFormat sdf = new SimpleDateFormat("yy-M-d");
			Date date1 = sdf.parse(inputDate);
			Date date2 = sdf.parse(olddate);

			bl = ((date1.getTime() - date2.getTime()) < 0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bl;
	}

}
