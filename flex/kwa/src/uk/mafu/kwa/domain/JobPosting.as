package uk.mafu.kwa.domain{
	
[RemoteClass(alias="uk.kwa.domain.JobPosting")]
[Tab(title="Main",order=1,
	field=title,field=text1,field=text2
)]
[Order(col="pk",asc="false")]
[Columns(col="pk",col="title")]
[Chooser(label="title")]
[Bindable]
public class JobPosting {
	    public var pk:Number  = -1 ;
	    
	    [Display(label="Title",lines=1)]
	    public var title:String; 
	    
	    [Display(label="Text 1",lines=15)]
	    public var text1:String;
	    
	    public var permalink:String;
	}
}