package uk.gormley.domain
{
import mx.collections.ArrayCollection;


[RemoteClass(alias="uk.gormley.domain.Catalogue")]
[Tab(title="Main",order=1,field=title,field=date,field=publisher,field=contributors,field=type)]
[Order(col="",asc="false")]
[Columns(col="pk",col="title",col="type")]
[Chooser(label="title")]
[Bindable]
public class Catalogue {
	
	public var pk:Object;
	
	[Display(label="Date")]
	public var date:Date;
	
	[Display(label="Title")]
	public var title:String;
	
	[Display(label="Publisher")]
	public var publisher:String;
	
	[Display(label="Contributors")]
	public var contributors:String;
	
	[Display(label="Type",widget=EnumWidget,enums="solo,group"
	,defaultValue="solo")]
	public var type:String;
	}
}
