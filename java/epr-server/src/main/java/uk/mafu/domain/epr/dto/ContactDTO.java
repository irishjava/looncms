package uk.mafu.domain.epr.dto;

import java.io.Serializable;
import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.PdfLink;

public class ContactDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String text;
	private ImageLink image;
	private PdfLink pdf;
	private String url;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ImageLink getImage() {
		return image;
	}
	public void setImage(ImageLink image) {
		this.image = image;
	}
	public PdfLink getPdf() {
		return pdf;
	}
	public void setPdf(PdfLink pdf) {
		this.pdf = pdf;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
