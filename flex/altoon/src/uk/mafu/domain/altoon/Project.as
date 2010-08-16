package uk.mafu.domain.altoon
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.altoon.Project")]
	[Tab(title="Main",order=1,
	field=name,
	field=text,
	field=info,
	field=client,
	field=associateArchitect,
	field=size,
	field=design,
	field=completed)]
	[Tab(title="Images",order=2,field=images,field=thumbnail)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="name",col="client")]
	[Chooser(label="name")]
	[Bindable]
	public class Project
	{
		public var pk:Number =-1;
		public var permalink:String;
		
		[Display(label="Name",lines=1)]
		public var name:String;
			
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var text:String;
		
		[Display(label="Clients",lines=1)]
		public var client:String;
		
		[Display(label="Associate Architects",lines=1)]
		public var associateArchitect:String;
		
		[Display(label="Size",lines=1)]
		public var size:String;
		
		[Display(label="Design",lines=1)]
		public var design:String;
		
		[Display(label="Completed")]
		public var completed:String;
				
		[Display(label="Thumbnail",widget="SingleImage")]
		public var thumbnail:ImageLink;
		
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Project Images",widget="ImageGallery")]
	  	public var images:Array = new Array();
	  	
	}
}