package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	
	/**
	 * Dispatched by the service, when an entity is returned back from the server.
	 */
	public class EntityLoadedEvent extends Event
	{
		public var result:Object;
		
		public static const NAME:String = Util.getFullClassName(EntityLoadedEvent);
		
		public function EntityLoadedEvent(object:Object) {
        	super(Util.getFullClassName(this), true,true);
        	this.result = object;
        	Util.debug("created new:" + Util.getClassname(this) );
        }

    	/**
     	*  @private
     	*/
    	override public function clone():Event
    	{
       		return new EntityLoadedEvent(Util.cloneObject(this.result));
    	}
	}
}