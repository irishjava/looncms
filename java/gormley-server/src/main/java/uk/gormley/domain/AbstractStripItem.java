package uk.gormley.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import uk.mafu.loon.domain.data.AudioLink;
import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.VideoLink;

@Entity
@Indexed
@Analyzer(impl = StandardAnalyzer.class)
public class AbstractStripItem extends AbstractPdfItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(optional = true)
	@JoinColumn(name = "audio1_fk")
	private AudioLink audio1;

	@OneToOne(optional = true)
	@JoinColumn(name = "audio2_fk")
	private AudioLink audio2;

	@OneToOne(optional = true)
	@JoinColumn(name = "audio3_fk")
	private AudioLink audio3;

	@OneToOne(optional = true)
	@JoinColumn(name = "infoimage_pk")
	private ImageLink infoImage;

	@Column(length = 2147483647)
	private String infoText;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "abstractstrip_progress_images")
	@IndexColumn(name = "position", base = 0)
	private List<ImageLink> progressImages;

	@OneToOne(optional = true)
	@JoinColumn(name = "video1_fk")
	private VideoLink video1;

	@OneToOne(optional = true)
	@JoinColumn(name = "video2_fk")
	private VideoLink video2;

	@OneToOne(optional = true)
	@JoinColumn(name = "video3_fk")
	private VideoLink video3;

	@OneToOne(optional = true)
	@JoinColumn(name = "videoThumb1_fk")
	private ImageLink videoThumb1;

	@OneToOne(optional = true)
	@JoinColumn(name = "videoThumb2_fk")
	private ImageLink videoThumb2;

	@OneToOne(optional = true)
	@JoinColumn(name = "videoThumb3_fk")
	private ImageLink videoThumb3;

	public AudioLink getAudio1() {
		return this.audio1;
	}

	public AudioLink getAudio2() {
		return this.audio2;
	}

	public AudioLink getAudio3() {
		return this.audio3;
	}

	public List<ImageLink> getProgressImages() {
		return this.progressImages;
	}

	public VideoLink getVideo1() {
		return this.video1;
	}

	public VideoLink getVideo2() {
		return this.video2;
	}

	public VideoLink getVideo3() {
		return this.video3;
	}

	public ImageLink getVideoThumb1() {
		return this.videoThumb1;
	}

	public ImageLink getVideoThumb2() {
		return this.videoThumb2;
	}

	public ImageLink getVideoThumb3() {
		return this.videoThumb3;
	}

	public void setAudio1(AudioLink audio1) {
		this.audio1 = audio1;
	}

	public void setAudio2(AudioLink audio2) {
		this.audio2 = audio2;
	}

	public void setAudio3(AudioLink audio3) {
		this.audio3 = audio3;
	}

	public ImageLink getInfoImage() {
		return infoImage;
	}

	public void setInfoImage(ImageLink infoImage) {
		this.infoImage = infoImage;
	}

	public String getInfoText() {
		return infoText;
	}

	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}

	public void setProgressImages(List<ImageLink> progressImages) {
		this.progressImages = progressImages;
	}

	public void setVideo1(VideoLink video1) {
		this.video1 = video1;
	}

	public void setVideo2(VideoLink video2) {
		this.video2 = video2;
	}

	public void setVideo3(VideoLink video3) {
		this.video3 = video3;
	}

	public void setVideoThumb1(ImageLink videoThumb1) {
		this.videoThumb1 = videoThumb1;
	}

	public void setVideoThumb2(ImageLink videoThumb2) {
		this.videoThumb2 = videoThumb2;
	}

	public void setVideoThumb3(ImageLink videoThumb3) {
		this.videoThumb3 = videoThumb3;
	}
} 