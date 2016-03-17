package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;

import Util.DBUtil;
import Entity.User;

public class ServerThread extends Thread {
	private BufferedReader br;
	private PrintStream ps;
	private Socket s;
	private User user;
	private DBUtil db = new DBUtil();

	public ServerThread(Socket s) throws IOException {
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		ps = new PrintStream(s.getOutputStream());
	}

	public void doservices() throws IOException, SQLException {
		String s1 = "��ӭ��½�ļ��ϴ�ϵͳ";
		ps.println(s1);
		ps.flush();
		String s2 = "��ѡ�� 1 ע�� 2��½ 3�ļ��ϴ�";
		ps.println(s2);
		ps.flush();
		String option = br.readLine();
		while(true){
		switch (option) {
		case "1":
			System.out.println("1");
			regisit();
			break;
		case "2":
			lode();
			break;
		case "3":
			Fileup();
			break;
		default:
			ps.println("��������ȷ��ѡ��");
			continue;
		}
		}

	}

	public void regisit() throws IOException, SQLException {
		ps.println("�������û���");
		ps.flush();
		String username;
		String password1;
		String password2;
		username = br.readLine();
		System.out.println("input username!");
		ps.println("����������");
		ps.flush();
		if ((password1 = br.readLine()) != null) {
			System.out.println("password1 input");
		}
		ps.println("��������һ������");
		ps.flush();
		if ((password2 = br.readLine()) != null && password2.equals(password1)) {
			System.out.println("two password compare");
			user = new User();
			user.regisit(username, password1);
			lode();
		} else {
			ps.println("�������벻ƥ�䣬��������һ��");
			ps.flush();
			if ((password2 = br.readLine()) != null
					&& password2.equals(password1)) {
				System.out.println("two password compare");
				Fileup();
			} else {
				ps.println("���벻ƥ�䣬������ע��");
				ps.flush();
				s.close();
			}
		}

	}

	public void lode() throws IOException, SQLException {
		String name;
		String password;
		ps.println("�������û���");
		ps.flush();
		name = br.readLine();
		ps.println("����������");
		password = br.readLine();
		user = new User();
		if ((user.LoginJudge(name, password))) {
			user = new User();
			user.setPassword(name);
			user.setPassword(password);
			Fileup();
		} else {
			ps.println("���������������һ��");
			ps.flush();
			password = br.readLine();
			if ((user.LoginJudge(name, password))) {
				
				Fileup();
			}
			else{
				s.close();
				System.exit(0);
			}
		}

	}

	public void Fileup() throws IOException, SQLException {
		ps.println("������Ҫ�ϴ��ļ��ľ���·��");
		ps.flush();
		String path = br.readLine();
		String fileName = getfileName(path);
		System.out.println(fileName);
		db.uplode(path, fileName, user.getUsername());
		ps.println("byby");
		ps.flush();
		s.close();
		System.exit(0);
		
		
	}
	public String getfileName(String path)
	{
		int index = path.lastIndexOf("\\");
		System.out.println(index);
		return path.substring(index+1, path.length());
		
				
	}

	public void run() {
		try {
			doservices();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
