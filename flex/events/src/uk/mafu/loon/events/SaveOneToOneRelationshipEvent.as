package uk.mafu.loon.events
{
	import flash.errors.IllegalOperationError;
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IExecutable;
	import uk.mafu.loon.IService;
	
	public class SaveOneToOneRelationshipEvent extends Event implements IExecutable
	{
		public static const NAME:String = Util.getFullClassName(SaveOneToOneRelationshipEvent);
		public var child:Object,parentEntity:Object,relationship_name:String;
		
		public function SaveOneToOneRelationshipEvent(child:Object,parentEntity:Object,relationship_name:String) {
        	super(NAME, true,true);
        	this.child = child;
        	this.parentEntity = parentEntity;
        	this.relationship_name = relationship_name;
         	Util.debug("created new:" + Util.getClassname(this) );
         	throw new IllegalOperationError("deprecated");
        }

    	/**
     	*  @private
     	*/
    	override public function clone():Event {
       		return new SaveOneToOneRelationshipEvent(Util.cloneObject(child),Util.cloneObject(parentEntity),Util.cloneObject(relationship_name) as String);
    	}
    	
		public function execute(service:IService,entityParent:Object = null):void {
			//service.saveOneToOneRelationship(parentEntity,child,relationship_name
		}
		
		public function isDuplicate(ex1:IExecutable):Boolean {
			if(Util.getClass(ex1) == Util.getClass(this)){
				var ex:SaveOneToOneRelationshipEvent = (ex1 as SaveOneToOneRelationshipEvent);
				if(ex.child == this.child && 
					ex.parentEntity == this.parentEntity &&  
					ex.relationship_name == this.relationship_name) {
					return true;
				} 					
			}
			return false;
		}
	}
}