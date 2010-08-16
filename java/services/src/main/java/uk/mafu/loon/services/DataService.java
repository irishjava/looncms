package uk.mafu.loon.services;

import uk.mafu.loon.domain.data.Data;
import uk.mafu.loon.domain.data.LoonAudio;
import uk.mafu.loon.domain.data.LoonImage;
import uk.mafu.loon.domain.data.LoonPdf;
import uk.mafu.loon.domain.data.LoonVideo;

public interface DataService {
	public LoonImage saveImage(LoonImage li);

	public LoonImage getImage(final int pk);

	public LoonVideo getVideoByVideoLinkPk(final int pk) throws BadLinkException;

	public LoonAudio getAudioByAudioLinkPk(final int pk) throws BadLinkException;

	public LoonImage getImageByImageLinkPk(final int pk) throws BadLinkException;

	public LoonPdf getPdfByPdfLinkPk(final int pk) throws BadLinkException;

	public LoonPdf getPdfByPk(final int pk) throws BadLinkException;

	public byte[] getData(int pk, Class<? extends Data> clazz);

	public LoonVideo getLoonVideo(Integer pk);

	public LoonAudio getLoonAudio(Integer pk);
}
