package uk.mafu.kwa.front;

import java.io.Serializable;

import uk.mafu.kwa.domain.Excite;
import uk.mafu.loon.domain.data.ImageLink;

public class EthosDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Excite ethosExcite;
	public ImageLink ethosImage;
	public String ethosText;

}
