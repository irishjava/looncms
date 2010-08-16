package uk.mafu.loon.altoon.front;

import java.util.List;
import uk.mafu.domain.altoon.Bibliography;
import uk.mafu.domain.altoon.Location;
import uk.mafu.domain.altoon.News;
import uk.mafu.domain.altoon.Office;
import uk.mafu.domain.altoon.Person;
import uk.mafu.domain.altoon.Project;
import uk.mafu.domain.altoon.ProjectCategory;
import uk.mafu.domain.altoon.ProjectLink;
import uk.mafu.domain.altoon.Region;
import uk.mafu.domain.altoon.Role;
import uk.mafu.domain.altoon.Service;
import uk.mafu.domain.altoon.dto.AwardDTO;
import uk.mafu.domain.altoon.dto.SubSectionDTO;
import uk.mafu.loon.domain.data.ImageLink;

public interface AltoonService {
	
	
	
	/**
	 * Projects .
	 */
	List<ProjectLink> listAllProjectLinksOrderedByWebsite();

	List<Project> listAllProjectsOrderedByWebsite();

	List<Project> listAllProjectsOrderedByAlphabet();

	List<ProjectCategory> listAllProjectCategoriesOrderedByWebsite(boolean allowEmpty);

	List<Project> listProjectsByCategoryOrderedByCategory(int categoryId);

	/**
	 * Geographic .
	 */
	List<Region> listAllRegionsOrderedByWebsite(boolean allowEmpty);

	List<Location> listLocationsByRegionOrderedByRegion(int regionId);

	/**
	 * Section objects .
	 */
	SubSectionDTO getProfile();

	SubSectionDTO getPhilosophy();

	SubSectionDTO getHistory();

	SubSectionDTO getSustainability();

	/**
	 * Bibliography .
	 */
	List<Bibliography> listAllBibliographiesOrderedByDate();

	/**
	 * Services .
	 */
	List<Service> listAllServicesOrderedByWebsite();

	Service getService(int id);

	/**
	 * People .
	 */
	List<Role> listAllRolesOrderedByWebsite(boolean allowEmpty);

	List<Person> listPeopleByRoleOrderedByRole(int roleId);

	Person getPerson(int personId);

	/**
	 * Awards .
	 */
	List<AwardDTO> listAllAwardsOrderedByDate();
	
	
	ImageLink getAwardsImage();

	/**
	 * News .
	 */
	List<News> listAllNewsOrderedByDate();

	/**
	 * Contact .
	 */
	List<Office> listAllOfficesOrderedByWebSite();

	Office getOffice(int officeId);

	// -----------------------------------
	List<News> listNews();

	Project getProjectByNews(int newsId);

	List<ProjectLink> listProjectLinks();

	Project getProjectByProjectLink(int projectLinkId);

	List<Location> listLocations();

	List<Project> listProjectsByLocation(int locationId);

	Project getFullyInitializedProjectById(int projectId);

	List<ProjectCategory> listProjectCategories();

	List<Project> listProjects();

	List<Service> listServices();

	List<Project> listProjectsByProjectCategory(int categoryId);

	List<ProjectCategory> listProjectCategoriesForProject(int projectId);

	List<AwardDTO> listAwardDTOs();

	List<Bibliography> listBibliographies();

	List<Office> listOffices();

	List<Role> listRoles();

	List<Person> listPeopleByRole(int roleId);

	Location getLocationForProject(int pk);

	String getImageUrl();

	String getPdfUrl();

	SubSectionDTO getProfileSection();

	SubSectionDTO getHistorySection();

	SubSectionDTO getSustainabilitySection();
}
