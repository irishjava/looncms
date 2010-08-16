package uk.mafu.loon.common;

import java.io.Serializable;

public class ImageStatistics implements Serializable {
	private static final long serialVersionUID = 1L;
	private int width ;
	private int height;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
