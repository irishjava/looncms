package uk.mafu.loon.domain.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ImageLink implements Link, Serializable {
	private static final long serialVersionUID = 1L;
	@Column(length = 2147483647)
	private String caption;

	@Column
	private boolean excite;

	@Column(name = "HEIGHT")
	private int height;

	@Column(name = "IMAGE_ID", nullable = false)
	private int imageId = -1;

	@Column
	private String meta;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk;

	@Column(length = 2147483647)
	private String text;
	 
	@Column
	private String title;
	 
	@Column(name = "WIDTH")
	private int width;

	public ImageLink() {
		super();
	}
	public ImageLink(int pk, String title, int width, int height,
			Integer imageId) {
		super();
		this.pk = pk;
		this.title = title;
		this.width = width;
		this.height = height;
		this.imageId = imageId;
	}
	public String getCaption(){
		return this.caption;
	}
	public int getHeight() {
		return height;
	}

	public int getImageId() {
		return imageId;
	}

	public String getMeta() {
		return meta;
	}

	public int getPk() {
		return pk;
	}

	public String getText() {
		return text;
	}

	public String getTitle() {
		return title;
	}

	public int getWidth() {
		return width;
	}

	public boolean isExcite() {
		return excite;
	}

	public void setCaption(String caption ){
		this.caption = caption;
	}

	public void setExcite(boolean excite) {
		this.excite = excite;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
