package uk.mafu.domain.saatchi
{
	[RemoteClass(alias="uk.mafu.domain.saatchi.BrandStrategyGroup")]
	[Tab(title="Main",order=1,
		field=title,field=items
	)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Chooser(label="title")]
	[Bindable]
	public class BrandStrategyGroup
	{
		public var pk:Number =-1;
		
		[Display(label="Name",lines=1)]
		public var title:String;

	  	public var permalink:String;

	 	[Relationship(end="uk.mafu.domain.saatchi::BrandStrategyItem",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Brand Strategy Items")]
		public var items:Array = new Array();
	}
}