package uk.mafu.loon.domain.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;

@Entity
public class LoonAudio implements Data, Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer pk = -1;
	@Lob
	@Column(length = 1048576)
	private byte[] data;
	//
	@Column
	private String filename;
	@Column
	private Date date;
	//
	@Column
	private String contentType;

	public String getContentType() {
		return contentType;
	}

	public byte[] getData() {
		return data;
	}

	public String getFilename() {
		return filename;
	}

	public Object getPk() {
		return pk;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@SuppressWarnings("unused")
	@PrePersist
	private void prePersist() {
		if (date == null) {
			date = new Date();
		}
	}
}
