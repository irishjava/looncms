package uk.mafu.domain.maverick;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import uk.mafu.loon.domain.data.ImageLink;

public class ContactDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(length = 2147483647)
	private String contactText1;
	@Column(length = 2147483647)
	private String contactText2;
	@Column(length = 2147483647)
	private String contactGoogleMapUrl;
	@OneToOne(optional = true)
	private ImageLink contactImage;

	public String getContactText1() {
		return contactText1;
	}

	public void setContactText1(String contactText1) {
		this.contactText1 = contactText1;
	}

	public String getContactText2() {
		return contactText2;
	}

	public void setContactText2(String contactText2) {
		this.contactText2 = contactText2;
	}

	public String getContactGoogleMapUrl() {
		return contactGoogleMapUrl;
	}

	public void setContactGoogleMapUrl(String contactGoogleMapUrl) {
		this.contactGoogleMapUrl = contactGoogleMapUrl;
	}

	public ImageLink getContactImage() {
		return contactImage;
	}

	public void setContactImage(ImageLink contactImage) {
		this.contactImage = contactImage;
	}
}