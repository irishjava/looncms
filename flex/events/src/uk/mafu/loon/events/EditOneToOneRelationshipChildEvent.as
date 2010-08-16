package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Assert;
	import uk.mafu.flex.util.Util;
	
	public class EditOneToOneRelationshipChildEvent extends Event 
	{
		public var child:Object,relationship:String,parent:Class,parentId:Object;
		public static const NAME:String = Util.getFullClassName(EditOneToOneRelationshipChildEvent);
		
		public function EditOneToOneRelationshipChildEvent(child:Object,relationship:String,parent:Class,parentId:Object) {
			super(NAME,true,true);
			this.child = child;
        	this.relationship = relationship;
        	this.parent = parent;
        	this.parentId = parentId;
        	Util.debug("created new:" + Util.getClassname(this) );
        }

    	/**
     	*  @private
     	*/
    	override public function clone():Event {
       		return Util.cloneObject(this)as Event;
    	}
   	}
}