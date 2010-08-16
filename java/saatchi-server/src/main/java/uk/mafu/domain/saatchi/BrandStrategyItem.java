package uk.mafu.domain.saatchi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.IndexColumn;
import uk.mafu.loon.domain.data.ImageLink;

@Entity
public class BrandStrategyItem implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@Column
	private String title;
	@Column(length = 2147483647)
	private String quoteBody;
	@Column(length = 2147483647)
	private String quoteReference;
	@Column(length = 2147483647)
	private String body;
	@Column
	private String permalink;
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<ImageLink> images = new ArrayList<ImageLink>();

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuoteBody() {
		return quoteBody;
	}

	public void setQuoteBody(String quoteBody) {
		this.quoteBody = quoteBody;
	}

	public String getQuoteReference() {
		return quoteReference;
	}

	public void setQuoteReference(String quoteReference) {
		this.quoteReference = quoteReference;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public List<ImageLink> getImages() {
		return images;
	}

	public void setImages(List<ImageLink> images) {
		this.images = images;
	}
}
