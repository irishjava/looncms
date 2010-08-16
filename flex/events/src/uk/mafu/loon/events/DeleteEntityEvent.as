package uk.mafu.loon.events
{
	import flash.events.Event;
	import uk.mafu.flex.util.Util;
	
	/**
	 * Dispatched when we wish for an entity to be deleted.
	 */
	public class DeleteEntityEvent extends Event
	{
		public var clazz:Class,pk:Object;
		
		public static const NAME:String = Util.getFullClassName(DeleteEntityEvent);
		
		public function DeleteEntityEvent(clazz:Class,pk:Object) {
        	super(Util.getFullClassName(this), true,true);
        	this.clazz = clazz;
        	this.pk = pk;
        	Util.debug("created new:" + Util.getClassname(this) );
        }

    	/**
     	*  @private
     	*/
    	override public function clone():Event
    	{
       		return new DeleteEntityEvent(clazz,pk);
    	}
	}
}