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
import org.apache.commons.lang.StringUtils;
import uk.mafu.loon.util.ImageUtil;

@Entity
public class LoonImage implements Data, Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer pk = -1;
	@Lob
	@Column(name = "data", length = 1048576)
	private byte[] data;
	//
	@Column(name = "filename")
	private String filename;
	@Column
	private Date date;
	@Column
	private Integer height;
	@Column
	private Integer width;
	@Column
	private String mimetype;

	@SuppressWarnings("unused")
	@PrePersist
	private void prePersist() {
		if (date == null) {
			date = new Date();
		}
		// Set the mime type first...
		if (StringUtils.endsWith(filename.toLowerCase(), ".png")) {
			this.mimetype = "image/png";
		} else if (StringUtils.endsWith(filename.toLowerCase(), ".jpg")) {
			this.mimetype = "image/jpeg";
		} else if (StringUtils.endsWith(filename.toLowerCase(), ".jpeg")) {
			this.mimetype = "image/jpeg";
		} else if (StringUtils.endsWith(filename.toLowerCase(), ".gif")) {
			this.mimetype = "image/gif";
		} else {
			throw new UnsupportedOperationException(
					"cannot determine mime type for file named:'" + filename
							+ "'");
		}
		// Now set the dimensions ....
		ImageUtil.ImageStatistics a = ImageUtil.getStatistics(data, mimetype);
		width = a.getWidth();
		height = a.getHeight();
	}

	public Object getPk() {
		return pk;
	}

	public void setPk(Object pk) {
		this.pk = (Integer) pk;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Integer getHeight() {
		return height;
	}

	public Integer getWidth() {
		return width;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMimetype() {
		if (this.mimetype != null) {
			return mimetype;
		} else {
			if (StringUtils.endsWith(filename.toLowerCase(), ".png")) {
				return "image/png";
			} else if (StringUtils.endsWith(filename.toLowerCase(), ".jpg")) {
				return "image/jpeg";
			} else if (StringUtils.endsWith(filename.toLowerCase(), ".gif")) {
				return "image/gif";
			} else {
				return null;
			}
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}