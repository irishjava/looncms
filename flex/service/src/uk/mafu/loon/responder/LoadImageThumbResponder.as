package uk.mafu.loon.responder
{
	import mx.core.IDataRenderer;
	import mx.rpc.events.ResultEvent;
	
	import uk.mafu.loon.dto.ImageThumb;
	import uk.mafu.loon.service.ThumbCache;

	public class LoadImageThumbResponder extends AbstractResponder
	{
		private var callee:IDataRenderer;
		
		public function LoadImageThumbResponder(callee:IDataRenderer) {
			this.callee = callee;
		}

		override public function result(data:Object):void {
			var img:ImageThumb = ResultEvent(data).result as ImageThumb;
			ThumbCache.addThumb(img);
			callee.data = img.data;
		}
		 
	}
}