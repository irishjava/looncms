package uk.gormley.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import uk.mafu.loon.domain.data.ImageLink;

@Entity
@Indexed
@Analyzer(impl=StandardAnalyzer.class)
public class Slide implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(optional = true)
	@JoinColumn(name = "image")
	private ImageLink image;

	@Id
	@DocumentId
	private String pk = UUID.randomUUID().toString();

	@Column
	private String title;

	@Column
	private String url;

	public ImageLink getImage() {
		return this.image;
	}

	public String getPk() {
		return this.pk;
	}

	public String getTitle() {
		return this.title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setImage(ImageLink image) {
		this.image = image;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
