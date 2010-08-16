package uk.mafu.loon.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CreateCommand implements Serializable {
	private static final long serialVersionUID = 1L;
	List<Step> steps = new ArrayList<Step>();
}
