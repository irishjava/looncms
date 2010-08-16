package uk.mafu.loon.domain.data
{
	import flash.utils.ByteArray;
	
	[RemoteClass(alias="uk.mafu.loon.domain.data.ImageLink")]
	[Bindable]
	public dynamic class ImageLink{
		public var pk:Number;
		public var excite:Boolean;
		public var title:String;
		public var caption:String;
		public var text:String;
		public var meta:String;
		public var width:Number;
		public var height:Number;
		public var imageId:Number;
	}
}
