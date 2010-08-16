package uk.mafu.loon.events
{
	import flash.events.Event;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IExecutable;
	import uk.mafu.loon.IService;
	
	public class CreateOneToOneRelationshipEvent extends Event  
	{
		public var clazz:Class,relationship:String,parent:Object;
		public static const NAME:String = Util.getFullClassName(CreateOneToOneRelationshipEvent);
		
		public function CreateOneToOneRelationshipEvent(clazz:Class,relationship:String,parent:Object) {
			super(NAME,true,true);
        	this.clazz = clazz;
        	this.relationship = relationship;
        	this.parent = parent;
        	Util.debug("created new:" + Util.getClassname(this) );
        }

    	/**
     	*  @private
     	*/
    	override public function clone():Event {
       		return new CreateOneToOneRelationshipEvent(clazz,relationship,
       		Util.cloneObject(parent));
    	}
	}
}