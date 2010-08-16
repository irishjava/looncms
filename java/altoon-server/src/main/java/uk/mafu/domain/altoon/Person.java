package uk.mafu.domain.altoon;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import uk.mafu.loon.domain.data.ImageLink;

@Entity
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(length = 2147483647)
	private String about;
	@OneToOne
	private ImageLink image;
	@Column
	private String name;
	@Column
	private String suffix;
	// permalink
	@Column
	private String permalink;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;

	public String getAbout() {
		return about;
	}

	public ImageLink getImage() {
		return image;
	}

	public String getName() {
		return name;
	}

	public String getPermalink() {
		return permalink;
	}

	public int getPk() {
		return pk;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public void setImage(ImageLink image) {
		this.image = image;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("unused")
	private void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}