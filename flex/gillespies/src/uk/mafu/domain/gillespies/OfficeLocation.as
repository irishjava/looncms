package uk.mafu.domain.gillespies
{
	[RemoteClass(alias="uk.mafu.domain.gillespies.OfficeLocation")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="name")]
	[Tab(title="Main Tab",order=1,field=name,field=longitude,field=latitude,field=offices)]
	[Chooser(label="name")]
	[Bindable]
	public class OfficeLocation
	{
		public var pk:Number =-1;
		public var permalink:String;
		
		[Display(label="Name")]
		public var name:String;
		
		[Display(label="Longitude")]
		public var longitude:Number;
		
		[Display(label="Lattitude")]
		public var latitude:Number;
				
		[Relationship(end="uk.mafu.domain.gillespies::Office",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Offices")]
		public var offices:Array = new Array();
	}
}