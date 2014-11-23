package sample.selenium.spark.taskmanager.model;

import java.sql.Timestamp;

public class Project {

	private int id;
	/**
	 * プロジェクトの名前
	 */
	private String title;

	private Timestamp created_at;
	private Timestamp updated_at;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public Project(String name) {
		this.title = name;
	}

	public Project() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Project [name=" + title + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
