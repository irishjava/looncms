package uk.mafu.domain.maverick
{
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.VideoLink;
	
	[RemoteClass(alias="uk.mafu.domain.maverick.Showreel")]
	[Tab(title="Basic",order=1,
		field=title,
		field=text1,
		field=text2
	)]
	[Tab(title="Media",order=2,
		field=video,
		field=videoImg
	)]
	[Tab(title="Campaign",order=3,
		field=campaign
	)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Chooser(label="title")]
	[Bindable]
	public class Showreel 
	{
		public var pk:Number =-1;

		[Display(label="Title")]
		public var title:String;
		
		[Display(label="Text1",lines=20,widget=RichTextWidget)]
		public var text1:String;
		
		[Display(label="Text2",lines=20,widget=RichTextWidget)]
		public var text2:String;

		//Media 
		[Display(label="Video",widget=SingleVideo)]
		public var video:VideoLink;
		
		[Display(label="Video Image",widget=SingleImage)]
		public var videoImg:ImageLink;

		//Relationships

		[Display(label="Campaign")]
		[Relationship(end="uk.mafu.domain.maverick::Campaign",type="ONE_TO_ONE")]
		public var campaign:Campaign;
	  
	}
}