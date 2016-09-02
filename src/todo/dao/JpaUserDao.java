package todo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import todo.model.User;

@Repository
public class JpaUserDao implements UserDao {
	
	@PersistenceContext
	EntityManager manager;

	@SuppressWarnings("unchecked")
	public boolean userExist(User user) {

		if (user == null) {
			throw new IllegalArgumentException("User cannot be null");
		}

		try {
			Query q = manager
			.createQuery("SELECT u FROM User u WHERE u.login LIKE :login AND u.password LIKE :password")
			.setParameter("login", user.getLogin())
			.setParameter("password", user.getPassword());

			List<User> listUser = q.getResultList();
			
			if (listUser.size() >= 1) {
				return true;
			}
			return false;

		} catch (PersistenceException pe) {
			throw new RuntimeException(pe);
		}
	}
}
