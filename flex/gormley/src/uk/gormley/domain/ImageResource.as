package uk.gormley.domain
{
import mx.collections.ArrayCollection;
import uk.mafu.loon.domain.data.ImageLink;

[RemoteClass(alias="uk.gormley.domain.ImageResource")]
[Tab(title="Main",order=1,field=title,field=text,field=image,field=date)]
[Tab(title="Pdfs",order=2,field=pdf1,field=pdf2,field=pdf3)]
[Tab(title="Urls",order=3,field=url1,field=url2,field=url3,field=url4,field=url5,field=url6)]
[Order(col="title",asc="false")]
[Columns(col="pk",col="title")]
[Chooser(label="title")]
[Bindable]
public class ImageResource extends AbstractPdfItem {

        [Display(label="Date")]
        public var date:Date;
 
        [Display(label="Image")]
        [Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_ONE")]
        public var image:ImageLink;
        }
}
