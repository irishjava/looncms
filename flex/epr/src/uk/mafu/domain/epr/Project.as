package uk.mafu.domain.epr
{
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.PdfLink;
	import uk.mafu.loon.domain.data.VideoLink;
	
	[RemoteClass(alias="uk.mafu.domain.epr.Project")]
	[Tab(title="Text",order=1,
		field=title,
		field=subTitle,
		field=text,
		field=client,
		field=location,
		field=servicesProvided,
		field=completed,
		field=value,
		field=photographer
	)]
	[Tab(title="Images",order=2,
		field=images,
		field=thumbnail
	)]
	[Tab(title="Video",order=4,
		field=video
	)]
	[Tab(title="PDF",order=5,
		field=pdf
	)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Chooser(label="title")]
	[Bindable]
	public class Project
	{
		public var pk:Number =-1;
		
		[Display(label="Title",lines=1)]
		public var title:String;
		
		[Display(label="Subtitle",lines=1)]
		public var subTitle:String;
		
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var text:String;
		
		[Display(label="Client",lines=1)]
		public var client:String;
		
		[Display(label="Location",lines=1)]
		public var location:String;
		
		[Display(label="Thumbnail",widget="SingleImage")]
		public var thumbnail:ImageLink;
		
		[Display(label="Services",lines=1)]
		public var servicesProvided:String;
		
	 	[Display(label="Completed")]
		public var completed:Date;
		
		[Display(label="PDF",widget=SinglePdf)]
		public var pdf:PdfLink;
		
		[Display(label="Video",widget=SingleVideo)]
		public var video:VideoLink;
			
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Project Images",widget="ImageGallery")]
	  	public var images:Array = new Array();
	  	
	 	[Display(label="Value",lines=1)]
		public var value:String;
		
		[Display(label="Photographer",lines=1)]
		public var photographer:String;
		
		public var permalink:String;
	}
}