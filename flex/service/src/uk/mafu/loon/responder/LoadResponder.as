package uk.mafu.loon.responder
{
	import mx.rpc.events.ResultEvent;
	import org.swizframework.Swiz;
	import uk.mafu.loon.events.EntityLoadedEvent;

	public class LoadResponder extends AbstractResponder
	{
		public function LoadResponder()	{
		}

		override public function result(data:Object):void
		{
			Swiz.dispatchEvent(
				new EntityLoadedEvent((data as ResultEvent).result ) 
			);
		}
		 
	}
}