package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	
   	public class LoadOneToManyRelationshipEvent extends Event
	{
		public var clazz:Class,pk:Number;
		
		public static const NAME:String = Util.getFullClassName(LoadOneToManyRelationshipEvent);
		
		public function LoadOneToManyRelationshipEvent(clazz:Class,pk:Number) {
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
       		return new LoadOneToManyRelationshipEvent(clazz,pk);
    	}
	}
}