package uk.gormley.domain
{
import uk.mafu.loon.domain.data.ImageLink;

[RemoteClass(alias="uk.gormley.domain.Art")]
[Tab(title="Main",order=1,field=title,field=type,field=text,field=date)]
[Tab(title="Image",order=2,field=image)]
[Tab(title="Images",order=3,field=images)]
[Tab(title="URLs",order=4,field=url1,field=url2,field=url3,field=url4,field=url5,field=url6)]
[Tab(title="Pdfs",order=6,field=pdf1,field=pdf2,field=pdf3)]
[Order(col="title",asc="false")]
[Columns(col="pk",col="title",col="type")]
[Chooser(label="title")]
[Bindable]
public class Art extends AbstractPdfItem {
	
	[Display(label="Type",widget=EnumWidget,enums="drawing,sculpture"
	,defaultValue="drawing")]
	public var type:String;
	
	[Display(label="Image",widget=SingleImage)]	
	public var image:ImageLink;
	
	[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
	[Display(label="Images",widget=ImageGallery)]
	public var images:Array;
	
	[Display(label="Date")]
	public var date:Date;
	}
}
