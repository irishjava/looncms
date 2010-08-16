package uk.mafu.domain.altoon.dto;

import java.io.Serializable;
import uk.mafu.domain.altoon.Award;
import uk.mafu.domain.altoon.Location;

public class AwardDTO implements Serializable, Comparable<AwardDTO> {
	private static final long serialVersionUID = 1L;
	public Award award;
	public Location location;

	public int compareTo(AwardDTO o) {
		return o.award.getDate().compareTo(this.award.getDate());
	}
}