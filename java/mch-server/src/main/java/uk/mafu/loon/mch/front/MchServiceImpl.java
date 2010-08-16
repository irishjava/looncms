package uk.mafu.loon.mch.front;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.util.Assert;
import uk.mafu.domain.mch.Project;
import uk.mafu.domain.mch.ProjectCategory;
import uk.mafu.domain.mch.ProjectLink;
import uk.mafu.domain.mch.Website;
import uk.mafu.loon.common.DownloadsConfig;
import uk.mafu.loon.domain.data.ImageLink;

public class MchServiceImpl extends JpaDaoSupport implements MchService {
	private static final long serialVersionUID = 1L;
	private DownloadsConfig config;

	@SuppressWarnings("unused")
	private MchServiceImpl() {
	// Spoilsport has done this to prevent initialization except with args
	}

	public MchServiceImpl(DownloadsConfig config) {
		this.config = config;
	}

	public String getImageUrl() {
		return config.getImageUrl();
	}

	public String getPdfUrl() {
		return config.getPdfUrl();
	}

	/**
	 * Only call this method from within a doInJpa block...
	 * @param em
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Website getWebsite(EntityManager em) {
		List<Website> resultList = em.createQuery("from Website").getResultList();
		Assert.isTrue(resultList.size() == 1, "only one website should exist in CMS ");
		return resultList.get(0);
	}

	public String getBannerText() {
		return (String) getJpaTemplate().execute(new JpaCallback() {
			public String doInJpa(EntityManager em) throws PersistenceException {
				return getWebsite(em).getBannerText();
			}
		});
	}

	public String getContactText() {
		return (String) getJpaTemplate().execute(new JpaCallback() {
			public String doInJpa(EntityManager em) throws PersistenceException {
				return getWebsite(em).getContactText();
			}
		});
	}

	public String getCreditText() {
		return (String) getJpaTemplate().execute(new JpaCallback() {
			public String doInJpa(EntityManager em) throws PersistenceException {
				return getWebsite(em).getCreditText();
			}
		});
	}

	public ImageLink getMap() {
		return (ImageLink) getJpaTemplate().execute(new JpaCallback() {
			public ImageLink doInJpa(EntityManager em) throws PersistenceException {
				return getWebsite(em).getMap();
			}
		});
	}

	public Project getProject(final int pk) {
		return (Project) getJpaTemplate().execute(new JpaCallback() {
			public Project doInJpa(EntityManager em) throws PersistenceException {
				Project project = em.find(Project.class, pk);
				if (project == null) {
					return null;
				}
				project.getImages().size();
				em.clear();
				return project;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectCategory> getProjectCategoriesByProject(final int pk) {
		return (List<ProjectCategory>) getJpaTemplate().execute(new JpaCallback() {
			public List<ProjectCategory> doInJpa(EntityManager em) throws PersistenceException {
				List<ProjectCategory> ret = new ArrayList<ProjectCategory>();
				List<ProjectCategory> projectCategories = (List<ProjectCategory>) em
						.createQuery("from ProjectCategory").getResultList();
				Project project = em.find(Project.class, pk);
				if (project == null) {
					throw new UnsupportedOperationException("invalid project pk:" + pk + "");
				}
				for (ProjectCategory projectCategory : projectCategories) {
					List<Project> projects = projectCategory.getProjects();
					for (Project project2 : projects) {
						if (project.getPk() == project2.getPk()) {
							ret.add(projectCategory);
							break;
						}
					}
				}
				em.clear();
				for (ProjectCategory projectCategory : ret) {
					projectCategory.getProjects().clear();
				}
				return ret;
			}
		});
	}

	public String getVideoUrl() {
		return config.getVideoUrl();
	}

	@SuppressWarnings("unchecked")
	public List<ProjectLink> listHomePageImages() {
		return (List<ProjectLink>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite(em);
				em.merge(website);
				Assert.notNull(website, "no configured website found in system");
				List<ProjectLink> slides = website.getProjectLinks();
				for (ProjectLink projectLink : slides) {
					if (projectLink.getProject() != null) {
						projectLink.getProject().getImages().size();
					}
				}
				slides.size();
				em.clear();
				return slides;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> listProjectsByCategory(final int pk) {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public List<Project> doInJpa(EntityManager em) throws PersistenceException {
				ProjectCategory category = em.find(ProjectCategory.class, pk);
				if (category == null) {
					throw new UnsupportedOperationException("invalid ProjectCategory pk:" + pk + "");
				}
				List<Project> projects = category.getProjects();
				projects.size();
				em.clear();
				return projects;
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
}
