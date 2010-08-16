package uk.mafu.kwa.domain {
[RemoteClass(alias="uk.kwa.domain.ProjectCategory")]
[Tab(title="Main",order=1,
	field=title,
	field=projects
)]
[Order(col="pk",asc="false")]
[Columns(col="pk",col="title")]
[Chooser(label="title")]
[Bindable]
public class ProjectCategory {

    public var pk:Number  = -1 ;
    
    [Display(label="Title",lines=1)]
    public var title:String; 
    
    public var permalink:String;

	[Relationship(end="uk.mafu.kwa.domain::Project",type="MANY_TO_MANY")]
	[Order(col="pk",asc="false")]
	[Display(label="Projects")]    
    public var projects:Array;
	}
}