package uk.mafu.kwa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import uk.mafu.loon.domain.data.ImageLink;

@Entity
@Table(name = "projectslides")
public class ProjectSlide implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@Column
	public String title;
	@Column
	public String caption;
	@OneToOne(optional = true)
	public Excite excite;
	@OneToOne(optional = true)
	public ImageLink image;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCaption() {
		return caption;
	}

	public Excite getExcite() {
		return excite;
	}

	public ImageLink getImage() {
		return image;
	}

	public int getPk() {
		return pk;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public void setExcite(Excite excite) {
		this.excite = excite;
	}

	public void setImage(ImageLink image) {
		this.image = image;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}
}
