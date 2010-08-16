package uk.mafu.loon.dto
{
	[RemoteClass(alias="uk.mafu.loon.dto.ManyToManyResult")]
	[Bindable]
	public dynamic class ManyToManyResult
	{
		public var data:Array = new Array();
		public var options:Array = new Array();
		public var relationship:String = new String();
		public var parentPk:Object ;
	}
}