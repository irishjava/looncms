package uk.mafu.domain.gillespies
{
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.VideoLink;
	
	
	[RemoteClass(alias="uk.mafu.domain.gillespies.Profile")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Tab(title="Main",order=1,field=title,field=text1,field=text2)]
	[Chooser(label="title")]
	[Bindable]
	public class Profile
	{
		public var pk:Number =-1;
		public var permalink:String;
		
		[Display(label="Title")]
		public var title:String;
	
		[Display(label="Text1",lines=15,widget=RichTextWidget)]
		public var text1:String;
	
		[Display(label="Text2",lines=15,widget=RichTextWidget)]
		public var text2:String;
	}
}