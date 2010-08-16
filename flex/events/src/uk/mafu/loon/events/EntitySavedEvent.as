package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;

	public class EntitySavedEvent extends Event{
		public static const NAME:String = Util.getFullClassName(EntitySavedEvent);
		private var _entity:Object;
		
		public function EntitySavedEvent(entity:Object){
        	super(NAME,true,true);
        	this._entity = entity;
        	Util.debug("created new:" + Util.getClassname(this) );
        }

    	override public function clone():Event{
       		return Util.cloneObject(this) as Event;
    	}
    	
    	public function get entity():Object {
    		return this._entity;
    	}
	}
}