package uk.mafu.domain.gillespies
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.gillespies.Website")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="name")]
	[Tab(title="Website",order=1,field=name)]
	[Tab(title="Projects",order=2,field=projectCategories,field=projectLocations,field=projects,field=projectLinks)]
	[Tab(title="Practice",order=3,field=profile,field=assignments,field=people,field=roles,field=yearOuts,field=sustainabilities,field=communityWorks,field=links,field=globalJobs,field=workings)]
	[Tab(title="Clients",order=4,field=clients,field=testimonials)]
	[Tab(title="News/Press",order=5,field=newsItems,field=blogentries,field=awards,field=publications,field=lecturesAndTalks,field=pressReleases)]
	[Tab(title="Contact",order=6,field=map,field=officeLocations)]
	[Chooser(label="name")]
	[Bindable]
	public class Website
	{
		public var pk:Number =-1;
 
		[Display(label="Name")]
		public var name:String;
	
		[Display(label="Map",widget=SingleImage)]
		public var map:ImageLink;
	
		[Relationship(end="uk.mafu.domain.gillespies::Assignment",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Assignments")]
		public var assignments:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.gillespies::Role",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Roles")]
		public var roles:Array = new Array();
				
		[Relationship(end="uk.mafu.domain.gillespies::OfficeLocation",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Office Locations")]
		public var officeLocations:Array = new Array();
				
		[Relationship(end="uk.mafu.domain.gillespies::Link",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Links")]
		public var links:Array = new Array();
				
		[Relationship(end="uk.mafu.domain.gillespies::BlogEntry",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Links")]
		public var blogentries:Array = new Array();
		 
		[Relationship(end="uk.mafu.domain.gillespies::Client",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Clients")]
		public var clients:Array = new Array();
	
		[Relationship(end="uk.mafu.domain.gillespies::Person",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="People")]
		public var people:Array = new Array();
		 
		[Relationship(end="uk.mafu.domain.gillespies::News",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="News Items")]
		public var newsItems:Array = new Array();
		 
		[Relationship(end="uk.mafu.domain.gillespies::ProjectCategory",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Project Categories")]
		public var projectCategories:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.gillespies::ProjectLink",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Home Page Images")]
		public var projectLinks:Array = new Array();
		 
	 	[Relationship(end="uk.mafu.domain.gillespies::ProjectLocation",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Project Locations")]
		public var projectLocations:Array = new Array();
		 
		[Relationship(end="uk.mafu.domain.gillespies::Award",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="awards")]
		public var awards:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.gillespies::Job",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Jobs")]
		public var globalJobs:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.gillespies::Working",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Working Here")]
		public var workings:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.gillespies::Project",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Projects")]
		public var projects:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.gillespies::Publication",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Publications")]
		public var publications:Array = new Array();
		
		[Display(label="Profile")]
		[Relationship(end="uk.mafu.domain.gillespies::Profile",type="ONE_TO_ONE")]
		public var profile:Profile;
		
		[Relationship(end="uk.mafu.domain.gillespies::YearOut",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Year Outs")]
		public var yearOuts:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.gillespies::Sustainability",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Sustainability")]
		public var sustainabilities:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.gillespies::PressRelease",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Press Releases")]
		public var pressReleases:Array = new Array();
		
	    [Relationship(end="uk.mafu.domain.gillespies::CommunityWork",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Community Works")]
		public var communityWorks:Array = new Array();	  
		
	  	[Relationship(end="uk.mafu.domain.gillespies::LectureAndTalk",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Lectures And Talks")]
		public var lecturesAndTalks:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.gillespies::Testimonial",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Testimonials")]
		public var testimonials:Array = new Array();
	}
}