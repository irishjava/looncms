package uk.gormley.domain {
	
import mx.collections.ArrayCollection;

[RemoteClass(alias="uk.gormley.domain.PressItem")]
[Tab(title="Main",order=1,field=title,field=publication,field=author,field=url,field=date)]
[Order(col="title",asc="false")]
[Columns(col="pk",col="title")]
[Chooser(label="title")]
[Bindable]
public class PressItem {
	
	public var pk:Object;
	
	[Display(label="Title")]
	public var title:String;
	
	[Display(label="Url",lines=1)]
	public var url:String;
	
	[Display(label="Date")]
	public var date:Date;
	
	[Display(label="Publication")]
	public var publication:String;
	
	[Display(label="Author")]
	public var author:String;
	
	}
}
