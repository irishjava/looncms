package uk.mafu.kwa.domain{
import uk.mafu.loon.domain.data.ImageLink;

[RemoteClass(alias="uk.kwa.domain.ProjectSlide")]
[Tab(title="Main",order=1,
	field=caption,
	field=title,
	field=excite,
	field=image
)]
[Order(col="pk",asc="false")]
[Columns(col="pk",col="title")]
[Chooser(label="title")]
[Bindable]

public class ProjectSlide {

    public var pk:Number  = -1 ;
    
    [Display(label="Caption",lines=1)] 
    public var caption:String;
    
    [Display(label="Title",lines=1)] 
    public var title:String;
    
    [Display(label="Excite")]
	[Relationship(end="uk.mafu.kwa.domain::Excite",type="ONE_TO_ONE")]
    public var excite:Excite;
    
    [Display(label="Image",widget=SingleImage)]
	public var image:ImageLink;
    
	}
}