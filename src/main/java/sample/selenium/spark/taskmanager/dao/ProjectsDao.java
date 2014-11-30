package sample.selenium.spark.taskmanager.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import sample.selenium.dbutils.DBConnectionManager.DBConnectionManagerException;
import sample.selenium.spark.taskmanager.model.Project;
import sample.selenium.spark.taskmanager.model.Task;

public class ProjectsDao extends AbstractDao {
	/**
	 * Projectsテーブルからすべてのプロジェクトを取得する
	 * 
	 * @return
	 */
	public List<Project> getAllProjects() {
		QueryRunner queryRunner = new QueryRunner();
		// ResultSetHandler<List<Object[]>> handler = new ArrayListHandler();

		List<Project> list = null;
		try {
			list = queryRunner.query(dbConnectionManager.open(),
					"select * from projects", new BeanListHandler<Project>(
							Project.class));
		} catch (SQLException | DBConnectionManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbConnectionManager.close();
		}
		return list;
	}

	public Project getProjectbyId(String id) {
		QueryRunner queryRunner = new QueryRunner();
		Project result = null;

		try {
			result = queryRunner.query(dbConnectionManager.open(),
					"select * from projects where id = ?",
					new BeanHandler<Project>(Project.class), id);
		} catch (SQLException | DBConnectionManagerException e) {
			e.printStackTrace();
		} finally {
			dbConnectionManager.close();
		}
		return result;

	}

	public void insertByTitle(String title) {

		QueryRunner queryRunner = new QueryRunner();

		try {
			queryRunner.update(dbConnectionManager.open(),
					"insert into projects(title) values(?)", title);
		} catch (SQLException | DBConnectionManagerException e) {
			e.printStackTrace();
		} finally {
			dbConnectionManager.close();
		}

	}
}
