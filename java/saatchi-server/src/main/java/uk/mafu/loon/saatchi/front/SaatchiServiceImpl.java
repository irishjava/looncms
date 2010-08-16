package uk.mafu.loon.saatchi.front;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.util.Assert;
import uk.mafu.domain.saatchi.ArticleItem;
import uk.mafu.domain.saatchi.Brand;
import uk.mafu.domain.saatchi.BrandGuidelinesDTO;
import uk.mafu.domain.saatchi.BrandDesignDTO;
import uk.mafu.domain.saatchi.BrandPhotographyDTO;
import uk.mafu.domain.saatchi.BrandStrategyDTO;
import uk.mafu.domain.saatchi.BrandStrategyGroup;
import uk.mafu.domain.saatchi.BrandStrategyItem;
import uk.mafu.domain.saatchi.ClientDTO;
import uk.mafu.domain.saatchi.ContactDTO;
import uk.mafu.domain.saatchi.NewsItem;
import uk.mafu.domain.saatchi.OfficeDTO;
import uk.mafu.domain.saatchi.PartnerDTO;
import uk.mafu.domain.saatchi.PhilosophyItem;
import uk.mafu.domain.saatchi.Product;
import uk.mafu.domain.saatchi.PurposeItem;
import uk.mafu.domain.saatchi.ViewItem;
import uk.mafu.domain.saatchi.Website;
import uk.mafu.domain.saatchi.AttitudeItem;
import uk.mafu.loon.common.DownloadsConfig;
import uk.mafu.loon.domain.data.ImageLink;

public class SaatchiServiceImpl extends JpaDaoSupport implements SaatchiService {
	private static final long serialVersionUID = 1L;
	private DownloadsConfig config;

	@SuppressWarnings("unused")
	private SaatchiServiceImpl() {
		// Spoilsport has done this to prevent initialization except with args
	}

	public SaatchiServiceImpl(DownloadsConfig config) {
		this.config = config;
	}

	public String getImageUrl() {
		return config.getImageUrl();
	}

	public Website getWebsite() {
		return (Website) getJpaTemplate().execute(new JpaCallback() {
			@SuppressWarnings("unchecked")
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Website> resultList = em.createQuery("from Website").getResultList();
				Assert.isTrue(resultList.size() == 1, "One and only one website should exist in the CMS ");
				return resultList.get(0);
			}
		});
	}

	public BrandGuidelinesDTO getBrandGuidelinesDTO() {
		return (BrandGuidelinesDTO) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				BrandGuidelinesDTO dto = new BrandGuidelinesDTO();
				Website website = getWebsite();
				List<Brand> brands = website.getBrandGuidelines();
				brands.size();
				dto.setBrands(brands);
				dto.setTitle(website.getBrandGuidelinesTitle());
				dto.setText(website.getBrandGuidelinesText());
				dto.setQuoteBody(website.getBrandGuidelinesQuoteBody());
				dto.setQuoteReference(website.getBrandGuidelinesQuoteReference());
				em.clear();
				return dto;
			}
		});
	}

	public BrandDesignDTO getBrandDesignDTO() {
		return (BrandDesignDTO) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				BrandDesignDTO dto = new BrandDesignDTO();
				Website website = getWebsite();
				List<Brand> brands = website.getBrandDesign();
				brands.size();
				dto.setBrands(brands);
				dto.setTitle(website.getBrandDesignTitle());
				dto.setText(website.getBrandDesignText());
				dto.setQuoteBody(website.getBrandDesignQuoteBody());
				dto.setQuoteReference(website.getBrandDesignQuoteReference());
				em.clear();
				return dto;
			}
		});
	}

	public BrandPhotographyDTO getBrandPhotographyDTO() {
		return (BrandPhotographyDTO) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				BrandPhotographyDTO dto = new BrandPhotographyDTO();
				Website website = getWebsite();
				List<Brand> brands = website.getBrandPhotography();
				brands.size();
				dto.setBrands(brands);
				dto.setTitle(website.getBrandPhotographyTitle());
				dto.setText(website.getBrandPhotographyText());
				dto.setQuoteBody(website.getBrandPhotographyQuoteBody());
				dto.setQuoteReference(website.getBrandPhotographyQuoteReference());
				em.clear();
				return dto;
			}
		});
	}

	public BrandStrategyDTO getBrandStrategyDTO() {
		return (BrandStrategyDTO) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				BrandStrategyDTO dto = new BrandStrategyDTO();
				Website website = getWebsite();
				dto.setTitle(website.getBrandStrategyTitle());
				dto.setText(website.getBrandStrategyText());
				dto.setQuoteBody(website.getBrandStrategyQuoteBody());
				dto.setQuoteReference(website.getBrandStrategyQuoteReference());
				em.clear();
				return dto;
			}
		});
	}

	public ClientDTO getClientDTO() {
		return (ClientDTO) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				ClientDTO dto = new ClientDTO();
				Website website = getWebsite();
				dto.setText1(website.getClientText1());
				dto.setText2(website.getClientText2());
				em.clear();
				return dto;
			}
		});
	}

	public ContactDTO getContactDTO() {
		return (ContactDTO) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				ContactDTO dto = new ContactDTO();
				Website website = getWebsite();
				dto.setText1(website.getContact1());
				dto.setText2(website.getContact2());
				dto.setText3(website.getContact3());
				dto.setBlogUrl(website.getBlogUrl());
				dto.setTwitterUrl(website.getTwitterUrl());
				dto.setGoogleMapUrl(website.getGoogleMapUrl());
				em.clear();
				return dto;
			}
		});
	}

	public OfficeDTO getOfficeDTO() {
		return (OfficeDTO) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				OfficeDTO dto = new OfficeDTO();
				Website website = getWebsite();
				dto.setText(website.getOfficeText());
				em.clear();
				return dto;
			}
		});
	}

	public PartnerDTO getPartnerDTO() {
		return (PartnerDTO) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				PartnerDTO dto = new PartnerDTO();
				Website website = getWebsite();
				dto.setText(website.getPartnerText());
				dto.setQuoteBody(website.getPartnerQuoteBody());
				dto.setQuoteReference(website.getPartnerQuoteReference());
				em.clear();
				return dto;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<BrandStrategyItem> listAllBrandStrategies() {
		return (List<BrandStrategyItem>) getJpaTemplate().execute(new JpaCallback() {
			public List<BrandStrategyItem> doInJpa(EntityManager em) throws PersistenceException {
				List<BrandStrategyItem> ret = em.createQuery("from BrandStrategyItem").getResultList();
				for (BrandStrategyItem item : ret) {
					item.getImages().size();
				}
				em.clear();
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ArticleItem> listArticleItems() {
		return (List<ArticleItem>) getJpaTemplate().execute(new JpaCallback() {
			public List<ArticleItem> doInJpa(EntityManager em) throws PersistenceException {
				List<ArticleItem> ret = em.createQuery("from ArticleItem a order by a.date desc").getResultList();
				em.clear();
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<BrandStrategyItem> listBrandStrategiesByGroup(final int groupId) {
		return (List<BrandStrategyItem>) getJpaTemplate().execute(new JpaCallback() {
			public List<BrandStrategyItem> doInJpa(EntityManager em) throws PersistenceException {
				BrandStrategyGroup find = em.find(BrandStrategyGroup.class, groupId);
				Assert.notNull(find, "group not found '" + groupId + "'");
				List<BrandStrategyItem> items = find.getItems();
				for (BrandStrategyItem item : items) {
					item.getImages().size();
				}
				em.clear();
				return items;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<BrandStrategyGroup> listBrandStrategyGroups() {
		return (List<BrandStrategyGroup>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<BrandStrategyGroup> strategyGroups = getWebsite().getStrategyGroups();
				strategyGroups.size();
				em.clear();
				return strategyGroups;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ImageLink> listHomePageItems() {
		return (List<ImageLink>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<ImageLink> homepageItems = getWebsite().getHomepageItems();
				homepageItems.size();
				em.clear();
				return homepageItems;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<NewsItem> listNewsItems() {
		return (List<NewsItem>) getJpaTemplate().execute(new JpaCallback() {
			public List<NewsItem> doInJpa(EntityManager em) throws PersistenceException {
				List<NewsItem> ret = em.createQuery("from NewsItem n order by n.date desc").getResultList();
				em.clear();
				return ret;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<PhilosophyItem> listPhilosophyItems() {
		return (List<PhilosophyItem>) getJpaTemplate().execute(new JpaCallback() {
			public List<PhilosophyItem> doInJpa(EntityManager em) throws PersistenceException {
				List<PhilosophyItem> philosophyItems = getWebsite().getPhilosophyItems();
				philosophyItems.size();
				em.clear();
				return philosophyItems;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Product> listProductsByBrand(final int brandId) {
		return (List<Product>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Brand brand = em.find(Brand.class, brandId);
				Assert.notNull(brand, "cannot find Brand '" + brandId + "'");
				List<Product> products = brand.getProducts();
				for (Product product : products) {
					product.getImages().size();
				}
				em.clear();
				return products;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ViewItem> listViewItems() {
		return (List<ViewItem>) getJpaTemplate().execute(new JpaCallback() {
			public List<ViewItem> doInJpa(EntityManager em) throws PersistenceException {
				List<ViewItem> views = (List<ViewItem>) em.createQuery("from ViewItem v order by v.date desc")
						.getResultList();
				for (ViewItem view : views) {
					view.getImages().size();
				}
				em.clear();
				return views;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<AttitudeItem> listAttitudeItems() {
		return (List<AttitudeItem>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<AttitudeItem> attitudeItems = getWebsite().getAttitudeItems();
				attitudeItems.size();
				em.clear();
				return attitudeItems;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<PurposeItem> listPurposeItems() {
		return (List<PurposeItem>) getJpaTemplate().execute(new JpaCallback() {
			public List<PurposeItem> doInJpa(EntityManager em) throws PersistenceException {
				List<PurposeItem> ret = getWebsite().getPurposeItems();
				ret.size();
				em.clear();
				return ret;
			}
		});
	}
}
