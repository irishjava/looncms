package uk.mafu.domain.altoon
{
	[RemoteClass(alias="uk.mafu.domain.altoon.News")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Tab(title="Main",order=1,field=title,field=text,field=date,field=project,field=images)]
	[Chooser(label="title")]
	[Bindable]
	public class News 
	{
		public var pk:Number =-1;
		public var permalink:String;
		
		[Display(label="Title")]
		public var title:String;
		
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var text:String;
		
		[Display(label="Date")]
		public var date:Date;
		
		[Display(label="Project")]
		[Relationship(end="uk.mafu.domain.altoon::Project",type="ONE_TO_ONE")]
		public var project:Project;
		
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Project Images",widget="ImageGallery")]
	  	public var images:Array = new Array();
		
		
		
		
		
	}
}