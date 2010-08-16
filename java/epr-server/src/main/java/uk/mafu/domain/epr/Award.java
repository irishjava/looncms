package uk.mafu.domain.epr;

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
import uk.mafu.loon.domain.data.PdfLink;

@Entity
public class Award implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@OneToOne
	private Project project;
	@Column
	private String title;
	@Column(length = 2147483647)
	private String text;
	@Column
	private Date date;
	@ManyToMany(fetch = FetchType.LAZY)
	@IndexColumn(name = "position", base = 0)
	private List<ImageLink> images = new ArrayList<ImageLink>();
	@Column
	private String permalink;
	@OneToOne(optional = true)
	private PdfLink pdf;
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<ImageLink> getImages() {
		return images;
	}
	public void setImages(List<ImageLink> images) {
		this.images = images;
	}
	public String getPermalink() {
		return permalink;
	}
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
	public PdfLink getPdf() {
		return pdf;
	}
	public void setPdf(PdfLink pdf) {
		this.pdf = pdf;
	}
	
	
	
}