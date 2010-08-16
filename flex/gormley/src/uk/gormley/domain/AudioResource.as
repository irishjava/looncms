package uk.gormley.domain
{
import mx.collections.ArrayCollection;
import uk.mafu.loon.domain.data.AudioLink;

[RemoteClass(alias="uk.gormley.domain.AudioResource")]
[Tab(title="Main",order=1,field=title,field=text,field=date,field=audio)]
[Tab(title="Pdfs",order=2,field=pdf1,field=pdf2,field=pdf3)]
[Tab(title="Urls",order=3,field=url1,field=url2,field=url3,field=url4,field=url5,field=url6)]
[Order(col="title",asc="false")]
[Columns(col="pk",col="title")]
[Chooser(label="title")]
[Bindable]
public class AudioResource extends AbstractPdfItem {

	[Display(label="Date")]
	public var date:Date; 

 	[Display(label="Audio")]
        [Relationship(end="uk.mafu.loon.domain.data::AudioLink",type="ONE_TO_ONE")]
        public var audio:AudioLink;
    }
}
