package com.Baha;
//import

public class AssistantMain {
	public static void main(String[] args) {
		try {
			RemoteBaha baha_Connection = new RemoteBaha();
			if(baha_Connection.getLogin_P()) {
				System.out.println("Nice");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
