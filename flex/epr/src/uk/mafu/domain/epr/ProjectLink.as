package uk.mafu.domain.epr
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.epr.ProjectLink")]
	[Tab(title="text",order=1,field=title,field=image,field=project)]
	[Columns(col="pk",col="title")]
	[Chooser(label="title")]
	[Bindable]
	public class ProjectLink
	{
		public var pk:Number =-1;
		public var permalink:String;
		
		[Display(label="Name")]
		public var title:String;
		
		[Display(label="Image",widget="SingleImage")]
		public var image:ImageLink;
		
		[Display(label="Project")]
		[Relationship(end="uk.mafu.domain.epr::Project",type="ONE_TO_ONE")]
		public var project:Project;
		
		
	}
}