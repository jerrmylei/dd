package Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Entity.User;
import Entity.UserFile;

public class ServerSoket {
	private User user;
	private UserFile userfile;
	
	public static void main(String args[]) throws IOException{
		ServerSocket ss = new ServerSocket(20000);
		Socket s;
		while(true){
			if((s = ss.accept()) != null){
			   System.out.println("clent  is loading!");
				new ServerThread(s).start();
			}
		}
	}
	

}
