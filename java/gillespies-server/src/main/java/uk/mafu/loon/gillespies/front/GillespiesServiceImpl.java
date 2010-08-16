package uk.mafu.loon.gillespies.front;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TemporalType;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.util.Assert;

import uk.mafu.domain.gillespies.Assignment;
import uk.mafu.domain.gillespies.Award;
import uk.mafu.domain.gillespies.BlogEntry;
import uk.mafu.domain.gillespies.Client;
import uk.mafu.domain.gillespies.CommunityWork;
import uk.mafu.domain.gillespies.Job;
import uk.mafu.domain.gillespies.LectureAndTalk;
import uk.mafu.domain.gillespies.Link;
import uk.mafu.domain.gillespies.News;
import uk.mafu.domain.gillespies.Office;
import uk.mafu.domain.gillespies.OfficeLocation;
import uk.mafu.domain.gillespies.Person;
import uk.mafu.domain.gillespies.PressRelease;
import uk.mafu.domain.gillespies.Profile;
import uk.mafu.domain.gillespies.Project;
import uk.mafu.domain.gillespies.ProjectCategory;
import uk.mafu.domain.gillespies.ProjectLink;
import uk.mafu.domain.gillespies.ProjectLocation;
import uk.mafu.domain.gillespies.Publication;
import uk.mafu.domain.gillespies.Role;
import uk.mafu.domain.gillespies.Sustainability;
import uk.mafu.domain.gillespies.Testimonial;
import uk.mafu.domain.gillespies.Website;
import uk.mafu.domain.gillespies.Working;
import uk.mafu.domain.gillespies.YearOut;
import uk.mafu.loon.common.DownloadsConfig;
import uk.mafu.loon.util.DateUtil;

public class GillespiesServiceImpl extends JpaDaoSupport implements
		GillespiesService {
	private static final int ARCHIVE_OFFSET = 14;
	private static final long serialVersionUID = 1L;
	private DownloadsConfig downloadsConfig;

	@SuppressWarnings("unused")
	private GillespiesServiceImpl() {
		// Spoilsport has done this to prevent initialization except with args
	}

	public GillespiesServiceImpl(DownloadsConfig downloadsConfigconfig) {
		this.downloadsConfig = downloadsConfigconfig;
		Assert.notNull(downloadsConfigconfig);
	}

	@SuppressWarnings("unchecked")
	public List<ProjectCategory> getCategoriesByProject(final int projectId) {
		return (List<ProjectCategory>) getJpaTemplate().execute(
				new JpaCallback() {
					public Object doInJpa(EntityManager em)
							throws PersistenceException {
						List<ProjectCategory> ret = new ArrayList<ProjectCategory>();
						Project project = em.find(Project.class, projectId);
						if (project == null) {
							return ret;
						}
						List<ProjectCategory> projectCategories = em
								.createQuery("from ProjectCategory")
								.getResultList();
						for (ProjectCategory category : projectCategories) {
							for (Project project1 : category.getProjects()) {
								if (project1.getPk() == project.getPk()) {
									ret.add(category);
									break;
								}
							}
						}
						em.clear();
						for (ProjectCategory categoryInReturn : ret) {
							categoryInReturn.getProjects().clear();
						}
						return ret;
					}
				});
	}

	public Project getFullyInitializedPoject(final int projectId) {
		return (Project) getJpaTemplate().execute(new JpaCallback() {
			public Project doInJpa(EntityManager em)
					throws PersistenceException {
				Project find = em.find(Project.class, projectId);
				find.getImages().size();
				// find.getPdfs().size();
				// find.getVideos().size();
				find.getAssignments().size();
				em.clear();
				return find;
			}
		});
	}

	public String getImageUrl() {
		return downloadsConfig.getImageUrl();
	}

	@SuppressWarnings("unchecked")
	public List<Office> getOfficesByJob(final int jobId) {
		return (List<Office>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Office> ret = new ArrayList<Office>();
				Job job = em.find(Job.class, jobId);
				if (job == null) {
					return ret;
				}
				List<Office> resultList = em.createQuery("from Office")
						.getResultList();
				for (Office office : resultList) {
					for (Job officeJob : office.getJobs()) {
						if (officeJob.getPk() == job.getPk()) {
							ret.add(office);
							break;
						}
					}
				}
				em.clear();
				for (Office retoffice : ret) {
					retoffice.getJobs().clear();
				}
				return ret;
			}
		});
	}

	public String getPdfUrl() {
		return downloadsConfig.getPdfUrl();
	}

	public Profile getProfile() {
		return (Profile) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Profile profile = getWebsite(em).getProfile();
				em.clear();
				return profile;
			}
		});
	}

	public Project getProjectByAward(final int awardId) {
		return (Project) getJpaTemplate().execute(new JpaCallback() {
			public Project doInJpa(EntityManager em)
					throws PersistenceException {
				Award find = em.find(Award.class, awardId);
				Project project = find.getProject();
				em.clear();
				return project;
			}
		});
	}

	public Project getProjectByNews(final int newsId) {
		return (Project) getJpaTemplate().execute(new JpaCallback() {
			public Project doInJpa(EntityManager em)
					throws PersistenceException {
				News find = em.find(News.class, newsId);
				Project project = find.getProject();
				em.clear();
				return project;
			}
		});
	}

	public String getVideoUrl() {
		return downloadsConfig.getVideoUrl();
	}

	private Website getWebsite(EntityManager em) {
		return (Website) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Object object = em.createQuery("from Website").getResultList()
						.get(0);
				return object;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<OfficeAndJob> listAllJobs() {
		return (List<OfficeAndJob>) getJpaTemplate().execute(new JpaCallback() {
			public List<OfficeAndJob> doInJpa(EntityManager em)
					throws PersistenceException {
				List<OfficeAndJob> ret = new ArrayList<OfficeAndJob>();
				List<Office> offices = em.createQuery("from Office")
						.getResultList();
				for (Office office : offices) {
					List<Job> jobs = office.getJobs();
					jobs.size();
					for (Job job : jobs) {
						OfficeAndJob officeAndJob = new OfficeAndJob(office,
								job);
						if (!ret.contains(officeAndJob)) {
							ret.add(officeAndJob);
						}
					}
				}
				List<Job> globalJobs = getWebsite(em).getGlobalJobs();
				for (Job job : globalJobs) {
					OfficeAndJob officeAndJob = new OfficeAndJob(job);
					if (!ret.contains(officeAndJob)) {
						ret.add(officeAndJob);
					}
				}
				em.clear();
				for (OfficeAndJob officeAndJob : ret) {
					if (officeAndJob.getOffice() != null) {
						officeAndJob.getOffice().setJobs(new ArrayList<Job>());
					}
				}
				Collections.sort(ret, new Comparator<OfficeAndJob>() {
					public int compare(OfficeAndJob o1, OfficeAndJob o2) {
						// Sort in reverse chronological order so most recent
						// comes first and then we work backwards..
						int compareTo = o2.getJob().getDate().compareTo(
								o1.getJob().getDate());
						return compareTo;
					}
				});
				return ret;
			}
		});
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<BlogEntry> listArchiveBlogs() {
		return (List<BlogEntry>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<BlogEntry> ret = new ArrayList<BlogEntry>();
				List<BlogEntry> blogentries = em.createQuery(
						"from BlogEntry as blog order by blog.date desc")
						.getResultList();
				if (blogentries.size() > ARCHIVE_OFFSET) {
					List<BlogEntry> subList = blogentries.subList(
							ARCHIVE_OFFSET, blogentries.size());
					for (BlogEntry blogEntry : subList) {
						blogEntry.getImages().size();
					}
					ret.addAll(subList);
				}
				em.clear();
				return ret;
			}
		});
	}

	public List<BlogEntry> listArchiveBlogsByYear(int year) {
		List<BlogEntry> ret = new ArrayList<BlogEntry>();
		for (BlogEntry news : listArchiveBlogs()) {
			DateTime dateTime = new DateTime(news.getDate());
			if (dateTime.getYear() == year) {
				ret.add(news);
			}
		}
		return ret;
	}

	/** this will return all blog items ordered by date, minus the first 14 */
	@SuppressWarnings("unchecked")
	public List<Integer> listArchiveBlogYears() {
		return (List<Integer>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Set<Integer> set = new HashSet<Integer>();
				List<Integer> ret = new ArrayList<Integer>();
				List<BlogEntry> blogentries = em.createQuery(
						"from BlogEntry as blog order by blog.date desc")
						.getResultList();
				// Initialize it ..
				blogentries.size();
				// Clear entity manager
				em.clear();
				if (blogentries.size() <= ARCHIVE_OFFSET) {
					return new ArrayList<Integer>();
				}

				if (blogentries.size() > ARCHIVE_OFFSET) {
					blogentries = blogentries.subList(ARCHIVE_OFFSET,
							blogentries.size());
				}

				for (BlogEntry blogEntry : blogentries) {
					DateTime dt = new DateTime(blogEntry.getDate().getTime());
					set.add(dt.getYear());
				}
				ret.addAll(set);
				Collections.sort(ret, new Comparator<Integer>() {
					public int compare(Integer o1, Integer o2) {
						if (o1 == o2) {
							return 0;
						}
						if (o1 < o2) {
							return 1;
						} else {
							return -1;
						}
					}
				});
				em.clear();
				return ret;
			}
		});
	}

	/** this will return all news items ordered by date, minus the first 14 */
	@SuppressWarnings("unchecked")
	public List<News> listArchiveNews() {
		return (List<News>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<News> ret = new ArrayList<News>();
				List<News> newsItems = em.createQuery(
						"from News as news order by news.date desc")
						.getResultList();
				if (newsItems.size() > ARCHIVE_OFFSET) {
					List<News> subList = newsItems.subList(ARCHIVE_OFFSET,
							newsItems.size());
					for (News News : subList) {
						News.getImages().size();
					}
					ret.addAll(subList);
				}
				em.clear();
				return ret;
			}
		});

	}

	public List<News> listArchiveNewsByYear(int year) {
		List<News> ret = new ArrayList<News>();
		for (News news : listArchiveNews()) {
			DateTime dateTime = new DateTime(news.getDate());
			if (dateTime.getYear() == year) {
				ret.add(news);
			}
		}
		return ret;
	}

	/**
	 * this will return a list of the years the news items were posted, ie,
	 * 2007, 2008, 2009
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> listArchiveNewsYears() {
		return (List<Integer>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Set<Integer> set = new HashSet<Integer>();
				List<Integer> ret = new ArrayList<Integer>();

				List<News> newsItems = em.createQuery(
						"from News as news order by news.date desc")
						.getResultList();
				// Initialize it ..
				newsItems.size();
				// Clear entity manager
				em.clear();

				if (newsItems.size() <= 14) {
					return new ArrayList<Integer>();
				}

				if (newsItems.size() > ARCHIVE_OFFSET) {
					newsItems = newsItems.subList(ARCHIVE_OFFSET, newsItems
							.size());
				}

				for (News news : newsItems) {
					DateTime dt = new DateTime(news.getDate().getTime());
					set.add(dt.getYear());
				}
				ret.addAll(set);

				Collections.sort(ret, new Comparator<Integer>() {
					public int compare(Integer o1, Integer o2) {
						if (o1 == o2) {
							return 0;
						}
						if (o1 < o2) {
							return 1;
						} else {
							return -1;
						}
					}
				});
				em.clear();
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Assignment> listAssignments() {
		return (List<Assignment>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Assignment> assignments = getWebsite(em).getAssignments();
				assignments.size();
				em.clear();
				return assignments;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Assignment> listAssignmentsByRole(final int roleId) {
		return (List<Assignment>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Assignment> ret = new ArrayList<Assignment>();
				List<Assignment> assignments = getWebsite(em).getAssignments();
				for (Assignment assignment : assignments) {
					if (assignment.getRole() != null
							&& assignment.getRole().getPk() == roleId) {
						assignment.getOffice();
						assignment.getPerson();
						ret.add(assignment);
					}
				}
				assignments.size();
				em.clear();
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Award> listAwards() {
		return (List<Award>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Award> awards = getWebsite(em).getAwards();
				awards.size();
				em.clear();
				return awards;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<BlogEntry> listBlogEntries() {
		return (List<BlogEntry>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<BlogEntry> blogentries = getWebsite(em).getBlogentries();
				for (BlogEntry blogEntry : blogentries) {
					blogEntry.getImages().size();
				}
				blogentries.size();
				em.clear();
				return blogentries;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<BlogEntry> listBlogEntriesByYear(final int year) {
		return (List<BlogEntry>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Date[] dates = DateUtil.getStartAndEndOfYear(year);
				List<BlogEntry> resultList = (List<BlogEntry>) em
						.createQuery(
								"from BlogEntry as blog where blog.date > ?1 and blog.date < ?2 ")
						.setParameter(1, dates[0], TemporalType.DATE)
						.setParameter(2, dates[1], TemporalType.DATE)
						.getResultList();
				for (BlogEntry blogEntry : resultList) {
					blogEntry.getImages().size();
				}
				em.clear();
				return resultList;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<BlogEntry> listBlogEntriesOrderByDate() {
		return (List<BlogEntry>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<BlogEntry> resultList = em.createQuery(
						"from BlogEntry as blog order by blog.date asc")
						.getResultList();
				for (BlogEntry blogEntry : resultList) {
					blogEntry.getImages().size();
				}
				em.clear();
				return resultList;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Client> listClients() {
		return (List<Client>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Client> clients = getWebsite(em).getClients();
				clients.size();
				em.clear();
				return clients;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<CommunityWork> listCommunityWork() {
		return (List<CommunityWork>) getJpaTemplate().execute(
				new JpaCallback() {
					public List<CommunityWork> doInJpa(EntityManager em)
							throws PersistenceException {
						List<CommunityWork> communityWorks = getWebsite(em)
								.getCommunityWorks();
						communityWorks.size();
						for (CommunityWork communityWork : communityWorks) {
							communityWork.getImages().size();
						}
						em.clear();
						return communityWorks;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<Job> listGlobalJobs() {
		return (List<Job>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Job> globalJobs = getWebsite(em).getGlobalJobs();
				globalJobs.size();
				em.clear();
				return globalJobs;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Job> listJobsByOffice(final int officeId) {
		return (List<Job>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Office find = em.find(Office.class, officeId);
				List<Job> jobs = find.getJobs();
				jobs.size();
				em.clear();
				return jobs;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Job> listJobsOrderByDate() {
		return (List<Job>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Job> res = em.createQuery(
						"from Job as job order by job.date").getResultList();
				em.clear();
				return res;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<LectureAndTalk> listLectureAndTalks() {
		return (List<LectureAndTalk>) getJpaTemplate().execute(
				new JpaCallback() {
					public List<LectureAndTalk> doInJpa(EntityManager em)
							throws PersistenceException {
						List<LectureAndTalk> lectureAndTalks = getWebsite(em)
								.getLecturesAndTalks();
						lectureAndTalks.size();
						for (LectureAndTalk lectureAndTalk : lectureAndTalks) {
							lectureAndTalk.getImages().size();
						}
						em.clear();
						return lectureAndTalks;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<Link> listLinks() {
		return (List<Link>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List find = em.createQuery("from Link").getResultList();
				em.clear();
				return find;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectLocation> listLocationsContainingProjects() {
		return (List<ProjectLocation>) getJpaTemplate().execute(
				new JpaCallback() {
					public Object doInJpa(EntityManager em)
							throws PersistenceException {
						List<ProjectLocation> resultList = em
								.createQuery(
										""
												+ "SELECT p FROM ProjectLocation p WHERE p.projects IS NOT EMPTY")
								.getResultList();
						em.clear();
						return resultList;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<News> listNews() {
		return (List<News>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<News> resultList = getWebsite(em).getNewsItems();
				for (News news : resultList) {
					news.getImages().size();
				}
				em.clear();
				return resultList;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<News> listNewsByYear(final int year) {
		return (List<News>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Date[] dates = DateUtil.getStartAndEndOfYear(year);
				List<News> resultList = (List<News>) em
						.createQuery(
								"from News as news  left join fetch news.images where news.date > ?1 and news.date < ?2 order by news.date desc")
						.setParameter(1, dates[0], TemporalType.DATE)
						.setParameter(2, dates[1], TemporalType.DATE)
						.getResultList();
				em.clear();
				ArrayList<News> ret = new ArrayList<News>();
				for (News news : resultList) {
					if (!ret.contains(news)) {
						ret.add(news);
					}
				}
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<News> listNewsOrderByDate() {
		return (List<News>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<News> resultList = em
						.createQuery(
								"from News as news left join fetch news.images order by news.date asc")
						.getResultList();
				em.clear();
				ArrayList<News> ret = new ArrayList<News>();
				for (News news : resultList) {
					if (!ret.contains(news)) {
						ret.add(news);
					}
				}
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<OfficeLocation> listOfficeLocations() {
		return (List<OfficeLocation>) getJpaTemplate().execute(
				new JpaCallback() {
					public Object doInJpa(EntityManager em)
							throws PersistenceException {
						List resultList = em.createQuery("from OfficeLocation")
								.getResultList();
						em.clear();
						return resultList;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<Office> listOffices() {
		return (List<Office>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List resultList = em.createQuery("from Office").getResultList();
				em.clear();
				return resultList;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Office> listOfficesByLocation(final int locationId) {
		return (List<Office>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				OfficeLocation find = em.find(OfficeLocation.class, locationId);
				List<Office> offices = find.getOffices();
				offices.size();
				em.clear();
				return offices;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Person> listPeople() {
		return (List<Person>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List resultList = em.createQuery("from Person").getResultList();
				em.clear();
				return resultList;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<PressRelease> listPressReleases() {
		return (List<PressRelease>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List resultList = em.createQuery("from PressRelease")
						.getResultList();
				em.clear();
				return resultList;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectCategory> listProjectCategories() {
		return (List<ProjectCategory>) getJpaTemplate().execute(
				new JpaCallback() {
					public Object doInJpa(EntityManager em)
							throws PersistenceException {
						// Change as per GILLESPIES-64
						// List<ProjectCategory> resultList = em.createQuery(
						// "" +
						// "SELECT p FROM ProjectCategory p WHERE p.projects IS NOT EMPTY").getResultList();
						// em.clear();
						// return resultList;
						List<ProjectCategory> projectCategories = getWebsite(em)
								.getProjectCategories();
						projectCategories.size();
						em.clear();
						return projectCategories;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectLink> listProjectLinks() {
		return (List<ProjectLink>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<ProjectLink> ret = new ArrayList<ProjectLink>();
				List<ProjectCategory> projectCategories = getWebsite(em)
						.getProjectCategories();
				List<ProjectLink> projectLinks = getWebsite(em)
						.getProjectLinks();
				projectLinks.size();
				outer: for (ProjectLink projectLink : projectLinks) {
					if (projectLink.getProject() != null) {
						for (ProjectCategory projectCategory : projectCategories) {
							List<Project> projects = projectCategory
									.getProjects();
							for (Project project : projects) {
								if (project.getPk() == projectLink.getProject()
										.getPk()) {
									ret.add(projectLink);
									continue outer;
								}
							}
						}
					}
				}
				em.clear();
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ProjectLocation> listProjectLocations() {
		return (List<ProjectLocation>) getJpaTemplate().execute(
				new JpaCallback() {
					public Object doInJpa(EntityManager em)
							throws PersistenceException {
						List resultList = em
								.createQuery("from ProjectLocation")
								.getResultList();
						resultList.size();
						em.clear();
						return resultList;
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
				List<Project> ret = new ArrayList<Project>();
				for (Project project : projects) {
					if (!project.empty()) {
						ret.add(project);
					}
				}
				em.clear();
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> listProjectsByCategory(final int categoryId) {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				ProjectCategory cat = em
						.find(ProjectCategory.class, categoryId);
				List<Project> projects = cat.getProjects();
				projects.size();
				List<Project> ret = new ArrayList<Project>();
				for (Project project : projects) {
					if (!project.empty()) {
						ret.add(project);
					}
				}
				em.clear();
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> listProjectsByCategoryIncludingEmpty(
			final int categoryId) {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				ProjectCategory find = em.find(ProjectCategory.class,
						categoryId);
				List<Project> projects = find.getProjects();
				projects.size();
				em.clear();
				return projects;
			}
		});
	}

	/**
	 * This method returns projects by location, but only if they are in the
	 * projects list within the website object.
	 */
	@SuppressWarnings("unchecked")
	public List<Project> listProjectsByLocation(final int locationId) {
		return (List<Project>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Project> websiteprojects = getWebsite(em).getProjects();
				ProjectLocation location = em.find(ProjectLocation.class,
						locationId);
				List<Project> projects = location.getProjects();

				List<Project> returnProjects = (List<Project>) CollectionUtils
						.intersection(websiteprojects, projects);
				em.clear();
				return returnProjects;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> listProjectsIncludingEmpty() {
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
	public List<Publication> listPublications() {
		return (List<Publication>) getJpaTemplate().execute(new JpaCallback() {
			public List<Publication> doInJpa(EntityManager em)
					throws PersistenceException {
				List<Publication> publications = getWebsite(em)
						.getPublications();
				publications.size();
				for (Publication publication : publications) {
					publication.getImages().size();
				}
				em.clear();
				return publications;
			}
		});
	}

	/** this will return the latest 14 blog items (ordered by date) */
	@SuppressWarnings("unchecked")
	public List<BlogEntry> listRecentBlogs() {
		return (List<BlogEntry>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<BlogEntry> ret = new ArrayList<BlogEntry>();
				List<BlogEntry> blogentries = em.createQuery(
						"from BlogEntry as blog order by blog.date desc")
						.getResultList();
				List<BlogEntry> subList = blogentries.subList(0, (blogentries
						.size() > ARCHIVE_OFFSET) ? ARCHIVE_OFFSET
						: blogentries.size());
				for (BlogEntry blogEntry : subList) {
					blogEntry.getImages().size();
				}
				ret.addAll(subList);
				em.clear();
				return ret;
			}
		});

	}

	/** this will return the latest 14 news items (ordered by date) */
	@SuppressWarnings("unchecked")
	public List<News> listRecentNews() {
		return (List<News>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<News> ret = new ArrayList<News>();
				List<News> newsItems = em.createQuery(
						"from News as news order by news.date desc")
						.getResultList();
				List<News> subList = newsItems.subList(0,
						(newsItems.size() > ARCHIVE_OFFSET) ? ARCHIVE_OFFSET
								: newsItems.size());
				for (News News : subList) {
					News.getImages().size();
				}
				ret.addAll(subList);
				em.clear();
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Role> listRoles() {
		return (List<Role>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List resultList = em.createQuery("from Role").getResultList();
				em.clear();
				return resultList;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Sustainability> listSustainability() {
		return (List<Sustainability>) getJpaTemplate().execute(
				new JpaCallback() {
					public List<Sustainability> doInJpa(EntityManager em)
							throws PersistenceException {
						List<Sustainability> sustainabilities = getWebsite(em)
								.getSustainabilities();
						sustainabilities.size();
						for (Sustainability sustainability : sustainabilities) {
							sustainability.getImages().size();
						}
						em.clear();
						return sustainabilities;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<Testimonial> listTestimonials() {
		return (List<Testimonial>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Testimonial> testimonials = getWebsite(em)
						.getTestimonials();
				testimonials.size();
				em.clear();
				return testimonials;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Working> listWorkings() {
		return (List<Working>) getJpaTemplate().execute(new JpaCallback() {
			public List<Working> doInJpa(EntityManager em)
					throws PersistenceException {
				List<Working> workings = getWebsite(em).getWorkings();
				workings.size();
				for (Working working : workings) {
					working.getImages().size();
				}
				em.clear();
				return workings;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<YearOut> listYearOut() {
		return (List<YearOut>) getJpaTemplate().execute(new JpaCallback() {
			public List<YearOut> doInJpa(EntityManager em)
					throws PersistenceException {
				List<YearOut> yearouts = getWebsite(em).getYearOuts();
				yearouts.size();
				for (YearOut yearOut : yearouts) {
					yearOut.getImages().size();
				}
				em.clear();
				return yearouts;
			}
		});
	}
}
