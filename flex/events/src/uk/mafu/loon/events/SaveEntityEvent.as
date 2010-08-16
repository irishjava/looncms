package uk.mafu.loon.events
{
	import flash.events.Event;
	import uk.mafu.flex.util.Util;
	
	public class SaveEntityEvent extends Event
	{
		public var entity:Object;
		public static const NAME:String = Util.getFullClassName(SaveEntityEvent);
		
		public function SaveEntityEvent(entity:Object) {
        	super(NAME, true,true);
        	this.entity = entity;
        	Util.debug("created new:" + Util.getClassname(this) );
        }
    	 
    	override public function clone():Event
    	{
       		return new SaveEntityEvent(entity);
    	}
	}
}