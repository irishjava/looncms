package uk.mafu.loon.responder
{
	import mx.rpc.events.ResultEvent;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.ISaveListener;
	import uk.mafu.loon.events.EntitySavedEvent;

	public class SaveResponder extends AbstractResponder
	{
	
		private var _saveListener:ISaveListener;
			
	 	public function SaveResponder(saveListener:ISaveListener = null){
	 		_saveListener = saveListener
		}

		override public function result(data:Object):void{
			Util.debug((data as ResultEvent).result);
			Swiz.dispatchEvent
			(
				new EntitySavedEvent((data as ResultEvent).result)
			);
			if(_saveListener!= null){
				_saveListener.itemSaved((data as ResultEvent).result);
			}
		}
	}
}