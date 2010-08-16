package uk.mafu.domain.saatchi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
	@Column
	private String brandDesignTitle;
	@Column(length = 2147483647)
	private String brandDesignText;
	//
	private String brandPhotographyTitle;
	@Column(length = 2147483647)
	private String brandPhotographyText;
	//
	private String brandGuidelinesTitle;
	@Column(length = 2147483647)
	private String brandGuidelinesText;
	//
	private String brandStrategyTitle;
	@Column(length = 2147483647)
	private String brandStrategyText;

	@Column(length = 2147483647)
	private String partnerText;

	@Column(length = 2147483647)
	private String partnerQuoteBody;

	@Column(length = 2147483647)
	private String partnerQuoteReference;

	@Column(length = 2147483647)
	private String officeText;

	@Column(length = 2147483647)
	private String clientText1;
	@Column(length = 2147483647)
	private String clientText2;

	@Column(length = 2147483647)
	private String brandDesignQuoteBody;
	@Column(length = 2147483647)
	private String brandDesignQuoteReference;

	@Column(length = 2147483647)
	private String brandGuidelinesQuoteBody;
	@Column(length = 2147483647)
	private String brandGuidelinesQuoteReference;

	@Column(length = 2147483647)
	private String brandPhotographyQuoteBody;
	@Column(length = 2147483647)
	private String brandPhotographyQuoteReference;

	@Column(length = 2147483647)
	private String brandStrategyQuoteBody;
	@Column(length = 2147483647)
	private String brandStrategyQuoteReference;

	@Column(length = 2147483647)
	private String contact1;

	@Column(length = 2147483647)
	private String contact2;

	@Column(length = 2147483647)
	private String contact3;

	@Column
	private String blogUrl;

	@Column
	private String twitterUrl;

	@Column
	private String googleMapUrl;
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<ImageLink> homepageItems = new ArrayList<ImageLink>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<BrandStrategyGroup> strategyGroups = new ArrayList<BrandStrategyGroup>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<AttitudeItem> attitudeItems = new ArrayList<AttitudeItem>();

	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<PurposeItem> purposeItems = new ArrayList<PurposeItem>();

	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	private List<PhilosophyItem> philosophyItems = new ArrayList<PhilosophyItem>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	@JoinTable(name = "website_brands_guidelines")
	private List<Brand> brandGuidelines = new ArrayList<Brand>();
	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	@JoinTable(name = "website_brands_design")
	private List<Brand> brandDesign = new ArrayList<Brand>();

	@ManyToMany
	@IndexColumn(name = "position", base = 0)
	@JoinTable(name = "website_brands_photography")
	private List<Brand> brandPhotography = new ArrayList<Brand>();

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

	public String getBrandDesignTitle() {
		return brandDesignTitle;
	}

	public void setBrandDesignTitle(String brandDesignTitle) {
		this.brandDesignTitle = brandDesignTitle;
	}

	public String getBrandDesignText() {
		return brandDesignText;
	}

	public void setBrandDesignText(String brandDesignText) {
		this.brandDesignText = brandDesignText;
	}

	public String getBrandPhotographyTitle() {
		return brandPhotographyTitle;
	}

	public void setBrandPhotographyTitle(String brandPhotographyTitle) {
		this.brandPhotographyTitle = brandPhotographyTitle;
	}

	public String getBrandPhotographyText() {
		return brandPhotographyText;
	}

	public void setBrandPhotographyText(String brandPhotographyText) {
		this.brandPhotographyText = brandPhotographyText;
	}

	public String getBrandGuidelinesTitle() {
		return brandGuidelinesTitle;
	}

	public void setBrandGuidelinesTitle(String brandGuidelinesTitle) {
		this.brandGuidelinesTitle = brandGuidelinesTitle;
	}

	public String getBrandGuidelinesText() {
		return brandGuidelinesText;
	}

	public void setBrandGuidelinesText(String brandGuidelinesText) {
		this.brandGuidelinesText = brandGuidelinesText;
	}

	public String getBrandStrategyTitle() {
		return brandStrategyTitle;
	}

	public void setBrandStrategyTitle(String brandStrategyTitle) {
		this.brandStrategyTitle = brandStrategyTitle;
	}

	public String getBrandStrategyText() {
		return brandStrategyText;
	}

	public void setBrandStrategyText(String brandStrategyText) {
		this.brandStrategyText = brandStrategyText;
	}

	public String getPartnerText() {
		return partnerText;
	}

	public void setPartnerText(String partnerText) {
		this.partnerText = partnerText;
	}

	public String getClientText1() {
		return clientText1;
	}

	public void setClientText1(String clientText1) {
		this.clientText1 = clientText1;
	}

	public String getClientText2() {
		return clientText2;
	}

	public void setClientText2(String clientText2) {
		this.clientText2 = clientText2;
	}

	public String getBrandDesignQuoteBody() {
		return brandDesignQuoteBody;
	}

	public void setBrandDesignQuoteBody(String brandDesignQuoteBody) {
		this.brandDesignQuoteBody = brandDesignQuoteBody;
	}

	public String getBrandDesignQuoteReference() {
		return brandDesignQuoteReference;
	}

	public void setBrandDesignQuoteReference(String brandDesignQuoteReference) {
		this.brandDesignQuoteReference = brandDesignQuoteReference;
	}

	public String getBrandGuidelinesQuoteBody() {
		return brandGuidelinesQuoteBody;
	}

	public void setBrandGuidelinesQuoteBody(String brandGuidelinesQuoteBody) {
		this.brandGuidelinesQuoteBody = brandGuidelinesQuoteBody;
	}

	public String getBrandGuidelinesQuoteReference() {
		return brandGuidelinesQuoteReference;
	}

	public void setBrandGuidelinesQuoteReference(String brandGuidelinesQuoteReference) {
		this.brandGuidelinesQuoteReference = brandGuidelinesQuoteReference;
	}

	public String getBrandPhotographyQuoteBody() {
		return brandPhotographyQuoteBody;
	}

	public void setBrandPhotographyQuoteBody(String brandPhotographyQuoteBody) {
		this.brandPhotographyQuoteBody = brandPhotographyQuoteBody;
	}

	public String getBrandPhotographyQuoteReference() {
		return brandPhotographyQuoteReference;
	}

	public void setBrandPhotographyQuoteReference(String brandPhotographyQuoteReference) {
		this.brandPhotographyQuoteReference = brandPhotographyQuoteReference;
	}

	public String getBrandStrategyQuoteBody() {
		return brandStrategyQuoteBody;
	}

	public void setBrandStrategyQuoteBody(String brandStrategyQuoteBody) {
		this.brandStrategyQuoteBody = brandStrategyQuoteBody;
	}

	public String getBrandStrategyQuoteReference() {
		return brandStrategyQuoteReference;
	}

	public void setBrandStrategyQuoteReference(String brandStrategyQuoteReference) {
		this.brandStrategyQuoteReference = brandStrategyQuoteReference;
	}

	public List<ImageLink> getHomepageItems() {
		return homepageItems;
	}

	public void setHomepageItems(List<ImageLink> homepageItems) {
		this.homepageItems = homepageItems;
	}

	public List<BrandStrategyGroup> getStrategyGroups() {
		return strategyGroups;
	}

	public void setStrategyGroups(List<BrandStrategyGroup> strategyGroups) {
		this.strategyGroups = strategyGroups;
	}

	public List<AttitudeItem> getAttitudeItems() {
		return attitudeItems;
	}

	public void setAttitudeItems(List<AttitudeItem> attitudeItems) {
		this.attitudeItems = attitudeItems;
	}

	public List<PhilosophyItem> getPhilosophyItems() {
		return philosophyItems;
	}

	public void setPhilosophyItems(List<PhilosophyItem> philosophyItems) {
		this.philosophyItems = philosophyItems;
	}

	
	
	public List<Brand> getBrandGuidelines() {
		return brandGuidelines;
	}

	public void setBrandGuidelines(List<Brand> guidelineBrands) {
		this.brandGuidelines = guidelineBrands;
	}

	public List<Brand> getBrandDesign() {
		return brandDesign;
	}

	public void setBrandDesign(List<Brand> designBrands) {
		this.brandDesign = designBrands;
	}

	public List<Brand> getBrandPhotography() {
		return brandPhotography;
	}

	public void setBrandPhotography(List<Brand> photographyBrands) {
		this.brandPhotography = photographyBrands;
	}

	public List<PurposeItem> getPurposeItems() {
		return purposeItems;
	}

	public void setPurposeItems(List<PurposeItem> purposeItems) {
		this.purposeItems = purposeItems;
	}

	public String getContact1() {
		return contact1;
	}

	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}

	public String getContact2() {
		return contact2;
	}

	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}

	public String getContact3() {
		return contact3;
	}

	public void setContact3(String contact3) {
		this.contact3 = contact3;
	}

	public String getBlogUrl() {
		return blogUrl;
	}

	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}

	public String getTwitterUrl() {
		return twitterUrl;
	}

	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}

	public String getGoogleMapUrl() {
		return googleMapUrl;
	}

	public void setGoogleMapUrl(String googleMapUrl) {
		this.googleMapUrl = googleMapUrl;
	}

	public String getOfficeText() {
		return officeText;
	}

	public void setOfficeText(String officeText) {
		this.officeText = officeText;
	}

	public String getPartnerQuoteBody() {
		return partnerQuoteBody;
	}

	public void setPartnerQuoteBody(String partnerQuoteBody) {
		this.partnerQuoteBody = partnerQuoteBody;
	}

	public String getPartnerQuoteReference() {
		return partnerQuoteReference;
	}

	public void setPartnerQuoteReference(String partnerQuoteReference) {
		this.partnerQuoteReference = partnerQuoteReference;
	}

	
	
	
}