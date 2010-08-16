package uk.mafu.kwa.domain{
import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.PdfLink;
import uk.mafu.loon.domain.data.VideoLink;

[RemoteClass(alias="uk.kwa.domain.Website")]

[Tab(title="Main",order=1,
	field=identifier,
	field=homepageExcite
)]
[Tab(title="Contact",order=2,
	field=contactText,
	field=contactImage,
	field=contactGoogleMapsLink,
	field=contactPdf
)]
[Tab(title="Ethos",order=3,
	field=ethosText,
	field=ethosImage,
	field=ethosExcite
)]
[Tab(title="Overview",order=4,
	field=overviewText,
	field=overviewVideo,
	field=overviewImage
)]
[Tab(title="Sustainability",order=5,
	field=sustainabilityText,
	field=sustainabilityImage,
	field=sustainabilityExcite
)]
[Tab(title="Monograph",order=6,
	field=monographText,
	field=monographImage,
	field=monographExcite
)]
[Tab(title="Clients",order=7,
	field=clientExcite,
	field=clients
)]
[Tab(title="Jobs",order=8,field=jobPostings)]
[Tab(title="Directors",order=9,field=directors,field=directorsExcite)]
[Tab(title="Employees",order=10,field=teamExcite,field=employees)]
[Tab(title="Project Cats",order=11,field=projectCategories)]
[Tab(title="Home Page Slides",order=12,field=homePageSlides)]
[Tab(title="News Excite",order=13,field=newsExcite)]
[Tab(title="Awards Excite",order=14,field=awardsExcite)]
[Tab(title="Press Excite",order=15,field=pressExcite)]
[Order(col="pk",asc="false")]
[Columns(col="pk",col="identifier")]
[Chooser(label="identifier")]
[Bindable]
public class Website {
    public var pk:Number  = -1 ;
    
    [Display(label="Identifier",lines=1)]
    public var identifier:String;
    
    [Display(label="Contact Text",lines=5)]
    public var contactText: String; 
    
    [Display(label="Contact Image",widget=SingleImage)]
	public var contactImage:ImageLink ;
    
    [Display(label="Contact Google Link",lines=1)]
    public var contactGoogleMapsLink: String ;
    
    [Display(label="Contact Pdf",widget=SinglePdf)]
    public var contactPdf:PdfLink ;
    
    [Display(label="Ethos Text",lines=5)]
    public var ethosText: String ;
    
    [Display(label="Ethos Image",widget=SingleImage)]
    public var ethosImage:ImageLink ;
    
    [Display(label="Overview Text",lines=5)]
    public var overviewText: String ;
    
    [Display(label="Overview Video",widget=SingleVideo)]
    public var overviewVideo:VideoLink;
    
    [Display(label="Overview Image",widget=SingleImage)]
    public var overviewImage:ImageLink;
    
    [Display(label="Monograph Text",lines=5)]
    public var monographText:String  ;
    
    [Display(label="Monograph Image",widget=SingleImage)]
    public var monographImage:ImageLink ;
    
    [Display(label="Monograph Excite")]
	[Relationship(end="uk.mafu.kwa.domain::Excite",type="ONE_TO_ONE")]
    public var monographExcite:Excite;
     
    [Display(label="Sustainability Text",lines=5)]
    public var sustainabilityText:String;
    		   
    [Display(label="Sustainability Image",widget=SingleImage)]
    public var sustainabilityImage:ImageLink;
    
    [Display(label="Sustainability Excite")]
	[Relationship(end="uk.mafu.kwa.domain::Excite",type="ONE_TO_ONE")]
    public var sustainabilityExcite:Excite;
    
    [Relationship(end="uk.mafu.kwa.domain::Client",type="MANY_TO_MANY")]
	[Order(col="pk",asc="false")]
	[Display(label="Clients")]
	public var clients:Array;
    
    [Relationship(end="uk.mafu.kwa.domain::Person",type="MANY_TO_MANY")]
	[Order(col="pk",asc="false")]
	[Display(label="Employees")]
	public var employees:Array;
    
    [Relationship(end="uk.mafu.kwa.domain::JobPosting",type="MANY_TO_MANY")]
	[Order(col="pk",asc="false")]
	[Display(label="Job Postings")]
	public var jobPostings:Array;
    
    [Relationship(end="uk.mafu.kwa.domain::Person",type="MANY_TO_MANY")]
	[Order(col="pk",asc="false")]
	[Display(label="Directors")]
	public var directors:Array;
    
    [Display(label="Team Excite")]
	[Relationship(end="uk.mafu.kwa.domain::Excite",type="ONE_TO_ONE")]
    public var teamExcite:Excite;
    
    [Display(label="Directors Excite")]
	[Relationship(end="uk.mafu.kwa.domain::Excite",type="ONE_TO_ONE")]
    public var directorsExcite:Excite;
    
    [Display(label="Ethos Excite")]
	[Relationship(end="uk.mafu.kwa.domain::Excite",type="ONE_TO_ONE")]
    public var ethosExcite:Excite;
    
    [Display(label="Client Excite")]
	[Relationship(end="uk.mafu.kwa.domain::Excite",type="ONE_TO_ONE")]
    public var clientExcite:Excite;
    
    [Display(label="Homepage Excite")]
	[Relationship(end="uk.mafu.kwa.domain::Excite",type="ONE_TO_ONE")]
    public var homepageExcite:Excite;
    
    [Relationship(end="uk.mafu.kwa.domain::ProjectCategory",type="MANY_TO_MANY")]
	[Order(col="pk",asc="false")]
	[Display(label="Project Categories")]
    public var projectCategories:Array;
    
    [Relationship(end="uk.mafu.kwa.domain::HomePageSlide",type="MANY_TO_MANY")]
	[Order(col="pk",asc="false")]
	[Display(label="Home Page Slides")]
    public var homePageSlides:Array;
    
    [Display(label="News Excite")]
	[Relationship(end="uk.mafu.kwa.domain::Excite",type="ONE_TO_ONE")]
    public var newsExcite:Excite;
    
    [Display(label="Awards Excite")]
	[Relationship(end="uk.mafu.kwa.domain::Excite",type="ONE_TO_ONE")]
    public var awardsExcite:Excite;
    
    [Display(label="Press Excite")]
	[Relationship(end="uk.mafu.kwa.domain::Excite",type="ONE_TO_ONE")]
    public var pressExcite:Excite;
    
	}
}