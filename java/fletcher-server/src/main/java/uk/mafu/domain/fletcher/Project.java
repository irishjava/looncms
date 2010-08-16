package uk.mafu.domain.fletcher;

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
import javax.persistence.OneToOne;
import org.hibernate.annotations.IndexColumn;
import uk.mafu.loon.domain.data.ImageLink;

@Entity
public class Project implements Serializable {
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

	@OneToOne(optional = true)
	private ImageLink thumb;
	@ManyToMany(fetch = FetchType.LAZY)
	@IndexColumn(name = "position", base = 0)
	private List<ImageLink> images = new ArrayList<ImageLink>();
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

	public ImageLink getThumb() {
		return thumb;
	}

	public void setThumb(ImageLink thumb) {
		this.thumb = thumb;
	}

	public List<ImageLink> getImages() {
		return images;
	}

	public void setImages(List<ImageLink> images) {
		this.images = images;
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
}