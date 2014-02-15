package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import play.Logger;
import play.data.validation.Constraints.Required;
import play.db.jpa.JPA;

@Entity
@SequenceGenerator(name = "task_seq", sequenceName = "task_seq")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
	public Long id;

	@Required
	public String label;

	public static List<Task> all() {
		@SuppressWarnings("unchecked")
		List<Task> tasks = JPA.em()
	            .createQuery("select t from Task t")
	            .getResultList();
		return tasks;
	}

	public static void create(Task task) {
		JPA.em().persist(task);
	}

	public static void delete(Long id) {
		Task task = JPA.em().find(Task.class, id);
		JPA.em().remove(task);
		Logger.debug("Task deleted {}", task);		
	}
	
	public String toString(){
		 return ReflectionToStringBuilder.toString(this);
		
	}

}
