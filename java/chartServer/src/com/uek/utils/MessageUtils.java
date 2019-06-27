package com.uek.utils;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class MessageUtils {
	public static void sendMessage(Socket socket , String line)
	{
		PrintStream ps;
		try {
			ps = new PrintStream(socket.getOutputStream());
			ps.println(line);
			ps.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
