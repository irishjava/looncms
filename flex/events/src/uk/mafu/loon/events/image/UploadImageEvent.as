package uk.mafu.loon.events.image
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	
   	public class UploadImageEvent extends Event
	{

		public var relationship:String;
		public var parent_clazz:Class;
		public var parentId:Object;
		public static const NAME:String = Util.getFullClassName(UploadImageEvent);
		
		public function UploadImageEvent(relationship:String,parent:Object) {
			super(NAME,true,true);
			this.relationship = relationship;
        	this.parent_clazz = Util.getClass(parent);
        	this.parentId = Util.getPrimaryKey(parent);
        	Util.debug("created new:" + Util.getClassname(this) );
        }

    	override public function clone():Event	{
       		return Util.cloneObject(this) as Event;
    	}
	}
}
