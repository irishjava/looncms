package uk.mafu.domain.gillespies
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.gillespies.Role")]
	[Tab(title="Main",order=1,field=name,field=singular)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="name",col="singular")]
	[Chooser(label="name")]
	[Bindable]
	public class Role
	{
		public var pk:Number = -1;
		
		[Display(label="Name")]
		public var name:String;
		
		[Display(label="Singular Name")]
		public var singular:String;
		
		public var permalink:String;
	}
}