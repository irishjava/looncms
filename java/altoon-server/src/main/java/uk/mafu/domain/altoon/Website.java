package uk.mafu.domain.altoon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.IndexColumn;
import uk.mafu.loon.domain.data.ImageLink;

@Entity
public class Website implements Serializable {
	private static final long serialVersionUID = 1L;
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Award> awards = new ArrayList<Award>();
	@OneToOne(optional = true)
	private ImageLink awardsImage;
	@Column
	private String identifier;
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Region> regions = new ArrayList<Region>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<News> newsItems = new ArrayList<News>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Office> offices = new ArrayList<Office>();
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<ProjectCategory> projectCategories = new ArrayList<ProjectCategory>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Project> projects = new ArrayList<Project>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<ProjectLink> projectLinks = new ArrayList<ProjectLink>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Role> roles = new ArrayList<Role>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Service> services = new ArrayList<Service>();
	//
	@Column(length = 2147483647)
	private String sustainabilityText;
	//
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	@JoinTable(name = "website_sustainability_images")
	private List<ImageLink> sustainabilityImages = new ArrayList<ImageLink>();
	@Column(length = 2147483647)
	private String profileText;
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	@JoinTable(name = "website_profile_images")
	private List<ImageLink> profileImages = new ArrayList<ImageLink>();
	//	
	@Column(length = 2147483647)
	private String historyText;
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	@JoinTable(name = "website_history_images")
	private List<ImageLink> historyImages = new ArrayList<ImageLink>();
	//	
	@Column(length = 2147483647)
	private String philosophyText;
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	@JoinTable(name = "website_philosophy_images")
	private List<ImageLink> philosophyImages = new ArrayList<ImageLink>();

	public List<Award> getAwards() {
		return awards;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public List<News> getNewsItems() {
		return newsItems;
	}

	public void setNewsItems(List<News> newsItems) {
		this.newsItems = newsItems;
	}

	public List<Office> getOffices() {
		return offices;
	}

	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public List<ProjectCategory> getProjectCategories() {
		return projectCategories;
	}

	public void setProjectCategories(List<ProjectCategory> projectCategories) {
		this.projectCategories = projectCategories;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public String getSustainabilityText() {
		return sustainabilityText;
	}

	public void setSustainabilityText(String sustainabilityText) {
		this.sustainabilityText = sustainabilityText;
	}

	public String getProfileText() {
		return profileText;
	}

	public void setProfileText(String profileText) {
		this.profileText = profileText;
	}

	public List<ImageLink> getProfileImages() {
		return profileImages;
	}

	public void setProfileImages(List<ImageLink> profileImages) {
		this.profileImages = profileImages;
	}

	public String getHistoryText() {
		return historyText;
	}

	public void setHistoryText(String historyText) {
		this.historyText = historyText;
	}

	public List<ImageLink> getHistoryImages() {
		return historyImages;
	}

	public void setHistoryImages(List<ImageLink> historyImages) {
		this.historyImages = historyImages;
	}

	//
	public List<ImageLink> getSustainabilityImages() {
		return sustainabilityImages;
	}

	public void setSustainabilityImages(List<ImageLink> sustainabilityImages) {
		this.sustainabilityImages = sustainabilityImages;
	}

	public String getPhilosophyText() {
		return philosophyText;
	}

	public void setPhilosophyText(String philosophyText) {
		this.philosophyText = philosophyText;
	}

	public List<ImageLink> getPhilosophyImages() {
		return philosophyImages;
	}

	public void setPhilosophyImages(List<ImageLink> philosophyImages) {
		this.philosophyImages = philosophyImages;
	}

	public List<ProjectLink> getProjectLinks() {
		return projectLinks;
	}

	public void setProjectLinks(List<ProjectLink> projectLinks) {
		this.projectLinks = projectLinks;
	}

	public ImageLink getAwardsImage() {
		return awardsImage;
	}

	public void setAwardsImage(ImageLink awardsImage) {
		this.awardsImage = awardsImage;
	}
}