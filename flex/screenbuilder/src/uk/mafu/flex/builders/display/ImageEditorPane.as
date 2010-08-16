package uk.mafu.flex.builders.display
{
	import flash.utils.ByteArray;
	
	import mx.containers.Canvas;
	
	import uk.mafu.loon.IConfiguration;
	import uk.mafu.loon.IService;
	
	[Event(name="imageUploadSuccess", type="uk.mafu.loon.events.im.ImageUploadedEvent")]
	[Event(name="imageUploadFailure", type="uk.mafu.loon.events.upload.ImageUploadedEvent")]
	public class ImageEditorPane extends Canvas
	{
		private var service:IService,config:IConfiguration;
		
		public function ImageEditorPane(service:IService,config:IConfiguration,bytes:ByteArray)
		{
			super();
			this.service = service;
			this.config = config;
			width = 600;
			height = 400;
		}
	}
}