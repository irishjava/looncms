package uk.gormley.domain
{
import mx.collections.ArrayCollection;

import uk.mafu.loon.domain.data.ImageLink;

[RemoteClass(alias="uk.gormley.domain.StudioView")]

[Tab(title="Main",order=1,field=title,field=text,field=date)]
[Tab(title="Image",order=2,field=image)]
[Tab(title="Urls",order=3,field=url1,field=url2,field=url3,field=url4,field=url5,field=url)]
[Order(col="title",asc="false")]
[Columns(col="pk",col="title")]
[Chooser(label="title")]
[Bindable]
public class StudioView extends AbstractLinkItem {
	
	[Display(label="Image",widget=SingleImage)]
	public var image:ImageLink;
	
	[Display(label="Date")]
	public var date:Date;

	}
}
