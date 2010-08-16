package uk.mafu.kwa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import uk.mafu.loon.domain.data.ImageLink;

@Entity
@Table(name = "persons")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(length = 125000)
	public String bio;

	@OneToOne(optional = true)
	@JoinColumn(name = "image")
	public ImageLink image;

	@Column
	public String name;
	@Column
	public String permalink;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@Column
	public String shortName;
	@Column
	public String suffix;

	@OneToOne(optional = true)
	@JoinColumn(name = "texture")
	public ImageLink texture;

	@OneToOne(optional = true)
	public Excite excite;

	public Excite getExcite() {
		return excite;
	}

	public void setExcite(Excite excite) {
		this.excite = excite;
	}

	public String getBio() {
		return bio;
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

	public String getShortName() {
		return shortName;
	}

	public String getSuffix() {
		return suffix;
	}

	public ImageLink getTexture() {
		return texture;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setImage(ImageLink image) {
		this.image = image;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void setTexture(ImageLink texture) {
		this.texture = texture;
	}
}
