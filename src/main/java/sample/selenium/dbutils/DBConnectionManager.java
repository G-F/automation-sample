package sample.selenium.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

	public class DBConnectionManagerException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4561233446946462138L;

	}

	private static DBConnectionManager instance = new DBConnectionManager();
	private Connection con;

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

	public Connection open() throws DBConnectionManagerException {

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
