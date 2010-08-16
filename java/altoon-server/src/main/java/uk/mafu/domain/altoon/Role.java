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
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column
	private String singular;
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Person> members = new ArrayList<Person>();
	@Column
	private String title;
	// permalink
	@Column
	private String permalink;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	public String getSingular() {
		return singular;
	}
	public void setSingular(String singular) {
		this.singular = singular;
	}
	public List<Person> getMembers() {
		return members;
	}
	public void setMembers(List<Person> members) {
		this.members = members;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public String getPermalink() {
		return permalink;
	}



}
