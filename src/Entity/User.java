package Entity;

import java.sql.SQLException;

import Util.DBUtil;

import com.mysql.jdbc.Util;

public class User {
	private  String username;
	private  String password;
	//
	private  DBUtil db = new DBUtil();  
	public  User(){
		/*this.username = username;
		this.password = password;*/
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void regisit(String username,String password) throws SQLException{
		if(db.Reguser(username,password)){
			System.out.println("already regisit");
		}
	}
    public  boolean LoginJudge(String username,String password) throws SQLException{
    	    String pass = db.SearchUser(username);
    	  
    	    System.out.println(pass);
    	    if(pass.equals(password)){
    	    	System.out.println(pass.equals(password));
    	    	return true;
    	    }
    	    else{
    	    return false;
    	    }
    }
}
