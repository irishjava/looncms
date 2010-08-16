package uk.mafu.flex.builders.display
{
	import flash.events.MouseEvent;
	
	import mx.containers.Canvas;
	import mx.containers.HBox;
	import mx.containers.VBox;
	import mx.controls.Button;
	import mx.controls.Image;
	import mx.managers.PopUpManager;
	import uk.mafu.flex.util.Util;
	import org.swizframework.Swiz;
	
	import uk.mafu.loon.IService;
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.LoonImage;
	import uk.mafu.loon.events.image.ImageEditEvent;
	import uk.mafu.loon.events.image.ImageLoadedEvent;
	
	
	[Event(name="imageCreated", type="uk.mafu.loon.events.image.ImageEditEvent")]
	public class ImageUploadWindow extends Canvas
	{
		//gui
		private var service:IService;
		private var vbox:VBox;
		private var buttonBar:HBox;
		//buttons
		private var select:Button;
		private var cancel:Button;
		private var save:Button;
		
		private var image:Image;
		
		//gui
 		private	var imageLink:ImageLink ;
		private	var _loonImage:LoonImage ;
		
		public function ImageUploadWindow(service:IService)	{
			super();
			this.service  = service;
			width = 600;
			height = 400;
			///create a hbox , first col add a vbox for the buttons, second col add an image holder.
			vbox = new VBox();
			vbox.percentHeight  = 100;
			vbox.percentWidth = 100;
			addChild(vbox);
			vbox.addChild(createButtonBar());
			addEventListeners();
			image = new Image();
			vbox.addChild(image);
		}

		private function set loonImage(img:LoonImage):void {
			_loonImage = img;
			image.data =  loonImage.data;
			image.width = loonImage.width;
			image.height = loonImage.height;
			Util.debug("reloading loon image canvas");
		}
 		
		private function get loonImage():LoonImage {
			return _loonImage;		
		}
				
		private function createButtonBar():HBox {
			buttonBar = new HBox();
			select = new Button();
			select.label = "Select";
			buttonBar.addChild(select);
			//		
			cancel = new Button();
			cancel.label = "Cancel";
			buttonBar.addChild(cancel);
			//
			save = new Button();
			save.label = "Save";
			buttonBar.addChild(save);
			return buttonBar;
		}
		
		private function addEventListeners():void {
			Swiz.addEventListener(ImageLoadedEvent.NAME,__handle__imageLoaded);
			cancel.addEventListener(MouseEvent.CLICK,__handle__cancel);
			save.addEventListener(MouseEvent.CLICK,__handle__save);
			select.addEventListener(MouseEvent.CLICK,__handle__select);
		}

		private function removeEventListeners():void {
			Swiz.removeEventListener(ImageLoadedEvent.NAME,__handle__imageLoaded);
			cancel.removeEventListener(MouseEvent.CLICK,__handle__cancel);
			cancel.removeEventListener(MouseEvent.CLICK,__handle__save);
			select.removeEventListener(MouseEvent.CLICK,__handle__select);
		}
		
		public function __handle__cancel(o:*):void {
			PopUpManager.removePopUp(this);
			removeEventListeners();
			removeAllChildren();
		}
		
		public function __handle__save(o:*):void {
			dispatchEvent(new ImageEditEvent(ImageEditEvent.IMAGE_CREATED,loonImage));
			PopUpManager.removePopUp(this);
			removeEventListeners();
			removeAllChildren();
		}
		
		public function __handle__select(o:*):void {
			service.createImage(this);
		}
		
		public function __handle__imageLoaded(e:ImageLoadedEvent):void {
			if(e.callee != null && e.callee === this ) {
				this.loonImage = e.image;
			}
		}
	}
}