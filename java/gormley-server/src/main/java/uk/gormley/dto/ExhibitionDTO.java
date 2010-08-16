package uk.gormley.dto;

import java.io.Serializable;

public class ExhibitionDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String text;
	private Integer year;

	public String getText() {
		return this.text;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}