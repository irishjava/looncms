package uk.gormley.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Analyzer(impl = StandardAnalyzer.class)
public class TextResource extends AbstractPdfItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private Date date;

	@Column
	private String filter;

	@Column(length = 2147483647)
	private String caption;

	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Date getDate() {
		return this.date;
	}

	public String getFilter() {
		return this.filter;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

}
