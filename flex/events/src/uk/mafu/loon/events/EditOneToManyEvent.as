package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	
	import uk.mafu.flex.util.Assert;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IExecutable;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.domain.data.ImageLink;
	
	public class EditOneToManyEvent extends Event implements IExecutable
	{
		public var children:ArrayCollection,relationship:String,parent:Class,parentId:Object;
		public static const NAME:String = Util.getFullClassName(EditOneToManyEvent);
		
		public function EditOneToManyEvent(children:ArrayCollection,relationship:String,parent:Class,parentId:Object) {
			super(NAME,true,true);
			Assert.instanceOf(children,ArrayCollection);
			Assert.instanceOf(relationship,String);
			Assert.instanceOf(parent,Class);
			Assert.instanceOf(parentId,Object);
			this.children = children;
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
    		var keys:Array = new Array();
    		for each (var obj:Object in children.toArray()) {
    			keys.push(Util.getPrimaryKey(obj));
    		}
    		service.saveOneToMany(
    					Util.getClass(this.parent),
    					parentId,
						this.relationship,
						keys
						);
									
		}
		
		public function isDuplicate(other:IExecutable):Boolean{
			//Same class, lets continue...
			if(other is Util.getClass(this)){
				var otherEdit:EditOneToManyEvent = other as EditOneToManyEvent;
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