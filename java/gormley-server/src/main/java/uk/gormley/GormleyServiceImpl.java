package uk.gormley;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.io.IOUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.joda.time.DateTime;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.util.Assert;

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
import uk.gormley.domain.Website;
import uk.gormley.dto.ExhibitionDTO;
import uk.gormley.dto.GormleyDTO;
import uk.mafu.loon.domain.data.PdfLink;
import uk.mafu.loon.util.ParseUtil;

public class GormleyServiceImpl extends JpaDaoSupport implements GormleyService {

    private Config config;

    /**
     * Bryan, verified
     */
    public AbstractLinkItem fetchAbstractBase(final Object id) {
        return (AbstractLinkItem) getJpaTemplate().execute(new JpaCallback() {
            public AbstractLinkItem doInJpa(EntityManager em)
                    throws PersistenceException {
                AbstractLinkItem find = em.find(AbstractLinkItem.class, id);
                em.clear();
                return find;
            }
        });
    }

    /**
     * Bryan, verified
     */

    @SuppressWarnings("unchecked")
    public List<AbstractLinkItem> listAbstractBase() {
        return (List<AbstractLinkItem>) getJpaTemplate().execute(
                new JpaCallback() {

                    public List<AbstractLinkItem> doInJpa(EntityManager em)
                            throws PersistenceException {
                        Query createQuery = em
                                .createQuery("from AbstractLinkItem");
                        List<AbstractLinkItem> ret = createQuery
                                .getResultList();
                        ret.size();
                        em.clear();
                        return ret;
                    }
                });
    }

    /**
     * Bryan, verified
     */

    public GormleyDTO fetchGormley() {
        Website website = getWebsite();
        GormleyDTO d = new GormleyDTO();
        d.title = website.getText1();
        d.caption = website.getText2();
        d.text = website.getText3();
        return d;
    }

    public List<ExhibitionDTO> fetchGroupExhibitionsDesc() {
        List<ExhibitionDTO> ret = new ArrayList<ExhibitionDTO>();
        String exhibitions = getDetatchedWebsite().getGroupExhibition();
        Map<Integer, List<String>> map = ParseUtil
                .yearMultiRowParse(exhibitions);
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            List<String> list = map.get(key);
            for (String string : list) {
                ExhibitionDTO dto = new ExhibitionDTO();
                dto.setYear(key);
                dto.setText(string);
                ret.add(dto);
            }
        }
        return ret;
    }

    @SuppressWarnings("unchecked")
    public List<ExhibitionDTO> fetchPublicExhibitionsDesc() {
        List<ExhibitionDTO> ret = new ArrayList<ExhibitionDTO>();
        String exhibitions = getDetatchedWebsite().getPublicExhibition();
        if (exhibitions == null) {
            return ret;
        }
        try {
            List<String> readLines = (List<String>) IOUtils
                    .readLines(new StringReader(exhibitions));
            for (String string : readLines) {
                ExhibitionDTO dto = new ExhibitionDTO();
                dto.setText(string);
                ret.add(dto);
            }
            return ret;
        } catch (IOException e) {
            throw new UnsupportedOperationException(e);
        }

    }

    public List<ExhibitionDTO> fetchSoloExhibitionsDesc() {
        List<ExhibitionDTO> ret = new ArrayList<ExhibitionDTO>();
        String groupExhibition = getDetatchedWebsite().getSoloExhibition();
        Map<Integer, List<String>> map = ParseUtil
                .yearMultiRowParse(groupExhibition);
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            List<String> list = map.get(key);
            for (String string : list) {
                ExhibitionDTO dto = new ExhibitionDTO();
                dto.setYear(key);
                dto.setText(string);
                ret.add(dto);
            }
        }
        return ret;
    }

    public Config getConfig() {
        return config;
    }

    protected Website getWebsite() {
        return (Website) getJpaTemplate().execute(new JpaCallback() {
            @SuppressWarnings("unchecked")
            public Website doInJpa(EntityManager em)
                    throws PersistenceException {
                List<Website> list = em.createQuery("from Website")
                        .getResultList();
                Assert.isTrue(list.size() == 1,
                        "One and only one website should exist in the CMS ");
                return list.get(0);
            }
        });
    }

    protected Website getDetatchedWebsite() {
        return (Website) getJpaTemplate().execute(new JpaCallback() {
            @SuppressWarnings("unchecked")
            public Website doInJpa(EntityManager em)
                    throws PersistenceException {
                List<Website> list = em.createQuery("from Website")
                        .getResultList();
                Assert.isTrue(list.size() == 1,
                        "One and only one website should exist in the CMS ");
                em.clear();
                return list.get(0);
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<NewsItem> listNewsDesc() {
        return (List<NewsItem>) getJpaTemplate().execute(new JpaCallback() {
            public List<NewsItem> doInJpa(EntityManager em)
                    throws PersistenceException {
                List<NewsItem> list = em.createQuery(
                        "from NewsItem n order by n.date desc").getResultList();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<Art> listArtDesc(final String type) {
        return (List<Art>) getJpaTemplate().execute(new JpaCallback() {
            public List<Art> doInJpa(EntityManager em)
                    throws PersistenceException {
                Query query = em
                        .createQuery("from Art as a where a.type =? order by a.date desc");
                query.setParameter(1, type);
                List<Art> list = query.getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<ArtGroup> listArtGroupByArt(final Object artPk) {
        return (List<ArtGroup>) getJpaTemplate().execute(new JpaCallback() {
            public List<ArtGroup> doInJpa(EntityManager em)
                    throws PersistenceException {
                List<ArtGroup> list = em.createQuery(
                        "from ArtGroup ag order by ag.start desc ")
                        .getResultList();
                List<ArtGroup> ret = new ArrayList<ArtGroup>();
                list.size();
                outer:
                for (ArtGroup group : list) {
                    for (Art art : group.getArts()) {
                        if (art.getPk().equals(artPk)) {
                            ret.add(group);
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
    public List<ArtGroup> listArtGroupDesc() {
        return (List<ArtGroup>) getJpaTemplate().execute(new JpaCallback() {
            public List<ArtGroup> doInJpa(EntityManager em)
                    throws PersistenceException {
                List<ArtGroup> list = em.createQuery(
                        "from ArtGroup as a order by a.start desc")
                        .getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<String> listArtGroupSubTypes(final String type) {
        return (List<String>) getJpaTemplate().execute(new JpaCallback() {
            public List<String> doInJpa(EntityManager em)
                    throws PersistenceException {
                Query query = em
                        .createQuery("SELECT a.artSubType from ArtGroup as a where a.artType = ? GROUP BY a.artSubType ");
                query.setParameter(1, type);
                List<String> list = query.getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<AudioResource> listAudioResourcesDesc() {
        return (List<AudioResource>) getJpaTemplate().execute(
                new JpaCallback() {
                    public List<AudioResource> doInJpa(EntityManager em)
                            throws PersistenceException {
                        List<AudioResource> list = em
                                .createQuery(
                                        "from AudioResource as a order by a.date desc ")
                                .getResultList();
                        list.size();
                        em.clear();
                        return list;
                    }
                });
    }

    @SuppressWarnings("unchecked")
    public List<NewsItem> listCurrentNewsDesc() {
        return (List<NewsItem>) getJpaTemplate().execute(new JpaCallback() {
            public List<NewsItem> doInJpa(EntityManager em)
                    throws PersistenceException {
                List<NewsItem> list = em.createQuery(
                        "from NewsItem as n order by n.date desc ")
                        .getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<Show> listCurrentShowsDesc() {
        return (List<Show>) getJpaTemplate().execute(new JpaCallback() {
            public List<Show> doInJpa(EntityManager em)
                    throws PersistenceException {
                Query query = em.createQuery("from Show as s "
                        + "where s.startDate < :now " + "and :now < s.endDate "
                        + "and s.permanent = false "
                        + "order by s.startDate desc ");
                query.setParameter("now", new Date());
                List<Show> list = query.getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<Show> listFutureShowsDesc() {
        return (List<Show>) getJpaTemplate().execute(new JpaCallback() {
            public List<Show> doInJpa(EntityManager em)
                    throws PersistenceException {
                Query q = em.createQuery("from Show as s "
                        + "where s.startDate > :now "
                        + "and s.permanent = false "
                        + "order by s.endDate desc");
                q.setParameter("now", new Date());
                List<Show> list = q.getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    /**
     * Return all shows where (todays date < startDate). Do not include Shows
     * marked permanent. Results returned sorted by date ascending.
     */
    @SuppressWarnings("unchecked")
    public List<Show> listFutureShowsAsc() {
        return (List<Show>) getJpaTemplate().execute(new JpaCallback() {
            public List<Show> doInJpa(EntityManager em)
                    throws PersistenceException {
                Query q = em
                        .createQuery("from Show as s "
                                + "where s.startDate > :now "
                                + "and s.permanent = false "
                                + "order by s.endDate asc");
                q.setParameter("now", new Date());
                List<Show> list = q.getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<Catalogue> listGroupCataloguesDesc() {
        return (List<Catalogue>) getJpaTemplate().execute(new JpaCallback() {
            public List<Catalogue> doInJpa(EntityManager em)
                    throws PersistenceException {
                List<Catalogue> list = em
                        .createQuery(
                                "from Catalogue as c where c.type = 'group' order by c.date desc ")
                        .getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<ImageResource> listImageResourcesDesc() {
        return (List<ImageResource>) getJpaTemplate().execute(
                new JpaCallback() {
                    public List<ImageResource> doInJpa(EntityManager em)
                            throws PersistenceException {
                        List<ImageResource> list = em.createQuery(
                                "from ImageResource as i order by i.date desc")
                                .getResultList();
                        list.size();
                        em.clear();
                        return list;
                    }
                });
    }

    @SuppressWarnings("unchecked")
    public List<Show> listPastGroupShowsDesc() {
        return (List<Show>) getJpaTemplate().execute(new JpaCallback() {
            public List<Show> doInJpa(EntityManager em)
                    throws PersistenceException {
                Query query = em.createQuery("from Show as s "
                        + "where s.type = 'group' " + "and s.endDate < :now "
                        + "and s.permanent = false "
                        + "order by s.endDate desc ");
                query.setParameter("now", new Date());
                List<Show> list = query.getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    /**
     * Return all shows where (todays date > endDate). Do not include Shows
     * marked permanent. Results returned sorted by date descending.
     */

    @SuppressWarnings("unchecked")
    public List<Show> listPastShowsDesc() {
        return (List<Show>) getJpaTemplate().execute(new JpaCallback() {
            public List<Show> doInJpa(EntityManager em)
                    throws PersistenceException {
                Query query = em.createQuery("from Show as s "
                        + "where s.endDate < :now "
                        + "and s.permanent = false "
                        + "order by s.endDate desc ");
                query.setParameter("now", new Date());
                List<Show> list = query.getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    /**
     * Return all shows where (todays date > endDate && type=='solo'). Do not
     * include Shows marked permanent. Results returned sorted by date
     * descending.
     */

    @SuppressWarnings("unchecked")
    public List<Show> listPastSoloShowsDesc() {
        return (List<Show>) getJpaTemplate().execute(new JpaCallback() {
            public List<Show> doInJpa(EntityManager em)
                    throws PersistenceException {
                Query query = em.createQuery("from Show as s "
                        + "where s.type = 'solo' " + "and s.endDate < :now "
                        + "and s.permanent = false "
                        + "order by s.endDate desc ");
                query.setParameter("now", new Date());
                List<Show> list = query.getResultList();
                list.size();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<Show> listPermanentShowsDesc() {
        return (List<Show>) getJpaTemplate().execute(new JpaCallback() {
            public List<Show> doInJpa(EntityManager em)
                    throws PersistenceException {
                List<Show> list = em.createQuery(
                        "from Show as s " + "where s.permanent = true "
                                + "order by s.endDate desc ").getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<PressItem> listPressArchivesDesc(final Integer start,
                                                 final Integer end) {
        return (List<PressItem>) getJpaTemplate().execute(new JpaCallback() {
            public List<PressItem> doInJpa(EntityManager em)
                    throws PersistenceException {
                Query q = em.createQuery("from PressItem p "
                        + "where p.date > :start and p.date < :end "
                        + "order by p.date desc");
                Date startDate = new DateTime(start, 1, 1, 1, 1, 1, 1).toDate();
                Date endDate = new DateTime(end, 1, 1, 1, 1, 1, 1).toDate();
                q.setParameter("start", startDate);
                q.setParameter("end", endDate);
                List<PressItem> list = q.getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<Show> listShowsDesc() {
        return (List<Show>) getJpaTemplate().execute(new JpaCallback() {
            public List<Show> doInJpa(EntityManager em)
                    throws PersistenceException {
                List<Show> list = em.createQuery(
                        "from Show as s " + "order by s.endDate desc  ")
                        .getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<Slide> listSlides() {
        return (List<Slide>) getJpaTemplate().execute(new JpaCallback() {
            public List<Slide> doInJpa(EntityManager em)
                    throws PersistenceException {
                List<Slide> list = getWebsite().getSlides();
                // em.createQuery("from Slide ").getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<Catalogue> listSoloCataloguesDesc() {
        return (List<Catalogue>) getJpaTemplate().execute(new JpaCallback() {
            public List<Catalogue> doInJpa(EntityManager em)
                    throws PersistenceException {
                List<Catalogue> list = em.createQuery(
                        "from Catalogue as c " + "where c.type = 'solo' "
                                + "order by c.date desc").getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<StudioView> listStudioViewsDesc() {
        return (List<StudioView>) getJpaTemplate().execute(new JpaCallback() {
            public List<StudioView> doInJpa(EntityManager em)
                    throws PersistenceException {
                List<StudioView> list = em.createQuery(
                        "from StudioView as s " + "order by s.date desc")
                        .getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<TextResource> listTextResourcesDesc(final String filter) {
        return (List<TextResource>) getJpaTemplate().execute(new JpaCallback() {
            public List<TextResource> doInJpa(EntityManager em)
                    throws PersistenceException {
                Query q = em.createQuery(" from TextResource as t "
                        + " where t.filter = :filter"
                        + " order by t.date desc ");
                q.setParameter("filter", filter);
                List<TextResource> list = q.getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<TextResource> listTextResourceDesc(final String filter) {
        return (List<TextResource>) getJpaTemplate().execute(new JpaCallback() {
            public List<TextResource> doInJpa(EntityManager em)
                    throws PersistenceException {
                Query query = em.createQuery("from TextResource as t "
                        + "where t.filter = ? " + "order by t.date desc");
                query.setParameter(1, filter);
                List<TextResource> list = query.getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<String> listTextResourceFilters() {
        return (List<String>) getJpaTemplate().execute(new JpaCallback() {
            public List<String> doInJpa(EntityManager em)
                    throws PersistenceException {
                List<String> list = em
                        .createQuery(
                                "SELECT t.filter from TextResource as t GROUP BY t.filter")
                        .getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<VideoResource> listVideoResourcesDesc() {
        return (List<VideoResource>) getJpaTemplate().execute(
                new JpaCallback() {
                    public List<VideoResource> doInJpa(EntityManager em)
                            throws PersistenceException {
                        List<VideoResource> list = em.createQuery(
                                "from VideoResource as v "
                                        + "order by v.date desc ")
                                .getResultList();
                        list.size();
                        em.clear();
                        return list;
                    }
                });
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public ArtGroup fetchArtGroup(final Object pk) {
        return (ArtGroup) getJpaTemplate().execute(new JpaCallback() {
            public ArtGroup doInJpa(EntityManager em)
                    throws PersistenceException {
                ArtGroup find = em.find(ArtGroup.class, pk);
                em.clear();
                return find;
            }
        });
    }

    public Config fetchConfig() {
        return config;
    }

    public Show fetchShow(final Object pk) {
        return (Show) getJpaTemplate().execute(new JpaCallback() {
            public Show doInJpa(EntityManager em) throws PersistenceException {
                Show show = em.find(Show.class, pk);
                em.clear();
                return show;
            }
        });
    }

    /**
     * Bryan,verified
     */
    @SuppressWarnings("unchecked")
    public List<? extends Object> search(final String querytext) {
        return (List<? extends Object>) getJpaTemplate().execute(
                new JpaCallback() {
                    public List<? extends Object> doInJpa(EntityManager em)
                            throws PersistenceException {
                        try {
                            FullTextEntityManager google = Search
                                    .getFullTextEntityManager(em);
                            // Fields in the domain model upon which we will
                            // search...
                            String[] fields = new String[]{"author",
                                    "caption", "contributors", "publication",
                                    "publisher", "text", "title"};
                            MultiFieldQueryParser parser = new MultiFieldQueryParser(
                                    fields, new StandardAnalyzer());

                            org.apache.lucene.search.Query query;
                            query = parser.parse(querytext);
                            // Query query = qp.parse(querytext);
                            FullTextQuery createFullTextQuery = google
                                    .createFullTextQuery(query, Object.class);
                            Object r = createFullTextQuery.getResultList();
                            List<Object> result = (List) r;
                            List<Object> resultclean = new ArrayList<Object>();

                            Hibernate.initialize(result);
                            for (Object object : result) {
                                if (HibernateProxy.class
                                        .isAssignableFrom(object.getClass())) {
                                    resultclean.add(((HibernateProxy) object)
                                            .getHibernateLazyInitializer()
                                            .getImplementation());
                                } else {
                                    resultclean.add(object);
                                }
                            }
                            em.clear();
                            for (Object object : resultclean) {
                                // Remove any unwanted fields (those missing the
                                // @RetainInSearch annotation).
                                StripperUtil.strip(object);
                            }
                            return resultclean;
                        } catch (ParseException e) {
                            e.printStackTrace();
                            throw new UnsupportedOperationException(e);
                        }
                    }
                });
    }

    @SuppressWarnings("unchecked")
    public List<PressItem> listPressItemsDesc() {
        return (List<PressItem>) getJpaTemplate().execute(new JpaCallback() {
            public List<PressItem> doInJpa(EntityManager em)
                    throws PersistenceException {
                Query q = em.createQuery("from PressItem p "
                        + " order by p.date desc");
                List<PressItem> list = q.getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    /**
     * This method should list all ArtGroups which have a matching artType
     * property as well as an optional matching artSubType.
     * <p/>
     * If artSubType is null, this method should return all ArtGroups which have
     * a matching artType.
     *
     * @param artType    The type of the ArtGroups to return. Corresponds to the
     *                   artType enum on ArtGroup
     * @param artSubType The sub type of the ArtGroups to return. Corresponds to the
     *                   artSubType property on ArtGroup.
     */
    @SuppressWarnings("unchecked")
    public List<ArtGroup> listArtGroupsByTypeDesc(final String artType,
                                                  final String artSubType) {
        return (List<ArtGroup>) getJpaTemplate().execute(new JpaCallback() {
            public List<ArtGroup> doInJpa(EntityManager em)
                    throws PersistenceException {
                Query query = null;

                if (artSubType != null) {
                    query = em
                            .createQuery("from ArtGroup as g "
                                    + "where g.artType = :type and g.artSubType = :subType "
                                    + "order by g.start desc");
                    query.setParameter("type", artType);
                    query.setParameter("subType", artSubType);
                } else {
                    query = em
                            .createQuery("from ArtGroup as g "
                                    + "where g.type = :type "
                                    + "order by g.start desc");
                    query.setParameter("type", artType);
                }
                List<ArtGroup> list = query.getResultList();
                list.size();
                em.clear();
                return list;
            }
        });
    }

    public PdfLink fetchGroupcatpdf1() {
        return (PdfLink) getJpaTemplate().execute(new JpaCallback() {
            public PdfLink doInJpa(EntityManager em) {
                PdfLink ret = getWebsite().getGroupcatpdf1();
                em.clear();
                return ret;
            }
        });
    }

    public PdfLink fetchGroupcatpdf2() {
        return (PdfLink) getJpaTemplate().execute(new JpaCallback() {
            public PdfLink doInJpa(EntityManager em) {
                PdfLink ret = getWebsite().getGroupcatpdf2();
                em.clear();
                return ret;
            }
        });
    }

    public PdfLink fetchGroupcatpdf3() {
        return (PdfLink) getJpaTemplate().execute(new JpaCallback() {
            public PdfLink doInJpa(EntityManager em) {
                PdfLink ret = getWebsite().getGroupcatpdf3();
                em.clear();
                return ret;
            }
        });
    }

    public PdfLink fetchGroupexpdf1() {
        return (PdfLink) getJpaTemplate().execute(new JpaCallback() {
            public PdfLink doInJpa(EntityManager em) {
                PdfLink ret = getWebsite().getGroupexpdf1();
                em.clear();
                return ret;
            }
        });
    }

    public PdfLink fetchGroupexpdf2() {
        return (PdfLink) getJpaTemplate().execute(new JpaCallback() {
            public PdfLink doInJpa(EntityManager em) {
                PdfLink ret = getWebsite().getGroupexpdf2();
                em.clear();
                return ret;
            }
        });
    }

    public PdfLink fetchGroupexpdf3() {
        return (PdfLink) getJpaTemplate().execute(new JpaCallback() {
            public PdfLink doInJpa(EntityManager em) {
                PdfLink ret = getWebsite().getGroupexpdf3();
                em.clear();
                return ret;
            }
        });
    }

    public PdfLink fetchPublicexpdf1() {
        return (PdfLink) getJpaTemplate().execute(new JpaCallback() {
            public PdfLink doInJpa(EntityManager em) {
                PdfLink ret = getWebsite().getPublicexpdf1();
                em.clear();
                return ret;
            }
        });
    }

    public PdfLink fetchPublicexpdf2() {
        return (PdfLink) getJpaTemplate().execute(new JpaCallback() {
            public PdfLink doInJpa(EntityManager em) {
                PdfLink ret = getWebsite().getPublicexpdf2();
                em.clear();
                return ret;
            }
        });
    }

    public PdfLink fetchPublicexpdf3() {
        return (PdfLink) getJpaTemplate().execute(new JpaCallback() {
            public PdfLink doInJpa(EntityManager em) {
                PdfLink ret = getWebsite().getPublicexpdf3();
                em.clear();
                return ret;
            }
        });
    }

    public PdfLink fetchSolocatpdf1() {
        return (PdfLink) getJpaTemplate().execute(new JpaCallback() {
            public PdfLink doInJpa(EntityManager em) {
                PdfLink ret = getWebsite().getSolocatpdf1();
                em.clear();
                return ret;
            }
        });
    }

    public PdfLink fetchSolocatpdf2() {
        return (PdfLink) getJpaTemplate().execute(new JpaCallback() {
            public PdfLink doInJpa(EntityManager em) {
                PdfLink ret = getWebsite().getSolocatpdf2();
                em.clear();
                return ret;
            }
        });
    }

    public PdfLink fetchSolocatpdf3() {
        return (PdfLink) getJpaTemplate().execute(new JpaCallback() {
            public PdfLink doInJpa(EntityManager em) {
                PdfLink ret = getWebsite().getSolocatpdf3();
                em.clear();
                return ret;
            }
        });
    }

    public PdfLink fetchSoloexpdf1() {
        return (PdfLink) getJpaTemplate().execute(new JpaCallback() {
            public PdfLink doInJpa(EntityManager em) {
                PdfLink ret = getWebsite().getSoloexpdf1();
                em.clear();
                return ret;
            }
        });
    }

    public PdfLink fetchSoloexpdf2() {
        return (PdfLink) getJpaTemplate().execute(new JpaCallback() {
            public PdfLink doInJpa(EntityManager em) {
                PdfLink ret = getWebsite().getSoloexpdf2();
                em.clear();
                return ret;
            }
        });
    }

    public PdfLink fetchSoloexpdf3() {
        return (PdfLink) getJpaTemplate().execute(new JpaCallback() {
            public PdfLink doInJpa(EntityManager em) {
                PdfLink ret = getWebsite().getSoloexpdf3();
                em.clear();
                return ret;
            }
        });
    }
}