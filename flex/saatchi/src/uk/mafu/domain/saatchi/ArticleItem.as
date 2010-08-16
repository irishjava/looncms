package uk.mafu.domain.saatchi
{
	[RemoteClass(alias="uk.mafu.domain.saatchi.ArticleItem")]
	[Tab(title="Main",order=1,
		field=title,field=body,field=date,field=url
	)]
	[Order(col="",asc="false")]
	[Columns(col="pk",col="title")]
	[Chooser(label="title")]
	[Bindable]
	public class ArticleItem
	{
		public var pk:Number =-1;
		
		[Display(label="Name",lines=1)]
		public var title:String;
		
		[Display(label="Text",lines=20)]
		public var body:String;
		
		[Display(label="Date")]
		public var date:Date;
		
		[Display(label="URL")]
		public var url:String;
	}
}