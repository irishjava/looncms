package uk.mafu.loon.fletcher.front;

import java.util.List;
import static junit.framework.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.mafu.domain.fletcher.Press;
import uk.mafu.domain.fletcher.Project;
import uk.mafu.domain.fletcher.ProjectCategory;
import uk.mafu.domain.fletcher.Slide;
import uk.mafu.domain.fletcher.Website;
import uk.mafu.loon.domain.data.PdfLink;
import com.caucho.hessian.client.HessianProxyFactory;

public class FletcherServiceImplIntegrationTest {
	FletcherService fletcherService;

	public FletcherServiceImplIntegrationTest() throws Throwable {
		String url = "http://localhost:8666/fletcher-server/loon/fletcherService";
		// String url =
		// "http://www.mafunet.com:10000/fletcher-server-0.0.1-SNAPSHOT/loon/fletcherService";
		HessianProxyFactory factory = new HessianProxyFactory();
		fletcherService = (FletcherService) factory.create(FletcherService.class, url);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}

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
	public final void testGetImageUrl() {
		fletcherService.getImageUrl();
	}

	@Test
	public final void testListCategoriesByProjectId() {
	// TODO:Implement
	}

	@Test
	public final void testListPress() {
		List<Press> listPress = fletcherService.listPress();
		assertTrue(listPress.size() > 0);
		for (Press press : listPress) {
			PdfLink pdf = press.getPdf();
			System.err.println(pdf);
		}
	}

	@Test
	public final void testListProjectCategories() {
		List<ProjectCategory> listProjectCategories = fletcherService.listProjectCategories();
		assertTrue(listProjectCategories.size() > 0);
		ProjectCategory projectCategory = listProjectCategories.get(0);
		boolean foundProjectWithImages = false;
		List<Project> projects = fletcherService.listProjectsByProjectCategory(projectCategory.getPk());
		assertTrue(projects.size() > 0);
		for (Project project : projects) {
			if (project.getImages().size() > 0) {
				foundProjectWithImages = true;
			}
		}
		assertTrue(foundProjectWithImages);
	}

	@Test
	public final void testListProjects() {
		List<Project> listProjects = fletcherService.listProjects();
		assertTrue(listProjects.size() > 0);
	}

	@Test
	public final void testListSlides() {
		List<Slide> listSlides = fletcherService.listSlides();
		assertTrue(listSlides.size() > 0);
	}

	@Test
	public final void testgetWebsite() {
		Website website = fletcherService.getWebsite();
		assertNotNull(website);
	}
}
