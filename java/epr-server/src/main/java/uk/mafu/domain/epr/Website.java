package uk.mafu.domain.epr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.IndexColumn;
import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.PdfLink;

@Entity
public class Website implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk = -1;
	@Column
	private String identifier;
	@Column(length = 2147483647)
	private String contactText;
	@Column
	private String contactUrl;
	@OneToOne(optional = true)
	private ImageLink contactMap;
	@OneToOne(optional = true)
	private PdfLink contactPdf;
	/**
	 * Approach
	 */
	@Column(length = 2147483647)
	private String approachText;
	@ManyToMany(fetch = FetchType.EAGER)
	@IndexColumn(name = "position", base = 0)
	private List<ImageLink> approachImages = new ArrayList<ImageLink>();
	/**
	 * Sustainability
	 */
	@Column(length = 2147483647)
	private String sustainabilityText;
	@ManyToMany(fetch = FetchType.EAGER)
	@IndexColumn(name = "position", base = 0)
	@JoinTable(name = "website_sustainabilityImages")
	private List<ImageLink> sustainabilityImages = new ArrayList<ImageLink>();
	/**
	 * Office
	 */
	@Column(length = 2147483647)
	private String officeText;
	@ManyToMany(fetch = FetchType.EAGER)
	@IndexColumn(name = "position", base = 0)
	@JoinTable(name = "website_officeImages")
	private List<ImageLink> officeImages = new ArrayList<ImageLink>();
	/**
	 * Model making
	 */
	@Column(length = 2147483647)
	private String modelMakingText;
	@ManyToMany(fetch = FetchType.EAGER)
	@IndexColumn(name = "position", base = 0)
	@JoinTable(name = "website_modelMakingImages")
	private List<ImageLink> modelMakingImages = new ArrayList<ImageLink>();
	/**
	 * Relationships
	 */
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<ProjectCategory> projectCategories = new ArrayList<ProjectCategory>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Award> awards = new ArrayList<Award>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Person> people = new ArrayList<Person>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<ProjectLink> projectLinks = new ArrayList<ProjectLink>();

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

	public String getContactText() {
		return contactText;
	}

	public void setContactText(String contactText) {
		this.contactText = contactText;
	}

	public String getContactUrl() {
		return contactUrl;
	}

	public void setContactUrl(String contactUrl) {
		this.contactUrl = contactUrl;
	}

	public ImageLink getContactMap() {
		return contactMap;
	}

	public void setContactMap(ImageLink contactMap) {
		this.contactMap = contactMap;
	}

	public PdfLink getContactPdf() {
		return contactPdf;
	}

	public void setContactPdf(PdfLink contactPdf) {
		this.contactPdf = contactPdf;
	}

	public String getApproachText() {
		return approachText;
	}

	public void setApproachText(String approachText) {
		this.approachText = approachText;
	}

	public List<ImageLink> getApproachImages() {
		return approachImages;
	}

	public void setApproachImages(List<ImageLink> approachImages) {
		this.approachImages = approachImages;
	}

	public String getSustainabilityText() {
		return sustainabilityText;
	}

	public void setSustainabilityText(String sustainabilityText) {
		this.sustainabilityText = sustainabilityText;
	}

	public List<ImageLink> getSustainabilityImages() {
		return sustainabilityImages;
	}

	public void setSustainabilityImages(List<ImageLink> sustainabilityImages) {
		this.sustainabilityImages = sustainabilityImages;
	}

	public String getOfficeText() {
		return officeText;
	}

	public void setOfficeText(String officeText) {
		this.officeText = officeText;
	}

	public List<ImageLink> getOfficeImages() {
		return officeImages;
	}

	public void setOfficeImages(List<ImageLink> officeImages) {
		this.officeImages = officeImages;
	}

	public String getModelMakingText() {
		return modelMakingText;
	}

	public void setModelMakingText(String modelMakingText) {
		this.modelMakingText = modelMakingText;
	}

	public List<ImageLink> getModelMakingImages() {
		return modelMakingImages;
	}

	public void setModelMakingImages(List<ImageLink> modelMakingImages) {
		this.modelMakingImages = modelMakingImages;
	}

	public List<ProjectCategory> getProjectCategories() {
		return projectCategories;
	}

	public void setProjectCategories(List<ProjectCategory> projectCategories) {
		this.projectCategories = projectCategories;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}

	public List<ProjectLink> getProjectLinks() {
		return projectLinks;
	}

	public void setProjectLinks(List<ProjectLink> projectLinks) {
		this.projectLinks = projectLinks;
	}
}
