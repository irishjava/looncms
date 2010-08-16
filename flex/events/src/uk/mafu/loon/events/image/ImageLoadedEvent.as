package uk.mafu.loon.events.image
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.domain.data.LoonImage;
	
   	public class ImageLoadedEvent extends Event
	{
		public static const NAME:String = Util.getFullClassName(ImageLoadedEvent);
		public var image:LoonImage;
		public var callee:Object;
		
		public function ImageLoadedEvent(image:LoonImage,callee:Object) {
        	super(NAME, true,true);
        	Util.debug("created new:" + Util.getClassname(this) );
        	this.image = image;
        	this.callee = callee;
        }

    	override public function clone():Event{
       		return Util.cloneObject(this) as Event;
    	}
	}
}