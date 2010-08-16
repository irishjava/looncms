package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Assert;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IExecutable;
	
	/**
	 * Dispatched when we wish for an entity to be deleted.
	 */
	public class RemoveImageEvent extends Event
	{
		public var imagelink:Object;
		public static const NAME:String = Util.getFullClassName(RemoveImageEvent);
		
		public function RemoveImageEvent(imagelink:Object) {
        	super(NAME, true,true);
        	this.imagelink  = imagelink;
        	Assert.notNull(imagelink);
        	Util.debug("created new:" + Util.getClassname(this) );
        }

    	/**
     	*  @private
     	*/
    	override public function clone():Event{
       		return Util.cloneObject(this) as Event;
    	}
	}
}