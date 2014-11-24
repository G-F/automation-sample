package sample.selenium.spark.taskmanager.model;

import java.sql.Timestamp;

public class Task {
	private int id;
	private String title;
	private boolean done;
	private int project_id;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	public Task() {
	}
	
	public Task(int project_id, String title) {
		this.project_id = project_id;
		this.title = title;
		this.created_at = new Timestamp(System.currentTimeMillis());
		this.updated_at = new Timestamp(System.currentTimeMillis());
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
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

}
