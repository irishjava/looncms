package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;

	public class EntityRemovedEvent extends Event{
		
		public static const NAME:String = Util.getFullClassName(EntityRemovedEvent);
		
		public var clazz:Class,pk:Object;
		
		public function EntityRemovedEvent(clazz:Class,pk:Object){
        	super(NAME,true,true);
        	this.clazz = clazz;
        	this.pk = pk;
        	Util.debug("created new:" + Util.getClassname(this) );
        }

    	override public function clone():Event{
       		return Util.cloneObject(this) as Event;
    	}
	}
}