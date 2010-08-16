package uk.mafu.kwa.front;

import java.io.Serializable;

import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.PdfLink;

public class ContactDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String contactGoogleMapsLink;
	public ImageLink contactImage;
	public PdfLink contactPdf;
	public String contactText;

}
