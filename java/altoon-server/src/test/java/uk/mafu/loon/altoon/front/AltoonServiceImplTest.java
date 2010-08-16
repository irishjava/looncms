package uk.mafu.loon.altoon.front;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Test;
import uk.mafu.domain.altoon.Bibliography;
import uk.mafu.domain.altoon.Location;
import uk.mafu.domain.altoon.News;
import uk.mafu.domain.altoon.Office;
import uk.mafu.domain.altoon.Person;
import uk.mafu.domain.altoon.Project;
import uk.mafu.domain.altoon.ProjectCategory;
import uk.mafu.domain.altoon.ProjectLink;
import uk.mafu.domain.altoon.Role;
import uk.mafu.domain.altoon.Service;
import uk.mafu.domain.altoon.dto.AwardDTO;
import uk.mafu.domain.altoon.dto.SubSectionDTO;
import uk.mafu.loon.domain.data.ImageLink;
import com.caucho.hessian.client.HessianProxyFactory;

public class AltoonServiceImplTest {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(AltoonServiceImplTest.class);
	AltoonService altoonService;

	public AltoonServiceImplTest() throws Throwable {
		String url = "http://localhost:8666/altoon-server/loon/altoonService";
		//String url =		"http://www.mafunet.com:10000/altoon-server-0.0.1-SNAPSHOT/loon/altoonService";
		HessianProxyFactory factory = new HessianProxyFactory();
		altoonService = (AltoonService) factory.create(AltoonService.class, url);
	}

	@Test
	public final void testGetAwardsImage() {
		ImageLink awardsImage = altoonService.getAwardsImage();
		assertNotNull(awardsImage);
	}
	
	@Test
	public final void testGetImageUrl() {
		String imageUrl = altoonService.getImageUrl();
		assertNotNull(imageUrl);
	}

	@Test
	public final void testGetPdfUrl() {
		String pdfUrl = altoonService.getPdfUrl();
		assertNotNull(pdfUrl);
	}

	@Test
	public final void testGetProjectByNews() {
		List<News> newsItems = altoonService.listNews();
		boolean found = false;
		for (News news : newsItems) {
			if (!(altoonService.getProjectByNews(news.getPk()) == null)) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	@Test
	public final void testGetFullyInitializedProjectById() {
		List<Project> listProjects = altoonService.listProjects();
		boolean found = false;
		for (Project project : listProjects) {
			Project initialized = altoonService.getFullyInitializedProjectById(project.getPk());
			if (initialized.getImages().size() > 0) {
				found = true;
			}
		}
		assertTrue(found);
	}

	@Test
	public final void testGetProjectByProjectLink() {
		List<ProjectLink> listProjectLinks = altoonService.listProjectLinks();
		boolean found = false;
		for (ProjectLink link : listProjectLinks) {
			Project p = altoonService.getProjectByProjectLink(link.getPk());
			if (p != null) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	@Test
	public final void testListAwardDTOs() {
		List<AwardDTO> listAwardDTOs = altoonService.listAwardDTOs();
		assertTrue(listAwardDTOs.size() > 0);
		for (AwardDTO awardDTO : listAwardDTOs) {
			assertNotNull(awardDTO.award);
			// Don't check that the location is not null, cause the project may
			// not have been set by user..
		}
	}

	@Test
	public final void testListBibliographies() {
		List<Bibliography> listBibliographies = altoonService.listBibliographies();
		assertTrue(listBibliographies.size() > 0);
	}

	@Test
	public final void testListLocations() {
		List<Location> listLocations = altoonService.listLocations();
		assertTrue(listLocations.size() > 0);
	}

	@Test
	public final void testListNews() {
		List<News> listNews = altoonService.listNews();
		assertTrue(listNews.size() > 0);
	}

	@Test
	public final void testListOffices() {
		List<Office> offices = altoonService.listOffices();
		assertTrue(offices.size() > 0);
	}

	@Test
	public final void testListPeopleByRole() {
		List<Role> roles = altoonService.listRoles();
		boolean found = false;
		for (Role role : roles) {
			List<Person> persons = altoonService.listPeopleByRole(role.getPk());
			if (persons.size() > 0) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	@Test
	public final void testListProjectCategories() {
		assertTrue(altoonService.listProjectCategories().size() > 0);
	}

	@Test
	public final void testListProjectLinks() {
		assertTrue(altoonService.listProjectCategories().size() > 0);
	}

	@Test
	public final void testListProjectsByLocation() {
		List<Location> listLocations = altoonService.listLocations();
		boolean found = false;
		for (Location location : listLocations) {
			if ((altoonService.listProjectsByLocation(location.getPk()).size() > 0)) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	@Test
	public final void testListAllProjectsOrderedByAlphabet() {
		List<Project> res = altoonService.listAllProjectsOrderedByAlphabet();
		res.size();
	}

	@Test
	public final void testListProjectsByProjectCategory() {
		List<ProjectCategory> cats = altoonService.listProjectCategories();
		boolean found = false;
		finish: for (ProjectCategory category : cats) {
			List<Project> projects = altoonService.listProjectsByProjectCategory(category.getPk());
			if ((projects.size() > 0)) {
				found = true;
				break finish;
			}
		}
		assertTrue(found);
	}

	@Test
	public final void testListProjectsByCategoryOrderedByCategory() {
		List<ProjectCategory> listProjectCategories = altoonService.listProjectCategories();
		boolean found = false;
		for (ProjectCategory category : listProjectCategories) {
			List<Project> listProjectsByCategoryOrderedByCategory = altoonService
					.listProjectsByCategoryOrderedByCategory(category.getPk());
			if (listProjectsByCategoryOrderedByCategory.size() > 0) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	@Test
	public final void testListRoles() {
		assertTrue(altoonService.listRoles().size() > 0);
	}

	@Test
	public final void testListProjectsByCategory() {
		boolean found = false;
		List<ProjectCategory> cats = altoonService.listProjectCategories();
		for (ProjectCategory cat : cats) {
			List<Project> list = altoonService.listProjectsByProjectCategory(cat.getPk());
			if (list.size() > 0) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	@Test
	public final void testListProjects() {
		List<Project> listProjects = altoonService.listProjects();
		assertTrue(listProjects.size() > 0);
	}

	@Test
	public final void testListProjectCategoriesForProject() {
		boolean found = false;
		List<Project> listProjects = altoonService.listProjects();
		for (Project project : listProjects) {
			List<ProjectCategory> listProjectCategoriesForProject = altoonService
					.listProjectCategoriesForProject(project.getPk());
			if (listProjectCategoriesForProject.size() > 0) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	@Test
	public final void testGetOffice() {
		List<Office> listOffices = altoonService.listOffices();
		assertTrue(listOffices.size() > 0);
		Office office = altoonService.getOffice(listOffices.get(0).getPk());
		assertNotNull(office);
	}

	@Test
	public final void testGetPerson() {
		List<Role> listRoles = altoonService.listRoles();
		finish: for (Role role : listRoles) {
			List<Person> listPeopleByRole = altoonService.listPeopleByRole(role.getPk());
			for (Person person : listPeopleByRole) {
				Person person2 = altoonService.getPerson(person.getPk());
				assertNotNull(person2);
				break finish;
			}
		}
	}

	@Test
	public final void testGetProfileSection() {
		SubSectionDTO profile = altoonService.getProfile();
		assertNotNull(profile);
	}

	@Test
	public final void testGetService() {
		List<Service> listServices = altoonService.listServices();
		for (Service service : listServices) {
			altoonService.getService(service.getPk());
		}
	}

	@Test
	public final void testGetSustainabilitySection() {
		SubSectionDTO sustainability = altoonService.getSustainability();
		assertNotNull(sustainability.getText());
	}
}
