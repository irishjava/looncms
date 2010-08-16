package uk.mafu.kwa.front;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.util.Assert;

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
import uk.mafu.kwa.domain.Website;
import uk.mafu.loon.common.DownloadsConfig;

public class KwaServiceImpl extends JpaDaoSupport implements KwaService {
	private static final long serialVersionUID = 1L;
	private DownloadsConfig config;

	@SuppressWarnings("unused")
	private KwaServiceImpl() {
		// Spoilsport has done this to prevent initialization except with args
	}

	public KwaServiceImpl(DownloadsConfig config) {
		this.config = config;
	}

	public ContactDTO fetchContactDTO() {
		return (ContactDTO) getJpaTemplate().execute(new JpaCallback() {
			public ContactDTO doInJpa(EntityManager em)
					throws PersistenceException {
				ContactDTO dto = new ContactDTO();
				Website website = getWebsite(em);
				dto.contactGoogleMapsLink = website.getContactGoogleMapsLink();
				dto.contactImage = website.getContactImage();
				dto.contactPdf = website.getContactPdf();
				dto.contactText = website.getContactText();
				em.clear();
				return dto;
			}
		});
	}

	public Excite fetchDirectorsExcite() {
		return (Excite) getJpaTemplate().execute(new JpaCallback() {
			public Excite doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite(em);
				Excite excite = website.getDirectorsExcite();
				em.clear();
				return excite;
			}
		});
	}

	public EthosDTO fetchEthosDTO() {
		return (EthosDTO) getJpaTemplate().execute(new JpaCallback() {
			public EthosDTO doInJpa(EntityManager em)
					throws PersistenceException {
				Website website = getWebsite(em);
				EthosDTO dto = new EthosDTO();
				dto.ethosExcite = website.getEthosExcite();
				dto.ethosImage = website.getEthosImage();
				dto.ethosText = website.getEthosText();
				em.clear();
				return dto;
			}
		});
	}

	public OverviewDTO fetchOverviewDTO() {
		return (OverviewDTO) getJpaTemplate().execute(new JpaCallback() {
			public OverviewDTO doInJpa(EntityManager em)
					throws PersistenceException {
				OverviewDTO dto = new OverviewDTO();
				Website website = getWebsite(em);
				dto.overviewImage = website.getOverviewImage();
				dto.overviewText = website.getOverviewText();
				dto.overviewVideo = website.getOverviewVideo();
				em.clear();
				return dto;
			}
		});
	}

	public Project fetchProject(final int pk) {
		return (Project) getJpaTemplate().execute(new JpaCallback() {
			public Project doInJpa(EntityManager em)
					throws PersistenceException {
				Project p = em.find(Project.class, pk);
				p.getSlides().size();
				em.clear();
				return p;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectSlide> fetchProjectSlideByProject(final int projectId) {
		return (List<ProjectSlide>) getJpaTemplate().execute(new JpaCallback() {
			public List<ProjectSlide> doInJpa(EntityManager em)
					throws PersistenceException {
				Project p = em.find(Project.class, projectId);
				List<ProjectSlide> slides = p.getSlides();
				slides.size();
				em.clear();
				return slides;
			}
		});
	}

	public SustainabilityDTO fetchSustainabilityDTO() {
		return (SustainabilityDTO) getJpaTemplate().execute(new JpaCallback() {
			public SustainabilityDTO doInJpa(EntityManager em)
					throws PersistenceException {
				SustainabilityDTO dto = new SustainabilityDTO();
				Website website = getWebsite(em);
				dto.sustainabilityExcite = website.getSustainabilityExcite();
				dto.sustainabilityImage = website.getSustainabilityImage();
				dto.sustainabilityText = website.getSustainabilityText();
				em.clear();
				return dto;
			}
		});
	}

	public Excite fetchTeamExcite() {
		return (Excite) getJpaTemplate().execute(new JpaCallback() {
			public Excite doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite(em);
				Excite teamExcite = website.getTeamExcite();
				em.clear();
				return teamExcite;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Award> listAwards() {
		return (List<Award>) getJpaTemplate().execute(new JpaCallback() {
			public List<Award> doInJpa(EntityManager em)
					throws PersistenceException {

				List<Award> awards = em.createQuery(
						"from Award a order by a.date desc").getResultList();
				awards.size();
				em.clear();
				return awards;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Client> listClients() {
		return (List<Client>) getJpaTemplate().execute(new JpaCallback() {
			public List<Client> doInJpa(EntityManager em)
					throws PersistenceException {
				List<Client> clients = getWebsite(em).getClients();
				clients.size();
				em.clear();
				return clients;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Person> listDirectors() {
		return (List<Person>) getJpaTemplate().execute(new JpaCallback() {
			public List<Person> doInJpa(EntityManager em)
					throws PersistenceException {
				List<Person> directors = getWebsite(em).getDirectors();
				directors.size();
				em.clear();
				return directors;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Person> listEmployees() {
		return (List<Person>) getJpaTemplate().execute(new JpaCallback() {
			public List<Person> doInJpa(EntityManager em)
					throws PersistenceException {
				List<Person> employees = getWebsite(em).getEmployees();
				employees.size();
				em.clear();
				return employees;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<JobPosting> listJobPostings() {
		return (List<JobPosting>) getJpaTemplate().execute(new JpaCallback() {
			public List<JobPosting> doInJpa(EntityManager em)
					throws PersistenceException {
				List<JobPosting> jobPostings = getWebsite(em).getJobPostings();
				jobPostings.size();
				em.clear();
				return jobPostings;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<News> listNewsItems() {
		return (List<News>) getJpaTemplate().execute(new JpaCallback() {
			public List<News> doInJpa(EntityManager em)
					throws PersistenceException {
				List<News> newsItems = em.createQuery(
						"from News n order by n.date desc").getResultList();
				newsItems.size();
				em.clear();
				return newsItems;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Press> listPressItems() {
		return (List<Press>) getJpaTemplate().execute(new JpaCallback() {
			public List<Press> doInJpa(EntityManager em)
					throws PersistenceException {

				List<Press> pressItems = em.createQuery(
						"from Press p order by p.date desc").getResultList();
				pressItems.size();
				em.clear();
				return pressItems;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> listProjectsByProjectCategory(
			final int projectCategoryId) {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public List<Project> doInJpa(EntityManager em)
					throws PersistenceException {
				ProjectCategory find = em.find(ProjectCategory.class,
						projectCategoryId);
				List<Project> list = find.projects;
				list.size();
				em.clear();
				return list;
			}
		});
	}

	public DownloadsConfig fetchDownloadsConfig() {
		return this.config;
	}

	/**
	 * Only call this method from within a doInJpa block...
	 * 
	 * @param em
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Website getWebsite(EntityManager em) {
		List<Website> resultList = em.createQuery("from Website")
				.getResultList();
		Assert.isTrue(resultList.size() == 1,
				"only one website should exist in CMS ");
		return resultList.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<HomePageSlide> fetchHomePageSlides() {
		return (List<HomePageSlide>) getJpaTemplate().execute(
				new JpaCallback() {
					public List<HomePageSlide> doInJpa(EntityManager em)
							throws PersistenceException {
						List<HomePageSlide> slides = getWebsite(em).getHomePageSlides();
						slides.size();
						em.clear();
						return slides;
					}
				});
	}

	public Excite fetchHomepageExcite() {
		return (Excite) getJpaTemplate().execute(new JpaCallback() {
			public Excite doInJpa(EntityManager em) throws PersistenceException {
				Excite homepageExcite = getWebsite(em).getHomepageExcite();
				em.clear();
				return homepageExcite;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<HomePageSlide> listHomepageSlides() {
		return (List<HomePageSlide>) getJpaTemplate().execute(
				new JpaCallback() {
					public List<HomePageSlide> doInJpa(EntityManager em)
							throws PersistenceException {
						List<HomePageSlide> hs = getWebsite(em).getHomePageSlides();
						hs.size();
						em.clear();
						return hs;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectCategory> listProjectCategories() {
		return (List<ProjectCategory>) getJpaTemplate().execute(
				new JpaCallback() {
					public List<ProjectCategory> doInJpa(EntityManager em)
							throws PersistenceException {
						List<ProjectCategory> hs = getWebsite(em).getProjectCategories();
						hs.size();
						for (ProjectCategory projectCategory : hs) {
							List<Project> projects = projectCategory.getProjects();
							projects.size();
							for (Project project : projects) {
								project.getSlides().size();
							}
							
						}
						em.clear();
						return hs;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectCategory> listProjectCategoriesHavingProjects() {
		return (List<ProjectCategory>) getJpaTemplate().execute(
				new JpaCallback() {
					public List<ProjectCategory> doInJpa(EntityManager em)
							throws PersistenceException {
						List<ProjectCategory> hs = getWebsite(em).getProjectCategories();
						hs.size();
						for (ProjectCategory projectCategory : hs) {
							List<Project> projects = projectCategory.getProjects();
							projects.size();
							for (Project project : projects) {
								project.getSlides().size();
							}
						}
						em.clear();
						List <ProjectCategory> ret = new ArrayList<ProjectCategory>();
						for (ProjectCategory projectCategory : hs) {
							if(projectCategory.getProjects().size() > 0){
								ret.add(projectCategory);
							}
						}
						return ret;
					}
				});
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectCategory> listProjectCategoriesByProjectId(
			final int projectId) {
		return (List<ProjectCategory>) getJpaTemplate().execute(
				new JpaCallback() {
					public List<ProjectCategory> doInJpa(EntityManager em)
							throws PersistenceException {
						List<ProjectCategory> hs = getWebsite(em).getProjectCategories();
						List<ProjectCategory> ret = new ArrayList<ProjectCategory>();
						outer: for (ProjectCategory projectCategory : hs) {
							for (Project p : projectCategory.getProjects()) {
								if (p.getPk() == projectId) {
									ret.add(projectCategory);
									continue outer;
								}
							}
						}
						em.clear();
						return ret;
					}
				});
	}

	public Excite fetchAwardsExcite() {
		return (Excite) getJpaTemplate().execute(new JpaCallback() {
			public Excite doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite(em);
				Excite excite = website.getAwardsExcite();
				em.clear();
				return excite;
			}
		});
	}

	public Excite fetchClientsExcite() {
		return (Excite) getJpaTemplate().execute(new JpaCallback() {
			public Excite doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite(em);
				Excite excite = website.getClientExcite();
				em.clear();
				return excite;
			}
		});
	}

	public Excite fetchNewsExcite() {
		return (Excite) getJpaTemplate().execute(new JpaCallback() {
			public Excite doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite(em);
				Excite excite = website.getNewsExcite();
				em.clear();
				return excite;
			}
		});
	}

	public Excite fetchPressExcite() {
		return (Excite) getJpaTemplate().execute(new JpaCallback() {
			public Excite doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite(em);
				Excite excite = website.getPressExcite();
				em.clear();
				return excite;
			}
		});
	}

	public MonographDTO fetchMonographDTO() {
			return (MonographDTO ) getJpaTemplate().execute(new JpaCallback() {
				public MonographDTO doInJpa(EntityManager em)
						throws PersistenceException {
					MonographDTO dto = new MonographDTO ();
					Website website = getWebsite(em);
					dto.excite = website.getMonographExcite();
					dto.image = website.getMonographImage();
					dto.text = website.getMonographText();
					em.clear();
					return dto;
				}
			});
		}
}