package isa.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NamedQueries(
	@NamedQuery(name = "Developer.findAll", query = "SELECT dev FROM Developer dev")
)

@Entity
@Cacheable(true)
@Table(name = "developer")
public class Developer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "first_name")
	String firstName;

	@Column(name = "last_name")
	String lastName;

	@Column(name = "city")
	String city;

	@ManyToMany(
		mappedBy = "developers",
		fetch = FetchType.LAZY)
	Set<Task> tasks = new HashSet<>();

	@ManyToOne(
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY)
	@JoinColumn(name = "id_active_task")
	Task activeTask;

	@Version
	Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Task getActiveTask() {
		return activeTask;
	}

	public void setActiveTask(Task activeTask) {
		this.activeTask = activeTask;
	}

	public Long getVersion() {
		return version;
	}

	public String toString() {
		return "Developer id: " + this.id + " (" + this.firstName + ") - activeTask=" + getActiveTask() + ", tasks=" + getTasks();
	}
}
