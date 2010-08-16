package uk.gormley.domain
{
import mx.collections.ArrayCollection;

import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.VideoLink;
import uk.mafu.loon.domain.data.AudioLink;

[RemoteClass(alias="uk.gormley.domain.NewsItem")]
[Tab(title="Main",order=1,field=title,field=text,field=date)]
[Tab(title="Image",order=2,field=image)]
[Tab(title="Video",order=3,field=video)]
[Tab(title="Audio",order=4,field=audio)]
[Tab(title="Pdfs",order=5,field=pdf1,field=pdf2,field=pdf3)]
[Tab(title="Urls",order=6,field=url1,field=url2,field=url3,field=url4,field=url5,field=url6)]
[Order(col="title",asc="false")]
[Columns(col="pk",col="title")]
[Chooser(label="title")]
[Bindable]
public class NewsItem extends AbstractPdfItem {
	
	[Display(label="Date")]
	public var date:Date;
	
	[Display(label="Image",widget=SingleImage)]
	public var image:ImageLink;
	
	[Display(label="Video",widget=SingleVideo)]	
	public var video:VideoLink;
	
	[Display(label="Audio",widget=SingleAudio)]	
	public var audio:AudioLink;
	}
}
