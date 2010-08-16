package uk.mafu.domain.saatchi
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.saatchi.ViewItem")]
	[Tab(title="Main",order=1,
		field=title,field=date
	)]
	[Tab(title="Body",order=2,
		field=body
	)]
	[Tab(title="Images",order=3,
		field=images
	)]
	[Order(col="title",asc="false")]
	[Columns(col="pk",col="title")]
	[Chooser(label="title")]
	[Bindable]
	public class ViewItem
	{
		public var pk:Number =-1;
		
		[Display(label="Name",lines=1)]
		public var title:String;
		
		[Display(label="Date")]
		public var date:Date;
		
		[Display(label="Body",lines=20)]
		public var body:String;
		
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Project Images",widget="ImageGallery")]
	  	public var images:Array = new Array();
	  	
	  	public var permalink:String;
	}
}