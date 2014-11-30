package sample.selenium.spark.taskmanager.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import sample.selenium.dbutils.DBConnectionManager.DBConnectionManagerException;
import sample.selenium.spark.taskmanager.model.Task;

public class TasksDao extends AbstractDao {

	public List<Task> getTasksByProjectId(String projectId) {
		QueryRunner queryRunner = new QueryRunner();
		List<Task> results = null;

		try {
			results = queryRunner.query(dbConnectionManager.open(),
					"select * from tasks where project_id = ?",
					new BeanListHandler<Task>(Task.class), projectId);
			dbConnectionManager.close();
		} catch (SQLException | DBConnectionManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return results;
	}

	public void insert(Task task) {
		QueryRunner queryRunner = new QueryRunner();

		try {
			queryRunner
					.update(dbConnectionManager.open(),
							"insert into tasks(title,project_id,created_at,updated_at) values(?,?,?,?)",
							task.getTitle(), task.getProject_id(), task
									.getCreated_at().getTime(), task
									.getUpdated_at());
		} catch (SQLException | DBConnectionManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbConnectionManager.close();
		}

	}

	/**
	 * タスクIDを取得して対象のタスクを削除する
	 * 
	 * @param params
	 */
	public void deleteByID(int taskID) {
		QueryRunner queryRunner = new QueryRunner();

		try {
			queryRunner.update(dbConnectionManager.open(),
					"delete from tasks where id = ? ", taskID);
		} catch (SQLException | DBConnectionManagerException e) {
			e.printStackTrace();
		} finally {
			dbConnectionManager.close();
		}
		//FIXME queryRunnerの返り値をチェックした方がよいかも

	}

}
