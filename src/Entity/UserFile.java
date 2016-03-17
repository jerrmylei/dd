package Entity;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import Util.DBUtil;
public class UserFile {
	private String filename;
	private String filepath;
	private DBUtil db = new DBUtil();
	public UserFile(String filename,String filepath){
		this.filename = filename;
		this.filepath = filepath;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	

}
