package tasks.controller;

//import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ResponseBody;

import tasks.dao.TaskDao;
import tasks.modelo.Task;

@Controller
public class TasksController {
	
	private final TaskDao dao;
	
	@Autowired
	public TasksController(TaskDao dao) {
//		dao = new TaskDao();
		this.dao = dao;
	}
	
	@RequestMapping("novatask")
	public String form() {
		return "tasks/form-tasks";
	}
	
	@RequestMapping("cadastratask")
	public String cadastra(@Valid Task task, BindingResult result) {
//		TaskDao dao = new TaskDao();
		if (result.hasFieldErrors("descricao")) {
			return "tasks/form-tasks";
		}
		dao.inserir(task);
		return "tasks/task-cadastrada";
	}
	
	@RequestMapping("gettasks")
	//primeira opção usando Model And View
//	public ModelAndView getTasks() {
//		List<Task> tasks = dao.getTasks();
//		ModelAndView mv = new ModelAndView("tasks/get-tasks");
//		mv.addObject("tasks", tasks);
//		return mv;
		public String getTasks(Model model) {
//			List<Task> tasks = dao.getTasks();
//			model.addAttribute(tasks);
			model.addAttribute("tasks", dao.getTasks());
//			return "tasks/get-tasks";
//			return "tasks/get-tasks-ajax";
//			return "tasks/get-tasks-ajax2";
//			return "tasks/get-tasks-ajax3";
			return "tasks/get-tasks-ajax4";
	}
	
	@RequestMapping("excluitask")
	public String exclui(Task task) {
		dao.exclui(task);
		//redirecionamento client side
		return "redirect:gettasks";
//		redirecionamento server side
//		return "forward:gettasks";
	}
	
	@RequestMapping("buscartask")
	public String busca(Long id, Model model) {
		model.addAttribute("task", dao.getById(id));
		return "tasks/busca-task";
	}
	
	@RequestMapping("editatask")
	public String edita(Task task) {
		dao.edita(task);
		return "redirect:gettasks";
	}
	
//	@ResponseBody
	@RequestMapping("finalizatask")
	public String finaliza(Long id, Model model) {
		dao.finaliza(id);
		model.addAttribute("task", dao.getById(id));
//		return "tasks/data-finalizada";
		return "tasks/data-finalizada2";
	}


	
	
	
	
	
}
