package uk.mafu.loon.domain.data
{
	import flash.utils.ByteArray;
		
	[RemoteClass(alias="uk.mafu.loon.domain.data.LoonImage")]
	[Bindable]
	public class LoonImage {
		public var pk:Number = -1;
		public var data:ByteArray;
		public var filename:String;
		public var date:Date = new Date();
		public var mimetype:String;
		public var height:Number;
		public var width:Number;
	}
}