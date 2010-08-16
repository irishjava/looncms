package uk.mafu.loon.epr.front;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.Date;
import java.util.List;
import junit.framework.JUnit4TestAdapter;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import uk.mafu.domain.epr.Award;
import uk.mafu.domain.epr.News;
import uk.mafu.domain.epr.Person;
import uk.mafu.domain.epr.Project;
import uk.mafu.domain.epr.ProjectCategory;
import uk.mafu.domain.epr.ProjectLink;
import uk.mafu.domain.epr.dto.ContactDTO;
import uk.mafu.domain.epr.dto.SectionDTO;
import uk.mafu.loon.domain.data.VideoLink;
import com.caucho.hessian.client.HessianProxyFactory;

public class EprServiceImplIntegrationTest {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(EprServiceImplIntegrationTest.class);
	// private static String url =
	// "http://localhost:8666/epr-server/loon/eprService";
	private static String url = "http://www.mafunet.com:10000/epr-server-0.0.1-SNAPSHOT/loon/eprService";
	private static EprService eprService;

	public static void main(String[] args) {
		junit.textui.TestRunner.run(new JUnit4TestAdapter(EprServiceImplIntegrationTest.class));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HessianProxyFactory factory = new HessianProxyFactory();
		eprService = (EprService) factory.create(EprService.class, url);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {}

	@Test
	@Ignore
	public void testCanDownloadAVideo() throws Exception {
		String videoUrl = eprService.getVideoUrl();
		List<Project> listAllProjects = eprService.listAllProjects();
		for (Project project : listAllProjects) {
			VideoLink video = project.getVideo();
			if (video != null) {
				HttpClientParams params = new HttpClientParams();
				HttpConnectionManager manager = new SimpleHttpConnectionManager(true);
				HttpClient client = new HttpClient(params, manager);
				HostConfiguration configuration = new HostConfiguration();
				// configuration.setHost();
				client.setHostConfiguration(configuration);
				@SuppressWarnings("unused")
				int executeMethod = client.executeMethod(new GetMethod(videoUrl + video.getPk()));
			}
		}
	}

	@Test
	@Ignore
	public final void testlistProjectsAndCheckThumbnails() {
		List<Project> listAllProjects = eprService.listAllProjects();
		assertTrue(listAllProjects.size() > 0);
		for (Project project : listAllProjects) {
			System.err.println(project.getPk() + ":" + project.getThumbnail());
		}
		Project project = eprService.getProject(listAllProjects.get(0).getPk());
		assertNotNull(project);
	}

	@Test
	public final void testEprServiceImpl() {}

	@Test
	public final void testGetProject() {
		List<Project> listAllProjects = eprService.listAllProjects();
		assertTrue(listAllProjects.size() > 0);
		Project project = eprService.getProject(listAllProjects.get(0).getPk());
		assertNotNull(project);
	}

	@Test
	public final void testListAllNews() {
		List<News> listAllNews = eprService.listAllNewsDateOrdered();
		assertTrue(listAllNews.size() > 0);
	}

	@Test
	public final void testListAwards() {
		List<Award> awards = eprService.listAllAwards();
		assertTrue(awards.size() > 0);
	}

	@Test
	public final void testListNewsByYear() {
		DateTime d = new DateTime(new Date());
		int year = d.getYear();
		int counter = 0;
		boolean found = false;
		while (counter < 5 && found == false) {
			List<News> news = eprService.listNewsByYear(year - counter);
			if (news.size() > 0) {
				found = true;
			}
			counter++;
		}
		assertTrue(found);
	}

	@Test
	public final void testListPeople() {
		List<Person> listPeople = eprService.listPeople();
		assertTrue(listPeople.size() > 0);
	}

	@Test
	public final void testListProjectCategories() {
		List<ProjectCategory> listProjectCategories = eprService.listNonEmptyProjectCategories();
		assertTrue(listProjectCategories.size() > 0);
	}

	@Test
	public final void testListProjectsByCategory() {
		boolean found = false;
		List<ProjectCategory> listProjectCategories = eprService.listNonEmptyProjectCategories();
		start: for (ProjectCategory projectCategory : listProjectCategories) {
			int pk = projectCategory.getPk();
			List<Project> listProjectsByCategory = eprService.listProjectsByCategory(pk);
			if (listProjectsByCategory.size() > 0) {
				found = true;
				continue start;
			}
		}
		assertTrue(found);
	}

	@Test
	public final void testGetImageUrl() {
		assertNotNull(eprService.getImageUrl());
	}

	@Test
	public final void testGetPdfUrl() {
		assertNotNull(eprService.getPdfUrl());
	}

	@Test
	public final void testGetVideoUrl() {
		assertNotNull(eprService.getVideoUrl());
	}

	@Test
	public final void testListAllProjects() {
		List<Project> listAllProjects = eprService.listAllProjects();
		assertTrue(listAllProjects.size() > 0);
		boolean found = false;
		for (Project project : listAllProjects) {
			if (project.getImages().size() > 0) {
				found = true;
				break;
			}
		}
		assertTrue("must have found one project with initialized images.", found);
	}

	@Test
	public final void testGetApproachSection() {
		SectionDTO section = eprService.getApproachSection();
		assertNotNull(section);
		assertTrue(section.getImages().size() > 0);
		assertNotNull(section.getText());
		assertNotNull(section.getIdentifier());
	}

	@Test
	public final void testGetModelMakingSection() {
		SectionDTO section = eprService.getModelMakingSection();
		assertNotNull(section);
		assertTrue(section.getImages().size() > 0);
		assertNotNull(section.getText());
		assertNotNull(section.getIdentifier());
	}

	@Test
	public final void testGetOfficeSection() {
		SectionDTO section = eprService.getOfficeSection();
		assertNotNull(section);
		assertTrue(section.getImages().size() > 0);
		assertNotNull(section.getText());
		assertNotNull(section.getIdentifier());
	}

	@Test
	public final void testGetSustainabilitySection() {
		SectionDTO section = eprService.getSustainabilitySection();
		assertNotNull(section);
		assertTrue(section.getImages().size() > 0);
		assertNotNull(section.getText());
		assertNotNull(section.getIdentifier());
	}

	@Test
	public final void testgetContactInformation() {
		ContactDTO contactDTO = eprService.getContactInformation();
		assertNotNull(contactDTO);
	}

	@Test
	public final void testListProjectLinks() {
		List<ProjectLink> listProjectLink = eprService.listProjectLinks();
		assertTrue(listProjectLink.size() > 0);
	}

	@Test
	public final void testListProjectCategoriesByProject() {
		List<ProjectCategory> listNonEmptyProjectCategories = eprService.listNonEmptyProjectCategories();
		outer: for (ProjectCategory projectCategory : listNonEmptyProjectCategories) {
			List<Project> listProjectsByCategory = eprService.listProjectsByCategory(projectCategory.getPk());
			for (Project project : listProjectsByCategory) {
				List<ProjectCategory> listProjectCategoriesByProject = eprService
						.listProjectCategoriesByProject(project.getPk());
				for (ProjectCategory projectCategory2 : listProjectCategoriesByProject) {
					if (projectCategory.getPk() == projectCategory2.getPk()) {
						break outer;
					}
				}
				fail("when supplied with a project, that was found via a category,"
						+ " the tested operation must return must find a project, so I should not get here ");
			}
		}
		// List<ProjectCategory> listProjectCategoriesByProject(int projectId) ;
	}
}
