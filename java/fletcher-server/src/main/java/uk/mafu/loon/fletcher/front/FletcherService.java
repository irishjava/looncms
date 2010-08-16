package uk.mafu.loon.fletcher.front;

import java.util.List;
import uk.mafu.domain.fletcher.Press;
import uk.mafu.domain.fletcher.Project;
import uk.mafu.domain.fletcher.ProjectCategory;
import uk.mafu.domain.fletcher.Slide;
import uk.mafu.domain.fletcher.Website;

public interface FletcherService {
	Website getWebsite();

	List<ProjectCategory> listProjectCategories();

	List<Project> listProjectsByProjectCategory(int categoryId);

	List<ProjectCategory> listCategoriesByProjectId(int projectId);

	List<Project> listProjects();

	List<Slide> listSlides();

	List<Press> listPress();

	String getImageUrl();

	String getPdfUrl();
}
