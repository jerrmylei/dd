package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class DBUtil {

	public Connection getCon() {
		String url = "jdbc:mysql://localhost:3306/fileuplode";
		String username = "root";
		String password = "root";
		Connection con;
		try {
			con = (Connection) DriverManager.getConnection(url, username,
					password);
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public java.sql.Statement Connection() throws SQLException {
		DBUtil jdbc = new DBUtil();
		Connection conn = jdbc.getCon();
		java.sql.Statement s = conn.createStatement();

		return s;

	}

	public String SearchUser(String username) throws SQLException {
		java.sql.Statement s = Connection();
		String sql = "select * from user where username=" + "'" + username
				+ "'";
		ResultSet rs = s.executeQuery(sql);
		if (rs.next()) {
			return rs.getString("password");
		}
		return null;
	}

	public boolean Reguser(String username, String password)
			throws SQLException {
		String sql = "insert into user (username,password) values('" + username
				+ "','" + password + "');";
		java.sql.Statement s = Connection();
		//
		try {
			if (s.executeUpdate(sql) > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	
	public void uplode(String path,String fileName,String username) throws SQLException, FileNotFoundException{
		Connection conn = getCon();
		FileInputStream  fs = new FileInputStream(path);
		File ff = new File(path);
		String sql = "insert into file (filename,content,username) values (?,?,?);";
		java.sql.PreparedStatement ps = null;
		ps = conn.prepareStatement(sql);
		ps.setString(1, fileName);
		ps.setBinaryStream(2, fs, ff.length());
		ps.setString(3, username);
		if( (ps.executeUpdate() ) > 0);
		System.out.println("lode  successful!");
		
		
	}
}
