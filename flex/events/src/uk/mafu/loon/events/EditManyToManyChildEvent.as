package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	
	public class EditManyToManyChildEvent extends Event 
	{
		public var child_clazz:Class;
		public var relationship:String;
		public var parent_clazz:Class;
		public var parentId:Object;
		public var childId:Object;
	
		public var child:Object;
		public var parent:Object;
	
		public static const NAME:String = Util.getFullClassName(EditManyToManyChildEvent);
		
		public function EditManyToManyChildEvent(child:Object,relationship:String,parent:Object) {
			super(NAME,true,true);
			this.child_clazz = Util.getClass(child);
        	this.relationship = relationship;
        	this.parent_clazz = Util.getClass(parent);
        	this.parentId = Util.getPrimaryKey(parent);
        	this.childId = Util.getPrimaryKey(child);
        	this.child  =  Util.cloneObject(child);
        	this.parent = Util.cloneObject(parent);
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