package uk.mafu.loon.dto;

import java.io.Serializable;
import java.util.List;

public class OneToManyResult implements Serializable {
	private static final long serialVersionUID = 1L;
	public List<Object> data;
	private Object parentPk;
	private String relationship;

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public Object getParentPk() {
		return parentPk;
	}

	public void setParentPk(Object parentPk) {
		this.parentPk = parentPk;
	}

	public OneToManyResult(List<Object> data, Object parentId,
			String relationship) {
		super();
		this.data = data;

		this.parentPk = parentId;
		this.relationship = relationship;
	}
}