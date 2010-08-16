package uk.mafu.loon.dto;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ReflectionUtils;

public class ManyToManyResult implements Serializable {
	private static final long serialVersionUID = 1L;
	public List<Object> data;
	public List<Object> options;
	private Object parentPk;
	private String relationship;

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
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

	public void setParentPk(Integer parentPk) {
		this.parentPk = parentPk;
	}

	public ManyToManyResult(List<Object> data, List<Object> options,
			Object parentId, String relationship) {
		super();
		this.data = data;
		this.options = cleanOptions(options, data);
		this.parentPk = parentId;
		this.relationship = relationship;
	}

	private List<Object> cleanOptions(List<Object> options,
			List<Object> selections) {
		List<Object> ret = new ArrayList<Object>();
		outer: for (Object option : options) {
			for (Object selection : selections) {
				if (getPk(selection).equals(getPk(option))) {
					continue outer;
				}
			}
			ret.add(option);
		}
		return ret;
	}

	private Object getPk(Object entity) {
		Field field = ReflectionUtils.findField(entity.getClass(), "pk");
		field.setAccessible(true);
		try {
			return field.get(entity);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new UnsupportedOperationException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new UnsupportedOperationException(e);
		}
	}
}