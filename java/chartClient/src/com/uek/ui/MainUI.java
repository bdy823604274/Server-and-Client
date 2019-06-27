package com.uek.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.uek.model.Member;
import com.uek.utils.FrameUtils;

public class MainUI extends JFrame{
	
	private JPanel jp1 , jp2 ,jp3 ,jp4;
	
	private JLabel jl11 , jl12 , jl13;
	
	private JLabel jl21;
	
	private JButton jb41;
	
	//可以认为是规范，一个公司内部的规范
	//将定义的控件 new出来 并且排列组合好
	public void init(List<Member> selfAndfriends)
	{
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		
		
		jl11 = new JLabel();
		jl12 = new JLabel();
		jl13 = new JLabel();
		
		jl21 = new JLabel();
		
		jb41 = new JButton("加好友");
		
		final Member self = selfAndfriends.get(0);
		
		jb41.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SearchUI searchUI = new SearchUI();
				searchUI.init(self);
				
				//缓存搜索页面
				FrameUtils.searchUI = searchUI;
			}
		});
		
		//准备设置绝对布局
		this.setLayout(null);
		
		//设置面板在父容器的位置，并且设置自己的宽高  5,5是右上角相对于父容器的位置 起始位置
		jp1.setBounds(5, 5, 275, 60);
		//设置面板的边框
		jp1.setBorder(BorderFactory.createLineBorder(Color.red));
		
		//jp1必须设置绝对布局，也就是把默认的FlowLayout去掉
		jp1.setLayout(null);
		
		jl11.setBounds(5, 5, 40, 40);
		//设置显示图片
		jl11.setIcon(new ImageIcon("headers\\header1.png"));
		jl11.setBorder(BorderFactory.createLineBorder(Color.red));
		jp1.add(jl11);
		
		
		jl12.setText(selfAndfriends.get(0).getName());//等价于new Label("软院");
		jl12.setBounds(60, 5, 40, 20);
		jp1.add(jl12);
		
		jl13.setText(selfAndfriends.get(0).getSignature());//等价于new Label("软院");
		jl13.setBounds(60, 20, 120, 40);
		jp1.add(jl13);
		
		this.add(jp1);
		
		jp2.setLayout(null);
		
		jp2.setBounds(5, 70, 275, 30);
		jp2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		
		jl21.setText("输入好友名...");
		jl21.setBounds(5, 5, 260, 20);
		jp2.add(jl21);
		
		this.add(jp2);
				
	
		jp3.setBounds(5, 105, 275, 400);
		jp3.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		
		jp3.setLayout(null);
		
		
		
		
		
		//去掉自己，剩下好友，准备好友列表
		List<Member> friends = selfAndfriends.subList(1, selfAndfriends.size());
		
		for (int i=0 ; i<friends.size() ; i++) {
			
			final Member friend = friends.get(i);
			
			JPanel jp31 = new JPanel();
			JLabel jl311 = new JLabel();
			JLabel jl312 = new JLabel();
			JLabel jl313 = new JLabel();
			
			jp31.setBounds(5, 5+i*45, 265, 50);
			jp31.setBorder(BorderFactory.createLineBorder(Color.red));
			jp31.setLayout(null);
			
			//适配器的设计模式 
			//如果实现MouseListener需要实现5个方法，实际我只关心单击方法 其他4个浪费
			//所以有个适配器类，MouseAdpater
			jp31.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					//如果没有打开过该好友的聊天窗口，就打开，否则就不打开了
					if(!FrameUtils.friendId2ChatUI.containsKey(friend.getId()))
					{
						ChatUI chatUI = new ChatUI();
						chatUI.init(self,friend);
						
						FrameUtils.friendId2ChatUI.put(friend.getId(), chatUI);
					}
				
					
				}
			});
			
			jl311.setBounds(5, 5, 40, 40);
			
			jl311.setIcon(new ImageIcon("headers\\header1.png"));
			jl311.setBorder(BorderFactory.createLineBorder(Color.red));
			jp31.add(jl311);
			
			
			jl312.setText(friend.getName());
			jl312.setBounds(60, 5, 40, 20);
			jp31.add(jl312);
			
			jl313.setText(friend.getSignature());
			jl313.setBounds(60, 20, 120, 40);
			jp31.add(jl313);
					
			jp3.add(jp31);
			
		}
		
		this.add(jp3);
			
		jp4.setBounds(5, 510, 275, 40);
		jp4.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		
		
		jp4.setLayout(null);
		jb41.setBounds(5, 5 , 100 , 30);//只给坐标定位，不给size setLocation+setSize=setBounds
		jp4.add(jb41);
		
		
		this.add(jp4);
		
		
		
		
				
		//this 就是 
		//JFrame public class MainUI extends JFrame
		this.setTitle(selfAndfriends.get(0).getName()+"的主界面");
		this.setSize(300, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	
	
	public JPanel getJp3() {
		return jp3;
	}



	public static void main(String[] args) {
		MainUI mainUI = new MainUI();
//		mainUI.init();
	}
	
	
	
}
