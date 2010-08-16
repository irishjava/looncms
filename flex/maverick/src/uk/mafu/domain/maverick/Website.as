package uk.mafu.domain.maverick
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.maverick.Website")]
	[Tab(title="Main",order=1,
		field=identifier
	)]
	[Tab(title="Contact",order=2,
		field=contactText1,
		field=contactText2,
		field=contactGoogleMapUrl,
		field=contactImage
	)]
	[Tab(title="Ethos",order=3,
		field=ethosText1,
		field=ethosText2,
		field=ethosImage
	)]

	// Relations
	[Tab(title="Press Items",order=4,
		field=pressItems
	)]
	
	[Tab(title="Awards",order=5,
		field=awards
	)]
	
	[Tab(title="Campaigns",order=6,
		field=campaignText1,
		field=campaignText2		
	)]

	[Tab(title="Campaigns",order=7,
		field=campaigns
	)]
		
	[Tab(title="News Items",order=8,
		field=newsItems
	)]
	
	[Tab(title="Project Categories",order=9,
		field=projectCategories
	)]
	
	[Tab(title="Showreel",order=10,
		field=showreel
	)]
	
	[Tab(title="Roles",order=11,
		field=roles
	)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="identifier")]
	[Chooser(label="identifier")]
	[Bindable]
	public class Website 
	{
		public var pk:Number =-1;

 		[Display(label="Name",lines=1)]
		public var identifier:String;

		[Display(label="Contact Text 1",lines=20,widget=RichTextWidget)]
		public var contactText1:String;

		[Display(label="Contact Text 2",lines=20,widget=RichTextWidget)]
		public var contactText2:String;
		
		[Display(label="Campaign Text 1",lines=20,widget=RichTextWidget)]
		public var campaignText1:String;

		[Display(label="Campaign Text 2",lines=20,widget=RichTextWidget)]
		public var campaignText2:String;

		[Display(label="Contact Google Map Url",lines=1)]
		public var contactGoogleMapUrl:String;

		[Display(label="Contact Image",widget=SingleImage)]
		public var contactImage:ImageLink;

		// Ethos
	
		[Display(label="Ethos Text 1",lines=20,widget=RichTextWidget)]
		public var ethosText1:String;
	
		[Display(label="Ethos Text 2",lines=20,widget=RichTextWidget)]
		public var ethosText2:String;
		
		[Display(label="Ethos Image",widget=SingleImage)]
		public var ethosImage:ImageLink;

		// Relations
	
		[Relationship(end="uk.mafu.domain.maverick::Press",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Press")]
		public var pressItems:Array = new Array();
	
		[Relationship(end="uk.mafu.domain.maverick::Award",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Award")]
		public var awards:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.maverick::Campaign",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Campaigns")]
		public var campaigns:Array = new Array();
	 
		[Relationship(end="uk.mafu.domain.maverick::News",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="newsItems")]
		public var newsItems:Array = new Array();
	
		[Relationship(end="uk.mafu.domain.maverick::ProjectCategory",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="project categories")]
		public var projectCategories:Array = new Array();

		[Display(label="Showreel")]
		[Relationship(end="uk.mafu.domain.maverick::Showreel",type="ONE_TO_ONE")]
		public var showreel:Showreel;
	 	 
	 	[Relationship(end="uk.mafu.domain.maverick::Role",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="roles")]
		public var roles:Array = new Array();

	  	public var permalink:String;
	}
}	 