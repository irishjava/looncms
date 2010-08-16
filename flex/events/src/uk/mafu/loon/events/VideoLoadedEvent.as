package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.domain.data.LoonVideo;
	
   	public class VideoLoadedEvent extends Event
	{
		public static const NAME:String = Util.getFullClassName(VideoLoadedEvent);
		public var video:LoonVideo;
		public var callee:Object;
		
		public function VideoLoadedEvent(video:LoonVideo,callee:Object) {
        	super(NAME, true,true);
        	Util.debug("created new:" + Util.getClassname(this) );
        	video = video;
        	this.callee = callee;
        }

    	override public function clone():Event{
       		return Util.cloneObject(this) as Event;
    	}
	}
}