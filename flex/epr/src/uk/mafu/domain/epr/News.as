package uk.mafu.domain.epr
{
	import uk.mafu.loon.domain.data.PdfLink;
	
	[RemoteClass(alias="uk.mafu.domain.epr.News")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Tab(title="Main Tab",order=1,
		field=title,
		field=subTitle,
		field=date,
		field=text,
		field=project,
		field=pdf,
		field=images
		)]
	[Chooser(label="project")]
	[Bindable]
	public class News 
	{
		public var pk:Number =-1;
		
		[Display(label="Title")]
		public var title:String;
		
		[Display(label="Subtitle")]
		public var subTitle:String;
		
		[Display(label="Date")]
		public var date:Date;
		
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var text:String;
		
		[Display(label="Project")]
		[Relationship(end="uk.mafu.domain.epr::Project",type="ONE_TO_ONE")]
		public var project:Project;
		
		[Display(label="PDF",widget=SinglePdf)]
		public var pdf:PdfLink;
		
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Project Images",widget="ImageGallery")]
	  	public var images:Array = new Array();
		
		public var permalink:String;
	}
}