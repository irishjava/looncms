package uk.mafu.domain.maverick;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.VideoLink;

@Entity
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@Column
	private String title;
	@Column
	private Date date;
	@Column(length = 2147483647)
	private String text1;
	@Column(length = 2147483647)
	private String text2;
	@OneToOne(optional = true)
	private ImageLink thumb;
	@OneToOne(optional = true)
	private ImageLink videoImg;
	@OneToOne(optional = true)
	private VideoLink video;
	
	
	
	@Column
	private String permalink;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public ImageLink getThumb() {
		return thumb;
	}

	public void setThumb(ImageLink thumb) {
		this.thumb = thumb;
	}

	public ImageLink getVideoImg() {
		return videoImg;
	}

	public void setVideoImg(ImageLink videoImg) {
		this.videoImg = videoImg;
	}

	public VideoLink getVideo() {
		return video;
	}

	public void setVideo(VideoLink video) {
		this.video = video;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
}