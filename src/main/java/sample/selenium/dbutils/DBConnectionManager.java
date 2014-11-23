package sample.selenium.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

	private static DBConnectionManager instance = new DBConnectionManager();
	private Connection con;

	private DBConnectionManager() {
		String dbpass = "jdbc:sqlite:C:\\Users\\masao\\Documents\\WORKSPACE\\workspace_selenium_sample\\task.sqlite3";
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(dbpass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static DBConnectionManager getInstance() {
		return instance;
	}

	public Connection getConnnction() {
		return con;
	}
}
