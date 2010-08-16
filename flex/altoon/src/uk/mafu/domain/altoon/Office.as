package uk.mafu.domain.altoon
{
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.PdfLink;
	
	[RemoteClass(alias="uk.mafu.domain.altoon.Office")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="name")]
	[Tab(title="Main",order=1,field="name",field="contactText",field="locationName",field="mapUrl")]
	[Tab(title="Images",order=2,field="image1",field="image2")]
	[Tab(title="Pdfs",order=3,field="pdf")]
	[Chooser(label="name")]
	[Bindable]
	public class Office
	{
		public var pk:Number =-1;
		public var permalink:String;
		
		[Display(label="Name")]
		public var name:String;

		[Display(label="Contact Text",lines=15)]
		public var contactText:String;
		
		[Display(label="image1",widget=SingleImage)]
		public var image1:ImageLink;
		
		[Display(label="image2",widget=SingleImage)]
		public var image2:ImageLink;
		
		[Display(label="pdf",widget=SingleImage)]
		public var pdf:PdfLink;
		
		[Display(label="locationName")]
		public var locationName:String;
		
		[Display(label="mapUrl")]
		public var mapUrl:String;
	}
}