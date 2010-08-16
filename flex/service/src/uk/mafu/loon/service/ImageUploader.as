package uk.mafu.loon.service
{
	import flash.events.DataEvent;
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.net.FileReference;
	import flash.net.URLRequest;
	import flash.net.URLRequestMethod;
	import flash.net.URLVariables;
	import uk.mafu.flex.util.Util;
	import mx.containers.HBox;
	import mx.containers.VBox;
	import mx.controls.Alert;
	import mx.controls.Button;
	import mx.controls.Image;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IConfiguration;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.events.image.ImageUploadEvent;

	
	[Event(name="imageUploadSuccess", type="uk.mafu.loon.events.upload.ImageUploadEvent")]
	[Event(name="imageUploadFailure", type="uk.mafu.loon.events.upload.ImageUploadEvent")]
	[Bindable]
	public class ImageUploader extends EventDispatcher
	{
		private var config:IConfiguration;
		private var hbox:HBox;
		private var buttonBar:VBox;
		private var select:Button;
		private var close:Button;
		private var image:Image;
		private	var params:ImageUploaderParam ;
		private	var imageUploader:ImageUploader;
			
		private const MAX_UPLOAD_SIZE:Number = 250000;	
		private var fileRef:FileReference;
		private var imageUploadParam:ImageUploaderParam;
		private var imageUploadResult:ImageUploaderResult
		
		public function ImageUploader(config:IConfiguration,service:IService){
			imageUploadParam = new ImageUploaderParam();
			imageUploadParam.target = config.getImageUploadUrl();
		}
		
		public function __handle__uploadComplete(e:DataEvent):void {
			imageUploadResult = new ImageUploaderResult(String(e.data));
			Util.debug(imageUploadResult.toString());
			dispatchEvent(new ImageUploadEvent(ImageUploadEvent.IMAGE_UPLOAD_SUCCESS,imageUploadResult));
		}
		
		public function selectFile():void {
			fileRef = new FileReference();
			fileRef.browse(ImageUploaderParam.getImageFilters());
			fileRef.addEventListener(Event.SELECT,__handle__selection);
			fileRef.addEventListener(DataEvent.UPLOAD_COMPLETE_DATA,__handle__uploadComplete);
		}
		
		public function __handle__selection(e:Event):void {
			if(fileRef.size > 285000) {
				Alert.show("The selected file is too large to upload, the maximum file size is 285 kB: '" + fileRef.name + "'");
				return;
			}
			var request:URLRequest = new URLRequest(imageUploadParam.target);
			var urlVars:URLVariables = new URLVariables();  
			request.method = URLRequestMethod.POST;
   			request.data = urlVars;
   			fileRef.upload(request,"data",true);
		}
	}
}