package uk.mafu.domain.maverick
{
	import uk.mafu.loon.domain.data.VideoLink;
	
	[RemoteClass(alias="uk.mafu.domain.maverick.ProjectCategory")]
	[Tab(title="Main",order=1,
		field=title,
		field=text1,
		field=text2,
		field=projects
	)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Chooser(label="title")]
	[Bindable]
	public class ProjectCategory
	{
		public var pk:Number =-1;
	
		[Display(label="Name",lines=1)]
		public var title:String;
		
		[Display(label="Text1",lines=20,widget=RichTextWidget)]
		public var text1:String;
		
		[Display(label="Text2",lines=20,widget=RichTextWidget)]
		public var text2:String;
		
		[Relationship(end="uk.mafu.domain.maverick::Project",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Projects")]
		public var projects:Array = new Array();
	  	
	  	public var permalink:String;
	}
}