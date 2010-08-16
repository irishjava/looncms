package uk.gormley.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Catalogue implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(length = 2147483647)
	private String contributors;

	@Id
	private String pk = UUID.randomUUID().toString();

	@Column(length = 2147483647)
	private String publisher;

	@Column
	private String title;

	@Column
	private String type;

	@Column
	private Date date;

	public String getContributors() {
		return this.contributors;
	}

	public String getPk() {
		return this.pk;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public String getTitle() {
		return this.title;
	}

	public String getType() {
		return this.type;
	}

	public Date getDate() {
		return this.date;
	}

	public void setContributors(String contributors) {
		this.contributors = contributors;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDate(Date date) {
		this.date = date;
	}
} 