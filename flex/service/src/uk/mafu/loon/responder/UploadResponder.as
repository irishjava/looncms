package uk.mafu.loon.responder
{
	import mx.core.IUIComponent;
	import mx.managers.PopUpManager;
	import mx.rpc.events.ResultEvent;
	
	import uk.mafu.loon.IUploader;
	
	public class UploadResponder extends AbstractResponder
	{
		private var callee:IUploader;
		private var popup:IUIComponent;
		
		public function UploadResponder(callee:IUploader,popup:IUIComponent) {
			this.callee = callee;
			this.popup = popup;
		}

		override public function result(data:Object):void {
			PopUpManager.removePopUp(popup);
			callee.uploadCompleted((data as ResultEvent).result as Object );
		}
	}
}