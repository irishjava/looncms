package uk.mafu.domain.altoon
{
	[RemoteClass(alias="uk.mafu.domain.altoon.Region")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="name")]
	[Tab(title="Main",order=1,field=name,field=locations)]
	[Chooser(label="name")]
	[Bindable]
	public class Region
	{
		public var pk:Number =-1;
		public var permalink:String;
		
		[Display(label="Name")]
		public var name:String;

		[Relationship(end="uk.mafu.domain.altoon::Location",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Locations")]
		public var locations:Array = new Array();
	}
}