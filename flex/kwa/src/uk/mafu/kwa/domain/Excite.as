package uk.mafu.kwa.domain{
import uk.mafu.loon.domain.data.ImageLink;

[RemoteClass(alias="uk.kwa.domain.Excite")]
[Tab(title="Main",order=1,
	field=title,field=text,field=image
)]
[Order(col="pk",asc="false")]
[Columns(col="pk",col="title")]
[Chooser(label="title")]
[Bindable]
public class Excite {
	public var pk:Number  = -1 ;
	
	[Display(label="Title",lines=1)]
	public var title:String;
	
	[Display(label="Text",lines=4)]
	public var text:String ;
	
	[Display(label="Image",widget=SingleImage)]
	public var image:ImageLink;
	}
}