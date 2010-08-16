package uk.mafu.domain.gillespies
{
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.VideoLink;
	
	[RemoteClass(alias="uk.mafu.domain.gillespies.Person")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="name")]
	[Tab(title="Main",order=1,field=name,field=info)]
	[Tab(title="Image",order=2,field=image)]
	[Tab(title="Video",order=3,field=video,
							field=videoThumbnail
	)]
	[Chooser(label="name")]
	[Bindable]
	public class Person
	{
		public var pk:Number =-1;
		public var permalink:String;
		
		[Display(label="Name")]
		public var name:String;
	
		[Display(label="Info",lines=15,widget=RichTextWidget)]
		public var info:String;
		
		[Display(label="Image",widget=SingleImage)]
		public var image:ImageLink;
		
		[Display(label="Video",widget=SingleVideo)]
		public var video:VideoLink;
		
		[Display(label="Thumbnail",widget=SingleImage)]
		public var videoThumbnail:ImageLink;
	}
}