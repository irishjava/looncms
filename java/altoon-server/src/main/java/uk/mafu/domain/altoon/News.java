package uk.mafu.domain.altoon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.IndexColumn;
import uk.mafu.loon.domain.data.ImageLink;

@Entity
public class News implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column
	private Date date;
	// permalink
	@Column
	private String permalink;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@OneToOne
	private Project project;
	@Column(length = 2147483647)
	private String text;
	@ManyToMany(fetch = FetchType.LAZY)
	@IndexColumn(name = "position", base = 0)
	private List<ImageLink> images = new ArrayList<ImageLink>();
	@Column
	private String title;

	public Date getDate() {
		return date;
	}

	public String getPermalink() {
		return permalink;
	}

	public int getPk() {
		return pk;
	}

	public Project getProject() {
		return project;
	}

	public String getText() {
		return text;
	}

	public String getTitle() {
		return title;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@SuppressWarnings("unused")
	private void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ImageLink> getImages() {
		return images;
	}

	public void setImages(List<ImageLink> images) {
		this.images = images;
	}
}