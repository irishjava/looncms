package uk.mafu.domain.altoon;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.PdfLink;

@Entity
public class Office implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(length = 2147483647)
	private String contactText;
	@Column
	private String locationName;
	@Column
	private String mapUrl;
	@OneToOne
	private PdfLink pdf;
	@Column
	private String name;
	@OneToOne
	@JoinColumn(name = "image1_pk")
	private ImageLink image1;
	@OneToOne
	@JoinColumn(name = "image2_pk")
	private ImageLink image2;
	// permalink
	@Column
	private String permalink;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;

	public String getLocationName() {
		return locationName;
	}

	public String getMapUrl() {
		return mapUrl;
	}

	public String getName() {
		return name;
	}

	public String getPermalink() {
		return permalink;
	}

	public int getPk() {
		return pk;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("unused")
	private void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public PdfLink getPdf() {
		return pdf;
	}

	public void setPdf(PdfLink pdf) {
		this.pdf = pdf;
	}

	public String getContactText() {
		return contactText;
	}

	public void setContactText(String contactText) {
		this.contactText = contactText;
	}

	public ImageLink getImage1() {
		return image1;
	}

	public void setImage1(ImageLink image1) {
		this.image1 = image1;
	}

	public ImageLink getImage2() {
		return image2;
	}

	public void setImage2(ImageLink image2) {
		this.image2 = image2;
	}
}