package uk.mafu.domain.gillespies
{
	
	[RemoteClass(alias="uk.mafu.domain.gillespies.ProjectLocation")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="name")]
	[Tab(title="Main",order=1,field=name,field=latitude,field=longitude,field=projects)]
	[Chooser(label="name")]
	[Bindable]
	public class ProjectLocation
	{
		public var pk:Number =-1;
		public var permalink:String;

		[Display(label="Name")]
		public var name:String;
		
		[Display(label="Latitude")]
		public var latitude:Number;
		
		[Display(label="Longitude")]
		public var longitude:Number;
				
		[Relationship(end="uk.mafu.domain.gillespies::Project",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Projects")]
		public var projects:Array = new Array();
	}
}