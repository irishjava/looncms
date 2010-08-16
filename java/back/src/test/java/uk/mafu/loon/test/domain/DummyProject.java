package uk.mafu.loon.test.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.IndexColumn;

@Entity
public class DummyProject implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@Column(length = 2147483647)
	private String title;
	@Column(length = 2147483647)
	private String text;
	@Column
	private String url;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@IndexColumn(name = "position", base = 0)
	private List<DummyClient> dummyClients = new ArrayList<DummyClient>();
	@Column
	private String permalink;

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	 

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<DummyClient> getClients() {
		return dummyClients;
	}

	public void setClients(List<DummyClient> dummyClients) {
		this.dummyClients = dummyClients;
	}
}