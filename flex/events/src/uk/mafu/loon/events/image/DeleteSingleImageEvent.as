package uk.mafu.loon.events.image
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Assert;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IExecutable;
	import uk.mafu.loon.IService;
	
	public class DeleteSingleImageEvent extends Event implements IExecutable
	{
		public var entityParent:Object,relationship:String;
		public static const NAME:String = Util.getFullClassName(DeleteSingleImageEvent);
		
		public function DeleteSingleImageEvent(entityParent:Object,relationship:String) {
			super(NAME,true,true);
			Assert.instanceOf(relationship,String);
			Assert.instanceOf(entityParent,Object);
			this.relationship = relationship;
			this.entityParent = entityParent;
        	Util.debug("created new:" + Util.getClassname(this) );
        }

    	/**
     	*  @private
     	*/
    	override public function clone():Event {
       		return Util.cloneObject(this)as Event;
    	}
    	
    	public function execute(service:IService,entityParent:Object=null):void{
    		service.deleteSingleLink(entityParent,relationship);
    	}
		
		public function isDuplicate(other:IExecutable):Boolean{
			//Wipe out any edit events that are in the queue, remember we need to do the inverse in the isDuplicate method of EditSingleImageEvent.. 
			if(other is Util.getClass(EditSingleImageEvent) || other is Util.getClass(DeleteSingleImageEvent) ) {
				return true;
			}
			return false;
		}
		
		
		
   	}
}