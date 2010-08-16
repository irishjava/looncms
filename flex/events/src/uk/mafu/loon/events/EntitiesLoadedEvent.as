package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	
	public class EntitiesLoadedEvent extends Event
	{
		public var result:Array;
		public var clazz:Class;
		
		public static const NAME:String = Util.getFullClassName(EntitiesLoadedEvent);
		
		public function EntitiesLoadedEvent(result:Array,clazz:Class) {
        	super(Util.getFullClassName(this), true,true);
        	this.result = result;
        	this.clazz = clazz;
        	Util.debug("created new:" + Util.getClassname(this) );
        }

    	/**
     	*  @private
     	*/
    	override public function clone():Event
    	{
       		return new EntitiesLoadedEvent(Util.cloneObject(this.result) as Array,clazz);
    	}
	}
}