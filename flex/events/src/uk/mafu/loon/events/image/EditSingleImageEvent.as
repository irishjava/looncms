package uk.mafu.loon.events.image
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Assert;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IExecutable;
	import uk.mafu.loon.IService;
	
	public class EditSingleImageEvent extends Event implements IExecutable
	{
		public var child:Object,relationship:String,parent:Object;
		public static const NAME:String = Util.getFullClassName(EditSingleImageEvent);
		
		public function EditSingleImageEvent(child:Object,relationship:String,parent:Object) {
			super(NAME,true,true);
			Assert.instanceOf(child,Object);
			Assert.instanceOf(relationship,String);
			Assert.instanceOf(parent,Object);
			this.child = child;
        	this.relationship = relationship;
        	this.parent = parent;
        	Util.debug("created new:" + Util.getClassname(this) );
        }

    	/**
     	*  @private
     	*/
    	override public function clone():Event {
       		return Util.cloneObject(this)as Event;
    	}
    	
    	public function execute(service:IService,entityParent:Object=null):void{
    		service.saveSingleLink(
									child,parent,
											relationship
											);
    	}
		
		public function isDuplicate(other:IExecutable):Boolean{
			//Same class, lets continue...
			if(other is Util.getClass(this)
			  && (other as EditSingleImageEvent).relationship == this.relationship
			  ){
				var otherEdit:EditSingleImageEvent = other as EditSingleImageEvent;
				if(otherEdit.relationship == this.relationship 
				&& Util.getPrimaryKey(otherEdit.parent) == Util.getPrimaryKey( this.parent)) {
					return true;
				}
			} 	
			return false;
		}
   	}
}