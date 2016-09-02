package todo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import todo.model.Todo;

@Repository
public class JdbcTodoDao {
	
	private final Connection connection;

	@Autowired
	public JdbcTodoDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void add(Todo todo) {
		String sql = "insert into todos (description, finished) values (?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, todo.getDescription());
			stmt.setBoolean(2, todo.isFinished());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Todo todo) {

		if (todo.getId() == null) {
			throw new IllegalStateException("Todo Id cannot be null.");
		}

		String sql = "delete from todos where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, todo.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Todo todo) {
		String sql = "update todos set description = ?, finished = ?, finishDate = ? where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, todo.getDescription());
			stmt.setBoolean(2, todo.isFinished());
			stmt.setDate(3, todo.getFinishDate() != null ? new Date(
					todo.getFinishDate().getTimeInMillis()) : null);
			stmt.setLong(4, todo.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Todo> list() {
		try {
			List<Todo> todo = new ArrayList<Todo>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from todos");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// add todo on list
				todo.add(populateTodo(rs));
			}

			rs.close();
			stmt.close();

			return todo;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Todo findById(Long id) {

		if (id == null) {
			throw new IllegalStateException("Todo Id cannot be null.");
		}

		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from todos where id = ?");
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return populateTodo(rs);
			}

			rs.close();
			stmt.close();

			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void finish(Long id) {

		if (id == null) {
			throw new IllegalStateException("Todo Id cannot be null.");
		}

		String sql = "update todos set finished = ?, finishDate = ? where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setLong(3, id);
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Todo populateTodo(ResultSet rs) throws SQLException {
		Todo todo = new Todo();

		// populates todo object
		todo.setId(rs.getLong("id"));
		todo.setDescription(rs.getString("description"));
		todo.setFinished(rs.getBoolean("finished"));

		// populates finishDate of todo, making conversion
		Date date = rs.getDate("finishDate");
		if (date != null) {
			Calendar finishDate = Calendar.getInstance();
			finishDate.setTime(date);
			todo.setFinishDate(finishDate);
		}
		return todo;
	}
}
