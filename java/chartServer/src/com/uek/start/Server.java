package com.uek.start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.uek.model.Member;
import com.uek.service.impl.MemberServiceImpl;
import com.uek.service.inter.IMemberService;
import com.uek.utils.MessageUtils;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket ss= new ServerSocket(8888);
		
		System.out.println("服务器已启动，请客户端连接");
		while(true) {
			Socket socket =ss.accept();
			new ServerThread(socket).start();
			
		}
		
	}
	

}

class ServerThread extends Thread{
	private Socket socket;
	public ServerThread(Socket socket) {
		this.socket = socket;	
	}
	@Override
	public void run() {
		try {
			//调用MemberService
			IMemberService memberService  = new MemberServiceImpl();
			
			PrintStream ps = new PrintStream(socket.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = null;
			while((line = br.readLine())!=null) {
				String arrs[] = line.split(",");
				//注册
				if("1".equals(arrs[0])) {
					//接收发送过来的注册对象
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					String returnMessage = "1,ok";
					try {
						if(arrs[2] !=null) {
							returnMessage = "1,nook,"+arrs[2];
							
						}else {
							Member member  = (Member)ois.readObject();
							//判断用户是否存在
							System.out.println(member);
							if(memberService.loadByName(member.getName()) == null) {
								//添加到数据库
								memberService.add(member);
								returnMessage = "1,ok,"+member.getName();
								
							}else {
								returnMessage = "1,nook,username is exist";
							}
							
						}
						
						System.out.println(returnMessage);
						ps.println(returnMessage);
						ps.flush();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
				}
				//登录 2,login,name,password
				if("2".equals(arrs[0])) {
					String name = arrs[2];
					String password = arrs[3];
					try {
						memberService.login(name, password);
						String returnMessage = "2,ok";
						MessageUtils.sendMessage(socket,returnMessage);
						//根据名字查自己
						Member self = memberService.loadByName(name);
						List<Member> selfAndfriend = memberService.loadFriends(self.getId());
						//输出自己
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(self);
						oos.flush();
						
					} catch (Exception e) {
						String returnMessage = "2,nook,"+e.getMessage();
						MessageUtils.sendMessage(socket , returnMessage);
					}
					
					
					
	
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}















