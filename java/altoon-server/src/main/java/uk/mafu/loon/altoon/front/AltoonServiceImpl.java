package uk.mafu.loon.altoon.front;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import uk.mafu.domain.altoon.Award;
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
import uk.mafu.domain.altoon.Website;
import uk.mafu.domain.altoon.dto.AwardDTO;
import uk.mafu.domain.altoon.dto.SubSectionDTO;
import uk.mafu.loon.common.DownloadsConfig;
import uk.mafu.loon.domain.data.ImageLink;

public class AltoonServiceImpl extends JpaDaoSupport implements AltoonService {
	private static final long serialVersionUID = 1L;
	private DownloadsConfig config;

	@SuppressWarnings("unused")
	private AltoonServiceImpl() {
	// Spoilsport has done this to prevent initialization except with args
	}

	public AltoonServiceImpl(DownloadsConfig config) {
		this.config = config;
	}

	public String getImageUrl() {
		return config.getImageUrl();
	}

	public Project getFullyInitializedProjectById(final int projectId) {
		return (Project) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Project project = em.find(Project.class, projectId);
				project.getImages().size();
				em.clear();
				return project;
			}
		});
	}

	public Project getProjectByNews(final int newsId) {
		return (Project) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				News news = em.find(News.class, newsId);
				Project project = news.getProject();
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
	public List<Person> listPeopleByRole(final int roleId) {
		return (List<Person>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Role role = em.find(Role.class, roleId);
				List<Person> people = role.getMembers();
				// init...
				people.size();
				em.clear();
				return people;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Role> listRoles() {
		return (List<Role>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Role> resultList = (List<Role>) em.createQuery("from " + Role.class.getName()).getResultList();
				em.clear();
				return resultList;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectLink> listProjectLinks() {
		return (List<ProjectLink>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<ProjectLink> resultList = (List<ProjectLink>) em
						.createQuery("from " + ProjectLink.class.getName()).getResultList();
				em.clear();
				return resultList;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Office> listOffices() {
		return (List<Office>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Office> resultList = (List<Office>) em.createQuery("from " + Office.class.getName())
						.getResultList();
				em.clear();
				return resultList;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectCategory> listProjectCategories() {
		return (List<ProjectCategory>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<ProjectCategory> resultList = (List<ProjectCategory>) em.createQuery(
						"from " + ProjectCategory.class.getName()).getResultList();
				em.clear();
				return resultList;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> listProjectsByCategory(final int categoryId) {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				ProjectCategory projectCategory = em.find(ProjectCategory.class, categoryId);
				List<Project> projects = projectCategory.getProjects();
				// init...
				projects.size();
				em.clear();
				return projects;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> listProjectsByLocation(final long locationId) {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Location location = em.find(Location.class, locationId);
				List<Project> projects = location.getProjects();
				// init...
				projects.size();
				em.clear();
				return projects;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Location> listLocations() {
		return (List<Location>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Location> resultList = (List<Location>) em.createQuery("from " + Location.class.getName())
						.getResultList();
				em.clear();
				return resultList;
			}
		});
	}

	public Project getProjectByProjectLink(final int projectLinkId) {
		return (Project) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				ProjectLink find = em.find(ProjectLink.class, projectLinkId);
				Project project = find.getProject();
				project.getImages().size();
				em.clear();
				return project;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<AwardDTO> listAwardDTOs() {
		return (List<AwardDTO>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Award> resultList = em.createQuery("from Award").getResultList();
				List<AwardDTO> awardDTOs = new ArrayList<AwardDTO>();
				for (Award award : resultList) {
					AwardDTO awardDTO = new AwardDTO();
					awardDTO.award = award;
					if (award.getProject() != null) {
						awardDTO.location = getLocationForProject(award.getProject().getPk());
					}
					awardDTOs.add(awardDTO);
				}
				em.clear();
				for (AwardDTO awardDTO : awardDTOs) {
					if (awardDTO.award != null && awardDTO.award.getProject() != null) {
						awardDTO.award.getProject().setImages(new ArrayList<ImageLink>());
					}
					if (awardDTO.location != null && awardDTO.location.getProjects() != null) {
						awardDTO.location.setProjects(new ArrayList<Project>());
					}
				}
				return awardDTOs;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public Location getLocationForProject(final int pk) {
		return (Location) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Location> resultList = em.createQuery("from Location").getResultList();
				for (Location location : resultList) {
					List<Project> projects = location.getProjects();
					for (Project project : projects) {
						if (project.getPk() == pk) {
							em.clear();
							return location;
						}
					}
				}
				em.clear();
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Bibliography> listBibliographies() {
		return (List<Bibliography>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Bibliography> resultList = (List<Bibliography>) em.createQuery(
						"from " + Bibliography.class.getName()).getResultList();
				em.clear();
				return resultList;
			}
		});
	}

	private Website getWebsite(EntityManager em) {
		return (Website) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Object object = em.createQuery("from Website").getResultList().get(0);
				return object;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<News> listNews() {
		return (List<News>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite(em);
				List<News> newsItems = website.getNewsItems();
				for (News news : newsItems) {
					news.getImages().size();
				}
				newsItems.size();
				em.clear();
				return newsItems;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> listProjects() {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite(em);
				List<Project> projects = website.getProjects();
				projects.size();
				em.clear();
				return projects;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> listProjectsByLocation(final int locationId) {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Location find = em.find(Location.class, locationId);
				List<Project> projects = find.getProjects();
				projects.size();
				em.clear();
				return projects;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> listProjectsByProjectCategory(final int categoryId) {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				ProjectCategory find = em.find(ProjectCategory.class, categoryId);
				List<Project> projects = find.getProjects();
				projects.size();
				em.clear();
				return projects;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectCategory> listProjectCategoriesForProject(final int projectId) {
		return (List<ProjectCategory>) getJpaTemplate().execute(new JpaCallback() {
			public List<ProjectCategory> doInJpa(EntityManager em) throws PersistenceException {
				List<ProjectCategory> ret = new ArrayList<ProjectCategory>();
				List<ProjectCategory> categories = em.createQuery("from ProjectCategory").getResultList();
				for (ProjectCategory category : categories) {
					for (Project project2 : category.getProjects()) {
						if (project2.getPk() == projectId) {
							ret.add(category);
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

	public Office getOffice(final int id) {
		return (Office) getJpaTemplate().execute(new JpaCallback() {
			public Office doInJpa(EntityManager em) throws PersistenceException {
				Office office = em.find(Office.class, id);
				em.clear();
				return office;
			}
		});
	}

	public Person getPerson(final int id) {
		return (Person) getJpaTemplate().execute(new JpaCallback() {
			public Person doInJpa(EntityManager em) throws PersistenceException {
				Person person = em.find(Person.class, id);
				em.clear();
				return person;
			}
		});
	}

	public SubSectionDTO getProfileSection() {
		return (SubSectionDTO) getJpaTemplate().execute(new JpaCallback() {
			public SubSectionDTO doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite(em);
				SubSectionDTO sectionDTO = new SubSectionDTO();
				List<ImageLink> profileImages = website.getProfileImages();
				profileImages.size();
				sectionDTO.setImages(profileImages);
				sectionDTO.setText(website.getProfileText());
				em.clear();
				return sectionDTO;
			}
		});
	}

	public SubSectionDTO getHistorySection() {
		return (SubSectionDTO) getJpaTemplate().execute(new JpaCallback() {
			public SubSectionDTO doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite(em);
				SubSectionDTO sectionDTO = new SubSectionDTO();
				List<ImageLink> historyImages = website.getHistoryImages();
				historyImages.size();
				sectionDTO.setImages(historyImages);
				sectionDTO.setText(website.getHistoryText());
				em.clear();
				return sectionDTO;
			}
		});
	}

	public SubSectionDTO getSustainabilitySection() {
		return (SubSectionDTO) getJpaTemplate().execute(new JpaCallback() {
			public SubSectionDTO doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite(em);
				SubSectionDTO sectionDTO = new SubSectionDTO();
				List<ImageLink> sustainabilityImages = website.getSustainabilityImages();
				sustainabilityImages.size();
				sectionDTO.setImages(sustainabilityImages);
				sectionDTO.setText(website.getSustainabilityText());
				em.clear();
				return sectionDTO;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Service> listServices() {
		return (List<Service>) getJpaTemplate().execute(new JpaCallback() {
			public List<Service> doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite(em);
				List<Service> services = website.getServices();
				for (Service service : services) {
					service.getImages().size();
				}
				services.size();
				em.clear();
				return services;
			}
		});
	}

	// -- New stuff from here on in ..
	public SubSectionDTO getHistory() {
		return getHistorySection();
	}

	public SubSectionDTO getPhilosophy() {
		return (SubSectionDTO) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Website website = getWebsite(em);
				SubSectionDTO dto = new SubSectionDTO();
				dto.setText(website.getPhilosophyText());
				List<ImageLink> philosophyImages = website.getPhilosophyImages();
				philosophyImages.size();
				dto.setImages(philosophyImages);
				em.clear();
				return dto;
			}
		});
	}

	public SubSectionDTO getProfile() {
		return getProfileSection();
	}

	public Service getService(final int id) {
		return (Service) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Service service = em.find(Service.class, id);
				List<ImageLink> images = service.getImages();
				images.size();
				em.clear();
				return service;
			}
		});
	}

	public SubSectionDTO getSustainability() {
		return getSustainabilitySection();
	}

	public List<AwardDTO> listAllAwardsOrderedByDate() {
		List<AwardDTO> listAwardDTOs = listAwardDTOs();
		Collections.sort(listAwardDTOs);
		return listAwardDTOs;
	}

	@SuppressWarnings("unchecked")
	public List<Bibliography> listAllBibliographiesOrderedByDate() {
		return (List<Bibliography>) getJpaTemplate().execute(new JpaCallback() {
			public List<Bibliography> doInJpa(EntityManager em) throws PersistenceException {
				List<Bibliography> resultList = em.createQuery("from Bibliography as bib order by bib.date asc")
						.getResultList();
				resultList.size();
				em.clear();
				return resultList;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<News> listAllNewsOrderedByDate() {
		return (List<News>) getJpaTemplate().execute(new JpaCallback() {
			public List<News> doInJpa(EntityManager em) throws PersistenceException {
				List<News> resultList = em.createQuery("from News as news order by news.date desc").getResultList();
				for (News news : resultList) {
					news.getImages().size();
				}
				resultList.size();
				em.clear();
				return resultList;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectCategory> listAllProjectCategoriesOrderedByWebsite(final boolean allowEmpty) {
		return (List<ProjectCategory>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<ProjectCategory> ret = new ArrayList<ProjectCategory>();
				List<ProjectCategory> categories = getWebsite(em).getProjectCategories();
				for (ProjectCategory category : categories) {
					if (allowEmpty == false) {
						if (category.getProjects().size() == 0) {
							continue;
						}
					}
					ret.add(category);
				}
				categories.size();
				em.clear();
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectLink> listAllProjectLinksOrderedByWebsite() {
		return (List<ProjectLink>) getJpaTemplate().execute(new JpaCallback() {
			public List<ProjectLink> doInJpa(EntityManager em) throws PersistenceException {
				List<ProjectLink> projectLinks = getWebsite(em).getProjectLinks();
				projectLinks.size();
				em.clear();
				return projectLinks;
			}
		});
	}

	public List<Project> listAllProjectsOrderedByAlphabet() {
		List<Project> listAllProjectsOrderedByWebsite = listAllProjectsOrderedByWebsite();
		Collections.sort(listAllProjectsOrderedByWebsite, new Comparator<Project>() {
			public int compare(Project o1, Project o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return listAllProjectsOrderedByWebsite;
	}

	@SuppressWarnings("unchecked")
	public List<Project> listAllProjectsOrderedByWebsite() {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Project> projects = getWebsite(em).getProjects();
				projects.size();
				em.clear();
				return projects;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Region> listAllRegionsOrderedByWebsite(final boolean allowEmpty) {
		return (List<Region>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Region> regions = getWebsite(em).getRegions();
				List<Region> ret = new ArrayList<Region>();
				for (Region region : regions) {
					if (allowEmpty == false && region.getLocations().size() == 0) {
						continue;
					}
					ret.add(region);
				}
				em.clear();
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Role> listAllRolesOrderedByWebsite(final boolean allowEmpty) {
		return (List<Role>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Role> ret = new ArrayList<Role>();
				List<Role> roles = getWebsite(em).getRoles();
				for (Role role : roles) {
					if (allowEmpty == false && role.getMembers().isEmpty()) {
						continue;
					}
					ret.add(role);
				}
				roles.size();
				em.clear();
				return roles;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Service> listAllServicesOrderedByWebsite() {
		return (List<Service>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Service> services = getWebsite(em).getServices();
				services.size();
				em.clear();
				return services;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Location> listLocationsByRegionOrderedByRegion(final int regionId) {
		return (List<Location>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Region region = em.find(Region.class, regionId);
				List<Location> locations = region.getLocations();
				locations.size();
				em.clear();
				return locations;
			}
		});
	}

	public List<Person> listPeopleByRoleOrderedByRole(int roleId) {
		return listPeopleByRole(roleId);
	}

	public List<Project> listProjectsByCategoryOrderedByCategory(int categoryId) {
		return listProjectsByCategory(categoryId);
	}

	@SuppressWarnings("unchecked")
	public List<Office> listAllOfficesOrderedByWebSite() {
		return (List<Office>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Office> newsItems = getWebsite(em).getOffices();
				newsItems.size();
				em.clear();
				return newsItems;
			}
		});
	}

	public String getPdfUrl() {
		return config.getPdfUrl();
	}

	public ImageLink getAwardsImage() {
		return (ImageLink) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				ImageLink link = getWebsite(em).getAwardsImage();
				em.clear();
				return link;
			}
		});
	}
}