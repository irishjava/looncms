package uk.mafu.flex.builders.display
{
	import flash.events.Event;
	
	import mx.binding.utils.BindingUtils;
	import mx.containers.ControlBar;
	import mx.containers.HBox;
	import mx.containers.Panel;
	import mx.controls.Label;
	import mx.controls.TextArea;
	import mx.controls.TextInput;
	import mx.core.UIComponent;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.flex.meta.ROW;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.domain.data.VideoLink;
	import uk.mafu.loon.events.EditOneToOneRelationshipEvent;
	import uk.mafu.loon.events.EntitySavedEvent;
	import uk.mafu.loon.events.OneToOneRelationshipLoadedEvent;
	import uk.mafu.loon.events.video.VideoEditEvent;
	import uk.mafu.loon.executions.EditSingleVideoExecution;

	public class SingleVideo extends EntityContainer
	{
		private var panel:Panel;
		
		private var relationship_name:String;
		private var row:ROW;
	
		private var titleLabel:Label;
		private var titleText:TextArea;	
		
		private var captionLabel:Label;
		private var captionText:TextArea;	
		
		private var widthLabel:Label;
		private var widthText:TextInput;
			
		private var heightLabel:Label;
		private var heightText:TextInput;
			
		private var videoPanel:VideoPanel;
	 
	 	override protected function reload(evt:EntitySavedEvent):void {
			removeWidgets();
			render();
		}	
	 
		private function __handle__OneToOneLoadedEvent(evt:OneToOneRelationshipLoadedEvent):void {
			if(Util.isSameEntity(evt.parent_clazz,Util.getClass(entityParent)) 
				&& (evt.relationship == relationship_name)) {
					if(evt.result.data != null){
						var res:VideoLink = VideoLink(evt.result.data);
						videoLink.pk =res.pk;
						videoLink.title = res.title;
						videoLink.caption = res.caption;
						videoLink.width = res.width;
						if(widthText.text == "NaN") {
							widthText.text = "";
						}
						videoLink.height = res.height;
						if(heightText.text == "NaN") {
							heightText.text = "";
						}
						videoLink.videoId = res.videoId;
						videoPanel.data = res.videoId;
						titleText.enabled = true;
						captionText.enabled = true;
						clearExecutionQueue();
					}
				}
		}
					
		public function SingleVideo(service:IService,entityParent:Object,row:ROW,parentContainer:UIComponent){
			super(service,row.type,Util.getClass(entityParent),row.name);
			this.row = row;
			this.relationship_name = row.name;
			this.entityParent = entityParent;
			parentContainer.addChild(this);
			this.relationship_name  = row.name;
			this.data = new VideoLink;
			render();
			//Disable if parent is not allready a persistent object 
			if(Util.isUnattachedEntity(entityParent)) {
				enabled = false;
			}
			else{
				Swiz.addEventListener(OneToOneRelationshipLoadedEvent.NAME,__handle__OneToOneLoadedEvent,false,0,true);
				service.loadOneToOne(
					parent_clazz,
					clazz,
					relationship_name,
					Util.getPrimaryKey(entityParent),
					["pk","title","caption","width","height","videoId"],false
				);
			}
		}
		
		private function get videoLink():VideoLink {
			return this.data as VideoLink;
		}
		
		private function set videoLink(link:VideoLink):void {
			this.data = link;
		}
		
		public override function set data(data:Object):void {
			super.data = data; 
			trace("SingleVideo set data called");
		}
		
		public override function render():void {
			titleLabel = new Label();
			titleLabel.text = "Title";
			addChild(titleLabel);
			
			captionLabel = new Label();
			captionLabel.text = "Caption";
			addChild(captionLabel);
			
			titleText = new TextArea();
			titleText.percentWidth = 65;
			titleText.height = 20 ;
			titleText.enabled = false;
			
			captionText = new TextArea();
			captionText.percentWidth = 65;
			captionText.height = 20 ;
			captionText.enabled = false;
			
			//Bind from widget to the data
			BindingUtils.bindProperty(titleText,"text",videoLink,"title" );
			//Bind from data to the widget
			BindingUtils.bindProperty(videoLink,"title",titleText, "text");
			titleText.addEventListener(Event.CHANGE,function (evt:Event):void{
				Util.debug("titleText.event.change data='" + data + "'");
				addToExecutionQueue(new EditSingleVideoExecution(data as VideoLink,relationship_name));
				Swiz.dispatchEvent(
					new EditOneToOneRelationshipEvent(
							data,relationship_name,Util.getClass(entityParent),Util.getPrimaryKey(entityParent)
						)
				);
			});
			
			//Bind from widget to the data
			BindingUtils.bindProperty(captionText,"text",videoLink,"caption" );
			//Bind from data to the widget
			BindingUtils.bindProperty(videoLink,"caption",captionText, "text");
			captionText.addEventListener(Event.CHANGE,function (evt:Event):void{
				Util.debug("captionText.event.change data='" + data + "'");
				addToExecutionQueue(new EditSingleVideoExecution(data as VideoLink,relationship_name));
				Swiz.dispatchEvent(
					new EditOneToOneRelationshipEvent(
							data,relationship_name,Util.getClass(entityParent),Util.getPrimaryKey(entityParent)
						)
				);
			});
			var captionRow:HBox = new HBox();
			captionRow.addChild(captionLabel);
			captionRow.addChild(captionText);
			addChild(captionRow);
			
			/////////////////////////////////////////////////////////////////////////////////////////////////////////
			var titleRow:HBox = new HBox();
			titleRow.addChild(titleLabel);
			titleRow.addChild(titleText);
			addChild(titleRow);
			
			var dimensionBar:ControlBar =new ControlBar();
			addChild(dimensionBar);
			
			///WIDTH ....
			widthLabel = new Label();
			widthLabel.text = "Width:";
			
			dimensionBar.addChild(widthLabel);
			
			widthText = new TextInput();
	 		widthText.width = 65;
			widthText.height = 20 ;
			 
			//Bind from widget to the data
			BindingUtils.bindProperty(widthText,"text",videoLink,"width" );
			//Bind from data to the widget
			BindingUtils.bindProperty(videoLink,"width",widthText, "text");
			widthText.addEventListener(Event.CHANGE,function (evt:Event):void{
				Util.debug("videoLink.width='" + videoLink.width  + "'");
				addToExecutionQueue(new EditSingleVideoExecution(data as VideoLink,relationship_name));
				Swiz.dispatchEvent(
					new EditOneToOneRelationshipEvent(
							data,relationship_name,Util.getClass(entityParent),Util.getPrimaryKey(entityParent)
						)
				);
			});
			dimensionBar.addChild(widthText);
			//////////////////////////////////////////////////////////////////////////////////////////////////			
			heightLabel = new Label();
			heightLabel.text = "Height:";
			
			dimensionBar.addChild(heightLabel);
			
			heightText = new TextInput();
			heightText.width = 30;
			heightText.height = 20 ;
			
			//Bind from widget to the data
			BindingUtils.bindProperty(heightText,"text",videoLink,"height" );
			//Bind from data to the widget
			BindingUtils.bindProperty(videoLink,"height",heightText, "text");
			heightText.addEventListener(Event.CHANGE,function (evt:Event):void{
				Util.debug(" videoLink.height='" + videoLink.height + "'");
				addToExecutionQueue(new EditSingleVideoExecution(data as VideoLink,relationship_name));
				Swiz.dispatchEvent(
					new EditOneToOneRelationshipEvent(
							data,relationship_name,Util.getClass(entityParent),Util.getPrimaryKey(entityParent)
						)
				);
			});
			dimensionBar.addChild(heightText);
			//////////////////////////////////////////////////////////////////////////////////////////////////
			
			videoPanel = new VideoPanel();
			videoPanel.service = service;
			videoPanel.closeButton.visible =false;
			addChild(videoPanel);
			videoPanel.addEventListener(VideoEditEvent.VIDEO_CREATE,function (e:VideoEditEvent):void{
				Util.debug("received video create event " + e.pk.toString() )
				videoLink.videoId = e.pk;
				addToExecutionQueue(new EditSingleVideoExecution(data as VideoLink,relationship_name));
				Swiz.dispatchEvent(
					new EditOneToOneRelationshipEvent(
							data,relationship_name,Util.getClass(entityParent),Util.getPrimaryKey(entityParent)
						)
				);
				titleText.enabled = true;
				captionText.enabled = true;
			});
			
			videoPanel.addEventListener(VideoEditEvent.VIDEO_DELETE,function (e:VideoEditEvent):void{
				Util.debug("received video delete event " + e.pk.toString() );
				videoLink.videoId = e.pk;
				addToExecutionQueue(new EditSingleVideoExecution(data as VideoLink,relationship_name));
				Swiz.dispatchEvent(
					new EditOneToOneRelationshipEvent(
							data,relationship_name,Util.getClass(entityParent),Util.getPrimaryKey(entityParent)
						)
				);
				titleText.enabled = false;
				captionText.enabled = false;
			});
		}
		
		override protected function removeEventListeners():void {
			Swiz.removeEventListener(OneToOneRelationshipLoadedEvent.NAME,__handle__OneToOneLoadedEvent);
		}
		
		override protected function removeWidgets():void{
			videoPanel.dispose();	
			this.removeAllChildren();
		}
	}
}