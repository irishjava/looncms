package uk.mafu.domain.altoon
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	
	[RemoteClass(alias="uk.mafu.domain.altoon.Person")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="name")]
	[Tab(title="Main",order=1,field=name,field=suffix,field=about,field=image)]
	[Chooser(label="name")]
	[Bindable]
	public class Person
	{
		public var pk:Number =-1;
		public var permalink:String;
		
		[Display(label="Name")]
		public var name:String;
		
		[Display(label="Suffix")]
		public var suffix:String;

		[Display(label="About",lines=15,widget=RichTextWidget)]
		public var about:String;
		
		[Display(label="Image",widget=SingleImage)]
		public var image:ImageLink;
		
		

	}
}