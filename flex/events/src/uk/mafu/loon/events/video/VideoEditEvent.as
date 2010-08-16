package uk.mafu.loon.events.video
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	
   	public class VideoEditEvent extends Event
	{
		public static const VIDEO_CREATE:String = "videoCreate";
		public static const VIDEO_DELETE:String = "videoDelete";
		public static const VIDEO_CLOSE:String = "videoClose";
		
		private var _pk:Number;
		
		public function VideoEditEvent(type:String,pk:Number = -1){
        	super(type, false, false);
         	Util.debug("created new:" + Util.getClassname(this) + " ' type='" + type + "'");
         	this._pk = pk;
    	}

	 	override public function clone():Event{
       		return Util.cloneObject(this) as Event;
    	}
    	
    	public function get pk():Number {
    		return _pk;
    	}
	}
}