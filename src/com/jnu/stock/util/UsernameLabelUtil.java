package com.jnu.stock.util;

import java.awt.Font;

import javax.swing.JLabel;

import com.jnu.stock.ShowStock;

public class UsernameLabelUtil {
	/**
	 * 登录后，设置面板左上角的标签为用户名
	 * @param userName 用户名
	 */
	public static void replaceUsername(ShowStock showStock, String userName) {
		JLabel usernameLabel = new JLabel(userName);
		usernameLabel.setFont(new Font("", 1, 30));
		showStock.add(usernameLabel);
		usernameLabel.setBounds(10, 10, 120, 30);
	}
}
