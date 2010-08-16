package uk.gormley.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import uk.mafu.loon.domain.data.AudioLink;
import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.VideoLink;

@Entity
@Indexed
@Analyzer(impl=StandardAnalyzer.class)
public class NewsItem extends AbstractPdfItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(optional = true)
	@JoinColumn(name = "audio_fk")
	private AudioLink audio;

	@Column
	private Date date;

	@OneToOne(optional = true)
	@JoinColumn(name = "image_fk")
	private ImageLink image;

	@OneToOne(optional = true)
	@JoinColumn(name = "video_fk")
	private VideoLink video;

	public AudioLink getAudio() {
		return this.audio;
	}

	public Date getDate() {
		return this.date;
	}

	public ImageLink getImage() {
		return this.image;
	}

	

	public VideoLink getVideo() {
		return this.video;
	}

	public void setAudio(AudioLink audio) {
		this.audio = audio;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setImage(ImageLink image) {
		this.image = image;
	}

	public void setVideo(VideoLink video) {
		this.video = video;
	}

}
