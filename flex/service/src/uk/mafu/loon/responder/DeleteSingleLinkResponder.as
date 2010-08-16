package uk.mafu.loon.responder
{
	import mx.rpc.events.ResultEvent;
	import uk.mafu.flex.util.Util;
	import org.swizframework.Swiz;
	import uk.mafu.loon.events.EntitySavedEvent;

	public class DeleteSingleLinkResponder extends AbstractResponder
	{
	 	public function DeleteSingleLinkResponder(){
		}

		override public function result(data:Object):void{
			Util.debug("DeleteSingleLinkResponder result");
		//	Swiz.dispatchEvent(SingleLinkDeletedEvent(callee:Object);
		}
	}
}