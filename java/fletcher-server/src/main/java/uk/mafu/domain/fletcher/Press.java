package uk.mafu.domain.fletcher;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.PdfLink;

@Entity
public class Press implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	//
	@Column
	private String title;
	@OneToOne(optional = true)
	private ImageLink thumb;
	@OneToOne(optional = true)
	private ImageLink image;
	@Column(length = 2147483647)
	private String text1;
	@Column(length = 2147483647)
	private String text2;
	@OneToOne(optional = true)
	private PdfLink pdf;
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

	public ImageLink getThumb() {
		return thumb;
	}

	public void setThumb(ImageLink thumb) {
		this.thumb = thumb;
	}

	public ImageLink getImage() {
		return image;
	}

	public void setImage(ImageLink image) {
		this.image = image;
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

	public PdfLink getPdf() {
		return pdf;
	}

	public void setPdf(PdfLink pdf) {
		this.pdf = pdf;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
}