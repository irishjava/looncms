package uk.mafu.domain.altoon;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column
	private String associateArchitect;
	@Column
	private String client;
	@Column
	private String completed;
	@Column
	private String design;
	@ManyToMany(fetch = FetchType.LAZY)
	@IndexColumn(name = "position", base = 0)
	private List<ImageLink> images = new ArrayList<ImageLink>();
	@Column
	private String name;
	// permalink
	@Column
	private String permalink;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@Column
	private String size;
	@Column(length = 2147483647)
	private String text;
	@OneToOne(optional = true)
	private ImageLink thumbnail;

	public String getAssociateArchitect() {
		return associateArchitect;
	}

	public String getClient() {
		return client;
	}

	public String getCompleted() {
		return completed;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<ImageLink> getImages() {
		return images;
	}

	public String getName() {
		return name;
	}

	public String getPermalink() {
		return permalink;
	}

	public int getPk() {
		return pk;
	}

	public String getText() {
		return text;
	}

	public ImageLink getThumbnail() {
		return thumbnail;
	}

	public void setAssociateArchitect(String associateArchitect) {
		this.associateArchitect = associateArchitect;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}

	public void setImages(List<ImageLink> images) {
		this.images = images;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("unused")
	private void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setThumbnail(ImageLink thumbnail) {
		this.thumbnail = thumbnail;
	}
}