package uk.mafu.kwa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import uk.mafu.loon.domain.data.ImageLink;

@Entity
@Table(name = "homepageslides")
public class HomePageSlide implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column
	public String permalink;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int pk = -1;

	@OneToOne(optional = true)
	public Project relatedProject;

	@OneToOne(optional = true)
	public ImageLink image;

	public ImageLink getImage() {
		return image;
	}

	public void setImage(ImageLink image) {
		this.image = image;
	}

	@Column
	public String title;

	public String getPermalink() {
		return permalink;
	}

	public int getPk() {
		return pk;
	}

	public Project getRelatedProject() {
		return relatedProject;
	}

	public String getTitle() {
		return title;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public void setRelatedProject(Project relatedProject) {
		this.relatedProject = relatedProject;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
