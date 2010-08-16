package uk.mafu.loon.dto
{
	import mx.collections.ArrayCollection;
	
	[RemoteClass(alias="uk.mafu.loon.dto.OneToOneResult")]
	[Bindable]
	public dynamic class OneToOneResult
	{
		public var data:Object;
		public var options:Array = new Array();
		public var relationship:String = new String();
		public var parentPk:Object ;
	}
}