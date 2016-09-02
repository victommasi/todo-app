package todo.dao;

import todo.model.User;

public interface UserDao {
	
	boolean userExist(User u);
}
