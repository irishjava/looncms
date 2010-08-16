package uk.mafu.domain.saatchi
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.saatchi.NewsItem")]
	[Tab(title="Main",order=1,
		field=title,field=body,field=date,field=image
	)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Chooser(label="title")]
	[Bindable]
	public class NewsItem
	{
	 	public var pk:Number =-1;
		
		[Display(label="Name",lines=1)]
		public var title:String;
		
		[Display(label="Body",lines=20)]
		public var body:String;
	 
		[Display(label="Date")]
		public var date:Date;
		
	  	[Display(label="Image",widget=SingleImage)]
		public var image:ImageLink;
	  	
	  	public var permalink:String;
	}
}