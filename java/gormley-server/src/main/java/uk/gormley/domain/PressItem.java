package uk.gormley.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Analyzer(impl=StandardAnalyzer.class)
public class PressItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private String author;
	
	@Column
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Id
	@DocumentId
	private String pk = UUID.randomUUID().toString();

	@Column(length = 2147483647)
	private String publication;

	@Column
	private String url;

	@Column
	private Date date;

	public String getAuthor() {
		return this.author;
	}

	public String getPk() {
		return this.pk;
	}

	public String getPublication() {
		return this.publication;
	}

	public String getUrl() {
		return this.url;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	 
}
