package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.dto.OneToOneResult;
	
	public class OneToOneRelationshipLoadedEvent extends Event
	{
		public var parent_clazz:Class,parentPk:Object,relationship:String,result:OneToOneResult;
		public static const NAME:String = Util.getFullClassName(OneToOneRelationshipLoadedEvent);
		
		public function OneToOneRelationshipLoadedEvent(parent_clazz:Class,parentPk:Object,relationship:String,
														result:OneToOneResult) {
        	super(Util.getFullClassName(this), true,true);
        	this.parent_clazz = parent_clazz;
        	this.parentPk = parentPk;
        	this.relationship = relationship;
        	this.result = result; 
        	Util.debug("created new:" + Util.getClassname(this) );
        }

    	/**
     	*  @private
     	*/
    	override public function clone():Event{
       		return Util.cloneObject(this) as Event;
    	}
	}
}


		
		