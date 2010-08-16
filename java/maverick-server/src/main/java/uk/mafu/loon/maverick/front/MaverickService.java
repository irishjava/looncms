package uk.mafu.loon.maverick.front;

import java.util.List;

import uk.mafu.domain.maverick.ArchiveItem;
import uk.mafu.domain.maverick.Award;
import uk.mafu.domain.maverick.Campaign;
import uk.mafu.domain.maverick.CampaignDTO;
import uk.mafu.domain.maverick.ContactDTO;
import uk.mafu.domain.maverick.EthosDTO;
import uk.mafu.domain.maverick.News;
import uk.mafu.domain.maverick.Person;
import uk.mafu.domain.maverick.Press;
import uk.mafu.domain.maverick.Project;
import uk.mafu.domain.maverick.ProjectCategory;
import uk.mafu.domain.maverick.Role;
import uk.mafu.domain.maverick.Showreel;

public interface MaverickService {

	Role getRoleById(int roleId);

	Campaign getCampaignById(int campaignId);

	/*
	 * The above method should list all people sorted as they are on the Website
	 * object (i.e. The list returned should be the same as concatenating the
	 * results of listPeopleByRole(int roleId) for each role on the site, in
	 * order).
	 */
	List<Person> listPeople();

	Person getPersonById(int personId);
	
	ArchiveItem getArchiveItemById(int id);
	
	List<ArchiveItem> listArchiveItems();

	List<Award> listAwards();

	ContactDTO getContactDTO();

	EthosDTO getEthosDTO();

	List<News> listNews();

	List<Press> listPress();

	List<Project> listProjects();

	List<Campaign> listCampaignsByProjectId(int projectId);

	List<Project> listProjectsByCampaignId(int campaignId);

	CampaignDTO getCampaignDTO();

	List<Role> listRoles();

	List<Role> listRolesByPerson(int personId);

	List<Person> listPeopleByRole(int roleId);

	List<ProjectCategory> listProjectCategories();

	List<Campaign> listCampaigns();

	List<Project> listProjectByCampaign(int campaignId);

	Project getProjectById(int projectId);

	List<Project> listProjectByProjectCategory(int categoryId);

	List<ProjectCategory> listProjectCategoriesByProject(int projectId);

	ProjectCategory getProjectCategoryById(int categoryId);

	Showreel getCurrentShowreel();

	String getVideoUrl();

	String getImageUrl();

	String getPdfUrl();
}
