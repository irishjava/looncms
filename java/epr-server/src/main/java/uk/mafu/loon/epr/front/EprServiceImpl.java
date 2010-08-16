package uk.mafu.loon.epr.front;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.util.Assert;
import uk.mafu.domain.epr.Award;
import uk.mafu.domain.epr.News;
import uk.mafu.domain.epr.Person;
import uk.mafu.domain.epr.Project;
import uk.mafu.domain.epr.ProjectCategory;
import uk.mafu.domain.epr.ProjectLink;
import uk.mafu.domain.epr.Website;
import uk.mafu.domain.epr.dto.ContactDTO;
import uk.mafu.domain.epr.dto.SectionDTO;
import uk.mafu.loon.common.DownloadsConfig;
import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.util.DateUtil;

public class EprServiceImpl extends JpaDaoSupport implements EprService {
	private static final long serialVersionUID = 1L;
	private DownloadsConfig config;

	@SuppressWarnings("unused")
	private EprServiceImpl() {
	// A spoilsport(Bryan) has done this to prevent initialization except with
	// arguments ..
	}

	public EprServiceImpl(DownloadsConfig config) {
		this.config = config;
	}

	// public Award getAwardWithProject(final int awardId) {
	// return (Award) getJpaTemplate().execute(new JpaCallback() {
	// public Object doInJpa(EntityManager em) throws PersistenceException {
	// Award award = em.find(Award.class, awardId);
	// em.clear();
	// return award;
	// }
	// });
	// }
	public Project getProject(final int id) {
		return (Project) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Project find = em.find(Project.class, id);
				find.getImages().size();
				em.clear();
				return find;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<News> listAllNewsDateOrdered() {
		return (List<News>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery("from News as news order by news.date desc ");
				List results = query.getResultList();
				em.clear();
				return results;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Award> listAllAwards() {
		return (List<Award>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery("from Award as award order by award.date desc ");
				List<Award> resultList = query.getResultList();
				for (Award award : resultList) {
					award.getImages().size();
				}
				em.clear();
				return resultList;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Award> listAwardsByYear(final int year) {
		return (List<Award>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Date[] dates = DateUtil.getStartAndEndOfYear(year);
				List<Award> ret = (List<Award>) em.createQuery(
						"from Award as award where award.date > ?1 and award.date < ?2 ").setParameter(1, dates[0],
						TemporalType.DATE).setParameter(2, dates[1], TemporalType.DATE).getResultList();
				for (Award award : ret) {
					award.getImages().size();
				}
				em.clear();
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<News> listNewsByYear(final int year) {
		return (List<News>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Date[] dates = DateUtil.getStartAndEndOfYear(year);
				List<News> resultList = (List<News>) em.createQuery(
						"from News as news where news.date > ?1 and news.date < ?2 ").setParameter(1, dates[0],
						TemporalType.DATE).setParameter(2, dates[1], TemporalType.DATE).getResultList();
				for (News news : resultList) {
					news.getImages().size();
				}
				em.clear();
				return resultList;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Person> listPeople() {
		return (List<Person>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Website> websites = (List<Website>) em.createQuery("from " + Website.class.getName())
						.getResultList();
				Assert.isTrue(websites.size() > 0, "A website must exist in system, we allways use the first one.");
				Website website = websites.get(0);
				List<Person> people = website.getPeople();
				people.size();
				em.clear();
				return people;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectLink> listProjectLinks() {
		return (List<ProjectLink>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Website> websites = (List<Website>) em.createQuery("from " + Website.class.getName())
						.getResultList();
				Assert.isTrue(websites.size() > 0, "A website must exist in system, we allways use the first one.");
				Website website = websites.get(0);
				List<ProjectLink> projectLinks = website.getProjectLinks();
				projectLinks.size();
				em.clear();
				return projectLinks;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectCategory> listNonEmptyProjectCategories() {
		return (List<ProjectCategory>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<ProjectCategory> ret = new ArrayList<ProjectCategory>();
				List<ProjectCategory> projectCategories = getWebsite(em).getProjectCategories();
				for (ProjectCategory projectCategory : projectCategories) {
					if (!projectCategory.getProjects().isEmpty()) {
						ret.add(projectCategory);
					}
				}
				// Query createQuery = em.createQuery("SELECT p FROM
				// ProjectCategory p WHERE p.projects IS NOT EMPTY ");
				// List<ProjectCategory> resultList = (List<ProjectCategory>)
				// createQuery.getResultList();
				em.clear();
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> listProjectsByCategory(final int projectCategoryId) {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				ProjectCategory projectCategory = em.find(ProjectCategory.class, projectCategoryId);
				List<Project> projects = projectCategory.getProjects();
				// init...
				projects.size();
				em.clear();
				return projects;
			}
		});
	}

	public String getImageUrl() {
		return config.getImageUrl();
	}

	public String getPdfUrl() {
		return config.getPdfUrl();
	}

	public String getVideoUrl() {
		return config.getVideoUrl();
	}

	@SuppressWarnings("unchecked")
	public List<Project> listAllProjects() {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Project> projects = em.createQuery("from Project as project left join fetch project.images ")
						.getResultList();
				projects.size();
				List<Project> ret = new ArrayList<Project>();
				for (Project project : projects) {
					if (!ret.contains(project)) {
						ret.add(project);
					}
				}
				em.clear();
				return ret;
			}
		});
	}

	public SectionDTO getApproachSection() {
		return (SectionDTO) getJpaTemplate().execute(new JpaCallback() {
			public SectionDTO doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite(em);
				SectionDTO dto = new SectionDTO();
				dto.setIdentifier("approach");
				dto.setText(website.getApproachText());
				List<ImageLink> approachImages = website.getApproachImages();
				approachImages.size();
				dto.setImages(approachImages);
				em.clear();
				return dto;
			}
		});
	}

	@SuppressWarnings("unchecked")
	private Website getWebsite(EntityManager em) {
		List<Website> websites = (List<Website>) em.createQuery("from " + Website.class.getName()).getResultList();
		Assert.isTrue(websites.size() > 0, "A website must exist in system, we allways use the first one.");
		Website website = websites.get(0);
		return website;
	}

	@SuppressWarnings("unchecked")
	public SectionDTO getModelMakingSection() {
		return (SectionDTO) getJpaTemplate().execute(new JpaCallback() {
			public SectionDTO doInJpa(EntityManager em) throws PersistenceException {
				List<Website> websites = (List<Website>) em.createQuery("from " + Website.class.getName())
						.getResultList();
				Assert.isTrue(websites.size() > 0, "A website must exist in system, we allways use the first one.");
				Website website = websites.get(0);
				SectionDTO dto = new SectionDTO();
				dto.setIdentifier("approach");
				dto.setText(website.getModelMakingText());
				List<ImageLink> modelMakingImages = website.getModelMakingImages();
				modelMakingImages.size();
				dto.setImages(modelMakingImages);
				em.clear();
				return dto;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public SectionDTO getOfficeSection() {
		return (SectionDTO) getJpaTemplate().execute(new JpaCallback() {
			public SectionDTO doInJpa(EntityManager em) throws PersistenceException {
				List<Website> websites = (List<Website>) em.createQuery("from " + Website.class.getName())
						.getResultList();
				Assert.isTrue(websites.size() > 0, "A website must exist in system, we allways use the first one.");
				Website website = websites.get(0);
				SectionDTO dto = new SectionDTO();
				dto.setIdentifier("approach");
				dto.setText(website.getOfficeText());
				List<ImageLink> officeImages = website.getOfficeImages();
				officeImages.size();
				dto.setImages(officeImages);
				em.clear();
				return dto;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public SectionDTO getSustainabilitySection() {
		return (SectionDTO) getJpaTemplate().execute(new JpaCallback() {
			public SectionDTO doInJpa(EntityManager em) throws PersistenceException {
				List<Website> websites = (List<Website>) em.createQuery("from " + Website.class.getName())
						.getResultList();
				Assert.isTrue(websites.size() > 0, "A website must exist in system, we allways use the first one.");
				Website website = websites.get(0);
				SectionDTO dto = new SectionDTO();
				dto.setIdentifier("approach");
				dto.setText(website.getSustainabilityText());
				List<ImageLink> sustainabilityImages = website.getSustainabilityImages();
				sustainabilityImages.size();
				dto.setImages(sustainabilityImages);
				em.clear();
				return dto;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public ContactDTO getContactInformation() {
		return (ContactDTO) getJpaTemplate().execute(new JpaCallback() {
			public ContactDTO doInJpa(EntityManager em) throws PersistenceException {
				List<Website> websites = (List<Website>) em.createQuery("from " + Website.class.getName())
						.getResultList();
				Assert.isTrue(websites.size() > 0, "A website must exist in system, we allways use the first one.");
				Website website = websites.get(0);
				ContactDTO contactDTO = new ContactDTO();
				contactDTO.setText(website.getContactText());
				contactDTO.setPdf(website.getContactPdf());
				contactDTO.setUrl(website.getContactUrl());
				contactDTO.setImage(website.getContactMap());
				em.clear();
				return contactDTO;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectCategory> listProjectCategoriesByProject(final int projectId) {
		return (List<ProjectCategory>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<ProjectCategory> ret = new ArrayList<ProjectCategory>();
				Query createQuery = em.createQuery("SELECT p FROM ProjectCategory p WHERE p.projects IS NOT EMPTY ");
				List<ProjectCategory> resultList = (List<ProjectCategory>) createQuery.getResultList();
				for (ProjectCategory projectCategory : resultList) {
					top: for (Project project : projectCategory.getProjects()) {
						if (project.getPk() == projectId) {
							ret.add(projectCategory);
							break top;
						}
					}
				}
				em.clear();
				for (ProjectCategory projectCategory2 : ret) {
					projectCategory2.setProjects(new ArrayList<Project>());
				}
				return ret;
			}
		});
	}
}