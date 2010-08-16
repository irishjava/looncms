package uk.mafu.loon.responder
{
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.flex.util.Assert;
	import uk.mafu.loon.events.EntitiesLoadedEvent;

	public class GetAllResponder extends AbstractResponder
	{
		private var clazz:Class;
	 	public function GetAllResponder(clazz:Class){
	 		this.clazz = clazz;
	 		Assert.notNull(this.clazz,"clazz may not be null");
		}

		override public function result(data:Object):void{
			Swiz.dispatchEvent(
				new EntitiesLoadedEvent(
					(data as ResultEvent).result as Array,clazz)
				);
		}
		
		 
	}
}