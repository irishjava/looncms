package uk.mafu.loon.epr.front;

import java.util.List;
import uk.mafu.domain.epr.Award;
import uk.mafu.domain.epr.News;
import uk.mafu.domain.epr.Person;
import uk.mafu.domain.epr.Project;
import uk.mafu.domain.epr.ProjectCategory;
import uk.mafu.domain.epr.ProjectLink;
import uk.mafu.domain.epr.dto.ContactDTO;
import uk.mafu.domain.epr.dto.SectionDTO;

public interface EprService {
	Project getProject(int id);

	List<News> listAllNewsDateOrdered();

	List<Project> listAllProjects();

	List<Award> listAllAwards();

	List<Award> listAwardsByYear( int year); 
	
	List<News> listNewsByYear(int year);

	List<Person> listPeople();

	List<ProjectLink> listProjectLinks();

	List<ProjectCategory> listNonEmptyProjectCategories();

	List<ProjectCategory> listProjectCategoriesByProject(int projectId);

	List<Project> listProjectsByCategory(int projectCategoryId);

	SectionDTO getApproachSection();

	SectionDTO getSustainabilitySection();

	SectionDTO getOfficeSection();

	SectionDTO getModelMakingSection();

	ContactDTO getContactInformation();

	String getImageUrl();

	String getVideoUrl();

	String getPdfUrl();
}
