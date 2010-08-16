package uk.mafu.loon.executions
{
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	
	import uk.mafu.flex.util.Assert;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IExecutable;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.domain.data.ImageLink;
	
	public class EditOneToManyImagesExecution implements IExecutable
	{
		public var children:ArrayCollection,relationship:String,parent:Class,parentId:Number;
		
		public function EditOneToManyImagesExecution(children:ArrayCollection,relationship:String,parent:Class,parentId:Number) {
			Assert.instanceOf(children,ArrayCollection);
			Assert.instanceOf(relationship,String);
			Assert.instanceOf(parent,Class);
			Assert.instanceOf(parentId,Number);
			this.children = children;
        	this.relationship = relationship;
        	this.parent = parent;
        	this.parentId = parentId;
        	Util.debug("created new:" + Util.getClassname(this) );
        }

    	public function execute(service:IService,entityParent:Object=null):void{
    		var imageLinks:Array = new Array();
    		for each (var obj:Object in children.toArray()) {
    			imageLinks.push( obj["imageLink"]);
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
				var otherEdit:EditOneToManyImagesExecution = other as EditOneToManyImagesExecution;
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