package uk.mafu.domain.gillespies;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import uk.mafu.loon.domain.data.PdfLink;

@Entity
public class PressRelease implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@Column
	private String title;
	@Column(length = 2147483647)
	private String text;
	@OneToOne
	private Project project;

	@OneToOne
	private PdfLink pdf;

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column
	private String permalink;
 
	public String getPermalink() {
		return permalink;
	}

	public PdfLink getPdf() {
		return pdf;
	}

	public void setPdf(PdfLink pdf) {
		this.pdf = pdf;
	}
}