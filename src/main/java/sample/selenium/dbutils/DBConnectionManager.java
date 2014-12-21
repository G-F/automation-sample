package sample.selenium.dbutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionManager {

	private static DBConnectionManager instance = new DBConnectionManager();
	private Connection con;
	private Properties properties = new Properties();

	public class DBConnectionManagerException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4561233446946462138L;

	}

	// private DBConnectionManager() {
	// String dbpass =
	// "jdbc:sqlite:C:\\Users\\masao\\Documents\\WORKSPACE\\workspace_selenium_sample\\task.sqlite3";
	// try {
	// Class.forName("org.sqlite.JDBC");
	// con = DriverManager.getConnection(dbpass);
	// } catch (ClassNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public static DBConnectionManager getInstance() {
		return instance;
	}

	public Connection getConnnction() {
		return con;
	}

	public Connection open() throws DBConnectionManagerException, IOException {

		int count = 0;
		while (con != null) {
			try {
				wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (count == 100) {
				throw new DBConnectionManagerException();
			}
		}

		try (InputStream inputStream = new FileInputStream(
				new File("taskmanager.properties"))) {
			properties.load(inputStream);
		}

		String dbpass = "jdbc:sqlite:C:\\Users\\masao\\Documents\\WORKSPACE\\workspace_selenium_sample\\task.sqlite3";
		dbpass = properties.getProperty("dbpass");
		System.out.println(dbpass);
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
		return con;
	}

	public void close() {
		try {
			con.close();
			con = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
