package uk.mafu.loon.responder
{
	import mx.rpc.events.ResultEvent;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.loon.domain.data.LoonPdf;
	import uk.mafu.loon.events.PdfLoadedEvent;

	public class LoadPdfResponder extends AbstractResponder
	{
		private var callee:Object;
		
		public function LoadPdfResponder(callee:Object = null)
		{
			this.callee = callee;
		}

		override public function result(data:Object):void
		{
			Swiz.dispatchEvent(
				new PdfLoadedEvent((data as ResultEvent).result as LoonPdf ,callee ) 
			);
		}
		 
	}
}