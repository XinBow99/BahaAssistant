package com.Baha;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class PostMethod {

	private CloseableHttpClient bahaclient;
	private String User_Information = "";
	private boolean signin = false;
	
	public PostMethod(String code,String userAC,String UserPW) throws IOException {
		// cookie setup
		BasicCookieStore cookieStore = new BasicCookieStore();
		BasicClientCookie cookie = new BasicClientCookie("ckAPP_VCODE", code);
		cookieStore.addCookie(cookie);
		// base login
		this.bahaclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		// Form
		List<NameValuePair> form = new ArrayList<>();
		form.add(new BasicNameValuePair("uid", userAC));
		form.add(new BasicNameValuePair("passwd", UserPW));
		form.add(new BasicNameValuePair("vcode", "7045"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, Consts.UTF_8);
		//set Headers
		HttpPost httpPost = new HttpPost("https://api.gamer.com.tw/mobile_app/user/v3/do_login.php");
		httpPost.setEntity(entity);
		httpPost.setHeader("user-agent", "Bahadroid (https://www.gamer.com.tw/)");
		httpPost.setHeader("x-bahamut-app-instanceid", "cc2zQIfDpg4");
		httpPost.setHeader("x-bahamut-app-android", "tw.com.gamer.android.activecenter");
		httpPost.setHeader("x-bahamut-app-version", "251");
		httpPost.setHeader("content-type", "application/x-www-form-urlencoded");
		httpPost.setHeader("accept-encoding", "gzip");
		httpPost.setHeader("cookie", "ckAPP_VCODE=7045");
		//Create handler author:Ramesh Fadatare
		ResponseHandler<String> responseHandler = response -> {
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity responseEntity = response.getEntity();
				return responseEntity != null ? EntityUtils.toString(responseEntity) : null;
			} else {
				throw new ClientProtocolException("Unexpected response status: " + status);
			}
		};
		//Post to Host
		String responseBody = getBahaclient().execute(httpPost, responseHandler);
		this.User_Information = responseBody;
		this.signin = true;
		System.out.println("[Info]登入完畢");
	}
	//取得目前連線內容
	public CloseableHttpClient getBahaclient() {
		return bahaclient;
	}
	//取得勇者資訊
	public String getUser_Information() {
		return User_Information;
	}
	//是否登入完畢
	public boolean get_Signin() {
		return signin;
	}
}