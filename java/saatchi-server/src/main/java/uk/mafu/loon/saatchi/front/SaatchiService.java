package uk.mafu.loon.saatchi.front;

import java.util.List;
import uk.mafu.domain.saatchi.ArticleItem;
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
import uk.mafu.domain.saatchi.AttitudeItem;
import uk.mafu.loon.domain.data.ImageLink;

public interface SaatchiService {
	/**
	 * Ordered by date
	 * 
	 * @return
	 */
	List<NewsItem> listNewsItems();

	/**
	 * Ordered by date
	 * 
	 * @return
	 */
	List<ViewItem> listViewItems();

	/**
	 * Ordered by date
	 * 
	 * @return
	 */
	List<ArticleItem> listArticleItems();

	BrandStrategyDTO getBrandStrategyDTO();

	BrandDesignDTO getBrandDesignDTO();

	BrandPhotographyDTO getBrandPhotographyDTO();

	BrandGuidelinesDTO getBrandGuidelinesDTO();

	ClientDTO getClientDTO();

	OfficeDTO getOfficeDTO();

	PartnerDTO getPartnerDTO();

	ContactDTO getContactDTO();

	List<ImageLink> listHomePageItems();

	List<AttitudeItem> listAttitudeItems();
	
	List<PurposeItem> listPurposeItems();

	List<PhilosophyItem> listPhilosophyItems();

	List<BrandStrategyGroup> listBrandStrategyGroups();

	List<BrandStrategyItem> listBrandStrategiesByGroup(int groupId);

	List<BrandStrategyItem> listAllBrandStrategies();

	List<Product> listProductsByBrand(int brandId);

	String getImageUrl();
}
