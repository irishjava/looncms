package uk.gormley.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import uk.mafu.loon.domain.data.ImageLink;

@Entity
@Indexed
@Analyzer(impl=StandardAnalyzer.class)
public class StudioView extends AbstractLinkItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private Date date;

	@OneToOne(optional = true)
	@JoinColumn(name = "image")
	private ImageLink image;

	public Date getDate() {
		return this.date;
	}

	public ImageLink getImage() {
		return this.image;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setImage(ImageLink image) {
		this.image = image;
	}
}