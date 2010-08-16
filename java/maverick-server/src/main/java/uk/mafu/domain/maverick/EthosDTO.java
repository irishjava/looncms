package uk.mafu.domain.maverick;

import java.io.Serializable;

import uk.mafu.loon.domain.data.ImageLink;

public class EthosDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	// Ethos
	
	public String ethosText1;
	
	public String ethosText2;
	
	public ImageLink ethosImage;

	public String getEthosText1() {
		return ethosText1;
	}

	public void setEthosText1(String ethosText1) {
		this.ethosText1 = ethosText1;
	}

	public String getEthosText2() {
		return ethosText2;
	}

	public void setEthosText2(String ethosText2) {
		this.ethosText2 = ethosText2;
	}

	public ImageLink getEthosImage() {
		return ethosImage;
	}

	public void setEthosImage(ImageLink ethosImage) {
		this.ethosImage = ethosImage;
	}
	
	
	
	
}