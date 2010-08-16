package uk.mafu.loon.executions
{
	import uk.mafu.flex.util.Assert;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IExecutable;
	import uk.mafu.loon.IService;
	
	public class EditSinglePdfExecution implements IExecutable {
		
		public var child:Object,relationship:String,deletion:Boolean;
		public static const NAME:String = Util.getFullClassName(EditSinglePdfExecution);
		
		public function EditSinglePdfExecution(child:Object,relationship:String,deletion:Boolean = false) {
			Assert.instanceOf(relationship,String);
			this.child = child;
        	this.relationship = relationship;
        	this.deletion  = deletion;
        	Util.debug("created new:" + Util.getClassname(this) );
        }

		public function execute(service:IService,entityParent:Object=null):void{
			if(deletion == false){
    			service.saveSingleLink(child,entityParent,relationship);
   			}
   			else {
   				service.deleteSingleLink(entityParent,relationship);
			}
    	}
		
		public function isDuplicate(other:IExecutable):Boolean{
			if(!other is EditSinglePdfExecution){
				return false;
			}
			var otherpdf:EditSinglePdfExecution = EditSinglePdfExecution(other);
			if( Util.isSameEntity(child,otherpdf) && relationship ==  otherpdf.relationship){
				return true;
			}
			else{
				return false;
			}
		}
	}
}