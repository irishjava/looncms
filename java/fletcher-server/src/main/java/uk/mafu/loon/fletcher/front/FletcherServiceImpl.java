package uk.mafu.loon.fletcher.front;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.util.Assert;
import uk.mafu.domain.fletcher.Press;
import uk.mafu.domain.fletcher.Project;
import uk.mafu.domain.fletcher.ProjectCategory;
import uk.mafu.domain.fletcher.Slide;
import uk.mafu.domain.fletcher.Website;
import uk.mafu.loon.common.DownloadsConfig;

public class FletcherServiceImpl extends JpaDaoSupport implements FletcherService {
	private static final long serialVersionUID = 1L;
	private DownloadsConfig config;

	@SuppressWarnings("unused")
	private FletcherServiceImpl() {
	// Spoilsport has done this to prevent initialization except with args
	}

	public FletcherServiceImpl(DownloadsConfig config) {
		this.config = config;
	}
	
	public String getImageUrl() {
		return config.getImageUrl();
	}

	public String getPdfUrl() {
		return config.getPdfUrl();
	}

	@SuppressWarnings("unchecked")
	public List<Press> listPress() {
		return (List<Press>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Website> resultList = em.createQuery("from Website").getResultList();
				Assert.isTrue(resultList.size() == 1, "only one website should exist in CMS ");
				Website website = resultList.get(0);
				List<Press> pressItems = website.getPressItems();
				pressItems.size();
				em.clear();
				return pressItems;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectCategory> listProjectCategories() {
		return (List<ProjectCategory>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Website> resultList = em.createQuery("from Website").getResultList();
				Assert.isTrue(resultList.size() == 1, "only one website should exist in CMS ");
				Website website = resultList.get(0);
				List<ProjectCategory> projectCategories = website.getProjectCategories();
				projectCategories.size();
				em.clear();
				return projectCategories;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> listProjects() {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Website> resultList = em.createQuery("from Website").getResultList();
				Assert.isTrue(resultList.size() == 1, "only one website should exist in CMS ");
				Website website = resultList.get(0);
				List<Project> projects = website.getProjects();
				for (Project project : projects) {
					project.getImages().size();
				}
				projects.size();
				em.clear();
				return projects;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Slide> listSlides() {
		return (List<Slide>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Website> resultList = em.createQuery("from Website").getResultList();
				Assert.isTrue(resultList.size() == 1, "only one website should exist in CMS ");
				Website website = resultList.get(0);
				List<Slide> slides = website.getSlides();
				slides.size();
				em.clear();
				return slides;
			}
		});
	}

	public Website getWebsite() {
		return (Website) getJpaTemplate().execute(new JpaCallback() {
			@SuppressWarnings("unchecked")
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Website> resultList = em.createQuery("from Website").getResultList();
				Assert.isTrue(resultList.size() == 1, "only one website should exist in CMS ");
				em.clear();
				return resultList.get(0);
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> listProjectsByProjectCategory(final int categoryId) {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public List<Project> doInJpa(EntityManager em) throws PersistenceException {
				ProjectCategory category = em.find(ProjectCategory.class, categoryId);
				List<Project> projects = category.getProjects();
				for (Project project : projects) {
					project.getImages().size();
				}
				projects.size();
				em.clear();
				return projects;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectCategory> listCategoriesByProjectId(final int projectId) {
		return (List<ProjectCategory>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Website> resultList = em.createQuery("from Website").getResultList();
				List<ProjectCategory> ret = new ArrayList<ProjectCategory>();
				Assert.isTrue(resultList.size() == 1, "only one website should exist in CMS ");
				Website website = resultList.get(0);
				Project find = em.find(Project.class, projectId);
				List<ProjectCategory> projectCategories = website.getProjectCategories();
				for (ProjectCategory projectCategory : projectCategories) {
					for (Project tmp : projectCategory.getProjects()) {
						if (tmp.getPk() == find.getPk()) {
							ret.add(projectCategory);
							break;
						}
					}
				}
				projectCategories.size();
				for (ProjectCategory cat : ret) {
					cat.getProjects().size();
				}
				em.clear();
				return ret;
			}
		});
	}
}
