package uk.mafu.domain.altoon
{
	[RemoteClass(alias="uk.mafu.domain.altoon.Role")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Tab(title="Main",order=1,field=title,field=singular,field=members)]
	[Chooser(label="title")]
	[Bindable]
	public class Role
	{
		public var pk:Number =-1;
		public var permalink:String;
		
		[Display(label="Title")]
		public var title:String;
		
		[Display(label="Singular Name")]
		public var singular:String;
		
		[Relationship(end="uk.mafu.domain.altoon::Person",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Members")]
		public var members:Array = new Array();
		
		
	}
}