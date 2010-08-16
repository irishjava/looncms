package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	
	import uk.mafu.flex.util.Assert;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IExecutable;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.domain.data.ImageLink;
	
	public class EditOneToManyImagesEvent extends Event implements IExecutable
	{
		public var children:ArrayCollection,relationship:String,parent:Class,parentId:Object;
		public static const NAME:String = Util.getFullClassName(EditOneToManyImagesEvent);
		
		public function EditOneToManyImagesEvent(children:ArrayCollection,relationship:String,parent:Class,parentId:Object) {
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
    		var imageLinks:Array = new Array();
    		for each (var obj:Object in children.toArray()) {
    			imageLinks.push( obj);//["imageLink"]
    		}
			service.saveOneToManyImages(
    					Util.getClass(this.parent),
    					parentId,
						this.relationship,
						imageLinks
						);
		}
		
		public function isDuplicate(other:IExecutable):Boolean{
			//Same class, lets continue...
			if(other is Util.getClass(this)){
				var otherEdit:EditOneToManyImagesEvent = other as EditOneToManyImagesEvent;
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