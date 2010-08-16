package uk.mafu.flex.util
{
	import flash.errors.IllegalOperationError;
	
	import mx.rpc.events.FaultEvent;
	
	public class FaultUtil
	{
		public static function getFaultType(evt:FaultEvent):FaultType {
			var msg:String = evt.fault.faultString;
			if(evt.fault.faultString.indexOf("Cannot delete a record of type:") > -1){
				return handleConstraintViolation(msg);
			}
			throw new IllegalOperationError("unknown fault type" + msg);
		} 	

		private static function handleConstraintViolation(msg:String):FaultType {
			return new FaultType(FaultType.CONSTRAINT_VIOLATION,msg);
		}
	}
}