package uk.gormley;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import uk.gormley.domain.AbstractLinkItem;
import uk.gormley.domain.Art;
import uk.gormley.domain.ArtGroup;
import uk.gormley.domain.AudioResource;
import uk.gormley.domain.Catalogue;
import uk.gormley.domain.ImageResource;
import uk.gormley.domain.NewsItem;
import uk.gormley.domain.PressItem;
import uk.gormley.domain.Show;
import uk.gormley.domain.Slide;
import uk.gormley.domain.StudioView;
import uk.gormley.domain.TextResource;
import uk.gormley.domain.VideoResource;
import uk.gormley.dto.ExhibitionDTO;
import uk.gormley.dto.GormleyDTO;

public abstract class AbstractGormeyIntegrationTest {

	public abstract GormleyService getService();

	@Test
	public void testListPressItemsDesc() {
		List<PressItem> l = getService().listPressItemsDesc();
		assertTrue(l.size() > 0);
	}

	@Test
	public void testFetchAbstractBase() throws SQLException {
		List<AbstractLinkItem> l = getService().listAbstractBase();
		assertTrue(l.size() > 3);
		for (AbstractLinkItem a : l) {
			assertNotNull(getService().fetchAbstractBase(a.getPk()));
			break;
		}
	}

	@Test
	public void testSearchForBanana() {
		List<? extends Object> search = getService().search("banana");
		assertNotNull(search);
		assertTrue(search.size() > 0);
	}

	@Test
	public void testSearchForBaltic() {
		List<? extends Object> search = getService().search("baltic");
		assertNotNull(search);
		assertTrue(search.size() > 0);
	}
	
	
	@Test
	public void testSearchForTest() {
		List<? extends Object> search = getService().search("test");
		assertNotNull(search);
		assertTrue(search.size() > 1);
	}
	
	@Test
	public void testSearchForShow() {
		List<? extends Object> search = getService().search("GORMLEY");
		assertNotNull(search);
		assertTrue(search.size() > 1);
	}
	
	

	@Test
	public void testFetchGormley() {
		GormleyDTO fetchGormley = getService().fetchGormley();
		assertNotNull(fetchGormley.caption);
		assertNotNull(fetchGormley.text);
		assertNotNull(fetchGormley.title);
	}

	@Test
	public void testFetchGroupExhibitionsDesc() {
		List<ExhibitionDTO> l = getService().fetchGroupExhibitionsDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);

	}

	@Test
	public void testFetchPublicExhibitionsDesc() {
		List<ExhibitionDTO> l = getService().fetchPublicExhibitionsDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testFetchSoloExhibitionsDesc() {
		List<ExhibitionDTO> l = getService().fetchSoloExhibitionsDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListNewsDesc() {
		List<NewsItem> l = getService().listNewsDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListArtDesc() {
		List<Art> l1 = getService().listArtDesc("drawing");
		List<Art> l2 = getService().listArtDesc("sculpture");
		assertTrue(l1.size() > 0 || l2.size() > 0);
	}

	@Test
	public void testListArtGroupByArt() {
		List<Art> arts = getService().listArtDesc("drawing");
		arts.addAll(getService().listArtDesc("sculpture"));
		boolean found = false;
		for (Art art : arts) {
			if (getService().listArtGroupByArt(art.getPk()).size() > 0) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	@Test
	public void testListArtGroupDesc() {
		List<ArtGroup> l = getService().listArtGroupDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	// @Test
	// public void testListArtGroupByTypeDesc() {
	// List<ArtGroup> l1 =
	// getService().listArtGroupsByTypeDesc("sculpture-series");
	// List<ArtGroup> l2 =
	// getService().listArtGroupsByTypeDesc("sculpture-project");
	// List<ArtGroup> l3 =
	// getService().listArtGroupsByTypeDesc("drawing-series");
	// List<ArtGroup> l4 =
	// getService().listArtGroupsByTypeDesc("drawing-workbook");
	// List<ArtGroup> l5 =
	// getService().listArtGroupsByTypeDesc("drawing-prints");
	// assertTrue(l1.size() > 0 || l2.size() > 0 || l3.size() > 0
	// || l4.size() > 0 || l5.size() > 0);
	//
	// }

	@Test
	public void testListArtGroupByTypeAndSubTypeDesc() {
		getService().listArtGroupsByTypeDesc(
				"sculpture-series", "test");
		getService().listArtGroupsByTypeDesc(
				"sculpture-project", "test");
		getService().listArtGroupsByTypeDesc(
				"drawing-series", "test");
		getService().listArtGroupsByTypeDesc(
				"drawing-workbook", "test");
		getService().listArtGroupsByTypeDesc(
				"drawing-prints", "test");
	}

	@Test
	public void testListArtGroupSubTypes() {

		List<String> l1 = getService().listArtGroupSubTypes("sculpture-series");
		List<String> l2 = getService()
				.listArtGroupSubTypes("sculpture-project");
		List<String> l3 = getService().listArtGroupSubTypes("drawing-series");
		List<String> l4 = getService().listArtGroupSubTypes("drawing-workbook");
		List<String> l5 = getService().listArtGroupSubTypes("drawing-prints");

		assertTrue(l1.size() > 0 || l2.size() > 0 || l3.size() > 0
				|| l4.size() > 0 || l5.size() > 0);

	}

	@Test
	public void testListAudioResourcesDesc() {
		List<AudioResource> l = getService().listAudioResourcesDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListCurrentNewsDesc() {
		List<NewsItem> l = getService().listCurrentNewsDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListCurrentShowsDesc() {
		List<Show> l = getService().listCurrentShowsDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListFutureShowsDesc() {
		List<Show> l = getService().listFutureShowsDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListGroupCataloguesDesc() {
		List<Catalogue> l = getService().listGroupCataloguesDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListImageResourcesDesc() {
		List<ImageResource> l = getService().listImageResourcesDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListPastGroupShowsDesc() {
		List<Show> l = getService().listPastGroupShowsDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListPastShowsDesc() {
		List<Show> l = getService().listPastGroupShowsDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListPastSoloShowsDesc() {
		List<Show> l = getService().listPastSoloShowsDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListPermanentShowsDesc() {
		List<Show> l = getService().listPastGroupShowsDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListPressArchivesDesc() {
		List<PressItem> l = getService().listPressArchivesDesc(1999, 2030);
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListShowsDesc() {
		List<Show> l = getService().listShowsDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListSlides() {
		List<Slide> l = getService().listSlides();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListSoloCataloguesDesc() {
		List<Catalogue> l = getService().listSoloCataloguesDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListStudioViewsDesc() {
		List<StudioView> l = getService().listStudioViewsDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListTextResourceDesc() {
		List<TextResource> l1 = getService().listTextResourceDesc("essay");
		List<TextResource> l2 = getService().listTextResourceDesc("interview");
		assertTrue(l1.size() > 0 || l2.size() > 0);
		;
	}

	@Test
	public void testListTextResourceFilters() {
		List<String> l = getService().listTextResourceFilters();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}

	@Test
	public void testListVideoResourcesDesc() {
		List<VideoResource> l = getService().listVideoResourcesDesc();
		assertNotNull(l);
		assertTrue(l.size() > 0);
	}
}
