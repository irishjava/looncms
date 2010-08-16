package uk.mafu.flex.builders.display
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.FileFilter;
	import flash.net.FileReference;
	import flash.utils.ByteArray;
	
	import mx.binding.utils.ChangeWatcher;
	import mx.containers.Box;
	import mx.containers.BoxDirection;
	import mx.containers.ControlBar;
	import mx.containers.HBox;
	import mx.containers.VBox;
	import mx.controls.Alert;
	import mx.controls.Button;
	import mx.controls.Spacer;
	import mx.core.ScrollPolicy;
	
	import uk.mafu.flex.util.Assert;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.IUploader;
	import uk.mafu.loon.domain.data.LoonVideo;
	import uk.mafu.loon.events.video.VideoEditEvent;
	
	[Event(name="videoCreate", type="uk.mafu.loon.events.video.VideoEditEvent")]
	[Event(name="videoDelete", type="uk.mafu.loon.events.video.VideoEditEvent")]
	[Event(name="videoClose", type="uk.mafu.loon.events.video.VideoEditEvent")]
	public class VideoPanel extends Box implements IUploader 
	{
	
//		[Bindable]
//		public var videoPk:Number = -1;
		
		[Bindable]
		public var service:IService;
		
		private var hbox:HBox;
		
		[Bindable]
		private var videoDisplay:ClearableVideoDisplay;
		private var vbox1:VBox;
		private var vbox2:VBox;
		private var vidButtonBar:ControlBar;
		private var entityButtonBar:ControlBar;
		private var addButton:Button;
		private var deleteButton:Button;
		public var closeButton:Button;
		
		private var playButton:Button;
		private var pauseButton:Button;
		private var stopButton:Button;
		
		public function VideoPanel()
		{
			
			direction = BoxDirection.HORIZONTAL;
			horizontalScrollPolicy = ScrollPolicy.OFF;
			verticalScrollPolicy = ScrollPolicy.OFF;
			
			//
//			height = 400;
//			width = 400;
			hbox = new HBox();
			vbox1 =new VBox();
			vbox2 =new VBox();
			hbox.addChild(vbox1);
			hbox.addChild(vbox2);
			
			videoDisplay = new ClearableVideoDisplay();
			videoDisplay.enabled= false;
			 
			
			videoDisplay.minWidth = 160;
			videoDisplay.minHeight = 90;
			//
			videoDisplay.maxWidth = 320;
			videoDisplay.maxHeight = 180;
			videoDisplay.maintainAspectRatio = true;
			
			vbox1.addChild(videoDisplay);
			
			vidButtonBar = new ControlBar();
			vbox1.addChild(vidButtonBar);
//		
		 	playButton = new Button();
		 	playButton.label = "Play";
		 	vidButtonBar.addChild(playButton);
		 	
		 	pauseButton = new Button();
		 	pauseButton.label = "Pause";
		 	vidButtonBar.addChild(pauseButton);
		 	
			stopButton = new Button();
			stopButton.label = "Stop";
			vidButtonBar.addChild(stopButton);	
			
			vidButtonBar.enabled = false;		
			
			playButton.addEventListener(MouseEvent.CLICK,function(e:*):void{
					videoDisplay.source = service.configuration.getVideoViewUrl() + data;
					videoDisplay.play();
			});
			
			pauseButton.addEventListener(MouseEvent.CLICK,function(e:*):void{
				if(videoDisplay.playing == true){
					videoDisplay.pause();
				}
			});
			
			stopButton.addEventListener(MouseEvent.CLICK,function(e:*):void{
				if(videoDisplay.playing == true){
					videoDisplay.stop();
				}
			});
			
			//Button bar
			
			entityButtonBar = new ControlBar();	
			entityButtonBar.direction = BoxDirection.VERTICAL;
			var spacer:Spacer = new Spacer();
			spacer.percentHeight = 10;
			entityButtonBar.addChild(spacer);
				
			vbox2.addChild(entityButtonBar);	
			//
			addButton = new Button();
			addButton.label = "Add";
			deleteButton = new Button();
			deleteButton.label = "Delete";
			closeButton = new Button();
			closeButton.label = "Close";
			//
			entityButtonBar.addChild(addButton);
			addButton.addEventListener(MouseEvent.CLICK,addButtonClicked);
			//
			entityButtonBar.addChild(deleteButton);
			deleteButton.addEventListener(MouseEvent.CLICK,deleteButtonClicked);
			//
			entityButtonBar.addChild(closeButton);
			closeButton.addEventListener(MouseEvent.CLICK,closeButtonClicked);
			
			
			addChild(hbox);
			ChangeWatcher.watch(this,"data",videoPkChanged);
			ChangeWatcher.watch(this,"service",serviceChanged);			
			ChangeWatcher.watch(videoDisplay,"enabled",videoDisplayEnabledChanged);
		}
		
		private function getThis():VideoPanel {
			return this;
		}
		
		private function addButtonClicked(evt:*):void {
			var f:FileReference = new FileReference();
			f.addEventListener(Event.COMPLETE,function (e:Event):void {
				var byteArray:ByteArray =new ByteArray();
				Util.debug(f.size.toString());
				Util.debug(f.name);
				byteArray.writeBytes(f.data,0);//,f.data.length -1
				Util.debug(byteArray.length);
				var vid:LoonVideo = new LoonVideo();
				vid.filename= f.name;
				vid.data = byteArray;
				service.uploadVideo(vid,getThis());
			});
			f.addEventListener(Event.SELECT,function (e:Event):void {
				f.load();
			});
			f.browse(
					[new FileFilter("Flv Video Files (*.flv)", "*.flv", "FLV"),
					new FileFilter("Mov Video Files (*.mov)", "*.mov", "MOV"),
					new FileFilter("MP4 Video Files (*.mp4)", "*.mp4", "MP4")]
					);	
		}
			
		public function dispose():void {
			if(videoDisplay!= null) {
				if(videoDisplay.playing == true) {
					videoDisplay.stop();
					videoDisplay.clear();
				}
				videoDisplay = null;
			}
			removeAllChildren();
		}
			
		private function deleteButtonClicked(evt:*):void {
			data= -1;
			dispatchEvent(new VideoEditEvent(VideoEditEvent.VIDEO_DELETE,data as Number));
		}
		
		public function uploadCompleted(pk:Object):void {
			data = pk;
			dispatchEvent(new VideoEditEvent(VideoEditEvent.VIDEO_CREATE,data as Number));
			Alert.show("Upload complete");
		}
		
		private function closeButtonClicked(evt:*):void {
			dispatchEvent(new VideoEditEvent(VideoEditEvent.VIDEO_CLOSE,data as Number));
		}
		
		private function videoPkChanged(evt:*):void {
			Util.debug("imagePk was changed, new value:" +  data );
			Assert.notNull(service,"service must not be null, how am I expected to do anything ?");
			if(data > 0){
				Util.debug("and service is not set to null, will enable player buttons..");
				vidButtonBar.enabled = true;
				videoDisplay.clear();
			}
			if(data  < 1){
				vidButtonBar.enabled = false;
				videoDisplay.clear();
			}
		} 
		
		private function videoDisplayEnabledChanged(evt:*):void {
			if(videoDisplay.enabled == true){
				vidButtonBar.enabled = true;	
			}
			else {
				vidButtonBar.enabled = false;	
			}
		} 

		private function serviceChanged(evt:*):void {
			Util.debug("service was changed");
		}
	}
}