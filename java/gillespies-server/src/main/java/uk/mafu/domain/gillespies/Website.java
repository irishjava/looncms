package uk.mafu.domain.gillespies;

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

@Entity
public class Website implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@Column
	private String name;
	@OneToOne
	private ImageLink map;
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Assignment> assignments = new ArrayList<Assignment>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Role> roles = new ArrayList<Role>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<OfficeLocation> officeLocations = new ArrayList<OfficeLocation>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Link> links = new ArrayList<Link>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<BlogEntry> blogentries = new ArrayList<BlogEntry>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Client> clients = new ArrayList<Client>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Person> people = new ArrayList<Person>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<News> newsItems = new ArrayList<News>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<ProjectCategory> projectCategories = new ArrayList<ProjectCategory>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<ProjectLocation> projectLocations = new ArrayList<ProjectLocation>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Award> awards = new ArrayList<Award>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Job> globalJobs = new ArrayList<Job>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Project> projects = new ArrayList<Project>();
	// Generic section stuff.
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Publication> publications = new ArrayList<Publication>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<YearOut> yearOuts = new ArrayList<YearOut>();
	@OneToOne
	private Profile profile;
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Sustainability> sustainabilities = new ArrayList<Sustainability>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Working> workings = new ArrayList<Working>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<PressRelease> pressReleases = new ArrayList<PressRelease>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<CommunityWork> communityWorks = new ArrayList<CommunityWork>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<LectureAndTalk> lecturesAndTalks = new ArrayList<LectureAndTalk>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Testimonial> testimonials = new ArrayList<Testimonial>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<ProjectLink> projectLinks = new ArrayList<ProjectLink>();

	public List<ProjectLink> getProjectLinks() {
		return projectLinks;
	}

	public void setProjectLinks(List<ProjectLink> projectLinks) {
		this.projectLinks = projectLinks;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Job> getGlobalJobs() {
		return globalJobs;
	}

	public void setGlobalJobs(List<Job> globalJobs) {
		this.globalJobs = globalJobs;
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageLink getMap() {
		return map;
	}

	public void setMap(ImageLink map) {
		this.map = map;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<OfficeLocation> getOfficeLocations() {
		return officeLocations;
	}

	public void setOfficeLocations(List<OfficeLocation> officeLocations) {
		this.officeLocations = officeLocations;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<BlogEntry> getBlogentries() {
		return blogentries;
	}

	public void setBlogentries(List<BlogEntry> blogentries) {
		this.blogentries = blogentries;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}

	public List<PressRelease> getPressReleases() {
		return pressReleases;
	}

	public void setPressReleases(List<PressRelease> pressReleases) {
		this.pressReleases = pressReleases;
	}

	public List<News> getNewsItems() {
		return newsItems;
	}

	public void setNewsItems(List<News> newsItems) {
		this.newsItems = newsItems;
	}

	public List<ProjectCategory> getProjectCategories() {
		return projectCategories;
	}

	public void setProjectCategories(List<ProjectCategory> projectCategories) {
		this.projectCategories = projectCategories;
	}

	public List<ProjectLocation> getProjectLocations() {
		return projectLocations;
	}

	public void setProjectLocations(List<ProjectLocation> projectLocations) {
		this.projectLocations = projectLocations;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}

	public List<Publication> getPublications() {
		return publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}

	public List<YearOut> getYearOuts() {
		return yearOuts;
	}

	public void setYearOuts(List<YearOut> yearOuts) {
		this.yearOuts = yearOuts;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<Sustainability> getSustainabilities() {
		return sustainabilities;
	}

	public void setSustainabilities(List<Sustainability> sustainabilities) {
		this.sustainabilities = sustainabilities;
	}

	public List<LectureAndTalk> getLecturesAndTalks() {
		return lecturesAndTalks;
	}

	public void setLecturesAndTalks(List<LectureAndTalk> lecturesAndTalks) {
		this.lecturesAndTalks = lecturesAndTalks;
	}

	public List<CommunityWork> getCommunityWorks() {
		return communityWorks;
	}

	public void setCommunityWorks(List<CommunityWork> communityWorks) {
		this.communityWorks = communityWorks;
	}

	public List<Testimonial> getTestimonials() {
		return testimonials;
	}

	public void setTestimonials(List<Testimonial> testimonials) {
		this.testimonials = testimonials;
	}

	public List<Working> getWorkings() {
		return workings;
	}

	public void setWorkings(List<Working> workings) {
		this.workings = workings;
	}
}
