package sample.selenium.spark.taskmanager.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import sample.selenium.dbutils.DBConnectionManager;
import sample.selenium.spark.taskmanager.model.Project;

public class ProjectsDao {
	private Connection connection;

	public ProjectsDao() {
		connection = DBConnectionManager.getInstance().getConnnction();
	}

	/**
	 * Projectsƒe[ƒuƒ‹‚Ìî•ñ‚ğ‚·‚×‚Äæ“¾‚·‚é
	 * 
	 * @return
	 */
	public List<Project> getAllProjects() {
		QueryRunner queryRunner = new QueryRunner();
		// ResultSetHandler<List<Object[]>> handler = new ArrayListHandler();

		List<Project> list = null;
		try {
			list = queryRunner.query(connection, "select * from projects",
					new BeanListHandler<Project>(Project.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}
