package uk.mafu.loon.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class UUIDEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String pk;

	@OneToOne
	private UUIDChildEntity child;
	
	@OneToMany
	private List<UUIDChildEntity> children = new ArrayList<UUIDChildEntity>();

	public String getPk() {
		return pk;
	}

	public UUIDChildEntity getChild() {
		return child;
	}

	public void setChild(UUIDChildEntity child) {
		this.child = child;
	}

	public List<UUIDChildEntity> getChildren() {
		return children;
	}

	public void setChildren(List<UUIDChildEntity> children) {
		this.children = children;
	}

	public void setPk(String pk) {
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