package uk.mafu.flex.builders.display
{
	import flash.events.Event;
	
	import mx.binding.utils.BindingUtils;
	import mx.containers.Panel;
	import mx.containers.VBox;
	import mx.controls.Label;
	import mx.controls.TextArea;
	import mx.core.UIComponent;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.flex.meta.ROW;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.domain.data.AudioLink;
	import uk.mafu.loon.events.AudioEditEvent;
	import uk.mafu.loon.events.EditOneToOneRelationshipEvent;
	import uk.mafu.loon.events.EntitySavedEvent;
	import uk.mafu.loon.events.OneToOneRelationshipLoadedEvent;
	import uk.mafu.loon.executions.EditSingleAudioExecution;

	public class SingleAudio extends EntityContainer
	{
		private var panel:Panel;
		
		private var relationship_name:String;
		private var row:ROW;
	
		private var titleLabel:Label;
		private var titleText:TextArea;	
		
		private var captionLabel:Label;
		private var captionText:TextArea;	
			
		private var videoPanel:AudioPanel;
	 
	 	override protected function reload(evt:EntitySavedEvent):void {
			removeWidgets();
			render();
		}	
	 
		private function __handle__OneToOneLoadedEvent(evt:OneToOneRelationshipLoadedEvent):void {
			if(Util.isSameEntity(evt.parent_clazz,Util.getClass(entityParent)) && 
				(evt.relationship == relationship_name)) {
					if(evt.result.data != null){
						var res:AudioLink = AudioLink(evt.result.data);
						audioLink.pk =res.pk;
						audioLink.title = res.title;
						audioLink.caption = res.caption;
						audioLink.audioId = res.audioId;
						videoPanel.data = res.audioId;
						titleText.enabled = true;
						captionText.enabled = true;
						clearExecutionQueue();
					}
				}
		}
					
		public function SingleAudio(service:IService,entityParent:Object,row:ROW,parentContainer:UIComponent){
			super(service,row.type,Util.getClass(entityParent),row.name);
			this.row = row;
			this.relationship_name = row.name;
			this.entityParent = entityParent;
			parentContainer.addChild(this);
			this.relationship_name  = row.name;
			this.data = new AudioLink;
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
		
		private function get audioLink():AudioLink {
			return this.data as AudioLink;
		}
		
		private function set audioLink(link:AudioLink):void {
			this.data = link;
		}
		
		public override function set data(data:Object):void {
			super.data = data; 
			trace("SingleAudio set data called");
		}
		
		public override function render():void {
			titleLabel = new Label();
			titleLabel.text = "Title";
			
			titleText = new TextArea();
			titleText.percentWidth = 65;
			titleText.height = 20 ;
			titleText.enabled = false;
			
			captionLabel = new Label();
			captionLabel.text = "Caption";
			
			captionText = new TextArea();
			captionText.percentWidth = 65;
			captionText.height = 20 ;
			captionText.enabled = false;
			
			//Bind from widget to the data
			BindingUtils.bindProperty(captionText,"text",audioLink,"caption" );
			//Bind from data to the widget
			BindingUtils.bindProperty(audioLink,"caption",captionText, "text");
			captionText.addEventListener(Event.CHANGE,function (evt:Event):void{
				Util.debug("captionText.event.change data='" + data + "'");
				addToExecutionQueue(new EditSingleAudioExecution(data as AudioLink,relationship_name));
				Swiz.dispatchEvent(
					new EditOneToOneRelationshipEvent(
							data,relationship_name,Util.getClass(entityParent),Util.getPrimaryKey(entityParent)
						)
				);
			});
			
			//Bind from widget to the data
			BindingUtils.bindProperty(titleText,"text",audioLink,"title" );
			//Bind from data to the widget
			BindingUtils.bindProperty(audioLink,"title",titleText, "text");
			titleText.addEventListener(Event.CHANGE,function (evt:Event):void{
				Util.debug("titleText.event.change data='" + data + "'");
				addToExecutionQueue(new EditSingleAudioExecution(data as AudioLink,relationship_name));
				Swiz.dispatchEvent(
					new EditOneToOneRelationshipEvent(
							data,relationship_name,Util.getClass(entityParent),Util.getPrimaryKey(entityParent)
						)
				);
			});
			
			/////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			var titleBox:VBox = new VBox();
			titleBox.addChild(titleLabel);
			titleBox.addChild(titleText);
				
			var captionBox:VBox = new VBox();
			captionBox.addChild(captionLabel);
			captionBox.addChild(captionText);
			
			addChild(titleBox);
			addChild(captionBox);
			
			//////////////////////////////////////////////////////////////////////////////////////////////////
			
			videoPanel = new AudioPanel();
			videoPanel.service = service;
			videoPanel.closeButton.visible =false;
			addChild(videoPanel);
			videoPanel.addEventListener(AudioEditEvent.AUDIO_CREATE,function (e:AudioEditEvent):void{
				Util.debug("received video create event " + e.pk.toString() )
				audioLink.audioId = e.pk;
				addToExecutionQueue(new EditSingleAudioExecution(data as AudioLink,relationship_name));
				Swiz.dispatchEvent(
					new EditOneToOneRelationshipEvent(
							data,relationship_name,Util.getClass(entityParent),Util.getPrimaryKey(entityParent)
						)
				);
				captionText.enabled = true;
				titleText.enabled = true;
			});
			
			videoPanel.addEventListener(AudioEditEvent.AUDIO_DELETE,function (e:AudioEditEvent):void{
				Util.debug("received video delete event " + e.pk.toString() );
				audioLink.audioId = e.pk;
				addToExecutionQueue(new EditSingleAudioExecution(data as AudioLink,relationship_name));
				Swiz.dispatchEvent(
					new EditOneToOneRelationshipEvent(
							data,relationship_name,Util.getClass(entityParent),Util.getPrimaryKey(entityParent)
						)
				);
				captionText.enabled = false;
				titleText.enabled = false;
			});
		}
		
		override protected function removeEventListeners():void {
			Swiz.removeEventListener(OneToOneRelationshipLoadedEvent.NAME,__handle__OneToOneLoadedEvent);
		}
		
		override protected function removeWidgets():void{
			//videoPanel.dispose();	
			this.removeAllChildren();
		}
	}
}