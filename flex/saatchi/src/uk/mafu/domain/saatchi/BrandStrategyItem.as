package uk.mafu.domain.saatchi
{
	[RemoteClass(alias="uk.mafu.domain.saatchi.BrandStrategyItem")]
	[Tab(title="Main",order=1,
		field=title,field=body
	)]
	[Tab(title="Quote",order=2,
		field=quoteBody,field=quoteReference
	)]
	[Tab(title="Images",order=3,
		field=images
	)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Chooser(label="title")]
	[Bindable]
	public class BrandStrategyItem
	{
		public var pk:Number =-1;
		
		[Display(label="Name",lines=1)]
		public var title:String;
		
		[Display(label="quoteBody",lines=20)]
		public var quoteBody:String;
		
		[Display(label="quoteReference",lines=20)]
		public var quoteReference:String;
		
		[Display(label="body",lines=20)]
		public var body:String;
		
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Project Images",widget="ImageGallery")]
	  	public var images:Array = new Array();
	  	
	  	public var permalink:String;
	}
}