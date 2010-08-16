package uk.mafu.loon.mch.front;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.mafu.domain.mch.Project;
import uk.mafu.domain.mch.ProjectCategory;
import uk.mafu.domain.mch.ProjectLink;
import uk.mafu.loon.domain.data.ImageLink;
import com.caucho.hessian.client.HessianProxyFactory;

public class MchServiceImplIntegrationTest {
	MchService mchService;

	public MchServiceImplIntegrationTest() throws Throwable {
		//String url = "http://localhost:8666/mch-server/loon/mchService";
		String url = "http://www.mafunet.com:10000/mch-server-0.0.1-SNAPSHOT/loon/mchService";
		HessianProxyFactory factory = new HessianProxyFactory();
		mchService = (MchService) factory.create(MchService.class, url);
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
		String imageUrl = mchService.getImageUrl();
		Assert.assertNotNull(imageUrl);
	}

	@Test
	public final void testListProjectCategories() {
		List<ProjectCategory> listProjectCategories = mchService.listProjectCategories();
		Assert.assertTrue(listProjectCategories.size() > 0);
	}

	
		
	
	@Test
	public final void testGetProject() throws IOException {
		boolean foundValidProject = false;
		List<ProjectLink> listHomePageImages = mchService.listHomePageImages();
		found: for (ProjectLink projectLink : listHomePageImages) {
			if (projectLink.getProject() != null) {
				Project project = mchService.getProject(projectLink.getPk());
				if (!project.getImages().isEmpty()) {
					URL u1 = new URL(mchService.getImageUrl() + project.getImages().get(0).getPk());
					String contentType1 = u1.openConnection().getContentType();
					if(!contentType1.startsWith("image")){
						continue;
					}
					if (project.getVideo() != null) {
						URL u = new URL(mchService.getVideoUrl() + project.getVideo().getPk());
						String contentType = u.openConnection().getContentType();
						if ("video/x-flv".equals(contentType)) {
							foundValidProject = true;
							break found;
						}
					}
				}
			}
		}
		Assert.assertTrue(foundValidProject);
	}

	@Test
	public final void testListHomePageImages() {
		List<ProjectLink> listHomePageImages = mchService.listHomePageImages();
		Assert.assertTrue(listHomePageImages.size() > 0);
	}

	@Test
	public final void testGetBannerText() {
		String bannerText = mchService.getBannerText();
		Assert.assertNotNull(bannerText);
	}

	@Test
	public final void testGetContactText() {
		String contactText = mchService.getContactText();
		Assert.assertNotNull(contactText);
	}

	@Test
	public final void testGetCreditText() {
		String creditText = mchService.getCreditText();
		Assert.assertNotNull(creditText);
	}

	@Test
	public final void testGetMap() {
		ImageLink map = mchService.getMap();
		Assert.assertNotNull(map);
	}

	@Test
	public final void testGetProjectCategoriesByProject() {
		boolean success = false;
		List<ProjectCategory> listProjectCategories = mchService.listProjectCategories();
		found: for (ProjectCategory projectCategory : listProjectCategories) {
			List<Project> listProjectsByCategory = mchService.listProjectsByCategory(projectCategory.getPk());
			for (Project project : listProjectsByCategory) {
				List<ProjectCategory> projectCategoriesByProject = mchService.getProjectCategoriesByProject(project
						.getPk());
				for (ProjectCategory projectCategory2 : projectCategoriesByProject) {
					if (projectCategory2.getPk() == projectCategory.getPk()) {
						// we found the project category... lets return
						success = true;
						break found;
					}
				}
			}
		}
		Assert.assertTrue(success);
	}

	@Test
	public final void testListProjectsByCategory() {
		boolean success = true;
		List<ProjectCategory> listProjectCategories = mchService.listProjectCategories();
		for (ProjectCategory projectCategory : listProjectCategories) {
			List<Project> listProjectsByCategory = mchService.listProjectsByCategory(projectCategory.getPk());
			if (listProjectsByCategory.isEmpty() == false) {
				success = true;
				break;
			}
		}
		Assert.assertTrue(success);
	}

	@Test
	public final void testGetVideoUrl() {
		String videoUrl = mchService.getVideoUrl();
		Assert.assertNotNull(videoUrl);
	}
}
