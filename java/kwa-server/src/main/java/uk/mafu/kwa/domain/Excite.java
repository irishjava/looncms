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
@Table(name = "excites")
public class Excite implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(optional = true)
	public ImageLink image;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	
	@Column
	public String title;
	
	@Column(length=125000)
	public String text;

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ImageLink getImage() {
		return image;
	}

	public int getPk() {
		return pk;
	}

	public String getText() {
		return text;
	}

	public void setImage(ImageLink image) {
		this.image = image;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public void setText(String text) {
		this.text = text;
	}
}
