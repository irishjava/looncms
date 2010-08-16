package uk.gormley;

import java.util.List;

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
import uk.mafu.loon.domain.data.PdfLink;

public interface GormleyService {

	AbstractLinkItem fetchAbstractBase(final Object id);

	ArtGroup fetchArtGroup(Object pk);

	Config fetchConfig();

	GormleyDTO fetchGormley();

	List<ExhibitionDTO> fetchGroupExhibitionsDesc();

	List<ExhibitionDTO> fetchPublicExhibitionsDesc();

	// /**
	// * This method dhosuld return all ArtGroup items whose enumerated artType
	// * property matches the type parameter. Results must be sorted by date
	// DESC.
	// */
	// List<ArtGroup> listArtGroupsByTypeDesc(String type);

	Show fetchShow(Object pk);

	List<ExhibitionDTO> fetchSoloExhibitionsDesc();

	List<AbstractLinkItem> listAbstractBase();

	/**
	 * 
	 * @param type
	 *            can be one of drawing,sculpture .
	 * @return
	 */
	List<Art> listArtDesc(String type);

	List<ArtGroup> listArtGroupByArt(final Object artPk);

	List<ArtGroup> listArtGroupDesc();

	/**
	 * This method should list all ArtGroups which have a matching artType
	 * property as well as an optional matching artSubType.
	 * 
	 * If artSubType is null, this method should return all ArtGroups which have
	 * a matching artType.
	 * 
	 * @param artType
	 *            The type of the ArtGroups to return. Corresponds to the
	 *            artType enum on ArtGroup
	 * @param artSubType
	 *            The sub type of the ArtGroups to return. Corresponds to the
	 *            artSubType property on ArtGroup.
	 */
	List<ArtGroup> listArtGroupsByTypeDesc(String artType, String artSubType);

	/**
	 * 
	 * @param type
	 *            valid arguments are any of the following
	 *            sculpture-series,sculpture
	 *            -project,drawing-series,drawing-workbook,drawing-prints
	 * @return
	 */
	List<String> listArtGroupSubTypes(final String type);

	List<AudioResource> listAudioResourcesDesc();

	List<NewsItem> listCurrentNewsDesc();

	/**
	 * Return all shows where (startDate < todays date < endDate). Do not
	 * include Shows marked permanent. Results returned sorted by date
	 * descending.
	 */
	List<Show> listCurrentShowsDesc();

	/**
	 * Return all shows where (todays date < startDate). Do not include Shows
	 * marked permanent. Results returned sorted by date descending.
	 */
	List<Show> listFutureShowsDesc();
	
	/**
	 * Return all shows where (todays date < startDate). Do not include Shows
	 * marked permanent. Results returned sorted by date ascending.
	 */
	List<Show> listFutureShowsAsc();
	
	

	List<Catalogue> listGroupCataloguesDesc();

	List<ImageResource> listImageResourcesDesc();

	List<NewsItem> listNewsDesc();

	/**
	 * Return all shows where (todays date > endDate && type=='group'). Do not
	 * include Shows marked permanent. Results returned sorted by date
	 * descending.
	 */

	List<Show> listPastGroupShowsDesc();

	/**
	 * Return all shows where (todays date > endDate). Do not include Shows
	 * marked permanent. Results returned sorted by date descending.
	 */
	List<Show> listPastShowsDesc();

	/**
	 * Return all shows where (todays date > endDate && type=='solo'). Do not
	 * include Shows marked permanent. Results returned sorted by date
	 * descending.
	 */
	List<Show> listPastSoloShowsDesc();

	/**
	 * Return all shows where (permanent == true). Results returned sorted by
	 * endDate descending.
	 */
	List<Show> listPermanentShowsDesc();

	List<PressItem> listPressArchivesDesc(final Integer start, final Integer end);

	/*
	 * Return all PressItems sorted by date descending.
	 */
	List<PressItem> listPressItemsDesc();

	/**
	 * Return all shows. Results returned sorted by endDate descending.
	 */
	List<Show> listShowsDesc();

	List<Slide> listSlides();

    PdfLink fetchGroupcatpdf1() ;
       PdfLink fetchGroupcatpdf2() ;
       PdfLink fetchGroupcatpdf3() ;
       PdfLink fetchGroupexpdf1() ;
       PdfLink fetchGroupexpdf2() ;
       PdfLink fetchGroupexpdf3() ;
       PdfLink fetchPublicexpdf1() ;
       PdfLink fetchPublicexpdf2() ;
       PdfLink fetchPublicexpdf3() ;
       PdfLink fetchSolocatpdf1() ;
       PdfLink fetchSolocatpdf2() ;
       PdfLink fetchSolocatpdf3() ;
       PdfLink fetchSoloexpdf1() ;
       PdfLink fetchSoloexpdf2() ;
       PdfLink fetchSoloexpdf3() ;



	/**
	 * from Catalogue as c where c.type = 'solo' order by c.date desc
	 */
	List<Catalogue> listSoloCataloguesDesc();

	List<StudioView> listStudioViewsDesc();

	List<TextResource> listTextResourceDesc(final String filter);

	List<String> listTextResourceFilters();

	List<TextResource> listTextResourcesDesc(final String filter);

	List<VideoResource> listVideoResourcesDesc();

	List<? extends Object> search(String search);
}
