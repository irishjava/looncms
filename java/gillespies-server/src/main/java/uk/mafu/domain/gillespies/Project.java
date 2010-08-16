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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.IndexColumn;

import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.PdfLink;
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
	private String header;
	@Column(length = 2147483647)
	private String content;
	@Column
	private String client;
	@Column
	private String designTeam;
	@Column
	private String capitalValue;
	@Column
	private String photographer;
	@Column(length = 2147483647)
	private String excite;
	@Column
	private String photomontage;
	@OneToOne(optional = true)
	private ImageLink thumbnail;
	@OneToOne(optional = true)
	private ImageLink frontPageImage;
	@ManyToMany(fetch = FetchType.LAZY)
	@IndexColumn(name = "position", base = 0)
	private List<ImageLink> images = new ArrayList<ImageLink>();
	//	
	// @ManyToMany(fetch = FetchType.LAZY)
	// @IndexColumn(name = "position", base = 0)
	// private List<VideoLink> videos = new ArrayList<VideoLink>();
	// @ManyToMany(fetch = FetchType.LAZY)
	// @IndexColumn(name = "position", base = 0)
	// private List<PdfLink> pdfs = new ArrayList<PdfLink>();
	//	
	//	
	@OneToOne(optional = true)
	private PdfLink pdf;
	@OneToOne(optional = true)
	private VideoLink video;
	@OneToOne(optional = true)
	@JoinColumn(name = "video_thumb_pk")
	private ImageLink videoThumbnail;
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Assignment> assignments = new ArrayList<Assignment>();

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

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getDesignTeam() {
		return designTeam;
	}

	public void setDesignTeam(String designTeam) {
		this.designTeam = designTeam;
	}

	public String getCapitalValue() {
		return capitalValue;
	}

	public void setCapitalValue(String capitalValue) {
		this.capitalValue = capitalValue;
	}

	public String getPhotographer() {
		return photographer;
	}

	public void setPhotographer(String photographer) {
		this.photographer = photographer;
	}

	public String getExcite() {
		return excite;
	}

	public void setExcite(String excite) {
		this.excite = excite;
	}

	public String getPhotomontage() {
		return photomontage;
	}

	public void setPhotomontage(String photomontage) {
		this.photomontage = photomontage;
	}

	public ImageLink getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(ImageLink thumbnail) {
		this.thumbnail = thumbnail;
	}

	public List<ImageLink> getImages() {
		return images;
	}

	public void setImages(List<ImageLink> images) {
		this.images = images;
	}

	@Column
	private String permalink;

	public String getPermalink() {
		return permalink;
	}

	public ImageLink getFrontPageImage() {
		return frontPageImage;
	}

	public void setFrontPageImage(ImageLink frontPageImage) {
		this.frontPageImage = frontPageImage;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public PdfLink getPdf() {
		return pdf;
	}

	public void setVideo(VideoLink video) {
		this.video = video;
	}

	public VideoLink getVideo() {
		return video;
	}

	public void setPdf(PdfLink pdf) {
		this.pdf = pdf;
	}

	public ImageLink getVideoThumbnail() {
		return videoThumbnail;
	}

	public void setVideoThumbnail(ImageLink videoThumbnail) {
		this.videoThumbnail = videoThumbnail;
	}

	public boolean empty() {
		if (content == null && thumbnail == null && frontPageImage == null && (images == null || images.size() == 0)) {
			return true;
		}
		return false;
	}
}
