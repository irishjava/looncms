package uk.mafu.domain.altoon
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	
	[RemoteClass(alias="uk.mafu.domain.altoon.Service")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Tab(title="Main",order=1,field=title,field=text)]
	[Tab(title="Images",order=2,field=images)]
	[Chooser(label="title")]
	[Bindable]
	public class Service
	{
		public var pk:Number =-1;
		public var permalink:String;
		
		[Display(label="Name")]
		public var title:String;

		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var text:String;
			
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Project Images",widget="ImageGallery")]
	  	public var images:Array = new Array();
		

	}
}