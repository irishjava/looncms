package uk.mafu.domain.gillespies
{
	import uk.mafu.loon.domain.data.ImageLink;
	
	[RemoteClass(alias="uk.mafu.domain.gillespies.Client")]
	[Tab(title="Main",order=1,field=title,field=text,field=testimonials)]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Chooser(label="title")]
	[Bindable]
	public class Client
	{
		public var pk:Number = -1;
		
		[Display(label="Title")]
		public var title:String;
		
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var text:String;
			
		[Relationship(end="uk.mafu.domain.gillespies::Testimonial",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Testimonials")]
		public var testimonials:Array = new Array();
	}
}