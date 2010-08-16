package uk.mafu.domain.gillespies
{
	import uk.mafu.loon.domain.data.PdfLink;
	
	
	[RemoteClass(alias="uk.mafu.domain.gillespies.PressRelease")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title")]
	[Tab(title="Main",order=1,field=title,field=text,field=pdf,field=project)]
	[Chooser(label="title")]
	[Bindable]
	public class PressRelease
	{
		public var pk:Number =-1;
		public var permalink:String;
		
		[Display(label="Title")]
		public var title:String;
		
		[Display(label="Text",lines=15,widget=RichTextWidget)]
		public var text:String;
		
		[Display(label="Pdf",widget=SinglePdf)]
		public var pdf:PdfLink;
		 
		[Display(label="Project")]
		[Relationship(end="uk.mafu.domain.gillespies::Project",type="ONE_TO_ONE")]
		public var project:Project;
	}
}