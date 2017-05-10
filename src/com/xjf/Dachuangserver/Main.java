package com.xjf.Dachuangserver;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.security.ProtectionDomain;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JTable;



public class Main extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfport;
	private JButton btnconnect;
	protected static JLabel lblTips;
	private JButton btnRefresh;
	private JButton btnSelected;
	
	
	public String port;
	public static int portint;
	protected static JList List_Connect_Device;
	protected static String ClientID=null;
	private JLabel lbtip2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		tfport = new JTextField();
		tfport.setColumns(10);
		
		btnconnect = new JButton("connect");
		btnconnect.addActionListener(this);
		
		lblTips = new JLabel("tips");
		
		JSeparator separator = new JSeparator();
		
		JPanel panel = new JPanel();
		
		JLabel lblNewLabel = new JLabel("连接上的设备");
		
		btnRefresh = new JButton("刷新");
		btnRefresh.addActionListener(this);
		
		btnSelected = new JButton("管理");
		btnSelected.addActionListener(this);
		
		lbtip2 = new JLabel("tips2");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tfport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnconnect)
							.addGap(18)
							.addComponent(lblTips))
						.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(btnRefresh))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnSelected)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lbtip2)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnconnect)
						.addComponent(lblTips))
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(btnRefresh))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSelected)
						.addComponent(lbtip2))
					.addContainerGap(62, Short.MAX_VALUE))
		);
		panel.setLayout(new BorderLayout(0, 0));
		
		List_Connect_Device = new JList();
		panel.add(List_Connect_Device, BorderLayout.CENTER);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnconnect){
			port=tfport.getText().toString();
			portint=Integer.parseInt(port);
			new ServerListener().start();
//			new SeverListener2().start();
			
		}
		
		if(e.getSource()==btnRefresh){
			new RefreshDevicelist().start();
		}
		if(e.getSource()==btnSelected){
			if(List_Connect_Device.getSelectedValue()!=null){
			ClientID=List_Connect_Device.getSelectedValue().toString();
			String clientidString=ClientID.substring(ClientID.indexOf('[')+1, ClientID.indexOf(']')); 
			new WinManage(clientidString).setVisible(true);
			}else lbtip2.setText("请选择一个客户");
			
		}
		
	} 
}





































