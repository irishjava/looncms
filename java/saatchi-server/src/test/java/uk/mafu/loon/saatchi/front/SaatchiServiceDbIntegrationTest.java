package uk.mafu.loon.saatchi.front;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import uk.mafu.domain.saatchi.BrandDesignDTO;
import uk.mafu.domain.saatchi.BrandGuidelinesDTO;
import uk.mafu.domain.saatchi.BrandPhotographyDTO;
import uk.mafu.domain.saatchi.BrandStrategyDTO;

public class SaatchiServiceDbIntegrationTest extends AbstractTransactionalDataSourceSpringContextTests {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(SaatchiServiceDbIntegrationTest.class);
	SaatchiService saatchiService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	public SaatchiServiceDbIntegrationTest() {
		super();
		ApplicationContext ctx = super.getApplicationContext();
		saatchiService = (SaatchiService) ctx.getBean("saatchiService");
		assertNotNull(saatchiService);
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "classpath:application-context.xml" };
	}

	@Test
	@Ignore
	public void testSaatchiServiceImpl() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetImageUrl() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetWebsite() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBrandGuidelinesDTO() {
		BrandGuidelinesDTO brandGuidelinesDTO = saatchiService.getBrandGuidelinesDTO();
		assertTrue(brandGuidelinesDTO.getBrands().size() > 0);
	}

	@Test
	public void testGetBrandDesignDTO() {
		BrandDesignDTO brandDesignDTO = saatchiService.getBrandDesignDTO();
		assertTrue(brandDesignDTO.getBrands().size() > 0);
	}

	@Test
	public void testGetBrandPhotographyDTO() {
		BrandPhotographyDTO brandPhotographyDTO = saatchiService.getBrandPhotographyDTO();
		assertTrue(brandPhotographyDTO.getBrands().size() > 0);
	}

	@Test
	@Ignore
	public void testGetBrandStrategyDTO() {
		BrandStrategyDTO brandStrategyDTO = saatchiService.getBrandStrategyDTO();
		assertNotNull(brandStrategyDTO);

	}

	@Test
	@Ignore
	public void testGetClientDTO() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetContactDTO() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetOfficeDTO() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetPartnerDTO() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testListAllBrandStrategies() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testListArticleItems() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testListBrandStrategiesByGroup() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testListBrandStrategyGroups() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testListHomePageItems() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testListNewsItems() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testListPhilosophyItems() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testListProductsByBrand() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testListViewItems() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testListAttitudeItems() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testListPurposeItems() {
		fail("Not yet implemented");
	}
}