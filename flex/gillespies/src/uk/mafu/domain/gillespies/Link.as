package uk.mafu.domain.gillespies
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.gillespies.Link")]
	[Tab(title="Main",order=1,field=title,field=text,field=url)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Chooser(label="title")]
	[Bindable]
	public class Link
	{
		public var pk:Number = -1;
		
		[Display(label="Title")]
		public var title:String;
		
		[Display(label="Url")]
		public var url:String;
		
		[Display(label="Link Text",lines=1)]
		public var text:String;
	}
}