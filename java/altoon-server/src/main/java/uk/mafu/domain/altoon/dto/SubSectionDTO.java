package uk.mafu.domain.altoon.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import uk.mafu.loon.domain.data.ImageLink;

public class SubSectionDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String text;
	private List<ImageLink> images = new ArrayList<ImageLink>();

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<ImageLink> getImages() {
		return images;
	}

	public void setImages(List<ImageLink> images) {
		this.images = images;
	}
}