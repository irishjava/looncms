package uk.mafu.loon.domain.data
{
	[RemoteClass(alias="uk.mafu.loon.domain.data.VideoLink")]
	[Bindable]
	public dynamic class VideoLink {
		public var pk:Number;
		public var title:String;
		public var caption:String;
		public var _width:Number  = -1;
		public var _height:Number = -1;
		
		public function set width(val:Number):void {
			_width = val;
		}
		
		public function get width():Number {
			return _width;
		}
		
		public function set height(val:Number):void {
			_height = val;
		}
		
		public function get height():Number {
			return _height;
		}
		
		public var videoId:Number;
	}
}
