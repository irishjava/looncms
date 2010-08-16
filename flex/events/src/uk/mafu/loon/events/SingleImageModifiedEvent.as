package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IExecutable;
	import uk.mafu.loon.IService;
	
	public class SingleImageModifiedEvent extends Event implements IExecutable{
	
		public var entity:Object;
		public static const NAME:String = Util.getFullClassName(EntityModifiedEvent);
		
		public function SingleImageModifiedEvent(entity:Object){
        	super(NAME, true,true);
        	this.entity = entity;
        	Util.debug("created new:" + Util.getClassname(this) );
        }

    	override public function clone():Event{
       		return new EntityModifiedEvent(Util.cloneObject(this.entity));
    	}
    	
    	public function execute(service:IService,entityParent:Object=null):void {
			service.save(entity);
		}
		
		public function isDuplicate(ex1:IExecutable):Boolean {
			if(Util.getClass(ex1) == Util.getClass(this)){
				var ex:EntityModifiedEvent = (ex1 as EntityModifiedEvent);
				if(ex.entity == this.entity) {
					return true;
				} 					
			}
			return false;
		}
	}
}