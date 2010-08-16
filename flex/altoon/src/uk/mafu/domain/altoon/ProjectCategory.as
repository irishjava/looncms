package uk.mafu.domain.altoon
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	
	[RemoteClass(alias="uk.mafu.domain.altoon.ProjectCategory")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Tab(title="Main",order=1,field=title,field=projects)]
	[Chooser(label="title")]
	[Bindable]
	public class ProjectCategory
	{
		public var pk:Number =-1;
		public var permalink:String;

		[Display(label="Title")]
		public var title:String;
			
		[Relationship(end="uk.mafu.domain.altoon::Project",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Projects")]
		public var projects:Array = new Array();
	}
}