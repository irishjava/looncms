package uk.mafu.domain.fletcher
{
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.PdfLink;
	
	[RemoteClass(alias="uk.mafu.domain.fletcher.Website")]
	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="identifier")]
	[Tab(title="Identifier",order=1,field=identifier)]
	[Tab(title="Practice",order=2,field=practiceAbout,field=practiceStatement,field=practiceImage)]
	[Tab(title="Contact",order=3,field=address,field=contact,field=email,field=googleLink,field=contactPdf,field=contactImage)]
	[Tab(title="Project Categories",order=4,field=projectCategories	)]
	[Tab(title="Projects",order=5,field=projects)]
	[Tab(title="Slides",order=6,field=slides)]
	[Tab(title="Press Items",order=7,field=pressItems)]
	[Chooser(label="identifier")]
	[Bindable]
	public class Website
	{
		public var pk:Number =-1;
 
 		[Display(label="Identifier")]
		public var identifier:String;
	
		[Display(label="Address",lines=15,widget=RichTextWidget)]
		public var address:String;
		
		[Display(label="Contact",lines=15,widget=RichTextWidget)]
		public var contact:String;
		
		[Display(label="Email",lines=15,widget=RichTextWidget)]
		public var email:String;
		
		[Display(label="Google Link")]
		public var googleLink:String;
		
		[Display(label="PDF",widget=SinglePdf)]
		public var contactPdf:PdfLink;
		
		[Display(label="Image",widget=SingleImage)]
		public var contactImage:ImageLink;
		
		[Display(label="About",lines=15,widget=RichTextWidget)]
		public var practiceAbout:String;
		
		[Display(label="Statement",lines=15,widget=RichTextWidget)]
		public var practiceStatement:String;
		
		[Display(label="Image",widget=SingleImage)]
		public var practiceImage:ImageLink;
		
		[Relationship(end="uk.mafu.domain.fletcher::ProjectCategory",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Project Categories")]
		public var projectCategories:Array = new Array();

		[Relationship(end="uk.mafu.domain.fletcher::Project",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Projects")]
		public var projects:Array = new Array();

		[Relationship(end="uk.mafu.domain.fletcher::Slide",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Slides")]
		public var slides:Array = new Array();
		
		[Relationship(end="uk.mafu.domain.fletcher::Press",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Press Items")]
		public var pressItems:Array = new Array();
	}
}