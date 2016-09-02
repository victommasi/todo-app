package todo.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import todo.model.Todo;

@Repository
public class JpaTodoDao implements TodoDao {

	@PersistenceContext
	EntityManager manager;
	
	public void add(Todo todo) {
		manager.persist(todo);
	}

	public void update(Todo todo) {
		manager.merge(todo);
	}

	public void remove(Todo todo) {
		Todo todoRemover = findById(todo.getId());
		manager.remove(todoRemover);
	}
	
	public Todo findById(Long id) {
		return manager.find(Todo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Todo> list() {
		return manager.createQuery("select t from Todo t").getResultList();
	}

	public void finish(Long id) {
		Todo todo = findById(id);
		todo.setFinished(true);
		todo.setFinishDate(Calendar.getInstance());
	}

}
