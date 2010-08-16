package uk.mafu.loon.responder
{
	import mx.rpc.events.ResultEvent;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.loon.IImageLoadedListener;
	import uk.mafu.loon.domain.data.LoonImage;
	import uk.mafu.loon.events.image.ImageLoadedEvent;

	public class LoadImageResponder extends AbstractResponder
	{
		private var callee:Object;
		
		public function LoadImageResponder(callee:Object = null) {
			this.callee = callee;
		}

		override public function result(data:Object):void {
			if(callee is IImageLoadedListener) {
				IImageLoadedListener(callee).handleImageLoaded(data);
			}
			else {
				Swiz.dispatchEvent(
					new ImageLoadedEvent((data as ResultEvent).result as LoonImage ,callee) 
				);
			}
		}
		 
	}
}