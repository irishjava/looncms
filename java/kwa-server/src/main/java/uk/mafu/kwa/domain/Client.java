package uk.mafu.kwa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private static final long serialVersionUID = 1L;
	@Column
	public String name;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	
	@OneToOne(optional = true)	
	public Project relatedProject;

	
	public int getPk() {
		return pk;
	}

	public Project getRelatedProject() {
		return relatedProject;
	}


	public void setPk(int pk) {
		this.pk = pk;
	}

	public void setRelatedProject(Project relatedProject) {
		this.relatedProject = relatedProject;
	}
}
