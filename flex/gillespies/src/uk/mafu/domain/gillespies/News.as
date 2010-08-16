package uk.mafu.domain.gillespies
{
	import uk.mafu.loon.domain.data.PdfLink;
	
	[RemoteClass(alias="uk.mafu.domain.gillespies.News")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Tab(title="Main Tab",order=1,field=title,field=text,field=url,field=date,field=project,field=images,field=pdf)]
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

		[Display(label="URL")]
		public var url:String;

	  	[Display(label="Date")]
		public var date:Date;
		
		[Display(label="Project")]
		[Relationship(end="uk.mafu.domain.gillespies::Project",type="ONE_TO_ONE")]
		public var project:Project;
		
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Project Images",widget=ImageGallery)]
	  	public var images:Array = new Array();
	  	
	  	[Display(label="Pdf",widget=SinglePdf)]
		public var pdf:PdfLink;
	  	
 	}
}