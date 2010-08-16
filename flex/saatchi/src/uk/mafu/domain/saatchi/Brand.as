package uk.mafu.domain.saatchi
{
	[RemoteClass(alias="uk.mafu.domain.saatchi.Brand")]
	[Tab(title="Main",order=1,
		field=title,field=products
	)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Chooser(label="title")]
	[Bindable]
	public class Brand
	{
		public var pk:Number =-1;
		
		[Display(label="Name",lines=1)]
		public var title:String;
		
	  	public var permalink:String;
	  	
		[Relationship(end="uk.mafu.domain.saatchi::Product",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Products")]
		public var products:Array = new Array();
	}
}