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
		String s1 = "欢迎登陆文件上传系统";
		ps.println(s1);
		ps.flush();
		String s2 = "请选择 1 注册 2登陆 3文件上传";
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
			ps.println("请输入正确的选择");
			continue;
		}
		}

	}

	public void regisit() throws IOException, SQLException {
		ps.println("请输入用户名");
		ps.flush();
		String username;
		String password1;
		String password2;
		username = br.readLine();
		System.out.println("input username!");
		ps.println("请输入密码");
		ps.flush();
		if ((password1 = br.readLine()) != null) {
			System.out.println("password1 input");
		}
		ps.println("请再输入一次密码");
		ps.flush();
		if ((password2 = br.readLine()) != null && password2.equals(password1)) {
			System.out.println("two password compare");
			user = new User();
			user.regisit(username, password1);
			lode();
		} else {
			ps.println("输入密码不匹配，请再输入一遍");
			ps.flush();
			if ((password2 = br.readLine()) != null
					&& password2.equals(password1)) {
				System.out.println("two password compare");
				Fileup();
			} else {
				ps.println("密码不匹配，请重新注册");
				ps.flush();
				s.close();
			}
		}

	}

	public void lode() throws IOException, SQLException {
		String name;
		String password;
		ps.println("请输入用户名");
		ps.flush();
		name = br.readLine();
		ps.println("请输入密码");
		password = br.readLine();
		user = new User();
		if ((user.LoginJudge(name, password))) {
			user = new User();
			user.setPassword(name);
			user.setPassword(password);
			Fileup();
		} else {
			ps.println("密码错误请再输入一次");
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
		ps.println("请输入要上传文件的绝对路径");
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
