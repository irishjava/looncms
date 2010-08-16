package uk.mafu.domain.mch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.IndexColumn;
import uk.mafu.loon.domain.data.ImageLink;

@Entity
public class Website implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@Column
	private String identifier;
	@OneToOne(optional = true)
	private ImageLink map;
	@Column(length = 2147483647)
	private String contactText;
	@Column(length = 2147483647)
	private String creditText;
	@Column(length = 2147483647)
	private String bannerText;
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<ProjectLink> projectLinks = new ArrayList<ProjectLink>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<ProjectCategory> projectCategories = new ArrayList<ProjectCategory>();

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public ImageLink getMap() {
		return map;
	}

	public void setMap(ImageLink map) {
		this.map = map;
	}

	public String getContactText() {
		return contactText;
	}

	public void setContactText(String contactText) {
		this.contactText = contactText;
	}

	public String getCreditText() {
		return creditText;
	}

	public void setCreditText(String creditText) {
		this.creditText = creditText;
	}

	public String getBannerText() {
		return bannerText;
	}

	public void setBannerText(String bannerText) {
		this.bannerText = bannerText;
	}

	public List<ProjectLink> getProjectLinks() {
		return projectLinks;
	}

	public void setProjectLinks(List<ProjectLink> projectLinks) {
		this.projectLinks = projectLinks;
	}

	public List<ProjectCategory> getProjectCategories() {
		return projectCategories;
	}

	public void setProjectCategories(List<ProjectCategory> projectCategories) {
		this.projectCategories = projectCategories;
	}
}
