package uk.mafu.domain.gillespies
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.gillespies.Job")]
	[Tab(title="Main",order=1,field=title,field=text,field=date)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title",col="date")]
	[Chooser(label="title")]
	[Bindable]
	public class Job
	{
		public var pk:Number = -1;
		public var permalink:String;
		
		[Display(label="title")]
		public var title:String;
			
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var text:String;
		
		[Display(label="Date")]
		public var date:Date;
				
	}
}