package uk.mafu.kwa.front;

import java.io.Serializable;

import uk.mafu.kwa.domain.Excite;
import uk.mafu.loon.domain.data.ImageLink;

public class MonographDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Excite excite; 
	public ImageLink image; 
	public String text ; 
	
}
