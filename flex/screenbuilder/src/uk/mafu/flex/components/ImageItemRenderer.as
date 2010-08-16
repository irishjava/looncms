package uk.mafu.flex.components
{
	import flash.utils.ByteArray;
	
	import mx.containers.Canvas;
	import mx.controls.Image;
	import mx.controls.Label;
	import mx.core.IFactory;
	
	import uk.mafu.loon.IImageThumbLoadedListener;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.domain.data.ImageLink;

	public class ImageItemRenderer extends Canvas implements IFactory,IImageThumbLoadedListener
	{

		[Embed(source="default.png",mimeType="application/octet-stream")]
		private var defaultPNG: Class;
		
		private var image:Image;
		private var title:Label;
		private var _service:IService;
		private var _data:ImageLink
		
		override public function set data(o:Object):void {
			this._data = ImageLink(o);
		}
		
		override public function get data():Object {
			return Object(this._data); 
		}
		
		public function get imageLink():ImageLink {
			return ImageLink(this.data);
		}
		
		public function ImageItemRenderer(service:IService)
		{
			super();
			this._service = service;
			width = 195;
			height = 195;
			setStyle("bgcolor",0xFFFFFF);
		}
		
		override protected function createChildren():void
		{
			super.createChildren();
			title = new Label();
			title.text = "untitled";
			image = new Image();
			
			var b:ByteArray = (new defaultPNG() as ByteArray);
			image.data =  b;
			image.x = 10;
			title.x = 10;
			
			this.addChild(image);
			this.addChild(title);
		}
		
		public function newInstance():* {
			return new ImageItemRenderer(_service);
		}
		
		override protected function commitProperties():void
		{
			super.commitProperties();
			var imageLink:ImageLink = (data as ImageLink);
			_service.loadImageThumb(imageLink.imageId,this.image);
			title.text = (imageLink.title != null) ? imageLink.title : "untitled";
			trace(imageLink);
		}
	}
}