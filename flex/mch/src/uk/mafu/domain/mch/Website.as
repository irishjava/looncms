package uk.mafu.domain.mch
{
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.PdfLink;
	
	[RemoteClass(alias="uk.mafu.domain.mch.Website")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="identifier")]
	[Tab(title="Identifier",order=1,field=identifier)]
	[Tab(title="Map",order=2,field=map)]
	[Tab(title="Contact",order=3,field=contactText)]
	[Tab(title="Credit",order=4,field=creditText)]
	[Tab(title="Banner",order=5,field=bannerText)]
	[Tab(title="Project Categories",order=6,field=projectCategories)]
	[Tab(title="Project Links",order=6,field=projectLinks)]
	[Chooser(label="identifier")]
	[Bindable]
	public class Website
	{
		public var pk:Number =-1;
 
 		[Display(label="Identifier")]
		public var identifier:String;
	
		[Display(label="Map",widget=SingleImage)]
		public var map:ImageLink;

		[Display(label="Contact Text",lines=15,widget=RichTextWidget)]
		public var contactText:String;
		
		[Display(label="Credit Text",lines=15,widget=RichTextWidget)]
		public var creditText:String;
		
		[Display(label="Banner Text",lines=15,widget=RichTextWidget)]
		public var bannerText:String;
		
		[Relationship(end="uk.mafu.domain.mch::ProjectCategory",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Project Categories")]
		public var projectCategories:Array = new Array();

		[Relationship(end="uk.mafu.domain.mch::ProjectLink",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Slides")]
		public var projectLinks:Array = new Array();
	}
}