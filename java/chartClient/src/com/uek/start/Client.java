package com.uek.start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.uek.ui.LoginUI;
import com.uek.ui.MainUI;
import com.uek.utils.FrameUtils;

public class Client {
	public static Socket clientSocket;
	public static void main(String[] args) throws UnknownHostException, IOException{
		Socket socket = new Socket("localhost",8888);
		Client.clientSocket = socket;
		new ClientThread(socket).start();
		
		//加载登录页面
		LoginUI loginUI = new LoginUI();
		
		loginUI.init();
		FrameUtils.loginUI = loginUI;
	}
}
class ClientThread extends Thread{
	private Socket socket;
	public ClientThread(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = null;
			while((line = br.readLine())!=null) {
				String strs[] = line.split(",");
				if("1".equals(strs[0])) {
					String okMessage = strs[1];
					String Message = strs[2];
					if(okMessage.equals("nook")) {
						JOptionPane.showMessageDialog(null,Message);
					}
					if("ok".equals(strs[1])) {
						System.out.println(strs[2]+"注册成功");
						FrameUtils.registerUI.dispose();
						LoginUI loginUI = FrameUtils.loginUI;
						loginUI.setVisible(true);
						loginUI.getJtf().setText(strs[2]);
					}
				}
				if("2".equals(strs[0])) {
					String okMessage = strs[1];
					if("ok".equals(okMessage)) {
						ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
						MainUI mainUI = new MainUI();
//						mainUI.init();
					}
					if("nook".equals(okMessage)) {
						String message = strs[2];
						JOptionPane.showMessageDialog(null, message);
					}
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
