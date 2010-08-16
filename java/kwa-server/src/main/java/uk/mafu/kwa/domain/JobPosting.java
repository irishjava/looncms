package uk.mafu.kwa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jobpostings")
public class JobPosting implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;

	@Column(length = 125000)
	public String text1;

	@Column
	public String title;

	@Column
	public String permalink;

	public String getPermalink() {
		return permalink;
	}

	public int getPk() {
		return pk;
	}

	public String getText1() {
		return text1;
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

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
