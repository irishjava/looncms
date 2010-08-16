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
import javax.persistence.OneToOne;
import org.hibernate.annotations.IndexColumn;
import uk.mafu.loon.domain.data.ImageLink;

@Entity
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@Column
	private String title;
	@Column
	private String permalink;
	@OneToOne(optional = true)
	private ImageLink logo;
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

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public ImageLink getLogo() {
		return logo;
	}

	public void setLogo(ImageLink logo) {
		this.logo = logo;
	}

	public List<ImageLink> getImages() {
		return images;
	}

	public void setImages(List<ImageLink> images) {
		this.images = images;
	}
}
