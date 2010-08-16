package uk.mafu.kwa.domain{
import uk.mafu.loon.domain.data.ImageLink;
[RemoteClass(alias="uk.kwa.domain.Press")]
[Tab(title="Main",order=1,
	field=title,
	field=date,
	field=text,
	field=image,
	field=relatedProject,
	field=excite)]
[Order(col="pk",asc="false")]
[Columns(col="pk",col="title")]
[Chooser(label="title")]
[Bindable]
public class Press {

    public var pk:Number  = -1 ;

    [Display(label="Title",lines=1)]
    public var title:String;

    [Display(label="Date")]
    public var date:Date;

    [Display(label="Text",lines=5)]
    public var text:String;

	[Display(label="Image",widget=SingleImage)]
    public var image:ImageLink;

	[Display(label="Related Project")]
	[Relationship(end="uk.mafu.kwa.domain::Project",type="ONE_TO_ONE")]
    public var relatedProject:Project;

	[Display(label="Excite")]
	[Relationship(end="uk.mafu.kwa.domain::Excite",type="ONE_TO_ONE")]
    public var excite:Excite;
     
    public var permalink:String;
	
	}
}