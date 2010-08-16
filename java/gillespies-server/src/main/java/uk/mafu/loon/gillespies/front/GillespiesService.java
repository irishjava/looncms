package uk.mafu.loon.gillespies.front;

import java.util.List;

import uk.mafu.domain.gillespies.Assignment;
import uk.mafu.domain.gillespies.Award;
import uk.mafu.domain.gillespies.BlogEntry;
import uk.mafu.domain.gillespies.Client;
import uk.mafu.domain.gillespies.CommunityWork;
import uk.mafu.domain.gillespies.Job;
import uk.mafu.domain.gillespies.LectureAndTalk;
import uk.mafu.domain.gillespies.Link;
import uk.mafu.domain.gillespies.News;
import uk.mafu.domain.gillespies.Office;
import uk.mafu.domain.gillespies.OfficeLocation;
import uk.mafu.domain.gillespies.Person;
import uk.mafu.domain.gillespies.PressRelease;
import uk.mafu.domain.gillespies.Profile;
import uk.mafu.domain.gillespies.Project;
import uk.mafu.domain.gillespies.ProjectCategory;
import uk.mafu.domain.gillespies.ProjectLink;
import uk.mafu.domain.gillespies.ProjectLocation;
import uk.mafu.domain.gillespies.Publication;
import uk.mafu.domain.gillespies.Role;
import uk.mafu.domain.gillespies.Sustainability;
import uk.mafu.domain.gillespies.Testimonial;
import uk.mafu.domain.gillespies.Working;
import uk.mafu.domain.gillespies.YearOut;

public interface GillespiesService {

	List<ProjectCategory> getCategoriesByProject(int jobId);

	Project getFullyInitializedPoject(int projectId);

	String getImageUrl();

	List<Office> getOfficesByJob(int jobId);

	String getPdfUrl();

	Profile getProfile();

	Project getProjectByAward(int awardId);

	Project getProjectByNews(int newsId);

	String getVideoUrl();

	List<OfficeAndJob> listAllJobs();

	/** this will return all blog items ordered by date, minus the first 14 */
	List<BlogEntry> listArchiveBlogs();

	List<BlogEntry> listArchiveBlogsByYear(final int year);

	/**
	 * this will return a list of the years that blog items were posted, ie,
	 * 2007, 2008, 2009
	 */
	List<Integer> listArchiveBlogYears();

	/** this will return all news items ordered by date, minus the first 14 */
	List<News> listArchiveNews();

	List<News> listArchiveNewsByYear(final int year);

	/**
	 * this will return a list of the years the news items were posted, ie,
	 * 2007, 2008, 2009
	 */
	List<Integer> listArchiveNewsYears();

	List<Assignment> listAssignments();

	List<Assignment> listAssignmentsByRole(int roleId);

	List<Award> listAwards();

	List<BlogEntry> listBlogEntries();

	// News Related
	List<BlogEntry> listBlogEntriesByYear(int year);

	List<BlogEntry> listBlogEntriesOrderByDate();

	List<Client> listClients();

	List<CommunityWork> listCommunityWork();

	/**
	 * Fetched via the Global Jobs list from the website object.
	 * 
	 * @param officeId
	 * @return
	 */
	List<Job> listGlobalJobs();

	List<Job> listJobsByOffice(int officeId);

	List<Job> listJobsOrderByDate();

	List<LectureAndTalk> listLectureAndTalks();

	List<Link> listLinks();

	List<ProjectLocation> listLocationsContainingProjects();

	List<News> listNews();

	// News Related
	List<News> listNewsByYear(int year);

	List<News> listNewsOrderByDate();

	List<OfficeLocation> listOfficeLocations();

	// Office related
	List<Office> listOffices();

	List<Office> listOfficesByLocation(int locationId);

	List<Person> listPeople();

	List<PressRelease> listPressReleases();

	List<ProjectCategory> listProjectCategories();

	List<ProjectLink> listProjectLinks();

	// Project location related
	List<ProjectLocation> listProjectLocations();

	// Project related
	List<Project> listProjects();

	List<Project> listProjectsByCategory(int categoryId);

	List<Project> listProjectsByCategoryIncludingEmpty(int categoryId);

	List<Project> listProjectsByLocation(int locationId); // Generic stuff

	List<Project> listProjectsIncludingEmpty();

	List<Publication> listPublications();

	/** this will return the latest 14 blog items (ordered by date) */
	List<BlogEntry> listRecentBlogs();

	// follows ....

	/** this will return the latest 14 news items (ordered by date) */
	List<News> listRecentNews();

	List<Role> listRoles();

	List<Sustainability> listSustainability();

	List<Testimonial> listTestimonials();

	List<Working> listWorkings();

	List<YearOut> listYearOut();

}
