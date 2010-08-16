package uk.mafu.domain.gillespies
{
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.PdfLink;
	
	[RemoteClass(alias="uk.mafu.domain.gillespies.Office")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="name")]
	[Tab(title="Main Tab",order=1,field=name,field=text,field=address1,field=address2,field=address3,field=city,field=country,field=postcode,field=phone,field=fax,field=email)]
	[Tab(title="PDF",order=2,field=pdf)]
	[Tab(title="Jobs",order=3,field=jobs)]
	[Tab(title="Map",order=4,field=map)]
	[Chooser(label="name")]
	[Bindable]
	public class Office 
	{
		public var pk:Number =-1;
		public var permalink:String;
		
		[Display(label="Name")]
		public var name:String;
		
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var text:String;
		
		[Display(label="Address 1")]
		public var address1:String;
		
		[Display(label="Address 2")]
		public var address2:String;
		
		[Display(label="Address 3")]
		public var address3:String;
		
		[Display(label="City")]
		public var city:String;
		
		[Display(label="Country")]
		public var country:String;

		[Display(label="Postcode")]
		public var postcode:String;

		[Display(label="Phone")]
		public var phone:String;
		
		[Display(label="Fax")]
		public var fax:String;
		
		[Display(label="Email")]
		public var email:String;
		
		[Display(label="Map",widget=SingleImage)]
		public var map:ImageLink;
		
		[Display(label="Pdf",widget=SinglePdf)]
		public var pdf:PdfLink;
		
		[Relationship(end="uk.mafu.domain.gillespies::Job",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Jobs")]
		public var jobs:Array = new Array();
	}
}