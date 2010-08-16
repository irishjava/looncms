/**
 * 
 */
package uk.mafu.kwa.front;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.mafu.kwa.domain.Award;
import uk.mafu.kwa.domain.Client;
import uk.mafu.kwa.domain.Excite;
import uk.mafu.kwa.domain.HomePageSlide;
import uk.mafu.kwa.domain.JobPosting;
import uk.mafu.kwa.domain.News;
import uk.mafu.kwa.domain.Person;
import uk.mafu.kwa.domain.Press;
import uk.mafu.kwa.domain.Project;
import uk.mafu.kwa.domain.ProjectCategory;
import uk.mafu.kwa.domain.ProjectSlide;
import uk.mafu.loon.common.DownloadsConfig;

/**
 * @author bryan
 * 
 */
public abstract class AbstractKwaServiceImplTest {

	public abstract KwaService fetchService();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		throw new UnsupportedOperationException("implement in subclass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		throw new UnsupportedOperationException("implement in subclass");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFetchDownloadsConfig() {
		DownloadsConfig d = fetchService().fetchDownloadsConfig();
		assertNotNull(d);
	}

	@Test
	public void testListNewsItems() {
		// listNewsItems
		List<News> d = fetchService().listNewsItems();
		assertNotNull(d);

	}

	@Test
	public void testListJobPostings() {
		// listJobPostings
		List<JobPosting> d = fetchService().listJobPostings();
		assertNotNull(d);
	}

	@Test
	public void testListAwards() {

		// listAwards
		List<Award> d = fetchService().listAwards();
		assertNotNull(d);
	}

	@Test
	public void testListClients() {

		// listClients
		List<Client> d = fetchService().listClients();
		assertNotNull(d);
	}

	@Test
	public void testListPressItems() {
		List<Press> d = fetchService().listPressItems();

		assertNotNull(d);
	}

	@Test
	public void testListDirectors() {
		List<Person> d = fetchService().listDirectors();
		assertNotNull(d);
	}

	@Test
	public void testListEmployees() {
		List<Person> d = fetchService().listEmployees();
		assertNotNull(d);
	}

	@Test
	public void testFetchProject() {
		Project d = fetchService().fetchProject(findValidProjectId());
		assertNotNull(d);
	}

	@Test
	public void testFetchProjectSlideByProject() {

		List<ProjectSlide> d = fetchService().fetchProjectSlideByProject(
				findValidProjectId());

		assertNotNull(d);
	}

	private int findValidProjectId() {
		List<ProjectCategory> listProjectCategories = fetchService()
				.listProjectCategories();
		for (ProjectCategory c : listProjectCategories) {
			List<Project> listProjectsByProjectCategory = fetchService()
					.listProjectsByProjectCategory(c.getPk());
			for (Project p : listProjectsByProjectCategory) {
				return p.getPk();
			}
		}
		throw new UnsupportedOperationException(
				"cannot itterate to a valid project.");
	}

	@Test
	public void testFetchTeamExcite() {
		Excite d = fetchService().fetchTeamExcite();
		assertNotNull(d);
	}

	@Test
	public void testFetchDirectorsExcite() {
		Excite d = fetchService().fetchDirectorsExcite();
		assertNotNull(d);
	}

	@Test
	public void testFetchContactDTO() {
		ContactDTO d = fetchService().fetchContactDTO();
		assertNotNull(d);
	}

	@Test
	public void testFetchEthosDTO() {
		EthosDTO d = fetchService().fetchEthosDTO();
		assertNotNull(d);
	}

	@Test
	public void testFetchOverviewDTO() {
		OverviewDTO d = fetchService().fetchOverviewDTO();
		assertNotNull(d);
	}

	@Test
	public void testFetchSustainabilityDTO() {
		SustainabilityDTO d = fetchService().fetchSustainabilityDTO();
		assertNotNull(d);
	}

	@Test
	public void testFetchMonographDTO() {
		MonographDTO d = fetchService().fetchMonographDTO();
		assertNotNull(d);
	}

	@Test
	public void testFetchHomePageSlides() {
		List<HomePageSlide> d = fetchService().fetchHomePageSlides();
		assertNotNull(d);
	}

	@Test
	public void testListProjectCategoriesHavingProjects() {
		List<ProjectCategory> d = fetchService()
				.listProjectCategoriesHavingProjects();
		assertTrue(d.size() > 0);
		for (ProjectCategory projectCategory : d) {
			if (projectCategory.getProjects().size() == 0) {
				fail("found project category without projects");
			}
		}
	}

	@Test
	public void testListProjectCategories() {
		List<ProjectCategory> d = fetchService().listProjectCategories();
		assertTrue(d.size() > 0);
	}

	@Test
	public void testListProjectCategoriesByProjectId() {
		List<ProjectCategory> d = fetchService()
				.listProjectCategoriesByProjectId(findValidProjectId());
		assertNotNull(d);
	}

	@Test
	public void testListHomepageSlides() {
		List<HomePageSlide> d = fetchService().listHomepageSlides();
		assertNotNull(d);
	}

	@Test
	public void testFetchHomepageExcite() {
		Excite d = fetchService().fetchHomepageExcite();
		assertNotNull(d);
	}

	@SuppressWarnings("unused")
	public void testListProjectsByProjectCategory() {
		List<ProjectCategory> listProjectCategories = fetchService()
				.listProjectCategories();
		for (ProjectCategory c : listProjectCategories) {
			List<Project> listProjectsByProjectCategory = fetchService()
					.listProjectsByProjectCategory(c.getPk());

			for (Project p : listProjectsByProjectCategory) {
				return;
			}
		}
		throw new UnsupportedOperationException(
				"cannot find a category, containing projects");
	}

	public void testFetchNewsExcite() {
		Excite fetchNewsExcite = fetchService().fetchNewsExcite();
		assertNotNull(fetchNewsExcite);
	}

	public void testFetchAwardsExcite() {
		Excite fetchAwardsExcite = fetchService().fetchAwardsExcite();
		assertNotNull(fetchAwardsExcite);
	}

	public void testFetchPressExcite() {
		Excite fetchPressExcite = fetchService().fetchPressExcite();
		assertNotNull(fetchPressExcite);
	}

	public void testFetchClientsExcite() {
		Excite fetchClientsExcite = fetchService().fetchClientsExcite();
		assertNotNull(fetchClientsExcite);

	}

}
