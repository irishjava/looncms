package uk.mafu.domain.altoon;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bibliography implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column
	private Date date;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@Column(length = 2147483647)
	private String text;

	@Column
	private String title;

	public Date getDate() {
		return date;
	}

	public int getPk() {
		return pk;
	}

	public String getText() {
		return text;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}