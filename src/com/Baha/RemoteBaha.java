package com.Baha;
//import

import java.io.IOException;
import java.util.Scanner;
public class RemoteBaha {
	private PostMethod postMethod;
	private String user_Account = "";
	private String user_pass = "";
	public RemoteBaha() throws IOException{
		System.out.println("[Info]建立登入資訊");
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("[Info]請輸入帳號：");
		setUser_Account(scanner.next());
		
		System.out.println("[Info]請輸入密碼：");
		setUser_pass(scanner.next());
		
		this.postMethod = new PostMethod("7045",getUser_Account(),getUser_pass());
		System.out.println("[Server response]" + postMethod.getUser_Information());
		
		scanner.close();
	}
	public boolean getLogin_P() {
		return postMethod.get_Signin();
	}
	public String getUser_Account() {
		return user_Account;
	}
	public void setUser_Account(String user_Account) {
		this.user_Account = user_Account;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	
}
