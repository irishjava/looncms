package uk.mafu.domain.saatchi;

import java.io.Serializable;

import uk.mafu.loon.domain.data.PdfLink;

public class ContactDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String text1;
	private String text2;
	private String text3;

	private String blogUrl;
	private String twitterUrl;
	private String googleMapUrl;

	public String getBlogUrl() {
		return blogUrl;
	}

	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}

	public String getTwitterUrl() {
		return twitterUrl;
	}

	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}

	public String getGoogleMapUrl() {
		return googleMapUrl;
	}

	public void setGoogleMapUrl(String googleMapUrl) {
		this.googleMapUrl = googleMapUrl;
	}

	private PdfLink contactpdf;

	public PdfLink getContactpdf() {
		return contactpdf;
	}

	public void setContactpdf(PdfLink contactpdf) {
		this.contactpdf = contactpdf;
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

	public String getText3() {
		return text3;
	}

	public void setText3(String text3) {
		this.text3 = text3;
	}

}
