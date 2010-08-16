package uk.mafu.loon.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class NumericEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer pk = -1;
	
	@OneToOne
	private NumericChildEntity child;
	
	@ManyToMany
	private List<NumericChildEntity> children = new ArrayList<NumericChildEntity>();

	public Integer getPk() {
		return pk;
	}

	public NumericChildEntity getChild() {
		return child;
	}

	public void setChild(NumericChildEntity child) {
		this.child = child;
	}

	public List<NumericChildEntity> getChildren() {
		return children;
	}

	public void setChildren(List<NumericChildEntity> children) {
		this.children = children;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}