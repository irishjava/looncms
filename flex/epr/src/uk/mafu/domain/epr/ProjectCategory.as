package uk.mafu.domain.epr
{
	
	[RemoteClass(alias="uk.mafu.domain.epr.ProjectCategory")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Tab(title="Main",order=1,
		field=title,
		field=projects
	)]
	[Chooser(label="title")]
	[Bindable]
	public class ProjectCategory
	{
		public var pk:Number =-1;

		[Display(label="Name")]
		public var title:String;
		
		[Relationship(end="uk.mafu.domain.epr::Project",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Projects")]
		public var projects:Array = new Array();
		
		public var permalink:String;
	}
}