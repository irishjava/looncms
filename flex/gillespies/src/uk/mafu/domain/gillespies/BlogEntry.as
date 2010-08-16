package uk.mafu.domain.gillespies
{
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.PdfLink;
	import uk.mafu.loon.domain.data.VideoLink;
	
	[RemoteClass(alias="uk.mafu.domain.gillespies.BlogEntry")]
	[Order(col="pk",asc="false")]
	[Columns(col=pk,col=title)]
	[Tab(title="Main",order=1,field=title,field=text,field=date,field=url)]
	[Tab(title="Pdf",order=2,field=pdf)]
	[Tab(title="Video",order=3,field=video,field=videoThumbnail)]
	[Tab(title="Images",order=4,field=images)]
	[Chooser(label="title")]
	[Bindable]
	public class BlogEntry
	{
		public var pk:Number = -1;
		public var permalink:String;
		
		[Display(label="Title")]
		public var title:String;
		
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var text:String;
	
		[Display(label="Date")]
		public var date:Date;
		
		[Display(label="URL")]
		public var url:String;
		
		[Display(label="Pdf",widget=SinglePdf)]
		public var pdf:PdfLink;
		
		[Display(label="Video",widget=SingleVideo)]
		public var video:VideoLink;
		
		[Display(label="Video Thumbnail",widget=SingleImage)]
		public var videoThumbnail:ImageLink;
		
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Images",widget=ImageGallery)]
	  	public var images:Array = new Array();
	
	}
}