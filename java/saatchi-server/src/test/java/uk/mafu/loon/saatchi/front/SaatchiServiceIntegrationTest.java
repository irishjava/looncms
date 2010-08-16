/**
 * 
 */
package uk.mafu.loon.saatchi.front;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.hasItem;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import uk.mafu.domain.saatchi.BrandStrategyGroup;
import uk.mafu.domain.saatchi.NewsItem;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * @author bryan
 * 
 */
public class SaatchiServiceIntegrationTest {
	static SaatchiService saatchiService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// String url =
		// "http://localhost:8666/saatchi-server/loon/saatchiService";
		String url = "http://www.mafunet.com/saatchi-server-0.0.1-SNAPSHOT/loon/saatchiService";
		HessianProxyFactory factory = new HessianProxyFactory();
		saatchiService = (SaatchiService) factory.create(SaatchiService.class,
				url);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#listNewsItems()}.
	 */
	@Test
	public final void testListNewsItems() {
		List<NewsItem> listNewsItems = saatchiService.listNewsItems();
		assertThat(listNewsItems, hasItem(any(NewsItem.class)));
	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#listViewItems()}.
	 */
	@Test
	public final void testListViewItems() {
		saatchiService.listViewItems();
	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#listArticleItems()}.
	 */
	@Test
	public final void testListArticleItems() {
		saatchiService.listArticleItems();
	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#getBrandStrategyDTO()}.
	 */
	@Test
	public final void testGetBrandStrategyDTO() {
		saatchiService.getBrandStrategyDTO();
	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#getBrandDesignDTO()}.
	 */
	@Test
	public final void testGetBrandIdentityDTO() {
		saatchiService.getBrandDesignDTO();
	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#getBrandPhotographyDTO()}
	 * .
	 */
	@Test
	public final void testGetBrandPhotographyDTO() {
		saatchiService.getBrandPhotographyDTO();
	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#getBrandGuidelinesDTO()}
	 * .
	 */
	@Test
	public final void testGetBrandCommunicationDTO() {
		saatchiService.getBrandGuidelinesDTO();
	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#getClientDTO()}.
	 */
	@Test
	public final void testGetClientDTO() {
		saatchiService.getClientDTO();
	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#getOfficeDTO()}.
	 */
	@Test
	public final void testGetOfficeDTO() {
		saatchiService.getOfficeDTO();
	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#getPartnerDTO()}.
	 */
	@Test
	public final void testGetPartnerDTO() {
		saatchiService.getPartnerDTO();
	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#getContactDTO()}.
	 */
	@Test
	public final void testGetContactDTO() {
		saatchiService.getContactDTO();
	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#listHomePageItems()}.
	 */
	@Test
	public final void testListHomePageItems() {
		saatchiService.listHomePageItems();
	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#listAttitudeItems()}.
	 */
	@Test
	public final void testListWhatWeDoItems() {
		saatchiService.listAttitudeItems();
	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#listPhilosophyItems()}.
	 */
	@Test
	public final void testListPhilosophyItems() {
		saatchiService.listPhilosophyItems();
	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#listBrandStrategyGroups()}
	 * .
	 */
	@Test
	public final void testListBrandStrategyGroups() {
		saatchiService.listBrandStrategyGroups();
	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#listBrandStrategiesByGroup(int)}
	 * .
	 */
	@Test
	public final void testListBrandStrategiesByGroup() {
		List<BrandStrategyGroup> listBrandStrategyGroups = saatchiService
				.listBrandStrategyGroups();
		for (BrandStrategyGroup group : listBrandStrategyGroups) {
			saatchiService.listBrandStrategiesByGroup(group.getPk());
		}

	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#listAllBrandStrategies()}
	 * .
	 */
	@Test
	public final void testListAllBrandStrategies() {
		saatchiService.listAllBrandStrategies();
	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#listProductsByBrand(int)}
	 * .
	 */
	@Test
	@Ignore
	public final void testListProductsByBrand() {

	}

	/**
	 * Test method for
	 * {@link uk.mafu.loon.saatchi.front.SaatchiService#getImageUrl()}.
	 */
	@Test
	public final void testGetImageUrl() {
		saatchiService.getImageUrl();
	}
}
