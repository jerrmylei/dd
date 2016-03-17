package Socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String args[]) throws UnknownHostException, IOException{
		Socket s = new Socket("127.0.0.1",20000);
		new ClientThread(s).start();
		
		
	}

}
