package uk.mafu.loon.maverick.front;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.util.Assert;

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
import uk.mafu.domain.maverick.Website;
import uk.mafu.loon.common.DownloadsConfig;

public class MaverickServiceImpl extends JpaDaoSupport implements
		MaverickService {
	private static final long serialVersionUID = 1L;
	private DownloadsConfig config;

	@SuppressWarnings("unused")
	private MaverickServiceImpl() {
		// Spoilsport has done this to prevent initialization except with args
	}

	public MaverickServiceImpl(DownloadsConfig config) {
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
				Website website = getWebsite();
				List<Press> pressItems = website.getPressItems();
				pressItems.size();
				em.clear();
				return pressItems;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectCategory> listProjectCategories() {
		return (List<ProjectCategory>) getJpaTemplate().execute(
				new JpaCallback() {
					public Object doInJpa(EntityManager em)
							throws PersistenceException {
						List<Website> resultList = em.createQuery(
								"from Website").getResultList();
						Assert.isTrue(resultList.size() == 1,
								"only one website should exist in CMS ");
						Website website = resultList.get(0);
						List<ProjectCategory> projectCategories = website
								.getProjectCategories();
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
				List<Project> projects = em.createQuery("from Project")
						.getResultList();
				projects.size();
				em.clear();
				return projects;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public Website getWebsite() {
		return (Website) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Website> resultList = em.createQuery("from Website")
						.getResultList();
				Assert.isTrue(resultList.size() == 1,
						"only one website should exist in CMS ");
				return resultList.get(0);
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> listProjectsByProjectCategory(final int categoryId) {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public List<Project> doInJpa(EntityManager em)
					throws PersistenceException {
				ProjectCategory category = em.find(ProjectCategory.class,
						categoryId);
				List<Project> projects = category.getProjects();
				projects.size();
				em.clear();
				return projects;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectCategory> listCategoriesByProjectId(final int projectId) {
		return (List<ProjectCategory>) getJpaTemplate().execute(
				new JpaCallback() {
					public Object doInJpa(EntityManager em)
							throws PersistenceException {
						List<Website> resultList = em.createQuery(
								"from Website").getResultList();
						List<ProjectCategory> ret = new ArrayList<ProjectCategory>();
						Assert.isTrue(resultList.size() == 1,
								"only one website should exist in CMS ");
						Website website = resultList.get(0);
						Project find = em.find(Project.class, projectId);
						List<ProjectCategory> projectCategories = website
								.getProjectCategories();
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

	public ContactDTO getContactDTO() {
		return (ContactDTO) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite();
				ContactDTO contactDTO = new ContactDTO();
				contactDTO.setContactText1(website.getContactText1());
				contactDTO.setContactText2(website.getContactText2());
				contactDTO.setContactImage(website.getContactImage());
				contactDTO.setContactGoogleMapUrl(website
						.getContactGoogleMapUrl());
				em.clear();
				return contactDTO;
			}
		});
	}

	public EthosDTO getEthosDTO() {
		return (EthosDTO) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite();
				EthosDTO ethosDTO = new EthosDTO();
				ethosDTO.setEthosText1(website.getEthosText1());
				ethosDTO.setEthosText2(website.getEthosText2());
				ethosDTO.setEthosImage(website.getEthosImage());
				em.clear();
				return ethosDTO;
			}
		});
	}

	public String getVideoUrl() {
		return config.getVideoUrl();
	}

	@SuppressWarnings("unchecked")
	public List<ArchiveItem> listArchiveItems() {
		return (List<ArchiveItem>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<ArchiveItem> ret = em.createQuery(
						"from ArchiveItem a order by a.date desc ")
						.getResultList();
				ret.size();
				em.clear();
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Award> listAwards() {
		return (List<Award>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite();
				List<Award> awards = website.getAwards();
				awards.size();
				em.clear();
				return awards;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<News> listNews() {
		return (List<News>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite();
				List<News> newsItems = website.getNewsItems();
				newsItems.size();
				em.clear();
				return newsItems;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> listProjectByProjectCategory(final int categoryId) {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				ProjectCategory cat = em
						.find(ProjectCategory.class, categoryId);
				List<Project> ret = cat.getProjects();
				ret.size();
				em.clear();
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectCategory> listProjectCategoriesByProject(
			final int projectId) {
		return (List<ProjectCategory>) getJpaTemplate().execute(
				new JpaCallback() {
					public Object doInJpa(EntityManager em)
							throws PersistenceException {
						List<ProjectCategory> ret = new ArrayList<ProjectCategory>();
						Website website = getWebsite();
						Project find = em.find(Project.class, projectId);
						List<ProjectCategory> projectCategories = website
								.getProjectCategories();
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

	public Project getProjectById(final int projectId) {
		return (Project) getJpaTemplate().execute(new JpaCallback() {
			public Project doInJpa(EntityManager em)
					throws PersistenceException {
				Project project = em.find(Project.class, projectId);
				em.clear();
				return project;
			}
		});
	}

	public ProjectCategory getProjectCategoryById(final int categoryId) {
		return (ProjectCategory) getJpaTemplate().execute(new JpaCallback() {
			public ProjectCategory doInJpa(EntityManager em)
					throws PersistenceException {
				ProjectCategory cat = em
						.find(ProjectCategory.class, categoryId);
				em.clear();
				return cat;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Campaign> listCampaigns() {
		return (List<Campaign>) getJpaTemplate().execute(new JpaCallback() {
			public List<Campaign> doInJpa(EntityManager em)
					throws PersistenceException {
				List<Campaign> campaigns = em.createQuery("from Campaign")
						.getResultList();
				campaigns.size();
				em.clear();
				return campaigns;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> listProjectByCampaign(final int campaignId) {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public List<Project> doInJpa(EntityManager em)
					throws PersistenceException {
				Campaign campaign = em.find(Campaign.class, campaignId);
				List<Project> ret = campaign.getProjects();
				ret.size();
				em.clear();
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> listProjectsByCampaignId(final int campaignId) {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public List<Project> doInJpa(EntityManager em)
					throws PersistenceException {
				Campaign find = em.find(Campaign.class, campaignId);
				List<Project> people = find.getProjects();
				em.clear();
				return people;
			}
		});
	}

	public CampaignDTO getCampaignDTO() {
		return (CampaignDTO) getJpaTemplate().execute(new JpaCallback() {
			public CampaignDTO doInJpa(EntityManager em)
					throws PersistenceException {
				Website website = getWebsite();
				CampaignDTO dto = new CampaignDTO();
				dto.text1 = website.getCampaignText1();
				dto.text2 = website.getCampaignText2();
				em.clear();
				return dto;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Person> listPeopleByRole(final int roleId) {
		return (List<Person>) getJpaTemplate().execute(new JpaCallback() {
			public List<Person> doInJpa(EntityManager em)
					throws PersistenceException {
				Role find = em.find(Role.class, roleId);
				List<Person> people = find.getPeople();
				people.size();
				em.clear();
				return people;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Role> listRoles() {
		return (List<Role>) getJpaTemplate().execute(new JpaCallback() {
			public List<Role> doInJpa(EntityManager em)
					throws PersistenceException {
				Website website = getWebsite();
				List<Role> roles = website.getRoles();
				roles.size();
				em.clear();
				return roles;
			}
		});
	}

	public Showreel getCurrentShowreel() {
		return (Showreel) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite();
				Showreel showreel = website.getShowreel();
				em.clear();
				return showreel;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Campaign> listCampaignsByProjectId(final int projectId) {
		return (List<Campaign>) getJpaTemplate().execute(new JpaCallback() {
			public List<Campaign> doInJpa(EntityManager em)
					throws PersistenceException {
				if (em.find(Project.class, projectId) == null) {
					throw new UnsupportedOperationException();
				}
				List<Campaign> campaigns = em.createQuery("from Campaign")
						.getResultList();
				List<Campaign> ret = new ArrayList<Campaign>();
				outer: for (Campaign campaign : campaigns) {
					for (Project p : campaign.getProjects()) {
						if (p.getPk() == projectId) {
							ret.add(campaign);
							continue outer;
						}
					}
				}
				em.clear();
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Role> listRolesByPerson(final int personId) {
		return (List<Role>) getJpaTemplate().execute(new JpaCallback() {
			public List<Role> doInJpa(EntityManager em)
					throws PersistenceException {
				List<Role> ret = new ArrayList<Role>();
				Website website = getWebsite();
				Person person = em.find(Person.class, personId);
				List<Role> roles = website.getRoles();
				for (Role role : roles) {
					for (Person tmp : role.getPeople()) {
						if (tmp.getPk() == person.getPk()) {
							ret.add(role);
							break;
						}
					}
				}
				roles.size();
				for (Role tmprole : ret) {
					tmprole.getPeople().size();
				}
				em.clear();
				return ret;
			}
		});
	}

	public Campaign getCampaignById(final int campaignId) {
		return (Campaign) getJpaTemplate().execute(new JpaCallback() {
			public Campaign doInJpa(EntityManager em)
					throws PersistenceException {
				Campaign find = em.find(Campaign.class, campaignId);
				em.clear();
				return find;
			}
		});
	}

	public Person getPersonById(final int personId) {
		return (Person) getJpaTemplate().execute(new JpaCallback() {
			public Person doInJpa(EntityManager em) throws PersistenceException {
				Person find = em.find(Person.class, personId);
				em.clear();
				return find;
			}
		});
	}

	public Role getRoleById(final int roleId) {
		return (Role) getJpaTemplate().execute(new JpaCallback() {
			public Role doInJpa(EntityManager em) throws PersistenceException {
				Role find = em.find(Role.class, roleId);
				em.clear();
				return find;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Person> listPeople() {
		return (List<Person>) getJpaTemplate().execute(new JpaCallback() {
			public List<Person> doInJpa(EntityManager em)
					throws PersistenceException {
				List<Person> ret = new ArrayList<Person>();
				Website website = getWebsite();
				List<Role> roles = website.getRoles();
				for (Role role : roles) {
					List<Person> people = role.getPeople();
					people.size();
					ret.addAll(people);
				}
				em.clear();
				return ret;
			}
		});
	}

	public ArchiveItem getArchiveItemById(final int id) {
		return (ArchiveItem) getJpaTemplate().execute(new JpaCallback() {
			public ArchiveItem doInJpa(EntityManager em)
					throws PersistenceException {
				ArchiveItem item = em.find(ArchiveItem.class, id);
				em.clear();
				return item;
			}
		});
	}
}