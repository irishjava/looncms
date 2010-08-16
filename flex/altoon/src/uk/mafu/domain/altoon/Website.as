package uk.mafu.domain.altoon
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.altoon.Website")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="identifier")]
	[Tab(title="Main",order=1,field=identifier)]
	[Tab(title="Awards",order=2,field=awards,field=awardsImage)]
	[Tab(title="Regions",order=3,field=regions)]
	[Tab(title="NewsItems",order=4,field=newsItems)]
	[Tab(title="Offices",order=5,field=offices)]
	[Tab(title="Project",order=7,field=projects,field=projectLinks,field=projectCategories)]
	[Tab(title="Roles",order=8,field=roles)]
	[Tab(title="Sustainability",order=9,field=sustainabilityText,field=sustainabilityImages)]
	[Tab(title="Profile",order=10,field=profileText,field=profileImages)]
	[Tab(title="History",order=11,field=historyText,field=historyImages)]
	[Tab(title="Philosophy",order=12,field=philosophyText,field=philosophyImages)]
	[Tab(title="Services",order=13,field=services)]
	
	
	[Chooser(label="identifier")]
	[Bindable]
	public class Website
	{
		public var pk:Number =-1;
 
		[Display(label="Identifier")]
		public var identifier:String;
	
		[Relationship(end="uk.mafu.domain.altoon::Award",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Awards")]
		public var awards:Array = new Array();
		
		[Display(label="Awards Image",widget=SingleImage)]
		public var awardsImage:ImageLink;

		[Relationship(end="uk.mafu.domain.altoon::Region",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Regions")]
		public var regions:Array = new Array();

		[Relationship(end="uk.mafu.domain.altoon::News",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="News Items")]
		public var newsItems:Array = new Array();

		[Relationship(end="uk.mafu.domain.altoon::Office",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Offices")]
		public var offices:Array = new Array();

		[Relationship(end="uk.mafu.domain.altoon::ProjectCategory",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Project Categories")]
		public var projectCategories:Array = new Array();

		[Relationship(end="uk.mafu.domain.altoon::Project",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Projects")]
		public var projects:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.altoon::ProjectLink",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Project Links")]
		public var projectLinks:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.altoon::Role",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Roles")]
		public var roles:Array = new Array();

		[Relationship(end="uk.mafu.domain.altoon::Service",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Services")]
		public var services:Array = new Array();

 		//SECTION STUFF ...
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var sustainabilityText:String;
	
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="MANY_TO_MANY")]
		[Display(label="Sustainability Images",widget="ImageGallery")]
		public var sustainabilityImages:Array = new Array();
	
		//
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var profileText:String;
	
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Profile Images",widget="ImageGallery")]
		public var profileImages:Array = new Array();
	
		//
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var historyText:String;
	
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="History Images",widget="ImageGallery")]
		public var historyImages:Array = new Array();
		
		//
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var philosophyText:String;
	
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Philosophy Images",widget="ImageGallery")]
		public var philosophyImages:Array = new Array();
		  
		//SECTION STUFF ....
	}
}