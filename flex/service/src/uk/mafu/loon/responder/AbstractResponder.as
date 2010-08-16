package uk.mafu.loon.responder
{
	import flash.errors.IllegalOperationError;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import uk.mafu.flex.util.Util;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.flex.util.FaultUtil;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.events.ApplicationErrorEvent;

	public class AbstractResponder implements IResponder
	{
		 
		public function result(data:Object):void{
			throw new IllegalOperationError("method must be impemented by subclass");
		}
		
		public function fault(info:Object):void{
			var faultEvent:FaultEvent = info as FaultEvent;
			Util.debug("ERROR:" + info.toString());
			Swiz.dispatchEvent(new ApplicationErrorEvent(FaultUtil.getFaultType(faultEvent)));
		}
	}
}