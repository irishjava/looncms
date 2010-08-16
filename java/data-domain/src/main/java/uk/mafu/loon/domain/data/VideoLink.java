package uk.mafu.loon.domain.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VideoLink implements Serializable, Link {
	private static final long serialVersionUID = 1L;
	@Column
	private String caption;
	@Column(nullable = true)
	private Integer height;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "VIDEO_ID", nullable = false)
	private int videoId = -1;

	@Column(nullable = true)
	private Integer width;

	public String getCaption() {
		return this.caption;
	}

	public Integer getHeight() {
		return height;
	}

	public int getPk() {
		return pk;
	}

	public String getTitle() {
		return title;
	}

	public int getVideoId() {
		return videoId;
	}

	public Integer getWidth() {
		return width;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}
}
