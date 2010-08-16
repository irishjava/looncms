package uk.mafu.domain.saatchi
{
	import uk.mafu.loon.domain.data.PdfLink;
	
	[RemoteClass(alias="uk.mafu.domain.saatchi.Website")]
	
	[Tab(title="Main",order=1,
		field=identifier)]
	[Tab(title="Brand Photography",order=2,
		field=brandPhotographyTitle,field=brandPhotographyText,field=brandPhotographyQuoteBody,field=brandPhotographyQuoteReference,field=brandPhotography)]
	[Tab(title="Brand Design",order=3,
		field=brandDesignTitle,field=brandDesignText,field=brandDesignQuoteBody,field=brandDesignQuoteReference,field=brandDesign)]
	[Tab(title="Brand Guidelines",order=4,
		field=brandGuidelinesTitle,field=brandGuidelinesText,field=brandGuidelinesQuoteBody,field=brandGuidelinesQuoteReference,field=brandGuidelines)]
	[Tab(title="Strategy",order=5,
		field=brandStrategyTitle,field=brandStrategyText,field=brandStrategyQuoteBody,field=brandStrategyQuoteReference)]
	[Tab(title="Partner",order=6,
		field=partnerText,field=partnerQuoteBody,field=partnerQuoteReference)]
	[Tab(title="Office",order=7,
		field=officeText)]
	[Tab(title="Client",order=8,
		field=clientText1,field=clientText2)]
	[Tab(title="Contact",order=9,
		field=contact1,field=contact2,field=contact3,field=contactpdf)]
	[Tab(title="Homepage",order=10,
		field=homepageItems)]
	[Tab(title="Strategy",order=11,
		field=strategyGroups)]
	[Tab(title="Attitude",order=12,
		field=attitudeItems)]
	[Tab(title="Purpose",order=13,
		field=purposeItems)]
	[Tab(title="Philosophy",order=14,
		field=philosophyItems)]
	[Tab(title="Urls",order=15,
		field=blogUrl,field=twitterUrl,field=googleMapUrl)]
	[Order(col="identifier",asc="false")]
	[Columns(col="pk",col="identifier")]
	[Chooser(label="identifier")]
	[Bindable]
	public class Website 
	{
		public var pk:Number =-1;
		
		[Display(label="Name",lines=1)]
		public var identifier:String;

		//--
		[Display(label="Title")]
		public var brandPhotographyTitle:String;

		[Display(label="Text",lines=10)]
		public var brandPhotographyText:String;

		[Display(label="Quote Body",lines=10)]
		public var brandPhotographyQuoteBody:String;

		[Display(label="Quote Ref",lines=10)]
		public var brandPhotographyQuoteReference:String;

		//--
		[Display(label="Title")]
		public var brandDesignTitle:String;

		[Display(label="Text",lines=10)]
		public var brandDesignText:String;

		[Display(label="Quote Body",lines=10)]
		public var brandDesignQuoteBody:String;

		[Display(label="Quote Ref",lines=10)]
		public var brandDesignQuoteReference:String;

		//--
		[Display(label="Title")]
		public var brandGuidelinesTitle:String;

		[Display(label="Text",lines=10)]
		public var brandGuidelinesText:String;

		[Display(label="Quote Body",lines=10)]
		public var brandGuidelinesQuoteBody:String;

		[Display(label="Quote Ref",lines=10)]
		public var brandGuidelinesQuoteReference:String;

		//--
		[Display(label="Title")]
		public var brandStrategyTitle:String;

		[Display(label="Text",lines=10)]
		public var brandStrategyText:String;
		
		[Display(label="Quote Body",lines=10)]
		public var brandStrategyQuoteBody:String;
		
		[Display(label="Quote Ref",lines=10)]
		public var brandStrategyQuoteReference:String;
		
		[Display(label="Text",lines=10)]
		public var partnerText:String;
		
		[Display(label="Quote Body",lines=10)]
		public var partnerQuoteBody:String;
		
		[Display(label="Quote Ref",lines=10)]
		public var partnerQuoteReference:String;
		
		[Display(label="officeText",lines=10)]
		public var officeText:String;

		[Display(label="clientText1",lines=10)]
		public var clientText1:String;

		[Display(label="clientText2",lines=10)]
		public var clientText2:String;
		
		[Display(label="contact1",lines=10)]
		public var contact1:String;
		[Display(label="contact2",lines=10)]
		public var contact2:String;
		[Display(label="contact3",lines=10)]
		public var contact3:String;

		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="MANY_TO_MANY")]
		[Display(label="Project Images",widget="ImageGallery")]
	  	public var homepageItems:Array = new Array();
	  	
	  	[Display(label="Blog Url")]
		public var blogUrl:String;
	  	
		[Display(label="Twitter Url")]
		public var twitterUrl:String;
	  	
	  	[Display(label="Google Map Url")]
		public var googleMapUrl:String;
	  	
	  	// Relations
	
		[Relationship(end="uk.mafu.domain.saatchi::BrandStrategyGroup",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="strategyGroups")]
		public var strategyGroups:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.saatchi::AttitudeItem",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Attitude Items")]
		public var attitudeItems:Array = new Array();
	
		[Relationship(end="uk.mafu.domain.saatchi::PhilosophyItem",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Philosophy Items")]
		public var philosophyItems:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.saatchi::PurposeItem",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="purpose Items")]
		public var purposeItems:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.saatchi::Brand",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Brand Guidelines")]
		public var brandGuidelines:Array = new Array();
	
		[Relationship(end="uk.mafu.domain.saatchi::Brand",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Brand Design")]
		public var brandDesign:Array = new Array();
	
		[Relationship(end="uk.mafu.domain.saatchi::Brand",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Brand Photography")]
		public var brandPhotography:Array = new Array();	 
	}
}	 