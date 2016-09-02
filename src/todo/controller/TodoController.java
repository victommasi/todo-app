package todo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import todo.dao.TodoDao;
import todo.model.Todo;

@Transactional
@Controller
public class TodoController {
	
	@Autowired
	@Qualifier("jpaTodoDao")
	TodoDao dao;
	
	@RequestMapping("newTodo")
	public String form(){
		return "todo/form";
	}
	
	@RequestMapping("addTodo")
	public String add(@Valid Todo todo, BindingResult result){
		
		if(result.hasFieldErrors("description")){
			return "todo/form";
		}
		dao.add(todo);
		return "todo/added";
	}
	
	@RequestMapping("listTodos")
	public String list(Model model){
		List<Todo> todos = dao.list();
		
		model.addAttribute("todos", todos);
		return "todo/list";
	}
	
	@RequestMapping("removeTodo")
	public String remove(Todo todo){
		dao.remove(todo);
		return "redirect:listTodos";
	}
	
	@RequestMapping("showTodo")
	public String show(Long id, Model model){
		model.addAttribute("todo", dao.findById(id));
		return "todo/show";
	}
	
	@RequestMapping("updateTodo")
	public String update(Todo todo) {
		dao.update(todo);
		return "redirect:listTodos";
	}
	
	@RequestMapping("finishTodo")
	public String finish(Long id, Model model){
		dao.finish(id);
		model.addAttribute("todo", dao.findById(id));
		return "todo/finished";
	}
}

