package uk.mafu.domain.fletcher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.IndexColumn;
import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.PdfLink;

@Entity
public class Website implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@Column
	private String identifier;
	@Column(length = 2147483647)
	private String address;
	@Column(length = 2147483647)
	private String contact;
	@Column
	private String googleLink;
	@Column
	private String email;
	@OneToOne(optional = true)
	private PdfLink contactPdf;
	@OneToOne(optional = true)
	private ImageLink contactImage;
	//
	@Column(length = 2147483647)
	private String practiceAbout;
	@Column(length = 2147483647)
	private String practiceStatement;
	@OneToOne(optional = true)
	private ImageLink practiceImage;
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Press> pressItems = new ArrayList<Press>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Slide> slides = new ArrayList<Slide>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Project> projects = new ArrayList<Project>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<ProjectCategory> projectCategories = new ArrayList<ProjectCategory>();

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PdfLink getContactPdf() {
		return contactPdf;
	}

	public void setContactPdf(PdfLink contactPdf) {
		this.contactPdf = contactPdf;
	}

	public ImageLink getContactImage() {
		return contactImage;
	}

	public void setContactImage(ImageLink contactImage) {
		this.contactImage = contactImage;
	}

	public String getPracticeAbout() {
		return practiceAbout;
	}

	public void setPracticeAbout(String practiceAbout) {
		this.practiceAbout = practiceAbout;
	}

	public String getPracticeStatement() {
		return practiceStatement;
	}

	public void setPracticeStatement(String practiceStatement) {
		this.practiceStatement = practiceStatement;
	}

	public ImageLink getPracticeImage() {
		return practiceImage;
	}

	public void setPracticeImage(ImageLink practiceImage) {
		this.practiceImage = practiceImage;
	}

	public List<Press> getPressItems() {
		return pressItems;
	}

	public void setPressItems(List<Press> pressItems) {
		this.pressItems = pressItems;
	}

	public List<Slide> getSlides() {
		return slides;
	}

	public void setSlides(List<Slide> slides) {
		this.slides = slides;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<ProjectCategory> getProjectCategories() {
		return projectCategories;
	}

	public void setProjectCategories(List<ProjectCategory> projectCategories) {
		this.projectCategories = projectCategories;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public void setGoogleLink(String googleLink) {
		this.googleLink = googleLink;
	}

	public String getGoogleLink() {
		return googleLink;
	}
}