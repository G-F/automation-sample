package sample.selenium.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import sample.selenium.spark.taskmanager.model.Project;

public class DBTest {
	
	public static void main(String[] args) {
		new DBTest().connectTest("example");
		
//		Connection temp;
//		try {
//			Class.forName("org.sqlite.JDBC");
//			temp = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\masao\\Documents\\WORKSPACE\\workspace_selenium_sample\\example");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
	public Connection con = null;

	public void connectTest(String dbpass) {
		if (dbpass.equals("example")) {
			dbpass = "jdbc:sqlite:C:\\Users\\masao\\Documents\\WORKSPACE\\workspace_selenium_sample\\example";
			System.out.println("ƒeƒXƒgDB");
		} else {
			dbpass = "jdbc:sqlite:C:\\Users\\masao\\Documents\\WORKSPACE\\workspace_selenium_sample\\task.sqlite3";
		}
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

		QueryRunner queryRunner = new QueryRunner();
		//List<Project> list = null;
		Project project = null;
		try {
			project = queryRunner.query(con,
					"select title,id from project where id = 1",
					new BeanHandler<Project>(Project.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ;
		}

		System.out.println(project);
		// for (Project project : list) {
		// System.out.println(project.getTitle());
		// }

		// QueryRunner queryRunner = new QueryRunner();
//		ResultSetHandler<List<Object[]>> handler = new ArrayListHandler();
//		List<Object[]> result = null;
//		try {
//			result = queryRunner.query(con, "select * from projects", handler);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		for (Object[] obj : result) {
//			System.out.println(obj[0]);
//		}
	}

}
