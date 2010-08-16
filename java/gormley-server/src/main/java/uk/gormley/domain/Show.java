package uk.gormley.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import uk.mafu.loon.domain.data.ImageLink;

@Entity
@Indexed
@Analyzer(impl = StandardAnalyzer.class)
public class Show extends AbstractStripItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private Date endDate;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "show_images")
	@IndexColumn(name = "position", base = 0)
	private List<ImageLink> images = new ArrayList<ImageLink>();

	@Column(nullable = true)
	private boolean permanent;

	
	@Column
	private Date startDate;

	@Column
	private String type;

	public Date getEndDate() {
		return this.endDate;
	}

	public List<ImageLink> getImages() {
		return this.images;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public String getType() {
		return this.type;
	}

	public boolean isPermanent() {
		return permanent;
	}
	
	public boolean getPermanent() {
		return permanent;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setImages(List<ImageLink> images) {
		this.images = images;
	}

	public void setPermanent(boolean permanent) {
		this.permanent = permanent;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setType(String type) {
		this.type = type;
	}
}