package uk.mafu.loon.maverick.front;

import java.util.List;
import static junit.framework.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

import uk.mafu.domain.maverick.EthosDTO;
import uk.mafu.domain.maverick.Project;
import uk.mafu.domain.maverick.ProjectCategory;
import uk.mafu.domain.maverick.Role;
import uk.mafu.domain.maverick.Showreel;
import com.caucho.hessian.client.HessianProxyFactory;

public class MaverickServiceImplIntegrationTest {
	MaverickService maverickService;

	public MaverickServiceImplIntegrationTest() throws Throwable {
		// String url =
		// "http://localhost:8666/maverick-server/loon/maverickService";
		String url = "http://www.mafunet.com:10000/maverick-server-0.0.1-SNAPSHOT/loon/maverickService";
		HessianProxyFactory factory = new HessianProxyFactory();
		maverickService = (MaverickService) factory.create(
				MaverickService.class, url);
	}

	@Test
	public void testListArchiveItems() {
		maverickService.listArchiveItems();
	}

	@Test
	public void testListAwards() {
		maverickService.listAwards();
	}

	@Test
	@Ignore
	public void testListBlogItems() {
	}

	@Test
	public void testGetProjectById() {
		Project project = maverickService.listProjects().get(0);
		Project projectById = maverickService.getProjectById(project.getPk());
		assertNotNull(projectById);
	}

	@Test
	@Ignore
	public final void testGetProjectCategoryById() {
		List<ProjectCategory> categories = maverickService
				.listProjectCategories();
		for (ProjectCategory category : categories) {
			maverickService.getProjectCategoryById(category.getPk());
			break;
		}
	}

	@Test
	@Ignore
	public void testListProjectsByClientId() {
	}

	@Test
	@Ignore
	public void testListClients() {
	}

	@Test
	@Ignore
	public void testGetContactDTO() {
	}

	@Test
	public void testGetEthosDTO() {
		EthosDTO ethosDTO = maverickService.getEthosDTO();
		assertNotNull(ethosDTO);
	}

	@Test
	@Ignore
	public void testListNews() {
	}

	@Test
	@Ignore
	public void testListPeople() {
	}

	@Test
	public void testListRoles() {
		List<Role> listRoles = maverickService.listRoles();
		assertNotNull(listRoles);
		assertTrue(listRoles.size() > 0);
	}

	@Test
	@Ignore
	public void testListPress() {
	}

	@Test
	@Ignore
	public void testListProjects() {
	}

	@Test
	@Ignore
	public void testListProjectCategories() {
	}

	@Test
	@Ignore
	public void testListProjectByProjectCategory() {
	}

	@Test
	@Ignore
	public void testListProjectCategoriesByProject() {
	}

	@Test
	public void testGetCurrentShowreel() {
		Showreel currentShowreel = this.maverickService.getCurrentShowreel();
		assertNotNull(currentShowreel.getCampaign());
	}

	@Test
	@Ignore
	public void testGetVideoUrl() {
	}

	@Test
	@Ignore
	public void testGetImageUrl() {
	}

	@Test
	@Ignore
	public void testGetPdfUrl() {
	}
	// FletcherService fletcherService;
	//
	// public FletcherServiceImplIntegrationTest() throws Throwable {
	// String url =
	// "http://localhost:8666/fletcher-server/loon/fletcherService";
	// // String url =
	// //
	// "http://www.mafunet.com:10000/fletcher-server-0.0.1-SNAPSHOT/loon/fletcherService";
	// HessianProxyFactory factory = new HessianProxyFactory();
	// fletcherService = (FletcherService) factory.create(FletcherService.class,
	// url);
	// }
	//
	// /**
	// * @throws java.lang.Exception
	// */
	// @BeforeClass
	// public static void setUpBeforeClass() throws Exception {}
	//
	// /**
	// * @throws java.lang.Exception
	// */
	// @AfterClass
	// public static void tearDownAfterClass() throws Exception {}
	//
	// /**
	// * @throws java.lang.Exception
	// */
	// @Before
	// public void setUp() throws Exception {}
	//
	// * @throws java.lang.Exception
	// */
	// @After
	// public void tearDown() throws Exception {}
	//
	// @Test
	// public final void testGetImageUrl() {
	// fletcherService.getImageUrl();
	// }
	//
	// @Test
	// public final void testListCategoriesByProjectId() {
	// // TODO:Implement
	// }
	//
	// @Test
	// public final void testListPress() {
	// List<Press> listPress = fletcherService.listPress();
	// assertTrue(listPress.size() > 0);
	// }
	//
	// @Test
	// public final void testListProjectCategories() {
	// List<ProjectCategory> listProjectCategories =
	// fletcherService.listProjectCategories();
	// assertTrue(listProjectCategories.size() > 0);
	// ProjectCategory projectCategory = listProjectCategories.get(0);
	// boolean foundProjectWithImages = false;
	// List<DummyProject> projects =
	// fletcherService.listProjectsByProjectCategory(projectCategory.getPk());
	// assertTrue(projects.size() > 0);
	// for (DummyProject project : projects) {
	// if (project.getImages().size() > 0) {
	// foundProjectWithImages = true;
	// }
	// }
	// assertTrue(foundProjectWithImages);
	// }
	//
	// @Test
	// public final void testListProjects() {
	// List<DummyProject> listProjects = fletcherService.listProjects();
	// assertTrue(listProjects.size() > 0);
	// }
	//
	// @Test
	// public final void testListSlides() {
	// List<Slide> listSlides = fletcherService.listSlides();
	// assertTrue(listSlides.size() > 0);
	// }
	//
	// @Test
	// public final void testgetWebsite() {
	// Website website = fletcherService.getWebsite();
	// assertNotNull(website);
	// }
}
