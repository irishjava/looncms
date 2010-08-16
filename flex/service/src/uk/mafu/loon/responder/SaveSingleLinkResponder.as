package uk.mafu.loon.responder
{
	import mx.rpc.events.ResultEvent;
	import uk.mafu.flex.util.Util;
	import org.swizframework.Swiz;
	import uk.mafu.loon.events.EntitySavedEvent;

	public class SaveSingleLinkResponder extends AbstractResponder
	{
	 	public function SaveSingleLinkResponder(){
		}

		override public function result(data:Object):void{
			Util.debug("SaveSingleLinkResponder result");
		}
	}
}