package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Assert;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IExecutable;
	import uk.mafu.loon.IService;
	
	public class EditOneToOneRelationshipEvent extends Event implements IExecutable
	{
		public var child:Object,relationship:String,parent:Class,parentId:Object;
		public static const NAME:String = Util.getFullClassName(EditOneToOneRelationshipEvent);
		
		public function EditOneToOneRelationshipEvent(child:Object,relationship:String,parent:Class,parentId:Object) {
			super(NAME,true,true);
			Assert.instanceOf(relationship,String);
			Assert.instanceOf(parent,Class);
			Assert.instanceOf(parentId,Object);
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
    	
    	public function execute(service:IService,entityParent:Object=null):void{
    		service.saveOneToOneRelationship(
											parent,	
											Util.getClass(child),
											parentId,
											Util.getPrimaryKey(child),
											relationship
											);
    	}
		
		public function isDuplicate(other:IExecutable):Boolean{
			//Same class, lets continue...
			if(other is Util.getClass(this)){
				var otherEdit:EditOneToOneRelationshipEvent = other as EditOneToOneRelationshipEvent;
				if(otherEdit.relationship == this.relationship 
				&& otherEdit.parent == this.parent 
				&& otherEdit.parentId == this.parentId) {
					return true;
				}
			} 	
			return false;
		}
   	}
}