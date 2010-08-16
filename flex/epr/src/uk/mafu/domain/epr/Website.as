package uk.mafu.domain.epr
{
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.PdfLink;
	
	[RemoteClass(alias="uk.mafu.domain.epr.Website")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="identifier")]
	[Tab(title="Main",order=1,
		field=identifier,
		field=contactText,
		field=contactUrl,
		field=contactMap,
		field=contactPdf
	)]
	[Tab(title="Approach",order=2,
		field=approachText,
		field=approachImages		 
	)]
	[Tab(title="Sustainability",order=3,
		field=sustainabilityText,
		field=sustainabilityImages
	)]
	[Tab(title="Office",order=4,
		field=officeText,
		field=officeImages
	)]
	[Tab(title="Model making",order=5,
		field=modelMakingText,
		field=modelMakingImages
	)]
	[Tab(title="Sorted items",order=6,
		field=projectCategories,
		field=awards,
		field=people,
		field=newsItems,
		field=projectLinks
	)]
	[Chooser(label="identifier")]
	[Bindable]
	public class Website
	{
		public var pk:Number =-1;
 
		[Display(label="Identifier")]
		public var identifier:String;
		
		[Display(label="Contact Text",lines=15,widget=RichTextWidget)]
		public var contactText:String;
		
		[Display(label="Google Url")]
		public var contactUrl:String;
		
		[Display(label="Image",widget="SingleImage")]
		public var contactMap:ImageLink;
		
		[Display(label="PDF",widget="SinglePdf")]
		public var contactPdf:PdfLink;
	 	
		//Approach---------------------------------------------------------------
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var approachText:String;
		
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Project Images",widget="ImageGallery")]
	  	public var approachImages:Array = new Array();	
			
		 
		//--------------------------------------------------------------------
		
	 	//Sustainability---------------------------------------------------------------
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var sustainabilityText:String;
		
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Project Images",widget="ImageGallery")]
	  	public var sustainabilityImages:Array = new Array();	
			
		 
		//--------------------------------------------------------------------
		
		//Office---------------------------------------------------------------
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var officeText:String;
		
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Project Images",widget="ImageGallery")]
	  	public var officeImages:Array = new Array();	
		 
		//--------------------------------------------------------------------
		
	 
		//Model making---------------------------------------------------------------
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var modelMakingText:String;
		
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Project Images",widget="ImageGallery")]
	  	public var modelMakingImages:Array = new Array();	
			
		 
		//--------------------------------------------------------------------
		 
	 	//Relationships-------------------------------------------------------
	
		[Relationship(end="uk.mafu.domain.epr::ProjectCategory",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Project Categories")]
		public var projectCategories:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.epr::Award",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Awards")]
		public var awards:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.epr::Person",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="People")]
		public var people:Array = new Array();
		
	 	[Relationship(end="uk.mafu.domain.epr::ProjectLink",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Project Links")]
		public var projectLinks:Array = new Array();		
		
	}
}