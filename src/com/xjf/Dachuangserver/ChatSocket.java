package com.xjf.Dachuangserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import javax.swing.JOptionPane;


public class ChatSocket extends Thread {
	
	private static String sql1 = null;
	private static DBHelperUpdate db1 = null;

	Socket socket;
	public ChatSocket(Socket s) {
		this.socket=s;
	}
	
	public void out(String out){
		try {
			OutputStream os =socket.getOutputStream();
			os.write(out.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			BufferedReader br=new BufferedReader(
					new InputStreamReader(
							socket.getInputStream(), "UTF-8"));
			String line =null;			
			while((line=br.readLine())!=null){
				System.out.println(line);
				String orderString = getOrder(line);
				String orderInfo = getOrderInfo(line);
				String roomNum = getRoom(line);
				
				if(orderString.equals("ORDER")){
					System.out.println(orderInfo+roomNum);
					//ChatManager.getChatManager().publish(this, orderInfo,roomNum);
					if(orderInfo.equals("TURNON1")){
						ChatManager.getChatManager().publish(this, "1111111",roomNum);
					}else if(orderInfo.equals("TURNON2")){
						ChatManager.getChatManager().publish(this, "2222222",roomNum);
					}else if(orderInfo.equals("TURNON3")){
						ChatManager.getChatManager().publish(this, "3333333",roomNum);
					}else if(orderInfo.equals("TURNON4")){
						ChatManager.getChatManager().publish(this, "4444444",roomNum);
					}
				}
				else if(orderString.equals("QUIT")){
					//JOptionPane.showMessageDialog(null, "是否同意退出","提醒",JOptionPane.QUESTION_MESSAGE);
					int quitConfirm =JOptionPane.showConfirmDialog(null, "确认退出？", "确认", JOptionPane.YES_NO_OPTION); 
					if(quitConfirm == 0){
						//退出
						sql1 = "DELETE FROM `Intelligent_Hotel`.`Client_tb` WHERE RoomNum = "+roomNum;
						db1 = new DBHelperUpdate(sql1);
						
						sql1 = "UPDATE `Intelligent_Hotel`.`Room_tb` SET `IsLiving`='N' WHERE `RoomNum`=" +roomNum;
						db1 =new DBHelperUpdate(sql1);
						
						db1.close();
						System.out.println("point1");
						ChatManager.getChatManager().publishToNodejs("COMFIRM");
						

					}else {
						//不退出
					}
					System.out.println(orderInfo+roomNum);
					
				}
				
			}
			br.close();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	private String getRoom(String line) {
		// TODO Auto-generated method stub
		return line.substring(line.indexOf('@')+1,line.indexOf('#'));
	}

	private String getOrderInfo(String line) {
		// TODO Auto-generated method stub
		return line.substring(line.indexOf('{')+1,line.indexOf('}'));
	}

	private String getOrder(String line) {
		// TODO Auto-generated method stub
		return line.substring(line.indexOf('[')+1,line.indexOf(']'));
	}


}
