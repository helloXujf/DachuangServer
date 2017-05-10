package com.xjf.Dachuangserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.sun.org.apache.bcel.internal.generic.RET;

public class ServerListener extends Thread {
	
	private static String sql = null;
	private static DBHelperUpdate db1 = null;
	
	@Override
	public void run() {
		
		try {
			ServerSocket serversocket =new ServerSocket(Main.portint);
			Main.lblTips.setText("success to start sever!");
			while(true) {
				Socket socket =serversocket.accept();
				InetAddress inetAddress = socket.getInetAddress();
				 String name_Device = inetAddress.getHostName().toString();
				 String iP_Device =inetAddress.getHostAddress().toString();
				 //将socket的信息写入数据库
				 
//					sql="INSERT INTO `Intelligent_Hotel`.`Client_tb` (`Name`, `ClientIP`) VALUES ('"+name_Device+"', '"+iP_Device+"');";
//					db1= new DBHelperUpdate(sql);
//					db1.close();
//				 
				 
				System.out.println("计算机名："+name_Device);
				System.out.println("IP地址："+iP_Device);
				JOptionPane.showMessageDialog(null, "有客户端链接到本机5566端口");
				
				
				ChatSocket cs = new ChatSocket(socket);
						cs.start();
						ChatManager.getChatManager().add(cs);
						ChatManager.getChatManager().publishToNodejs("hello");

			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Main.lblTips.setText("fail to Create!");
			e.printStackTrace();
		}
	}
} 
