/**
 * 
 */
package uk.mafu.loon.gillespies.front;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import uk.mafu.domain.gillespies.Assignment;
import uk.mafu.domain.gillespies.Award;
import uk.mafu.domain.gillespies.BlogEntry;
import uk.mafu.domain.gillespies.Client;
import uk.mafu.domain.gillespies.CommunityWork;
import uk.mafu.domain.gillespies.Job;
import uk.mafu.domain.gillespies.LectureAndTalk;
import uk.mafu.domain.gillespies.News;
import uk.mafu.domain.gillespies.Office;
import uk.mafu.domain.gillespies.OfficeLocation;
import uk.mafu.domain.gillespies.Profile;
import uk.mafu.domain.gillespies.Project;
import uk.mafu.domain.gillespies.ProjectCategory;
import uk.mafu.domain.gillespies.ProjectLink;
import uk.mafu.domain.gillespies.ProjectLocation;
import uk.mafu.domain.gillespies.Publication;
import uk.mafu.domain.gillespies.Sustainability;
import uk.mafu.domain.gillespies.Testimonial;
import uk.mafu.domain.gillespies.YearOut;

/**
 * @author bryan
 * 
 */
public abstract class AbstractGillespiesIntegrationTest {
	

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#getImageUrl()}.
	 */
	@Test
	public final void testGetImageUrl() {
		String imageUrl = getService().getImageUrl();
		assertNotNull(imageUrl);
	}

	public abstract GillespiesService getService() ;

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listAssignments()}.
	 */
	@Test
	public final void testListAssignments() {
		List<Assignment> listAssignments = getService().listAssignments();
		assertTrue(listAssignments.size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listAwards()}.
	 */
	@Test
	public final void testListAwards() {
		List<Award> listAwards = getService().listAwards();
		assertTrue(listAwards.size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listBlogEntries()}.
	 */
	@Test
	public final void testListBlogEntries() {
		List<BlogEntry> listBlogEntries = getService().listBlogEntries();
		assertTrue(listBlogEntries.size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listClients()}.
	 */
	@Test
	public final void testListClients() {
		List<Client> listClients = getService().listClients();
		assertTrue(listClients.size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listGlobalJobs()}.
	 */
	@Test
	public final void testListGlobalJobs() {
		List<Job> listGlobalJobs = getService().listGlobalJobs();
		assertTrue(listGlobalJobs.size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listJobsByOffice(int)}.
	 */
	@Test
	public final void testListJobsByOffice() {
		Office office = getService().listOffices().get(0);
		assertNotNull(office);
		assertTrue(getService().listJobsByOffice(office.getPk()).size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listJobsOrderByDate()}.
	 */
	@Test
	public final void testListJobsOrderByDate() {
		assertTrue(getService().listJobsOrderByDate().size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listLinks()}.
	 */
	@Test
	public final void testListLinks() {
		assertTrue(getService().listLinks().size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listNews()}.
	 */
	@Test
	public final void testListNews() {
		assertTrue(getService().listNews().size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listNewsByYear(int)}.
	 */
	@Test
	public final void testListNewsByYear() {
		assertTrue(getService().listNewsByYear(2008).size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listNewsOrderByDate()}.
	 */
	@Test
	public final void testListNewsOrderByDate() {
		assertTrue(getService().listNewsOrderByDate().size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listOfficeLocations()}.
	 */
	@Test
	public final void testListOfficeLocations() {
		assertTrue(getService().listOfficeLocations().size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listOffices()}.
	 */
	@Test
	public void testListOffices() {
		List<Office> listOffices = getService().listOffices();
		assertTrue(listOffices.size() > 0);
	}

	
	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listOfficesByLocation(int)}.
	 */
	@Test
	public final void testListOfficesByLocation() {
		OfficeLocation officeLocation = getService().listOfficeLocations().get(0);
		assertTrue(getService().listOfficesByLocation(officeLocation.getPk()).size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listPeople()}.
	 */
	@Test
	public final void testListPeople() {
		assertTrue(getService().listPeople().size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listPressReleases()}.
	 */
	@Test
	public final void testListPressReleases() {
		assertTrue(getService().listPressReleases().size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listProjectCategories()}.
	 */
	@Test
	public final void testListProjectCategories() {
		assertTrue(getService().listProjectCategories().size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listProjectLocations()}.
	 */
	@Test
	public final void testListProjectLocations() {
		assertTrue(getService().listProjectLocations().size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listProjects()}.
	 */
	@Test
	public final void testListProjects() {
		assertTrue(getService().listProjects().size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listProjectsByCategory(int)}.
	 */
	@Test
	public final void testListProjectsByCategory() {
		boolean found = false;
		List<ProjectCategory> listProjectCategories = getService().listProjectCategories();
		assertTrue(listProjectCategories.size() > 0);
		for (ProjectCategory projectCategory : listProjectCategories) {
			if (getService().listProjectsByCategory(projectCategory.getPk()).size() > 0) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listProjectsByLocation(int)}.
	 */
	@Test
	public final void testListProjectsByLocation() {
		boolean found = false;
		List<ProjectLocation> list = getService().listProjectLocations();
		assertTrue(list.size() > 0);
		for (ProjectLocation projectLocation : list) {
			if (getService().listProjectsByLocation(projectLocation.getPk()).size() > 0) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listRoles()}.
	 */
	@Test
	public final void testListRoles() {
		assertTrue(getService().listRoles().size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listTestimonials()}.
	 */
	@Test
	public final void testListTestimonials() {
		List<Testimonial> listTestimonials = getService().listTestimonials();
		assertTrue(listTestimonials.size() > 0);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#getOfficesByJob(final int jobId)}.
	 */
	@Test
	public final void testGetOfficesByJob() {
		List<Job> jobs = getService().listJobsOrderByDate();
		boolean found = false;
		for (Job job : jobs) {
			List<Office> officesByJob = getService().getOfficesByJob(job.getPk());
			if (officesByJob.size() > 0) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#getCategoriesByProject(final int
	 * jobId)}.
	 */
	@Test
	public final void testGetCategoriesByProject() {
		List<Project> projects = getService().listProjects();
		boolean found = false;
		for (Project project : projects) {
			List<ProjectCategory> projectCategories = getService().getCategoriesByProject(project.getPk());
			if (projectCategories.size() > 0) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	@Test
	public final void testGetProfile() {
		Profile profile = getService().getProfile();
		assertNotNull(profile);
	}

	@Test
	public final void testListCommunityWork() {
		List<CommunityWork> listCommunityWork = getService().listCommunityWork();
		assertTrue(listCommunityWork.size() > 0);
	}

	@Test
	public final void testListLectureAndTalks() {
		List<LectureAndTalk> listLectureAndTalks = getService().listLectureAndTalks();
		assertTrue(listLectureAndTalks.size() > 0);
	}

	@Test
	public final void testListPublications() {
		List<Publication> listPublications = getService().listPublications();
		assertTrue(listPublications.size() > 0);
	}

	@Test
	public final void testListSustainability() {
		List<Sustainability> listSustainability = getService().listSustainability();
		assertTrue(listSustainability.size() > 0);
	}

	@Test
	public final void testListYearOut() {
		List<YearOut> listYearOut = getService().listYearOut();
		assertTrue(listYearOut.size() > 0);
	}

	@Test
	public final void testListAllJobs() {
		List<OfficeAndJob> allJobs = getService().listAllJobs();
		// Assert that is in reverse chronological order
		OfficeAndJob prev = null;
		for (OfficeAndJob officeAndJob : allJobs) {
			if (prev != null) {
				assertTrue("reverse chrono", officeAndJob.getJob().getDate().compareTo(prev.getJob().getDate()) == -1);
			}
			prev = officeAndJob;
		}
		assertTrue(allJobs.size() > 0);
	}

	@Test
	public final void testGetProjectByAward() {
		List<Award> awards = getService().listAwards();
		boolean found = false;
		for (Award award : awards) {
			Project p = getService().getProjectByAward(award.getPk());
			if (p != null) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	@Test
	public final void testGetProjectByNews() {
		List<News> newsItems = getService().listNews();
		boolean found = false;
		for (News news : newsItems) {
			Project p = getService().getProjectByNews(news.getPk());
			if (p != null) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	@Test
	public void testListBlogEntriesByYear() {
		assertTrue(getService().listBlogEntriesByYear(2008).size() > 0);
	}

	@Test
	public void testListBlogEntriesOrderByDate() {
		List<BlogEntry> listBlogEntriesOrderByDate = getService().listBlogEntriesOrderByDate();
		assertTrue(listBlogEntriesOrderByDate.size() > 0);
	}

	@Test
	public void testListProjectLinks() {
		List<ProjectLink> list = getService().listProjectLinks();
		assertTrue(list.size() > 0);
	}

	@Test
	public void testListArchiveBlogYears() {
		List<Integer> listArchiveBlogYears = getService().listArchiveBlogYears();
		assertNotNull(listArchiveBlogYears);
	}

	@Test
	public void testListArchiveBlogs() {
		List<BlogEntry> listArchiveBlogs = getService().listArchiveBlogs();
		assert (listArchiveBlogs.size() > 0);
	}

	@Test
	public void testListArchiveNews() {
		List<News> listArchiveNews = getService().listArchiveNews();
		assert (listArchiveNews.size() > 0);
	}

	@Test
	public void testListArchiveNewsYears() {
		List<Integer> listArchiveNewsYears = getService().listArchiveNewsYears();
		assertNotNull(listArchiveNewsYears);
	}

	@Test
	public void testListRecentBlogs() {
		List<BlogEntry> listRecentBlogs = getService().listRecentBlogs();
		assert (listRecentBlogs.size() > 4);
	}

	@Test
	public void testListRecentNews() {
		List<News> listRecentNews = getService().listRecentNews();
		assert (listRecentNews.size() > 4);
	}
}