package uk.mafu.domain.epr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@Column
	private String title;
	@Column
	private String subTitle;
	@Column(length = 2147483647)
	private String text;
	@Column
	private String client;
	@Column
	private String value;
	@Column
	private String photographer;
	@Column
	private String location;
	@OneToOne(optional = true)
	private ImageLink thumbnail;
	@Column
	private String servicesProvided;
	@Column
	private Date completed;
	@OneToOne(optional = true)
	private PdfLink pdf;
	@OneToOne(optional = true)
	private VideoLink video;
	@ManyToMany(fetch = FetchType.LAZY)
	@IndexColumn(name = "position", base = 0)
	private List<ImageLink> images = new ArrayList<ImageLink>();
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

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ImageLink getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(ImageLink thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getServicesProvided() {
		return servicesProvided;
	}

	public void setServicesProvided(String servicesProvided) {
		this.servicesProvided = servicesProvided;
	}

	public Date getCompleted() {
		return completed;
	}

	public void setCompleted(Date completed) {
		this.completed = completed;
	}

	public PdfLink getPdf() {
		return pdf;
	}

	public void setPdf(PdfLink pdf) {
		this.pdf = pdf;
	}

	public VideoLink getVideo() {
		return video;
	}

	public void setVideo(VideoLink video) {
		this.video = video;
	}

	public List<ImageLink> getImages() {
		return images;
	}

	public void setImages(List<ImageLink> images) {
		this.images = images;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPhotographer() {
		return photographer;
	}

	public void setPhotographer(String photographer) {
		this.photographer = photographer;
	}
}