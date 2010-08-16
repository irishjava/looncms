package uk.mafu.domain.maverick
{
	
	[RemoteClass(alias="uk.mafu.domain.maverick.Role")]
	[Tab(title="Main",order=1,
		field=name,
		field=people
	)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="name")]
	[Chooser(label="name")]
	[Bindable]
	public class Role{
	
		public var pk:Number =-1;
		
		[Display(label="Name",lines=1)]
		public var name:String;

		[Relationship(end="uk.mafu.domain.maverick::Person",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="people")]
		public var people:Array = new Array();
			
	  	public var permalink:String;	
	}
}