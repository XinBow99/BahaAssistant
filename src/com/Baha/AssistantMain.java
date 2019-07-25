package com.Baha;

//import
import java.util.Scanner;

public class AssistantMain {
	public static void main(String[] args) {
		try {
			RemoteBaha baha_Connection = new RemoteBaha();
			if (baha_Connection.getLogin_P()) {
				Scanner scanner = new Scanner(System.in);
				System.out.print("[Info]是否儲存帳號密碼(y/n)：");
				if (scanner.next().equals("y")) {
					baha_Connection.setUser_Account(baha_Connection.getUser_Account(), true);
					baha_Connection.setUser_pass(baha_Connection.getUser_pass(), true);
				} else {
					baha_Connection.setUser_Account("", true);
					baha_Connection.setUser_pass("", true);
				}
				scanner.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
