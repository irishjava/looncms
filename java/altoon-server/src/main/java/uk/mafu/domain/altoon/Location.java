package uk.mafu.domain.altoon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.IndexColumn;

@Entity
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column
	private Double latitude;
	@Column
	private Double longitude;
	@Column
	private String name;
	// permalink
	@Column
	private String permalink;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Project> projects = new ArrayList<Project>();

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public String getName() {
		return name;
	}

	public String getPermalink() {
		return permalink;
	}

	public int getPk() {
		return pk;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("unused")
	private void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
}