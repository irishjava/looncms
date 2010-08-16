package uk.mafu.domain.fletcher
{
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.PdfLink;
	
	[RemoteClass(alias="uk.mafu.domain.fletcher.Press")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Tab(title="Main",order=1,
		field=title,
		field=pdf,
		field=thumb)]
	[Tab(title="Image",order=2,
		field=image)]
	[Tab(title="Text",order=3,
		field=text1,
		field=text2)]
	[Chooser(label="title")]
	[Bindable]
	public class Press 
	{
		public var pk:Number =-1;
		
		[Display(label="Title")]
		public var title:String;
							
		[Display(label="Thumbnail",widget="SingleImage")]
		public var thumb:ImageLink;
		
		[Display(label="Image",widget="SingleImage")]
		public var image:ImageLink;
		
		[Display(label="Text1",lines=15,widget=RichTextWidget)]
		public var text1:String;
		
		[Display(label="Text2",lines=15,widget=RichTextWidget)]
		public var text2:String;

		[Display(label="pdf",widget=SingleImage)]
		public var pdf:PdfLink;
		
		public var permalink:String;
	}
}
