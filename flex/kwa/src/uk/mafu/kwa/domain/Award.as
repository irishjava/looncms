package uk.mafu.kwa.domain {
	import uk.mafu.loon.domain.data.ImageLink;
	

[RemoteClass(alias="uk.kwa.domain.Award")]
[Tab(title="Main",order=1,
	field=title, 
	field=awardTitle,
	field=text,
	field=date,
	field=relatedProject,
	field=excite)]
[Tab(title="Image",order=2,field=image)]
[Order(col="pk",asc="false")]
[Columns(col="pk",col="title")]
[Chooser(label="title")]
[Bindable]
public class Award {
	    public var pk:Number = -1 ;
	        
	    [Display(label="Title",lines=1)]
	    public var title:String;
	    
	    [Display(label="Award Title",lines=1)]
	    public var awardTitle:String;
	    
	    [Display(label="Text",lines=5)] 
	    public var text:String; 
	    
	    [Display(label="Image",widget=SingleImage)]	
		public var image:ImageLink;
	    	    
	    [Display(label="Date")]
	    public var date:Date;
	    
	    [Display(label="Related Project")]
		[Relationship(end="uk.mafu.kwa.domain::Project",type="ONE_TO_ONE")]
	    public var relatedProject:Project;
	    
	    [Display(label="Excite")]
		[Relationship(end="uk.mafu.kwa.domain::Excite",type="ONE_TO_ONE")]
	    public var excite:Excite;
	    
	    public var permalink:String;
	}
}