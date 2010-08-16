package uk.gormley.domain
{
import mx.collections.ArrayCollection;

import uk.mafu.loon.domain.data.ImageLink;

[RemoteClass(alias="uk.gormley.domain.Slide")]
[Tab(title="Main",order=1,field=title,field=url,field=image)]
[Order(col="title",asc="false")]
[Columns(col="pk",col="title")]
[Chooser(label="title")]
[Bindable]
public class Slide {
	
	public var pk:Object;
	
	[Display(label="Title")]
	public var title:String;
	
	public var image:ImageLink;
	
	[Display(label="Url")]
	public var url:String;
	
	}
}
