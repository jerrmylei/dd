package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread {
       Socket s;
       BufferedReader br;
       PrintStream ps;
       public ClientThread(Socket s) throws IOException{
    	   this.s = s;
    	   br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    	   ps = new PrintStream(s.getOutputStream());
       }
       public void run(){
    	   Scanner sc = new Scanner(System.in);
    	   String t1 = null;
    	   String t2 = null;
    	   String input;
		try {
			t1 = br.readLine();
			//t2 = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   System.out.println(t1);
    	 //  System.out.println(t2);
    	   try {
			/*if(br.readLine().equals("��ѡ�� 1 ע�� 2��½ 3�ļ��ϴ�")){
				System.out.println("��ѡ�� 1 ע�� 2��½ 3�ļ��ϴ�");
				input = sc.next();
				System.out.println(input);
				ps.println(input);
				   
			   
			else{*/
				while((t2 = br.readLine()) != null){
					System.out.println(t2);
					if(t2.equals("byby")){
						s.close();
						System.exit(0);
					}
					if(s == null ){
						s.close();
					}
					ps.println(sc.next());
				}
				
			//}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   
    	   
       }

}
