package isa.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import java.util.HashSet;
import java.util.Set;

@Entity
@Cacheable
@Table(name = "task")
public class Task {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "title")
	@NotNull
	String title;

	@Column(name = "closed")
	@DefaultValue(value = "false")
	Boolean closed;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "developer_task",
		joinColumns = {@JoinColumn(name = "id_task")},
		inverseJoinColumns = {@JoinColumn(name = "id_developer")}
	)
	Set<Developer> developers = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "project_id")
	Project project;

	@Version
	Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Developer> getDevelopers() {
		return developers;
	}

	public void setDevelopers(Set<Developer> developers) {
		this.developers = developers;
	}

	public boolean addDeveloper(Developer dev) {
		return this.developers.add(dev);
	}

	public boolean removeDeveloper(Developer dev) {
		return this.developers.remove(dev);
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public boolean isClosed() {
		return this.closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public Long getVersion() {
		return version;
	}
}
