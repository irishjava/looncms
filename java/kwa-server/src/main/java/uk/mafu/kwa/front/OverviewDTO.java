package uk.mafu.kwa.front;

import java.io.Serializable;

import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.VideoLink;

public class OverviewDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImageLink overviewImage;
	public String overviewText;
	public VideoLink overviewVideo;

}
