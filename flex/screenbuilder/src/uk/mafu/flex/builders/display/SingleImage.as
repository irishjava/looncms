package uk.mafu.flex.builders.display
{
	import flash.events.MouseEvent;
	
	import mx.containers.Box;
	import mx.containers.BoxDirection;
	import mx.containers.HBox;
	import mx.containers.VBox;
	import mx.controls.Button;
	import mx.controls.Image;
	import mx.controls.Label;
	import mx.core.Application;
	import mx.core.UIComponent;
	import mx.managers.PopUpManager;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.flex.meta.ROW;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.LoonImage;
	import uk.mafu.loon.events.EntitySavedEvent;
	import uk.mafu.loon.events.OneToOneRelationshipLoadedEvent;
	import uk.mafu.loon.events.image.DeleteSingleImageEvent;
	import uk.mafu.loon.events.image.EditSingleImageEvent;
	import uk.mafu.loon.events.image.ImageEditEvent;
	import uk.mafu.loon.events.image.ImageLoadedEvent;

	[Bindable]
	public class SingleImage extends EntityContainer
	{
		//
		private var imageBox:Box ;
		private var outerHbox:HBox ;
		private var rightVbox:VBox ;
		private var addButton:Button ;
		private var editPropertiesButton:Button ;
		private var deleteButton:Button ;
	
		//private var canvas:Canvas;
		private var image:Image;
		private var _loonImage:LoonImage;
		private var _imageLink:ImageLink = new ImageLink;
		
		private var titleLabel:Label;
		private var relationship_name:String;
		
		private var row:ROW;
		
		public function set imageLink(i:ImageLink) :void {
			this._imageLink = i;
		}
		
		public function get imageLink() :ImageLink {
			return this._imageLink;
		}
			
		public function SingleImage(service:IService,entityParent:Object,row:ROW,parentContainer:UIComponent){
			super(service,row.type,Util.getClass(entityParent),row.name);
			this.row = row;
			direction = BoxDirection.VERTICAL;
			this.relationship_name = row.name;
			parentContainer.addChild(this);
			this.entityParent = entityParent
			render();
			addListeners();
			if(Util.isTransient(entityParent)) {
				this.enabled =  false;
			}
			else{
				service.loadOneToOne(
				parent_clazz,
				clazz,
				relationship_name,
				Util.getPrimaryKey(entityParent),
				["pk","excite","title","text","caption","width","height","imageId"],false);
			}
		}
	
		public override function render():void {
			outerHbox = new HBox();
			var label:Label = new Label();
			label.text = row.label;
			addChild(label);
			
			image = new Image();
			imageBox = new Box();
			rightVbox = new VBox();
			
			addButton = new Button();
			addButton.label = "Add";
			rightVbox.addChild(addButton);
			
			deleteButton = new Button();
			deleteButton.label = "Delete";
			rightVbox.addChild(deleteButton);
			
			editPropertiesButton = new Button();
			editPropertiesButton .label = "Edit Properties";
			rightVbox.addChild(editPropertiesButton);
			editPropertiesButton.enabled = false;
			
			imageBox.addChild(image);
			imageBox.maxHeight = 300;
			imageBox.maxWidth = 400;
			
			outerHbox.addChild(imageBox);
			outerHbox.addChild(rightVbox);
			this.addChild(outerHbox);
		
		}
		
		private function __handle__OneToOneLoadedEvent(evt:OneToOneRelationshipLoadedEvent):void {
			if( Util.isSameEntity(evt.parent_clazz,Util.getClass(parent_clazz)) 
				&& (evt.relationship == relationship_name)) {
					if(evt.result.data != null){
						this.imageLink = ImageLink(evt.result.data);
						service.loadImage(_imageLink.imageId,image);
						editPropertiesButton.enabled = true;
					}
				}
		}
		
		private function __handle__EditSingleImageEvent(evt:EditSingleImageEvent):void {
			if(evt.relationship == relationship_name && 
				evt.parent == entityParent){
				addToExecutionQueue(evt);
				editPropertiesButton.enabled = true;
			}
		}
		
		private function __handle__DeleteSingleImageEvent(evt:DeleteSingleImageEvent):void {
			if(evt.relationship == relationship_name && 
				evt.entityParent === entityParent){
				addToExecutionQueue(evt);
				editPropertiesButton.enabled = false;
			}
		}
		
		private function __handle__click_add(evt:*):void {
			var iupw:ImageUploadWindow = new ImageUploadWindow(service);
			PopUpManager.addPopUp(iupw,Application.application as Application,true);
			PopUpManager.centerPopUp(iupw);
			PopUpManager.bringToFront(iupw);
			iupw.addEventListener(ImageEditEvent.IMAGE_CREATED,
			function(evt:ImageEditEvent):void {
				var changed:Boolean =false;
				if(imageLink == null){
					imageLink = new ImageLink();
					changed = true;
				}
				else {
					if(!(imageLink.imageId == evt.loonImage.pk )) {
						imageLink.imageId = evt.loonImage.pk
						changed = true;
					}
					if(!(imageLink.height == evt.loonImage.height )) {
						imageLink.height = evt.loonImage.height
						changed = true;
					}
					if(!(imageLink.width == evt.loonImage.width )) {
						imageLink.width = evt.loonImage.width
						changed = true;
					}
					//If stuff has changed, it's probably about time we reloaded the image ...
					if(changed == true) {
							service.loadImage(imageLink.imageId,image);
							var editSingle:EditSingleImageEvent  = 
								new EditSingleImageEvent(
									imageLink,relationship_name,entityParent);
									//
							Swiz.dispatchEvent(editSingle);
					}
				}
			});
		}
		
		private function __handle__click_delete(evt:*):void {
			_loonImage = null;
			image.data = null;
			Swiz.dispatchEvent(new DeleteSingleImageEvent(entityParent,
									relationship_name));
		}
		
		private function __handle__ImageLoadedEvent(evt:ImageLoadedEvent):void {
			if(evt.callee === image) {
				if(evt.image != null) {
					image.data = evt.image.data;
					image.width = evt.image.width;
					image.height = evt.image.height;
					editPropertiesButton.enabled = true;
				}
			}	
		}
		
		override protected function reload(evt:EntitySavedEvent):void {
			service.loadOneToOne(
					parent_clazz,
					clazz,
					relationship_name,
					Util.getPrimaryKey(entityParent),
					["pk","excite","title","text",",caption","width","height","imageId"]
					);
		}
		
		override protected function removeEventListeners():void {
			Swiz.removeEventListener(OneToOneRelationshipLoadedEvent.NAME,__handle__OneToOneLoadedEvent);
			Swiz.removeEventListener(ImageLoadedEvent.NAME ,__handle__ImageLoadedEvent);
			Swiz.removeEventListener(EditSingleImageEvent.NAME ,__handle__EditSingleImageEvent);
			Swiz.removeEventListener(DeleteSingleImageEvent.NAME ,__handle__DeleteSingleImageEvent);
			addButton.removeEventListener(MouseEvent.CLICK,__handle__click_add);
			deleteButton.removeEventListener(MouseEvent.CLICK,__handle__click_delete);
			editPropertiesButton.removeEventListener(MouseEvent.CLICK,__handle__click_editPropertiesButton);
		}
		
		private function addListeners():void {
			Swiz.addEventListener(OneToOneRelationshipLoadedEvent.NAME,__handle__OneToOneLoadedEvent,false,0,true);
			Swiz.addEventListener(ImageLoadedEvent.NAME ,__handle__ImageLoadedEvent,false,0,true);
			Swiz.addEventListener(EditSingleImageEvent.NAME ,__handle__EditSingleImageEvent,false,0,true);
			Swiz.addEventListener(DeleteSingleImageEvent.NAME ,__handle__DeleteSingleImageEvent,false,0,true);
			addButton.addEventListener(MouseEvent.CLICK,__handle__click_add);
			deleteButton.addEventListener(MouseEvent.CLICK,__handle__click_delete);
			editPropertiesButton.addEventListener(MouseEvent.CLICK,__handle__click_editPropertiesButton);
		}
		
		private function __handle__click_editPropertiesButton(evt:*):void {
			var imagePropertyEditorWindow:ImagePropertyEditorWindow = new ImagePropertyEditorWindow(imageLink);
			imagePropertyEditorWindow.addEventListener(ImageEditEvent.PROPERTIES_MODIFIED,function (e:*):void{
				var editSingle:EditSingleImageEvent  = 
								new EditSingleImageEvent(
									imageLink,relationship_name,entityParent);
				Swiz.dispatchEvent(editSingle);
			});
			PopUpManager.addPopUp(imagePropertyEditorWindow,Application.application as Application,true);
			PopUpManager.centerPopUp(imagePropertyEditorWindow);
		}
		
		override protected function removeWidgets():void{
			this.removeAllChildren();
		}
	}
}