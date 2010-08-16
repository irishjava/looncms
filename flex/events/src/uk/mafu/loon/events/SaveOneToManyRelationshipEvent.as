package uk.mafu.loon.events
{
	import flash.events.Event;
	import uk.mafu.flex.util.Util;
	
	public class SaveOneToManyRelationshipEvent extends Event
	{
		public var clazz:Class,pk:Object;
		
		public static const NAME:String = Util.getFullClassName(SaveOneToManyRelationshipEvent);
		
		public function SaveOneToManyRelationshipEvent(clazz:Class,pk:Object) {
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
       		return new SaveOneToManyRelationshipEvent(clazz,pk);
    	}
	}
}