package uk.gormley.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import uk.gormley.RetainInSearch;

@Entity
@Indexed
@Analyzer(impl = StandardAnalyzer.class)
public class ArtGroup extends AbstractStripItem implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManyToMany(fetch = FetchType.EAGER)
	@IndexColumn(name = "position", base = 0)
	public List<Art> arts = new ArrayList<Art>();

	@Column
	@RetainInSearch
	private String artSubType;

	@Column
	@RetainInSearch
	private String artType;

	@Column
	private Date end;

	@Column
	private Date start;

	public List<Art> getArts() {
		return this.arts;
	}

	public String getArtSubType() {
		return this.artSubType;
	}

	public String getArtType() {
		return this.artType;
	}

	public Date getEnd() {
		return this.end;
	}

	public Date getStart() {
		return this.start;
	}

	public void setArts(List<Art> arts) {
		this.arts = arts;
	}

	public void setArtSubType(String artSubType) {
		this.artSubType = artSubType;
	}

	public void setArtType(String artType) {
		this.artType = artType;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public void setStart(Date start) {
		this.start = start;
	}
}