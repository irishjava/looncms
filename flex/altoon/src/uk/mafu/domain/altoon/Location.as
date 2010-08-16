package uk.mafu.domain.altoon
{
	[RemoteClass(alias="uk.mafu.domain.altoon.Location")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="name")]
	[Tab(title="Main",order=1,field=name,field=latitude,field=longitude,field=projects)]
	[Chooser(label="name")]
	[Bindable]
	public class Location
	{
		public var pk:Number =-1;
		public var permalink:String;
		
		[Display(label="Longitude")]
		public var longitude:Number;
		
		[Display(label="Latitude")]
		public var latitude:Number;
		
		[Display(label="Name")]
		public var name:String;

		[Relationship(end="uk.mafu.domain.altoon::Project",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Projects")]
		public var projects:Array = new Array();
	}
}