package uk.mafu.loon.events.image
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.domain.data.ImageLink;
	
   	public class ImageEditedEvent extends Event
	{
		public static const NAME:String = Util.getFullClassName(ImageEditedEvent);
		public var image:ImageLink;
		
		public function ImageEditedEvent(image:ImageLink) {
        	super(NAME, true,true);
        	Util.debug("created new:" + Util.getClassname(this) );
        	image = image;
        }

    	override public function clone():Event{
       		return Util.cloneObject(this) as Event;
    	}
	}
}