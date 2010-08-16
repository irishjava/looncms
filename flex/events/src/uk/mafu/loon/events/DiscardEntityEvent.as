package uk.mafu.loon.events
{
	import flash.events.Event;
	import uk.mafu.flex.util.Util;
	
	public class DiscardEntityEvent extends Event
	{
		public var entity:Object;
		public static const NAME:String = Util.getFullClassName(DiscardEntityEvent);
		
		public function DiscardEntityEvent(entity:Object) {
        	super(Util.getFullClassName(this),true,true);
        	this.entity = entity;
        	Util.debug("created new:" + Util.getClassname(this) );
        }

    	override public function clone():Event {
       		return new DiscardEntityEvent(this.entity);
    	}
	}
}