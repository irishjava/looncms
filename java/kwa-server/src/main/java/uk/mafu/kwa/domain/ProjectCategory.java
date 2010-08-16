package uk.mafu.kwa.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name = "projectcategories")
public class ProjectCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column
	public String permalink;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;

	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	public List<Project> projects = new ArrayList<Project>();
	
	
	@Column
	public String title;

	public String getPermalink() {
		return permalink;
	}

	public int getPk() {
		return pk;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public String getTitle() {
		return title;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
