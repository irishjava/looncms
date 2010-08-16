package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Assert;
	import uk.mafu.flex.util.FaultType;
	import uk.mafu.flex.util.Util;
	
	public class ApplicationErrorEvent extends Event
	{
		public var faultType:FaultType;
		public static const NAME:String = Util.getFullClassName(ApplicationErrorEvent);
		
		public function ApplicationErrorEvent(faultType:FaultType) {
        	super(Util.getFullClassName(this),true,true);
        	Util.debug("created new:" + Util.getClassname(this) );
        	Assert.notNull(faultType,"faultType");
        	this.faultType = faultType;
        	faultType.type;
        }

    	override public function clone():Event {
       		return new ApplicationErrorEvent(Util.cloneObject(faultType) as FaultType);
    	}
	}
}