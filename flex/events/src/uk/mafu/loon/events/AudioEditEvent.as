package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	
   	public class AudioEditEvent extends Event
	{
		public static const AUDIO_CREATE:String = "audioCreate";
		public static const AUDIO_DELETE:String = "audioDelete";
		public static const AUDIO_CLOSE:String = "audioClose";

		private var _pk:Number;

		public function AudioEditEvent(type:String,pk:Number = -1){
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