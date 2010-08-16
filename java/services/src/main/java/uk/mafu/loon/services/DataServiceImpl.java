package uk.mafu.loon.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import uk.mafu.loon.domain.data.AudioLink;
import uk.mafu.loon.domain.data.Data;
import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.LoonAudio;
import uk.mafu.loon.domain.data.LoonImage;
import uk.mafu.loon.domain.data.LoonPdf;
import uk.mafu.loon.domain.data.LoonVideo;
import uk.mafu.loon.domain.data.PdfLink;
import uk.mafu.loon.domain.data.VideoLink;

public class DataServiceImpl extends JpaDaoSupport implements DataService {
	public byte[] getData(final int pk, final Class<? extends Data> clazz) {
		return (byte[]) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em) throws PersistenceException {
				Data data = em.find(clazz, pk);
				if (data == null) {
					throw new UnsupportedOperationException("can't load data with id='" + pk + "'");
				}
				byte[] ret = data.getData();
				em.clear();
				return ret;
			}
		});
	}

	public LoonImage getImage(final int pk) {
		return (LoonImage) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em) throws PersistenceException {
				LoonImage loonImage = em.find(LoonImage.class, pk);
				em.clear();
				return loonImage;
			}
		});
	}

	public LoonImage saveImage(final LoonImage loonImage) {
		return (LoonImage) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em) throws PersistenceException {
				em.persist(loonImage);
				em.clear();
				em.flush();
				return loonImage;
			}
		});
	}

	public LoonImage getImageByImageLinkPk(final int pk) throws BadLinkException {
		return (LoonImage) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em) throws PersistenceException {
				ImageLink link = em.find(ImageLink.class, pk);
				if (link == null) {
					throw new BadLinkException("Bad image link pk=" + pk);
				}
				LoonImage loonImage = em.find(LoonImage.class, link.getImageId());
				if (loonImage == null) {
					throw new BadLinkException("Bad image link pk=" + link.getPk()
							+ " references missing loonimage with imageid=" + link.getImageId());
				}
				em.clear();
				return loonImage;
			}
		});
	}

	public LoonVideo getVideoByVideoLinkPk(final int pk) throws BadLinkException {
		return (LoonVideo) getJpaTemplate().execute(new JpaCallback() {
			public LoonVideo doInJpa(final EntityManager em) throws PersistenceException {
				VideoLink link = em.find(VideoLink.class, pk);
				if (link == null) {
					throw new BadLinkException("Bad video link pk=" + pk);
				}
				LoonVideo loonImage = em.find(LoonVideo.class, link.getVideoId());
				if (loonImage == null) {
					throw new BadLinkException("Bad video link  pk=" + link.getPk()
							+ " references missing loonVideo with videoId=" + link.getVideoId());
				}
				em.clear();
				return loonImage;
			}
		});
	}
	
	public LoonAudio getAudioByAudioLinkPk(final int pk) throws BadLinkException {
		return (LoonAudio) getJpaTemplate().execute(new JpaCallback() {
			public LoonAudio doInJpa(final EntityManager em) throws PersistenceException {
				AudioLink link = em.find(AudioLink.class, pk);
				if (link == null) {
					throw new BadLinkException("Bad video link pk=" + pk);
				}
				LoonAudio loonImage = em.find(LoonAudio.class, link.getAudioId());
				if (loonImage == null) {
					throw new BadLinkException("Bad video link  pk=" + link.getPk()
							+ " references missing loonVideo with videoId=" + link.getAudioId());
				}
				em.clear();
				return loonImage;
			}
		});
	}

	public LoonVideo getLoonVideo(final Integer pk) {
		return (LoonVideo) getJpaTemplate().execute(new JpaCallback() {
			public LoonVideo doInJpa(final EntityManager em) throws PersistenceException {
				LoonVideo loonImage = em.find(LoonVideo.class, pk);
				em.clear();
				return loonImage;
			}
		});
	}
	
	public LoonAudio getLoonAudio(final Integer pk) {
		return (LoonAudio) getJpaTemplate().execute(new JpaCallback() {
			public LoonAudio doInJpa(final EntityManager em) throws PersistenceException {
				LoonAudio loonImage = em.find(LoonAudio.class, pk);
				em.clear();
				return loonImage;
			}
		});
	}

	public LoonPdf getPdfByPdfLinkPk(final int pk) throws BadLinkException {
		return (LoonPdf) getJpaTemplate().execute(new JpaCallback() {
			public LoonPdf doInJpa(final EntityManager em) throws PersistenceException {
				PdfLink link = em.find(PdfLink.class, pk);
				if (link == null) {
					throw new BadLinkException("Bad pdf link pk=" + pk);
				}
				LoonPdf loonImage = em.find(LoonPdf.class, link.getPdfId());
				if (loonImage == null) {
					throw new BadLinkException("Bad pdf link  pk=" + link.getPk()
							+ " references missing loonPdf with pdfId=" + link.getPdfId());
				}
				em.clear();
				return loonImage;
			}
		});
	}

	public LoonPdf getPdfByPk(final int pk) throws BadLinkException {
		return (LoonPdf) getJpaTemplate().execute(new JpaCallback() {
			public LoonPdf doInJpa(final EntityManager em) throws PersistenceException {
				LoonPdf pdf = em.find(LoonPdf.class, pk);
				if (pdf == null) {
					throw new BadLinkException("Bad pdf link pk=" + pk);
				}
				em.clear();
				return pdf;
			}
		});
	}
}