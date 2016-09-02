package todo.dao;

import java.util.List;
import todo.model.Todo;

public interface TodoDao {
	
	Todo findById(Long id);
	List<Todo> list();
	void add(Todo t);
	void update(Todo t);
	void remove(Todo t);
	void finish(Long id);
}
