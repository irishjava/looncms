package uk.mafu.kwa.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.PdfLink;
import uk.mafu.loon.domain.data.VideoLink;

@Entity
@Table(name = "projects")
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(optional = true)
	@JoinColumn(name = "featuredImage")
	public ImageLink featuredImage;

	@OneToOne(optional = true)
	@JoinColumn(name = "infoExcite")
	public Excite infoExcite;

	@OneToOne(optional = true)
	@JoinColumn(name = "infoImage")
	public ImageLink infoImage;
	@Column(length = 125000)
	public String infoText;

	@OneToOne(optional = true)
	@JoinColumn(name = "pdf")
	public PdfLink pdf;
	@Column
	public String permalink;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;

	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	public List<ProjectSlide> slides = new ArrayList<ProjectSlide>();
	@Column
	public String title;

	@OneToOne(optional = true)
	@JoinColumn(name = "video")
	public VideoLink video;

	@OneToOne(optional = true)
	@JoinColumn(name = "videoExcite")
	public Excite videoExcite;

	@OneToOne(optional = true)
	@JoinColumn(name = "videoImage")
	public ImageLink videoImage;

	public ImageLink getFeaturedImage() {
		return featuredImage;
	}

	public Excite getInfoExcite() {
		return infoExcite;
	}

	public ImageLink getInfoImage() {
		return infoImage;
	}

	public String getInfoText() {
		return infoText;
	}

	public PdfLink getPdf() {
		return pdf;
	}

	public String getPermalink() {
		return permalink;
	}

	public int getPk() {
		return pk;
	}

	public List<ProjectSlide> getSlides() {
		return slides;
	}

	public String getTitle() {
		return title;
	}

	public VideoLink getVideo() {
		return video;
	}

	public Excite getVideoExcite() {
		return videoExcite;
	}

	public ImageLink getVideoImage() {
		return videoImage;
	}

	public void setFeaturedImage(ImageLink featuredImage) {
		this.featuredImage = featuredImage;
	}

	public void setInfoExcite(Excite infoExcite) {
		this.infoExcite = infoExcite;
	}

	public void setInfoImage(ImageLink infoImage) {
		this.infoImage = infoImage;
	}

	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}

	public void setPdf(PdfLink pdf) {
		this.pdf = pdf;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public void setSlides(List<ProjectSlide> slides) {
		this.slides = slides;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setVideo(VideoLink video) {
		this.video = video;
	}

	public void setVideoExcite(Excite videoExcite) {
		this.videoExcite = videoExcite;
	}

	public void setVideoImage(ImageLink videoImage) {
		this.videoImage = videoImage;
	}
}
