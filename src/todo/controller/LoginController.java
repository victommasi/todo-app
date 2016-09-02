package todo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import todo.dao.JdbcUserDao;
import todo.dao.JpaUserDao;
import todo.dao.TodoDao;
import todo.dao.UserDao;
import todo.model.User;

@Controller
public class LoginController {
	
	@Autowired
	@Qualifier("jpaUserDao")
	UserDao dao;
	
	@RequestMapping("loginForm")
	public String loginForm(){
		return "todo/login-form";
	}
	
	@RequestMapping("doLogin")
	public String doLogin(User user, HttpSession session){
		if(dao.userExist(user)){
			session.setAttribute("userLogged", user);
			return "menu";
		}
		return "redirect:loginForm";
	}
	
	@RequestMapping("doLogout")
	public String doLogout(HttpSession session){
		session.invalidate();
		return "redirect:loginForm";
	}
	
	
}
