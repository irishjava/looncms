package uk.gormley.dto;

import java.io.Serializable;

public class GormleyDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	public String caption;
	public String title;
	public String text;

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}