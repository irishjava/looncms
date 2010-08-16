package uk.gormley.domain
{
import mx.collections.ArrayCollection;

[RemoteClass(alias="uk.gormley.domain.TextResource")]
[Tab(title="Main",order=1,field=title,field=text,field=caption,field=date)]
[Tab(title="Filter",order=2,field=filter)]
[Tab(title="Pdfs",order=3,field=pdf1,field=pdf2,field=pdf3)]
[Tab(title="Urls",order=4,field=url1,field=url2,field=url3,field=url4,field=url5,field=url6)]
[Order(col="title",asc="false")]
[Columns(col="pk",col="title",col="filter")]
[Chooser(label="title")]
[Bindable]
public class TextResource extends AbstractPdfItem {
	
	[Display(label="Caption")]
	public var caption:String;
	
	[Display(label="Type",widget=EnumWidget,enums="essay,interview"
	,defaultValue="solo")]
	public var filter:String;
	
	[Display(label="Date")]
	public var date:Date;
	}
} 
