package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	
	public class CreateEntityEvent extends Event  
	{
		public var clazz:Class;
		public static const NAME:String = Util.getFullClassName(CreateEntityEvent);
		
		public function CreateEntityEvent(clazz:Class) {
			super(NAME,true,true);
        	this.clazz = clazz;
        	Util.debug("created new:" + Util.getClassname(this) );
        }

    	/**
     	*  @private
     	*/
    	override public function clone():Event {
       		return Util.cloneObject(this) as CreateEntityEvent;
    	}
	}
}