package uk.mafu.kwa.front;

import java.util.List;

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

public interface KwaService {

	DownloadsConfig fetchDownloadsConfig();

	// listNewsItems
	List<News> listNewsItems();

	// listJobPostings
	List<JobPosting> listJobPostings();

	// listAwards
	List<Award> listAwards();

	// listClients
	List<Client> listClients();

	// listPressItems
	List<Press> listPressItems();

	// listDirectors
	List<Person> listDirectors();

	// listEmployees
	List<Person> listEmployees();

	// listProjects
	List<Project> listProjectsByProjectCategory(final int projectCategoryId);

	// fetchProject
	Project fetchProject(final int pk);

	// fetchProjectSlidesByProject projectId
	List<ProjectSlide> fetchProjectSlideByProject(final int projectId);

	// Pure Excite stuff on website
	// fetchTeamExcite

	Excite fetchTeamExcite();

	// fetchDirectorsExcite
	Excite fetchDirectorsExcite();
	
	// fetchDirectorsExcite
	Excite fetchNewsExcite();
	
	// fetchDirectorsExcite
	Excite fetchAwardsExcite();
	
	// fetchDirectorsExcite
	Excite fetchPressExcite();
	
	// fetchDirectorsExcite
	Excite fetchClientsExcite();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// fetchContactDTO
	ContactDTO fetchContactDTO();

	// fetchEthosDTO

	EthosDTO fetchEthosDTO();

	// fetchOverviewDTO
	OverviewDTO fetchOverviewDTO();

	// fetchSustainabilityDTO
	SustainabilityDTO fetchSustainabilityDTO();
	
	MonographDTO fetchMonographDTO();

	List<HomePageSlide> fetchHomePageSlides();

	List<ProjectCategory> listProjectCategories();
	List<ProjectCategory> listProjectCategoriesHavingProjects();
	
	
	
	
	
	
	
	
	
	
	
	

	List<ProjectCategory> listProjectCategoriesByProjectId(int projectId);

	List<HomePageSlide> listHomepageSlides();

	Excite fetchHomepageExcite();

}