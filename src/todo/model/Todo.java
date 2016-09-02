package todo.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Todo {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull (message="{todo.description.empty}")
	@Size (min=5, message="{todo.description.small}")
	private String description;
	
	private boolean finished;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar finishDate;
	
	public Todo(){}
	
	//gas
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public Calendar getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Calendar finishDate) {
		this.finishDate = finishDate;
	}

}
