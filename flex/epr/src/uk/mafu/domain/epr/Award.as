package uk.mafu.domain.epr
{
	import uk.mafu.loon.domain.data.PdfLink;
	
	[RemoteClass(alias="uk.mafu.domain.epr.Award")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Tab(title="Main",order=1,
		field=title,
		field=text,
		field=project,
		field=date,
		field=pdf,
		field=images
	)]
	[Chooser(label="title")]
	[Bindable]
	public class Award
	{
		public var pk:Number = -1;
		
		[Display(label="Title")]
		public var title:String;
		
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var text:String;
	
		[Display(label="Project")]
		[Relationship(end="uk.mafu.domain.epr::Project",type="ONE_TO_ONE")]
		public var project:Project;
		
		[Display(label="Date")]
		public var date:Date;
	
		[Display(label="PDF",widget=SinglePdf)]
		public var pdf:PdfLink;
	
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Project Images",widget="ImageGallery")]
	  	public var images:Array = new Array();
	
		public var permalink:String;
		
		
		 
	}
}