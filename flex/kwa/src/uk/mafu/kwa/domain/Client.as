package uk.mafu.kwa.domain{
	
[RemoteClass(alias="uk.kwa.domain.Client")]
[Tab(title="Main",order=1,
	field=name,field=relatedProject
)]
[Order(col="pk",asc="false")]
[Columns(col="pk",col="name")]
[Chooser(label="name")]
[Bindable]
public class Client {
	
    public var pk:Number  = -1 ;
    
    [Display(label="Name",lines=1)]
    public var name:String;
    
    [Display(label="Related Project")]
	[Relationship(end="uk.mafu.kwa.domain::Project",type="ONE_TO_ONE")]
    public var relatedProject:Project;
	}
}