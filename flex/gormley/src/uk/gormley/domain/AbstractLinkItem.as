package uk.gormley.domain
{

import mx.collections.ArrayCollection;


[RemoteClass(alias="uk.gormley.domain.AbstractLinkItem")]
[Bindable]
public class AbstractLinkItem {
	
	public var pk:Object;
	
	[Display(label="Title",lines=1)]
	public var title:String;
	
	[Display(label="Text",lines=10)]
	public var text:String;
	
	[Display(label="Url 1",lines=1)]
	public var url1:String;
	
	[Display(label="Url 2",lines=1)]
	public var url2:String;
	
	[Display(label="Url 3",lines=1)]
	public var url3:String;
	
	[Display(label="Url 4",lines=1)]
	public var url4:String;
	
	[Display(label="Url 5",lines=1)]
	public var url5:String;
	
	[Display(label="Url 6",lines=1)]
	public var url6:String;
	
	}
}
