package uk.mafu.loon.domain.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AudioLink implements Serializable, Link {
	private static final long serialVersionUID = 1L;
	@Column
	private int audioId = -1;
	@Column
	private String caption;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk;
	@Column
	private String title;

	public int getAudioId() {
		return audioId;
	}

	public String getCaption() {
		return this.caption;
	}

	public int getPk() {
		return pk;
	}

	public String getTitle() {
		return title;
	}

	public void setAudioId(int audioId) {
		this.audioId = audioId;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
