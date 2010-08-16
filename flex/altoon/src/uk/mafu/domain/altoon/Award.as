package uk.mafu.domain.altoon
{
	[RemoteClass(alias="uk.mafu.domain.altoon.Award")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title",col="date")]
	[Tab(title="Main",order=1,field="title",field="text",field="project",field="date")]
	[Chooser(label="title")]
	[Bindable]
	public class Award
	{
		public var pk:Number = -1;
		public var permalink:String;
		
		[Display(label="Project")]
		[Relationship(end="uk.mafu.domain.altoon::Project",type="ONE_TO_ONE")]
		public var project:Project;
		
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var text:String;
		
		[Display(label="Title")]
		public var title:String;
		
		[Display(label="Date")]
		public var date:Date;
	}
}