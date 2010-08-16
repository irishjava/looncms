package uk.mafu.kwa.domain{
	import uk.mafu.loon.domain.data.ImageLink;
	
[RemoteClass(alias="uk.kwa.domain.HomePageSlide")]
[Tab(title="Main",order=1,
	field=title,field=relatedProject,field=image
)]
[Order(col="pk",asc="false")]
[Columns(col="pk",col="title")]
[Chooser(label="title")]
[Bindable]
public class HomePageSlide {
	
    public var pk:Number  = -1 ;
    
    [Display(label="Title",lines=1)]
    public var title:String;
    
    public var permalink:String;
    
    [Display(label="Related Project")]
	[Relationship(end="uk.mafu.kwa.domain::Project",type="ONE_TO_ONE")]
    public var relatedProject:Project;
    
    
    [Display(label="Image",widget=SingleImage)]
	public var image:ImageLink;
	}
}