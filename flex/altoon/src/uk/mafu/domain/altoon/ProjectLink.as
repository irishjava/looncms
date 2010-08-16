package uk.mafu.domain.altoon
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.altoon.ProjectLink")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="name")]
	[Tab(title="Main",order=1,field=name,field=project,field=image)]
	[Chooser(label="name")]
	[Bindable]
	public class ProjectLink
	{
		public var pk:Number =-1;

		public var permalink:String;
		
		[Display(label="Name")]
		public var name:String;
		
		[Display(label="Project")]
		[Relationship(end="uk.mafu.domain.altoon::Project",type="ONE_TO_ONE")]
		public var project:Project;
		
		[Display(label="image",widget=SingleImage)]
		public var image:ImageLink;
	}
}