package uk.mafu.domain.altoon
{
	[RemoteClass(alias="uk.mafu.domain.altoon.Bibliography")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Tab(title="Main",order=1,field="title",field="text",field="date")]
	[Chooser(label="title")]
	[Bindable]
	public class Bibliography
	{
		public var pk:Number = -1;
		
		[Display(label="Title")]
		public var title:String;
		
		[Display(label="Text")]
		public var text:String;
		
		[Display(label="Date")]
		public var date:Date;
		
	
		
	}
}