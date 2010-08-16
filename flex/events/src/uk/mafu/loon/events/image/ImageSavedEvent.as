package uk.mafu.loon.events.image
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	
   	public class ImageSavedEvent extends Event
	{
		public static const EDIT_COMPLETE:String = "imageEditComplete";
		public static const EDIT_CANCELED:String = "imageEditCancelled";
		
		public function ImageSavedEvent(type:String){
        	super(type, false, false);
         	Util.debug("created new:" + Util.getClassname(this) + " ' type='" + type + "'");
    	}

	 	override public function clone():Event
    	{
       		return Util.cloneObject(this) as Event;
    	}
	}
}