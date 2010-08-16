package uk.mafu.loon.dto;

import java.io.Serializable;

public class ImageThumb implements Serializable {
	private static final long serialVersionUID = 1L;
	private byte[] data;
	private String mimetype;
	private Object linkPk = -1;

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public Object getLinkPk() {
		return linkPk;
	}

	public void setLinkPk(Object linkPk) {
		this.linkPk = linkPk;
	}
}
