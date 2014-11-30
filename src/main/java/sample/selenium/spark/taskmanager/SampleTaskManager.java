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

		get("/project/new", (req, res) -> {
			// FIXME project/:idより前に書く必要あり⇒解決策ない?
			// FIXME モデル渡す必要ないなら静的ファイルに送ってもよいかも
				return new ModelAndView(new HashMap<String, Object>(),
						"/projects/new.vm");
			}, new VelocityTemplateEngine());

		// 新しいプロジェクトを作成する
		post("project/create", (req, res) -> {

			projectsDao.insertByTitle(req.queryParams("title"));
			res.redirect("/");
			return null;
		});

		// 既存のプロジェクトの編集画面へ遷移する
		get("/project/:id/edit", (req, res) -> {
			Project project = projectsDao.getProjectbyId(req.params(":id"));
			HashMap<String, Object> model = makeProjectModel(project);

			return new ModelAndView(model, "/projects/edit.vm");
		}, new VelocityTemplateEngine());

		//プロジェクトを更新する
		post("/project/:id/update", (req, res) -> {
			projectsDao.updateTitleByID(req.params(":id"),req.queryParams("title"));
			res.redirect("/");
			return null;
		});

		// 既存のプロジェクトを削除する
		get("/project/:id/delete",(req,res) -> {
			projectsDao.deleteById(req.params(":id"));
			tasksDao.deleteByProjectId(req.params(":id"));
			res.redirect("/");
			return null;
		});

		// プロジェクトの画面を表示する。パラメータはプロジェクトid
		get("/project/:id", (req, res) -> {
			// パラメータを取得
				String id = req.params(":id");
				// パラメータからプロジェクトを取得
				Project project = projectsDao.getProjectbyId(id);

				HashMap<String, Object> model = makeProjectModel(project);
				List<Task> tasks = tasksDao.getTasksByProjectId(id);
				model.put("tasks", tasks);
				return new ModelAndView(model, "/projects/project.vm");
			}, new VelocityTemplateEngine());

		get("/hello/:dbpass", (req, res) -> {
			req.params(":dbpass");
			new DBTest().connectTest(req.params(":dbpass"));
			return "Hello World";
		});

		// taskを新規作成する
		post("/project/:id/newtask",
				(req, res) -> {
					// 新しいタスクを作ってリダイレクトすればよい
					Task task = new Task(Integer.parseInt(req.params("id")),
							req.queryParams("title"));

					tasksDao.insert(task);
					res.redirect("/project/" + req.params("id"));
					return null;
				});

		// FIXME 本当はdeleteメソッドで作成したい。その場合のパスは/project/:projectId/task/:taskId
		// タスクを削除する
		get("/project/:projectId/task/:taskId/delete", (req, res) -> {

			tasksDao.deleteByID(Integer.parseInt(req.params("taskId")));

			res.redirect("/project/" + req.params("projectId"));
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

	// FIXME この下のメソッドはクラスに切り分けたい
	private static HashMap<String, Object> makeProjectModel(Project project) {
		HashMap<String, Object> model = new HashMap<String, Object>();
		makeProjectModel(project, model);
		return model;
	}

	private static void makeProjectModel(Project project,
			HashMap<String, Object> model) {
		if (project == null) {
			throw new NotFoundException();
		}
		model.put("project", project);
	}

}
