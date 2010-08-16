package uk.mafu.loon.responder
{
	import mx.rpc.events.ResultEvent;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.loon.domain.data.LoonVideo;
	import uk.mafu.loon.events.VideoLoadedEvent;
	
	public class LoadVideoResponder extends AbstractResponder
	{
		private var callee:Object;
		
		public function LoadVideoResponder(callee:Object = null) {
			this.callee = callee;
		}

		override public function result(data:Object):void {
			Swiz.dispatchEvent(
				new VideoLoadedEvent((data as ResultEvent).result as LoonVideo ,callee) 
			);
		}
	}
}