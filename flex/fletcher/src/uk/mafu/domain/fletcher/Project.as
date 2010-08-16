package uk.mafu.domain.fletcher
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.fletcher.Project")]
	[Tab(title="Main",order=1,
		field=title,
		field=text,
		field=url
		
	)]
	[Tab(title="Images",order=2,field=thumb,field=images)]
		
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Chooser(label="title")]
	[Bindable]
	public class Project
	{
		public var pk:Number =-1;
		
		[Display(label="Title",lines=1)]
		public var title:String;
		
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var text:String;
		
		[Display(label="View panorama URL")]
		public var url:String;
		
		
		[Display(label="Thumbnail",widget="SingleImage")]
		public var thumb:ImageLink;
		
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Project Images",widget="ImageGallery")]
	  	public var images:Array = new Array();
	  	
	  	public var permalink:String;
	}
}