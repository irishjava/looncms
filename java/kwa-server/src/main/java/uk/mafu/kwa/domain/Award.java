package uk.mafu.kwa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import uk.mafu.loon.domain.data.ImageLink;

@Entity
@Table(name = "awards")
public class Award implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	public String awardTitle;
	@Column
	public Date date;

	public ImageLink getImage() {
		return image;
	}

	public void setImage(ImageLink image) {
		this.image = image;
	}

	@OneToOne(optional = true)
	public Excite excite;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "image")
	public ImageLink image;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;

	@OneToOne(optional = true)
	public Project relatedProject;
	@Column(length = 125000)
	public String text;
	@Column
	public String title;

	@Column
	private String permalink;

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getAwardTitle() {
		return awardTitle;
	}

	public Date getDate() {
		return date;
	}

	public Excite getExcite() {
		return excite;
	}

	public int getPk() {
		return pk;
	}

	public Project getRelatedProject() {
		return relatedProject;
	}

	public String getText() {
		return text;
	}

	public String getTitle() {
		return title;
	}

	public void setAwardTitle(String awardTitle) {
		this.awardTitle = awardTitle;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setExcite(Excite excite) {
		this.excite = excite;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public void setRelatedProject(Project relatedProject) {
		this.relatedProject = relatedProject;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
