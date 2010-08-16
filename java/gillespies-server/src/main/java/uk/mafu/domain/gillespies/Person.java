package uk.mafu.domain.gillespies;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.VideoLink;

@Entity
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@Column
	private String name;
	@Column(length = 2147483647)
	private String info;
	@OneToOne
	private ImageLink image;
	@OneToOne
	private VideoLink video;
	@OneToOne(optional = true)
	private ImageLink videoThumbnail;

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public ImageLink getImage() {
		return image;
	}

	public void setImage(ImageLink image) {
		this.image = image;
	}

	public VideoLink getVideo() {
		return video;
	}

	public void setVideo(VideoLink video) {
		this.video = video;
	}

	@Column
	private String permalink;

	public String getPermalink() {
		return permalink;
	}

	public ImageLink getVideoThumbnail() {
		return videoThumbnail;
	}

	public void setVideoThumbnail(ImageLink videoThumbnail) {
		this.videoThumbnail = videoThumbnail;
	}
}