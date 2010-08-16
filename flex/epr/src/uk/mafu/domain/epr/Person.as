package uk.mafu.domain.epr
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	
	[RemoteClass(alias="uk.mafu.domain.epr.Person")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="name")]
	[Tab(title="Main",order=1,
		field=name,
		field=email,
		field=date,
		field=phone,
		field=role
	)]
	[Tab(title="Images",order=2,field=image)]
	[Chooser(label="name")]
	[Bindable]
	public class Person
	{
		public var pk:Number =-1;

		[Display(label="Name")]
		public var name:String;
		
		[Display(label="Email")]
		public var email:String; 

		[Display(label="Type")]
		[Enum(options="'manager'->Manager,'pleb'->Worker")]
		public var type:String; 
		
		[Display(label="Phone")]
		public var phone:String; 
				
		[Display(label="Role")]
		public var role:String; 
		
		[Display(label="Image",widget="SingleImage")]
		public var image:ImageLink;
		
		public var permalink:String; 
		
	}
}
