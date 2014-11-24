package sample.selenium.spark.taskmanager;

import static spark.Spark.*;
import sample.selenium.dbutils.*;
import sample.selenium.spark.taskmanager.dao.ProjectsDao;
import sample.selenium.spark.taskmanager.dao.TasksDao;
import sample.selenium.spark.taskmanager.exception.NotFoundException;
import sample.selenium.spark.taskmanager.model.Project;
import sample.selenium.spark.taskmanager.model.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class SampleTaskManager {
	private static ProjectsDao projectsDao = new ProjectsDao();
	private static TasksDao tasksDao = new TasksDao();

	public static void main(String[] args) {

		// FIXME ここではプロジェクトのタイトルだけ取得する方がよいかも
		// トップ画面(プロジェクト一覧)を表示する
		get("/", (req, res) -> {
			List<Project> projects;
			projects = projectsDao.getAllProjects();
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("projects", projects);
			return new ModelAndView(model, "/projects/index.vm");
		}, new VelocityTemplateEngine());

		// プロジェクトの画面を表示する。パラメータはプロジェクトid
		get("/project/:id", (req, res) -> {
			HashMap<String, Object> model = new HashMap<String, Object>();
			// パラメータを取得
				String id = req.params(":id");

				// パラメータからプロジェクトを取得
				Project project = projectsDao.getProjectbyId(id);
				if (project == null) {
					throw new NotFoundException();
				}

				model.put("project", project);
				List<Task> tasks = tasksDao.getTasksByProjectId(id);
				model.put("tasks", tasks);
				return new ModelAndView(model, "/projects/project.vm");
			}, new VelocityTemplateEngine());

		get("/hello/:dbpass", (req, res) -> {
			req.params(":dbpass");
			new DBTest().connectTest(req.params(":dbpass"));
			return "Hello World";
		});

		get("/template", (req, res) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("hello", "Hello World");
			return new ModelAndView(model, "hello.wm");
		}, new VelocityTemplateEngine());

		get("/template2", (req, res) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("hello", "Hello World");
			return new ModelAndView(model, "test/hello2.vm");
		}, new VelocityTemplateEngine());

		post("/project/:id/newtask",
				(req, res) -> {
					// 新しいタスクを作ってリダイレクトすればよい
					Task task = new Task(Integer.parseInt(req.params("id")),
							req.queryParams("title"));

					tasksDao.insert(task);
					res.redirect("/project/" + req.params("id"));
					return null;
				});

		exception(NotFoundException.class, (e, req, res) -> {
			res.status(404);
			res.body("Not Found");

		});

		exception(RuntimeException.class, (e, req, res) -> {
			e.printStackTrace();
			stop();
		});
	}
}
