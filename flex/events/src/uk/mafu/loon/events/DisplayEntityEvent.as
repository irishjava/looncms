package uk.mafu.loon.events
{
	import flash.events.Event;
	import uk.mafu.flex.util.Util;
	
 	public class DisplayEntityEvent extends Event
	{
		public var clazz:Class,pk:Object;
		
		public static const NAME:String = Util.getFullClassName(DisplayEntityEvent);
		
		public function DisplayEntityEvent(clazz:Class,pk:Object) {
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
       		return new DisplayEntityEvent(clazz,pk);
    	}
	}
}