package com.xjf.Dachuangserver;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Vector;

public class ChatManager {

	private ChatManager(){}
	private static final ChatManager cm = new ChatManager();
	public static ChatManager getChatManager(){
		return cm;
	}
	
	Vector<ChatSocket> vector = new Vector<ChatSocket>();
	
	public void add(ChatSocket cs){
		vector.add(cs);
	}
	
	public void publish(ChatSocket cs, String orderInfo, String roomNum){
		for (int i = 0; i < vector.size(); i++) {
			ChatSocket csChatSocket = vector.get(i);
			csChatSocket.out(orderInfo);
			
			
//			if(!cs.equals(csChatSocket)){
//			//if(roomNum.equals("810")){
//				//if(cs.socket.getInetAddress().getHostName().toString().equals(roomNum)){
//
//				csChatSocket.out(orderInfo+"hhh\n"); 
//			}
		}
		
	}
	public void publishToNodejs(String infoString){
		for (int i = 0; i < vector.size(); i++) {
			ChatSocket csChatSocket = vector.get(i);
			
			//test 8266
			//csChatSocket.out("111111111");
			
			String iPString= csChatSocket.socket.getInetAddress().getHostAddress().toString();
			if(iPString.equals("127.0.0.1")){
				csChatSocket.out(infoString);
			}
		}
	}
	
}
