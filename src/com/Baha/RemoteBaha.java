package com.Baha;

//import
import org.json.*;
import java.io.IOException;
import java.util.Scanner;

public class RemoteBaha {
	private PostMethod postMethod;
	private String user_Account = "";
	private String user_pass = "";
	private JSONObject user_Infor;

	private ReadXml bahaConfig = new ReadXml();

	public RemoteBaha() throws IOException {
		if (this.bahaConfig.getAc() == "" && this.bahaConfig.getPw() == "") {
			System.out.println("[Info]建立登入資訊");
			Scanner scanner = new Scanner(System.in);

			System.out.println("[Info]請輸入帳號：");
			setUser_Account(scanner.next(), false);

			System.out.println("[Info]請輸入密碼：");
			setUser_pass(scanner.next(), false);
			//scanner.close();
		} else {
			setUser_Account(this.bahaConfig.getAc(), false);
			setUser_pass(this.bahaConfig.getPw(), false);
		}
		this.postMethod = new PostMethod("7045", getUser_Account(), getUser_pass(), this.bahaConfig.GetLogin());
		if (getLogin_P()) {
			setUser_Infor(new JSONObject(postMethod.getUser_Information()));// 登入後設定使用者資訊

			System.out.println("[Server response]" + getUser_Infor().getString("userid"));
		}
	}

	public boolean getLogin_P() {
		return postMethod.get_Signin();
	}

	public String getUser_Account() {
		return user_Account;
	}

	public void setUser_Account(String user_Account, boolean save) {
		if (save)
			this.bahaConfig.setRemAc(user_Account);
		this.user_Account = user_Account;
	}

	public String getUser_pass() {
		return user_pass;
	}

	public void setUser_pass(String user_pass, boolean save) {
		if (save)
			this.bahaConfig.setRem(user_pass);
		this.user_pass = user_pass;
	}

	// 使用者資訊
	public JSONObject getUser_Infor() {
		return user_Infor;
	}

	public void setUser_Infor(JSONObject user_Infor) {
		this.user_Infor = user_Infor;
	}

	public ReadXml getBahaConfig() {
		return bahaConfig;
	}

}
