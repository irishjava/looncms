package uk.mafu.domain.fletcher
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.fletcher.Slide")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Tab(title="Main",order=1,
		field=title,
		field=image,
		field=project
	)]
	[Chooser(label="title")]
	[Bindable]
	public class Slide
	{
		public var pk:Number =-1;

		[Display(label="Title")]
		public var title:String;
		
		[Display(label="Image",widget=SingleImage)]
		public var image:ImageLink;
		
		[Display(label="Project")]
		[Relationship(end="uk.mafu.domain.fletcher::Project",type="ONE_TO_ONE")]
		public var project:Project;
		
		
	}
}