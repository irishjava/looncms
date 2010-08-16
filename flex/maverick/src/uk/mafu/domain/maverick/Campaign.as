package uk.mafu.domain.maverick
{
	import uk.mafu.loon.domain.data.VideoLink;
	import uk.mafu.loon.domain.data.ImageLink;
	[RemoteClass(alias="uk.mafu.domain.maverick.Client")]
	[Tab(title="Main",order=1,
		field=title)]
	[Tab(title="Text 1",order=2,
		field=text1)]
	[Tab(title="Text 2",order=3,
		field=text2)]
	[Tab(title="Projects",order=4,
		field=projects)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Chooser(label="title")]
	[Bindable]
	public class Campaign
	{
		public var pk:Number =-1;
	
		[Display(label="Title",lines=1)]
		public var title:String;
		
		[Display(label="Text 1",lines=20,widget=RichTextWidget)]
		public var text1:String;

		[Display(label="Text 2",lines=20,widget=RichTextWidget)]
		public var text2:String;
	  	
	  	[Relationship(end="uk.mafu.domain.maverick::Project",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Projects")]
		public var projects:Array = new Array();
	  	
	  	public var permalink:String;
	  	
	}
}