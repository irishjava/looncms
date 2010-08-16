package uk.mafu.domain.maverick
{
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.VideoLink;
	
	[RemoteClass(alias="uk.mafu.domain.maverick.Project")]
	[Tab(title="Basic",order=1,
		field=title,
		field=date,
		field=text1,
		field=text2)]
	[Tab(title="Media",order=2,
		field=thumb,
		field=videoImg,
		field=video)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Chooser(label="title")]
	[Bindable]
	public class Project 
	{
		public var pk:Number =-1;
		
		[Display(label="Title",lines=1)]
		public var title:String;
		
		[Display(label="Date")]
		public var date:Date;
		
		[Display(label="Text1",lines=20,widget=RichTextWidget)]
		public var text1:String;
	
		[Display(label="Text2",lines=20,widget=RichTextWidget)]
		public var text2:String;
	
		//Media 
		
		[Display(label="Thumbnail",widget=SingleImage)]
		public var thumb:ImageLink;
		
		[Display(label="VideoImage",widget=SingleImage)]
		public var videoImg:ImageLink;
		
		[Display(label="Video",widget=SingleVideo)]
		public var video:VideoLink;
	
	  	public var permalink:String;
	}
}