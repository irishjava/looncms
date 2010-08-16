package uk.mafu.domain.altoon;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Award implements Serializable {
	private static final long serialVersionUID = 1L;
	// permalink
	@Column
	private String permalink;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@OneToOne
	private Project project;
	@Column(length = 2147483647)
	private String text;
	@Column
	private String title;
	@Column
	private Date date;

	public String getPermalink() {
		return permalink;
	}

	public int getPk() {
		return pk;
	}

	public Project getProject() {
		return project;
	}

	public String getText() {
		return text;
	}

	public String getTitle() {
		return title;
	}

	@SuppressWarnings("unused")
	private void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}