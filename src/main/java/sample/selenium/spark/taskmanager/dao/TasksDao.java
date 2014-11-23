package sample.selenium.spark.taskmanager.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import sample.selenium.dbutils.DBConnectionManager.DBConnectionManagerException;
import sample.selenium.spark.taskmanager.model.Task;

public class TasksDao extends AbstractDao{

	public List<Task> getTasksByProjectId(String projectId) {
		QueryRunner queryRunner = new QueryRunner();
		List<Task> results = null;
		
		try {
			results = queryRunner.query(dbConnectionManager.open(),"select * from tasks where project_id = ?", new BeanListHandler<Task>(Task.class), projectId);
		    dbConnectionManager.close();
		} catch (SQLException | DBConnectionManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;
	}
	
	

}
