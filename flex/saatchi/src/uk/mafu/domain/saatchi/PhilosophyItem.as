package uk.mafu.domain.saatchi
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.saatchi.PhilosophyItem")]
	[Tab(title="Main",order=1,field=title,field=body)]
	[Tab(title="Quote",order=2,field=quoteBody,field=quoteReference)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Chooser(label="title")]
	[Bindable]
	public class PhilosophyItem
	{
		public var pk:Number =-1;
		
		[Display(label="Name",lines=1)]
		public var title:String;
		
		[Display(label="body",lines=20)]
		public var body:String;
		 
		[Display(label="quoteBody",lines=15)]
		public var quoteBody:String;
		
		[Display(label="quoteReference",lines=15)]
		public var quoteReference:String;
		  
	  	public var permalink:String;
	}
}