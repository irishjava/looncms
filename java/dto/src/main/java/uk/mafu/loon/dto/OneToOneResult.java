package uk.mafu.loon.dto;

import java.io.Serializable;
import java.util.List;

public class OneToOneResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private Object data;
	private List<Object> options;
	private Object parentPk;
	private String relationship;

	public OneToOneResult(Object data, List<Object> options, Object parentId,
			String relationship) {
		this.data = data;
		this.options = options;
		this.parentPk = parentId;
		this.relationship = relationship;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<Object> getOptions() {
		return options;
	}

	public void setOptions(List<Object> options) {
		this.options = options;
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
}