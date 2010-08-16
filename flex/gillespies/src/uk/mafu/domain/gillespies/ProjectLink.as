package uk.mafu.domain.gillespies
{
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.VideoLink;
	
	[RemoteClass(alias="uk.mafu.domain.gillespies.ProjectLink")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="name")]
	[Tab(title="Main",order=1,field=name,field=image,field=project)]
	[Chooser(label="name")]
	[Bindable]
	public class ProjectLink
	{
		public var pk:Number =-1;
		
		[Display(label="Name")]
		public var name:String;
	
		[Display(label="Image",widget=SingleImage)]
		public var image:ImageLink;
		
		[Display(label="Project")]
		[Relationship(end="uk.mafu.domain.gillespies::Project",type="ONE_TO_ONE")]
		public var project:Project;
	}
}