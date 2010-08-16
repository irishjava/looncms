package uk.mafu.loon.domain.data
{
	import flash.utils.ByteArray;
		
	[RemoteClass(alias="uk.mafu.loon.domain.data.LoonAudio")]
	[Bindable]
	public class LoonAudio{
		public var pk:Number = -1;
		public var data:ByteArray;
		public var date:Date = new Date();
		public var filename:String;
		public var contentType:String;
	}
}