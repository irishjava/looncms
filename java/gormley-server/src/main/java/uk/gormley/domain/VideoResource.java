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

import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.VideoLink;

@Entity
@Indexed
@Analyzer(impl=StandardAnalyzer.class)
public class VideoResource extends AbstractPdfItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private Date date;
	
	@OneToOne(optional=true)
	@JoinColumn(name="img_res_img_link")
	private ImageLink image;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	@OneToOne(optional=true)
	@JoinColumn(name="vid_res_vid_link")
	private VideoLink video;
	
	

}
