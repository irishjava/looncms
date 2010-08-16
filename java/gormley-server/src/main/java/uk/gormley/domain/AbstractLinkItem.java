package uk.gormley.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import uk.gormley.RetainInSearch;

@Entity
@Indexed
@Analyzer(impl=StandardAnalyzer.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AbstractLinkItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@DocumentId
	@RetainInSearch
	protected String pk = UUID.randomUUID().toString();
	
	@Field(index = Index.TOKENIZED, store = Store.YES)
	@Column(length = 2147483647)
	private String text;
	@Column
	@Field(index = Index.TOKENIZED, store = Store.YES)
	@RetainInSearch
	protected String title;
	@Column
	private String url1;
	@Column
	private String url2;
	@Column
	private String url3;
	@Column
	private String url4;
	@Column
	private String url5;
	@Column
	private String url6;

	public String getPk() {
		return this.pk;
	}

	public String getText() {
		return this.text;
	}

	public String getTitle() {
		return this.title;
	}

	public String getUrl1() {
		return this.url1;
	}

	public String getUrl2() {
		return this.url2;
	}

	public String getUrl3() {
		return this.url3;
	}

	public String getUrl4() {
		return this.url4;
	}

	public String getUrl5() {
		return this.url5;
	}

	public String getUrl6() {
		return this.url6;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public void setUrl3(String url3) {
		this.url3 = url3;
	}

	public void setUrl4(String url4) {
		this.url4 = url4;
	}

	public void setUrl5(String url5) {
		this.url5 = url5;
	}

	public void setUrl6(String url6) {
		this.url6 = url6;
	}
}