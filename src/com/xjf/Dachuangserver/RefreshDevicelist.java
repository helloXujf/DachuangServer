package com.xjf.Dachuangserver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.RET;

public class RefreshDevicelist extends Thread {

	private static String sql=null;
	private static DBHelper db1=null;
	private static ResultSet ret=null;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		sql="select * from Client_tb order by ID desc";
		db1 = new DBHelper(sql);
        ArrayList<String> list=new ArrayList<String>();

		try {
			ret = db1.pst.executeQuery();//执行语句，得到结果集  
			while(ret.next()){
				String ID = ret.getString(1);
				String Name = ret.getString(2);
				String PhoneNum = ret.getString(3);
				String RoomNum = ret.getString(4);
				String ClientIP = ret.getString(5);
				
				String listitem = "["+ID+"]"+"      "+Name+"      "+PhoneNum+"      "+RoomNum+"      "+ClientIP;
				list.add(listitem);
				
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Main.List_Connect_Device.setListData(list.toArray());
		
		
	}
}
