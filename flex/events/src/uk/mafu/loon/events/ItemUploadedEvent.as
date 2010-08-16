package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	
   	public class ItemUploadedEvent extends Event
	{
		public static const NAME:String = Util.getFullClassName(ItemUploadedEvent);
		public var result:Number;
		public var callee:Object;
		
		public function ItemUploadedEvent(result:Number,callee:Object) {
        	super(NAME, true,true);
        	Util.debug("created new:" + NAME );
        	result = result;
        	this.callee = callee;
        }

    	override public function clone():Event{
       		return Util.cloneObject(this) as Event;
    	}
	}
}