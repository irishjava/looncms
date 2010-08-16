package uk.mafu.kwa.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.PdfLink;
import uk.mafu.loon.domain.data.VideoLink;

@Entity
@Table(name = "websites")
public class Website implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(optional = true)
	@JoinColumn(name = "awardsExcite")
	public Excite awardsExcite;

	@OneToOne(optional = true)
	@JoinColumn(name = "clientsExcite")
	public Excite clientExcite;

	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	public List<Client> clients = new ArrayList<Client>();

	@Column
	public String contactGoogleMapsLink;

	@OneToOne(optional = true)
	@JoinColumn(name = "contactImage")
	public ImageLink contactImage;

	@OneToOne(optional = true)
	@JoinColumn(name = "contactPdf")
	public PdfLink contactPdf;

	@Column(length = 125000)
	public String contactText;

	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	@JoinTable(name = "websites_directors")
	public List<Person> directors = new ArrayList<Person>();

	@OneToOne(optional = true)
	@JoinColumn(name = "directorsExcite")
	public Excite directorsExcite;

	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	@JoinTable(name = "websites_employees")
	public List<Person> employees = new ArrayList<Person>();

	@OneToOne(optional = true)
	@JoinColumn(name = "ethosExcite")
	public Excite ethosExcite;

	@OneToOne(optional = true)
	@JoinColumn(name = "ethosImage")
	public ImageLink ethosImage;

	@Column(length = 125000)
	public String ethosText;

	@OneToOne(optional = true)
	@JoinColumn(name = "homepageExcite")
	public Excite homepageExcite;

	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	public List<HomePageSlide> homePageSlides = new ArrayList<HomePageSlide>();

	@Column
	private String identifier;

	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	public List<JobPosting> jobPostings = new ArrayList<JobPosting>();

	@OneToOne(optional = true)
	@JoinColumn(name = "monographExcite")
	public Excite monographExcite;

	@OneToOne(optional = true)
	@JoinColumn(name = "monographImage")
	public ImageLink monographImage;

	@Column(length = 125000)
	public String monographText;

	@OneToOne(optional = true)
	@JoinColumn(name = "newsExcite")
	public Excite newsExcite;

	@OneToOne(optional = true)
	@JoinColumn(name = "overviewImage")
	public ImageLink overviewImage;

	@Column(length = 125000)
	public String overviewText;

	@OneToOne(optional = true)
	@JoinColumn(name = "overviewVideo")
	public VideoLink overviewVideo;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;

	@OneToOne(optional = true)
	@JoinColumn(name = "pressExcite")
	public Excite pressExcite;

	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	public List<ProjectCategory> projectCategories = new ArrayList<ProjectCategory>();

	@OneToOne(optional = true)
	@JoinColumn(name = "sustainabilityExcite")
	public Excite sustainabilityExcite;

	@OneToOne(optional = true)
	@JoinColumn(name = "sustainabilityImage")
	public ImageLink sustainabilityImage;

	@Column(length = 125000)
	public String sustainabilityText;

	@OneToOne(optional = true)
	@JoinColumn(name = "teamExcite")
	public Excite teamExcite;

	public Excite getAwardsExcite() {
		return awardsExcite;
	}
	
	public Excite getClientExcite() {
		return clientExcite;
	}

	public List<Client> getClients() {
		return clients;
	}

	public String getContactGoogleMapsLink() {
		return contactGoogleMapsLink;
	}
	
	public ImageLink getContactImage() {
		return contactImage;
	}

	public PdfLink getContactPdf() {
		return contactPdf;
	}

	public String getContactText() {
		return contactText;
	}

	public List<Person> getDirectors() {
		return directors;
	}

	public Excite getDirectorsExcite() {
		return directorsExcite;
	}

	public List<Person> getEmployees() {
		return employees;
	}

	public Excite getEthosExcite() {
		return ethosExcite;
	}

	public ImageLink getEthosImage() {
		return ethosImage;
	}

	public String getEthosText() {
		return ethosText;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public Excite getHomepageExcite() {
		return homepageExcite;
	}

	public List<HomePageSlide> getHomePageSlides() {
		return homePageSlides;
	}

	public String getIdentifier() {
		return identifier;
	}

	public List<JobPosting> getJobPostings() {
		return jobPostings;
	}

	public Excite getMonographExcite() {
		return monographExcite;
	}

	public ImageLink getMonographImage() {
		return monographImage;
	}

	public String getMonographText() {
		return monographText;
	}

	public Excite getNewsExcite() {
		return newsExcite;
	}

	public ImageLink getOverviewImage() {
		return overviewImage;
	}

	public String getOverviewText() {
		return overviewText;
	}

	public VideoLink getOverviewVideo() {
		return overviewVideo;
	}

	public int getPk() {
		return pk;
	}

	public Excite getPressExcite() {
		return pressExcite;
	}

	public List<ProjectCategory> getProjectCategories() {
		return projectCategories;
	}

	public Excite getSustainabilityExcite() {
		return sustainabilityExcite;
	}

	public ImageLink getSustainabilityImage() {
		return sustainabilityImage;
	}

	public String getSustainabilityText() {
		return sustainabilityText;
	}

	public Excite getTeamExcite() {
		return teamExcite;
	}

	public void setAwardsExcite(Excite awardsExcite) {
		this.awardsExcite = awardsExcite;
	}

	public void setClientExcite(Excite clientExcite) {
		this.clientExcite = clientExcite;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public void setContactGoogleMapsLink(String contactGoogleMapsLink) {
		this.contactGoogleMapsLink = contactGoogleMapsLink;
	}

	public void setContactImage(ImageLink contactImage) {
		this.contactImage = contactImage;
	}

	public void setContactPdf(PdfLink contactPdf) {
		this.contactPdf = contactPdf;
	}

	public void setContactText(String contactText) {
		this.contactText = contactText;
	}

	public void setDirectors(List<Person> directors) {
		this.directors = directors;
	}

	public void setDirectorsExcite(Excite directorsExcite) {
		this.directorsExcite = directorsExcite;
	}

	public void setEmployees(List<Person> employees) {
		this.employees = employees;
	}

	public void setEthosExcite(Excite ethosExcite) {
		this.ethosExcite = ethosExcite;
	}

	public void setEthosImage(ImageLink ethosImage) {
		this.ethosImage = ethosImage;
	}

	public void setEthosText(String ethosText) {
		this.ethosText = ethosText;
	}

	public void setHomepageExcite(Excite homepageExcite) {
		this.homepageExcite = homepageExcite;
	}

	public void setHomePageSlides(List<HomePageSlide> homePageSlides) {
		this.homePageSlides = homePageSlides;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public void setJobPostings(List<JobPosting> jobPostings) {
		this.jobPostings = jobPostings;
	}

	public void setMonographExcite(Excite monographExcite) {
		this.monographExcite = monographExcite;
	}

	public void setMonographImage(ImageLink monographImage) {
		this.monographImage = monographImage;
	}

	public void setMonographText(String monographText) {
		this.monographText = monographText;
	}

	public void setNewsExcite(Excite newsExcite) {
		this.newsExcite = newsExcite;
	}

	public void setOverviewImage(ImageLink overviewImage) {
		this.overviewImage = overviewImage;
	}

	public void setOverviewText(String overviewText) {
		this.overviewText = overviewText;
	}

	public void setOverviewVideo(VideoLink overviewVideo) {
		this.overviewVideo = overviewVideo;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public void setPressExcite(Excite pressExcite) {
		this.pressExcite = pressExcite;
	}

	public void setProjectCategories(List<ProjectCategory> projectCategories) {
		this.projectCategories = projectCategories;
	}

	public void setSustainabilityExcite(Excite sustainabilityExcite) {
		this.sustainabilityExcite = sustainabilityExcite;
	}

	public void setSustainabilityImage(ImageLink sustainabilityImage) {
		this.sustainabilityImage = sustainabilityImage;
	}

	public void setSustainabilityText(String sustainabilityText) {
		this.sustainabilityText = sustainabilityText;
	}

	public void setTeamExcite(Excite teamExcite) {
		this.teamExcite = teamExcite;
	}
}
