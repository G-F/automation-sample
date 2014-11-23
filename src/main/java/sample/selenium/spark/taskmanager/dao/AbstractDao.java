package sample.selenium.spark.taskmanager.dao;

import sample.selenium.dbutils.DBConnectionManager;

public abstract class AbstractDao {
	protected DBConnectionManager dbConnectionManager;

	public AbstractDao() {
		dbConnectionManager = new DBConnectionManager();
	}

}
