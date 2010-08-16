package uk.mafu.domain.gillespies
{
	[RemoteClass(alias="uk.mafu.domain.gillespies.Assignment")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Tab(title="Main",order=1,field=title,field=role,field=person,field=office)]
	[Chooser(label="title")]
	[Bindable]
	public class Assignment
	{
		public var pk:Number = -1;
		
		[Display(label="Title")]
		public var title:String;
		
		[Display(label="Role")]
		[Relationship(end="uk.mafu.domain.gillespies::Role",type="ONE_TO_ONE")]
		public var role:Role;
		
		[Display(label="Person")]
		[Relationship(end="uk.mafu.domain.gillespies::Person",type="ONE_TO_ONE")]
		public var person:Person;
		
		[Display(label="Office")]
		[Relationship(end="uk.mafu.domain.gillespies::Office",type="ONE_TO_ONE")]
		public var office:Office;
		
	}
}