package uk.mafu.domain.saatchi
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.maverick.AttitudeItem")]
	[Tab(title="Main",order=1,
		field=title
	)]
	[Tab(title="Body",order=2,
		field=body
	)]
	[Tab(title="Quote",order=3,
		field=quoteBody,field=quoteReference
	)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Chooser(label="title")]
	[Bindable]
	public class AttitudeItem
	{
		public var pk:Number =-1;
		
		[Display(label="Name",lines=1)]
		public var title:String;
		
	 
		
		[Display(label="Body",lines=20)]
		public var body:String;

		[Display(label="Quote body",lines=20)]
		public var quoteBody:String;
		
		[Display(label="Quote reference",lines=20)]
		public var quoteReference:String;
		
	  	public var permalink:String;
	  
	}
}