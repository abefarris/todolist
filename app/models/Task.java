package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import play.Logger;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.jpa.JPA;

@Entity
@SequenceGenerator(name = "task_seq", sequenceName = "task_seq")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
	public Long id;

	@Required
	@MaxLength(value = 255)
	@MinLength(value = 1)
	public String taskName;
	
	
	public static Page all() {
		return all(1, 10);
	}

	public static Page all(int page, int pageSize) {
		
        if(page < 1) page = 1;
        Long total = (Long)JPA.em()
            .createQuery("select count(t) from Task t")
            .getSingleResult();
        
		@SuppressWarnings("unchecked")
		List<Task> data = JPA.em()
			.createQuery("select t from Task t order by t.id desc")
			.setFirstResult((page - 1) * pageSize)
			.setMaxResults(pageSize)
			.getResultList();
			
		return new Page(data, total, page, pageSize);
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
	
    /**
     * Used to represent a task page.
     */	
	
	public static class Page {
        private final int pageSize;
        private final long totalRowCount;
        private final int pageIndex;
        private final List<Task> list;
        
        public Page(List<Task> data, long total, int page, int pageSize) {
            this.list = data;
            this.totalRowCount = total;
            this.pageIndex = page;
            this.pageSize = pageSize;
        }
        
        public long getTotalRowCount() {
            return totalRowCount;
        }
        
        public int getPageIndex() {
            return pageIndex;
        }
        
        public List<Task> getList() {
            return list;
        }
        
        public boolean hasPrev() {
            return pageIndex > 1;
        }
        
        public boolean hasNext() {
            return (totalRowCount/pageSize) >= pageIndex;
        }
        
        public String getDisplayXtoYofZ() {
            int start = ((pageIndex - 1) * pageSize + 1);
            int end = start + Math.min(pageSize, list.size()) - 1;
            return start + " to " + end + " of " + totalRowCount;
        }
        
	}
			

}
