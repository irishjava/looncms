package uk.mafu.kwa.domain{
import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.PdfLink;
import uk.mafu.loon.domain.data.VideoLink;

[RemoteClass(alias="uk.kwa.domain.Project")]
[Tab(title="Main",order=1,
	field=title,
	field=infoText,
	field=infoImage,
	field=featuredImage,
	field=video,
	field=videoImage,
	field=pdf,
	field=infoExcite,
	field=videoExcite
)]
[Tab(title="Slides",order=2,
	field=slides
)]
[Order(col="pk",asc="false")]
[Columns(col="pk",col="title")]
[Chooser(label="title")]
[Bindable]
public class Project {
	 	
 	public var pk:Number  = -1 ;
    
    [Display(label="Title",lines=1)]
    public var title:String;
    
    [Display(label="Info Text",lines=15)]
    public var infoText:String;

	[Display(label="Info Image",widget=SingleImage)]    
    public var infoImage:ImageLink;
    
    [Display(label="Featured Image",widget=SingleImage)]
    public var featuredImage:ImageLink;
    
    [Display(label="Video",widget=SingleVideo)]
    public var video:VideoLink;
    
    [Display(label="Video Image",widget=SingleImage)]
    public var videoImage:ImageLink;
    
    [Display(label="Pdf",widget=SinglePdf)]
    public var pdf:PdfLink;
    
    public var permalink:String ;
        
    [Relationship(end="uk.mafu.kwa.domain::ProjectSlide",type="MANY_TO_MANY")]
	[Order(col="pk",asc="false")]
	[Display(label="Slides")]
    public var slides:Array;
  	
  	[Display(label="Info Excite")]
	[Relationship(end="uk.mafu.kwa.domain::Excite",type="ONE_TO_ONE")]
	public var infoExcite:Excite;
  
  	[Display(label="Video Excite")]
	[Relationship(end="uk.mafu.kwa.domain::Excite",type="ONE_TO_ONE")]
    public var videoExcite:Excite;
	}
}