package uk.mafu.domain.mch
{
	import uk.mafu.loon.domain.data.VideoLink;
	
	[RemoteClass(alias="uk.mafu.domain.mch.Project")]
	[Tab(title="Main",order=1,
		field=name,
		field=text
	)]
	[Tab(title="Images",order=2,field=thumb,field=images)]
	[Tab(title="Video",order=3,field=video)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="name")]
	[Chooser(label="name")]
	[Bindable]
	public class Project
	{
		public var pk:Number =-1;
		
		[Display(label="Name",lines=1)]
		public var name:String;
		
		[Display(label="Text",lines=20,widget=RichTextWidget)]
		public var text:String;
	
		[Display(label="Video",widget=SingleVideo)]
		public var video:VideoLink;
		
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Project Images",widget="ImageGallery")]
	  	public var images:Array = new Array();
	  	
	  	public var permalink:String;
	  
	}
}