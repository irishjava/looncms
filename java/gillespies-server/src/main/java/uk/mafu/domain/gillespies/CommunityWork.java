package uk.mafu.domain.gillespies;

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
import uk.mafu.loon.domain.data.PdfLink;
import uk.mafu.loon.domain.data.VideoLink;

@Entity
public class CommunityWork implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@Column
	private String title;
	@Column(length = 2147483647)
	private String text;
	@OneToOne
	private PdfLink pdf;
	@ManyToMany(fetch = FetchType.LAZY)
	@IndexColumn(name = "position", base = 0)
	private List<ImageLink> images = new ArrayList<ImageLink>();
	@OneToOne
	private VideoLink video;
	@OneToOne(optional = true)
	private ImageLink videoThumbnail;
	@Column
	private String url;
	@Column
	private String permalink;

	public String getPermalink() {
		return permalink;
	}

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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public PdfLink getPdf() {
		return pdf;
	}

	public void setPdf(PdfLink pdf) {
		this.pdf = pdf;
	}

	public List<ImageLink> getImages() {
		return images;
	}

	public void setImages(List<ImageLink> images) {
		this.images = images;
	}

	public VideoLink getVideo() {
		return video;
	}

	public void setVideo(VideoLink video) {
		this.video = video;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ImageLink getVideoThumbnail() {
		return videoThumbnail;
	}

	public void setVideoThumbnail(ImageLink videoThumbnail) {
		this.videoThumbnail = videoThumbnail;
	}
}