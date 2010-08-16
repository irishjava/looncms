package uk.gormley.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import uk.gormley.RetainInSearch;
import uk.mafu.loon.domain.data.ImageLink;

@Entity
@Indexed
@Analyzer(impl = StandardAnalyzer.class)
public class Art extends AbstractPdfItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Column
	private Date date;

	// image
	@OneToOne(optional = true)
	@JoinColumn(name = "image_fk")
	private ImageLink image;

	@ManyToMany(fetch = FetchType.EAGER,cascade={CascadeType.REMOVE})
	@IndexColumn(name = "position", base = 0)
	private List<ImageLink> images = new ArrayList<ImageLink>();

	@Column
	@RetainInSearch
	private String type;

	public Date getDate() {
		return this.date;
	}

	public ImageLink getImage() {
		return this.image;
	}

	public List<ImageLink> getImages() {
		return this.images;
	}

	public String getType() {
		return this.type;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setImage(ImageLink image) {
		this.image = image;
	}

	public void setImages(List<ImageLink> images) {
		this.images = images;
	}

	public void setType(String type) {
		this.type = type;
	}
}
