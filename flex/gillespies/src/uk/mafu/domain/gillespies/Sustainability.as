package uk.mafu.domain.gillespies
{
	import uk.mafu.loon.domain.data.PdfLink;
	import uk.mafu.loon.domain.data.VideoLink;
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.gillespies.Sustainability")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Tab(title="Main",order=1,field=title,field=text,field=pdf,field=images,field=url)]
	[Tab(title="Video",order=2,field=video,field=videoThumbnail)]
	[Chooser(label="title")]
	[Bindable]
	public class Sustainability
	{
		public var pk:Number =-1;
		public var permalink:String;
		
		[Display(label="Title")]
		public var title:String;
		
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var text:String;
		
		[Display(label="Pdf",widget=SinglePdf)]
		public var pdf:PdfLink;

		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Images",widget=ImageGallery)]
	  	public var images:Array = new Array();
		
		[Display(label="Video",widget=SingleVideo)]
		public var video:VideoLink;
		
		[Display(label="Thumbnail",widget=SingleImage)]
		public var videoThumbnail:ImageLink;

		[Display(label="URL")]
		public var url:String;
	}
}