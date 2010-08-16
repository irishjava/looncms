package uk.mafu.domain.maverick
{
	import uk.mafu.loon.domain.data.VideoLink;
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.maverick.Person")]
	[Tab(title="Main",order=1,
		field=name,
		field=position,
		field=bio,
		field=project
	)]
	[Tab(title="Media",order=1,
		field=image,
		field=thumbnail,
		field=video
	)]
	
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="name")]
	[Chooser(label="name")]
	[Bindable]
	public class Person
	{
		public var pk:Number =-1;
		
		[Display(label="Name",lines=1)]
		public var name:String;
		
		[Display(label="Text",lines=20,widget=RichTextWidget)]
		public var bio:String;
	
		[Display(label="Image",widget=SingleImage)]
		public var image:ImageLink;
		 
		[Display(label="Position")]
		public var position:String;
	 
		[Display(label="Thumbnail",widget=SingleImage)]
		public var thumbnail:ImageLink;
		 
		[Display(label="Video",widget=SingleVideo)]
		public var video:VideoLink;
		 
		[Display(label="Project")]
		[Relationship(end="uk.mafu.domain.maverick::Project",type="ONE_TO_ONE")]
		public var project:Project;

	  	public var permalink:String;
	}
}