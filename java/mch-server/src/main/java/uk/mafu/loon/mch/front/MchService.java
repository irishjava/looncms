package uk.mafu.loon.mch.front;

import java.util.List;
import uk.mafu.domain.mch.Project;
import uk.mafu.domain.mch.ProjectCategory;
import uk.mafu.domain.mch.ProjectLink;
import uk.mafu.loon.domain.data.ImageLink;

public interface MchService {
 

	List<ProjectCategory> listProjectCategories();

	Project getProject(int pk);

	List<ProjectLink> listHomePageImages();

	String getBannerText();

	String getContactText();

	String getCreditText();

	ImageLink getMap();

	List<ProjectCategory> getProjectCategoriesByProject(int projectPk);

	List<Project> listProjectsByCategory(int categoryPk);

	String getImageUrl();

	String getVideoUrl();
}
