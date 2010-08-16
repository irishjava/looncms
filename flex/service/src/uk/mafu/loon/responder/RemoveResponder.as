package uk.mafu.loon.responder
{
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.flex.util.Assert;
	import uk.mafu.flex.util.FaultUtil;
	import uk.mafu.loon.events.ApplicationErrorEvent;
	import uk.mafu.loon.events.EntityRemovedEvent;

	public class RemoveResponder extends AbstractResponder
	{
		public var clazz:Class,pk:Object;
		
	 	public function RemoveResponder(clazz:Class,pk:Object){
	 		Assert.notNull(clazz,"clazz");
	 		Assert.notNull(pk,"pk");
	 		this.clazz = clazz;
	 		this.pk = pk;
		}

		override public function result(data:Object):void{
			Swiz.dispatchEvent(new EntityRemovedEvent(clazz,pk));
		}
		
		
	}
}