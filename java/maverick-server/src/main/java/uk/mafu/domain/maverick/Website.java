package uk.mafu.domain.maverick;

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
	// Contact
	@Column(length = 2147483647)
	private String contactText1;
	@Column(length = 2147483647)
	private String contactText2;
	@Column(length = 2147483647)
	private String campaignText1;
	@Column(length = 2147483647)
	private String campaignText2;
	@Column(length = 2147483647)
	private String contactGoogleMapUrl;
	@OneToOne(optional = true)
	private ImageLink contactImage;
	// Ethos
	@Column(length = 2147483647)
	private String ethosText1;

	public ImageLink getEthosImage() {
		return ethosImage;
	}

	public void setEthosImage(ImageLink ethosImage) {
		this.ethosImage = ethosImage;
	}

	@Column(length = 2147483647)
	private String ethosText2;

	@OneToOne(optional = true)
	private ImageLink ethosImage;

	// Relations
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Press> pressItems = new ArrayList<Press>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Award> awards = new ArrayList<Award>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Role> roles = new ArrayList<Role>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<News> newsItems = new ArrayList<News>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<ProjectCategory> projectCategories = new ArrayList<ProjectCategory>();

	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<Campaign> campaigns = new ArrayList<Campaign>();
	@OneToOne(optional = true)
	private Showreel showreel;

	public Showreel getShowreel() {
		return showreel;
	}

	public void setShowreel(Showreel showreel) {
		this.showreel = showreel;
	}

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

	public String getContactText1() {
		return contactText1;
	}

	public void setContactText1(String contactText1) {
		this.contactText1 = contactText1;
	}

	public String getContactText2() {
		return contactText2;
	}

	public void setContactText2(String contactText2) {
		this.contactText2 = contactText2;
	}

	public String getCampaignText1() {
		return campaignText1;
	}

	public void setCampaignText1(String campaignText1) {
		this.campaignText1 = campaignText1;
	}

	public String getCampaignText2() {
		return campaignText2;
	}

	public void setCampaignText2(String campaignText2) {
		this.campaignText2 = campaignText2;
	}

	public String getContactGoogleMapUrl() {
		return contactGoogleMapUrl;
	}

	public void setContactGoogleMapUrl(String contactGoogleMapUrl) {
		this.contactGoogleMapUrl = contactGoogleMapUrl;
	}

	public ImageLink getContactImage() {
		return contactImage;
	}

	public void setContactImage(ImageLink contactImage) {
		this.contactImage = contactImage;
	}

	public String getEthosText1() {
		return ethosText1;
	}

	public void setEthosText1(String ethosText1) {
		this.ethosText1 = ethosText1;
	}

	public String getEthosText2() {
		return ethosText2;
	}

	public void setEthosText2(String ethosText2) {
		this.ethosText2 = ethosText2;
	}

	public List<Press> getPressItems() {
		return pressItems;
	}

	public void setPressItems(List<Press> pressItems) {
		this.pressItems = pressItems;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<News> getNewsItems() {
		return newsItems;
	}

	public void setNewsItems(List<News> newsItems) {
		this.newsItems = newsItems;
	}

	public List<ProjectCategory> getProjectCategories() {
		return projectCategories;
	}

	public void setProjectCategories(List<ProjectCategory> projectCategories) {
		this.projectCategories = projectCategories;
	}

	public List<Campaign> getCampaigns() {
		return campaigns;
	}

	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}
}