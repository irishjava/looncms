package uk.mafu.loon.executions
{
	import uk.mafu.flex.util.Assert;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IExecutable;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.domain.data.AudioLink;
	
	public class EditSingleAudioExecution implements IExecutable {
		
		public var child:AudioLink,relationship:String,parent:Object;
		public static const NAME:String = Util.getFullClassName(EditSingleAudioExecution);
		
		public function EditSingleAudioExecution(child:AudioLink,relationship:String) {
			Assert.instanceOf(relationship,String);
			this.child = child;
        	this.relationship = relationship;
        	Util.debug("created new:" + Util.getClassname(this) );
        }

		public function execute(service:IService,entityParent:Object=null):void{
			if(Util.isTransient(this.child,"audioId")) {
				service.deleteSingleLink(entityParent,relationship);
			}
    		else{
    		service.saveSingleLink(child,entityParent,relationship);
    		}
    	}
		
		public function isDuplicate(other:IExecutable):Boolean{
			return true;
		}
	}
}