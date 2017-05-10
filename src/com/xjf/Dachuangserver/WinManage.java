package com.xjf.Dachuangserver;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.xml.internal.bind.v2.runtime.Name;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class WinManage extends JFrame implements ActionListener {

	private JPanel contentPane;
	
	
	
	private static String sql=null;
	private static DBHelper db1=null;
	private static ResultSet ret=null;
	
	private static String sql2 = null;
	private static DBHelper db2 = null;
	private static ResultSet ret2 =null;
	
	private static String sql3 = null;
	private static DBHelper db3 = null;
	private static ResultSet ret3 =null;
	
	private static String sql4 = null;
	private static DBHelperUpdate db4 = null;
	
	String oldroom = null;
	String newroom = null;
	private JButton btnHandin;
	private JComboBox cbRoomNum;
	private String roomNumString;
	private String clientid=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinManage frame = new WinManage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WinManage(String clientidString){
		clientid = clientidString;
		Vector<String> itemVector = new Vector<>();
		char state = 'N';
		String livingroomnumString=null;
		//获取客户入住房间号
		sql3 = "SELECT RoomNum FROM Intelligent_Hotel.Client_tb where ID = "+clientidString;
		db3 = new DBHelper(sql3);
		try {
			ret3 = db3.pst.executeQuery();
			while (ret3.next()){
				livingroomnumString=ret3.getString(1);
				itemVector.addElement(livingroomnumString);
				state ='Y';
				oldroom = livingroomnumString;
			}
			ret3.close();
			db3.close();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//初始化combobox内容
		
		sql="select RoomNum from Room_tb where IsLiving = 'N'";
		db1 = new DBHelper(sql);
		
		try {
			ret = db1.pst.executeQuery();
			while (ret.next()){
				roomNumString = ret.getString(1);
				itemVector.addElement(roomNumString);
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//执行语句，得到结果集  
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		String Name = null;
		String PhoneNum=null;
		String ClientIP=null;
		String ID=null;
		sql2="SELECT * FROM Intelligent_Hotel.Client_tb WHERE ID = "+clientidString+";";
		db2 = new DBHelper(sql2);
		try {
			ret2 = db2.pst.executeQuery();
			while (ret2.next()){
				 Name = ret2.getString(2);
				 PhoneNum = ret2.getString(3);
				 ClientIP = ret2.getString(5);
				 ID = ret2.getString(1);
						
			}
			ret2.close();
			db2.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//执行语句，得到结果集  

		
		JLabel label = new JLabel("姓名：");
		
		JLabel lblName = new JLabel("Name");
		lblName.setText(Name);
		
		JLabel lblNewLabel = new JLabel("电话：");
		
		JLabel lblNewLabel_1 = new JLabel("   ID:");
		
		JLabel lblIpaddress = new JLabel("   IP:");
		
		JLabel lblPhone = new JLabel("Phone");
		
		JLabel lblIdnum = new JLabel("IDNum");
		lblIdnum.setText(ID);
		JLabel lblIpaddress_1 = new JLabel("IPAddress");
		lblIpaddress_1.setText(ClientIP);
		JLabel label_1 = new JLabel("入住房间：");
		
		

		
		cbRoomNum = new JComboBox(itemVector);
		if(state=='Y'){
			cbRoomNum.setSelectedItem(livingroomnumString);

		}
		cbRoomNum.setBorder(BorderFactory.createTitledBorder("分配房间"));   
		
		
		
		btnHandin = new JButton("Handin");
		btnHandin.addActionListener(this);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addGap(18)
							.addComponent(lblName))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(lblPhone))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addComponent(lblIdnum))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblIpaddress)
							.addGap(18)
							.addComponent(lblIpaddress_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1)
							.addGap(18)
							.addComponent(cbRoomNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnHandin))
					.addContainerGap(299, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(lblName))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblPhone))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblIdnum))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIpaddress)
						.addComponent(lblIpaddress_1))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(cbRoomNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
					.addComponent(btnHandin)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);};
	public WinManage() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
		// TODO Auto-generated method stub
		if(e.getSource()==btnHandin){
			newroom = cbRoomNum.getSelectedItem().toString();
			if(newroom != oldroom){
				sql4 = "UPDATE `Intelligent_Hotel`.`Client_tb` SET `RoomNum`='"+newroom+"' WHERE `ID`='"+clientid+"';";
						
				db4 = new DBHelperUpdate(sql4);
				sql4 = "UPDATE `Intelligent_Hotel`.`Room_tb` SET `IsLiving`='Y' WHERE `RoomNum`='"+newroom+"';";
				db4 = new DBHelperUpdate(sql4);

				sql4 = "UPDATE `Intelligent_Hotel`.`Room_tb` SET `IsLiving`='N' WHERE `RoomNum`='"+oldroom+"';";
				db4 = new DBHelperUpdate(sql4);

				db4.close();
				//将房间信息发送给客户
//				String RoomInfo = "#"+clientid+"@"+newroom+"$"+
//				ChatManager.getChatManager().publish(this, );

				
				JOptionPane.showMessageDialog(null, "修改成功");
				this.dispose();

			}else {
				JOptionPane.showMessageDialog(null, "未发生任何改动");

			}
			
		}
	}
}




















