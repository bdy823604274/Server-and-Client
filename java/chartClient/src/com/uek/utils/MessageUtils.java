package com.uek.utils;

import java.io.IOException;
import java.io.PrintStream;

import com.uek.start.Client;


public class MessageUtils {

	public static void sendMessage(String line)
	{
		PrintStream ps;
		try {
			ps = new PrintStream(Client.clientSocket.getOutputStream());
			ps.println(line);
			ps.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
