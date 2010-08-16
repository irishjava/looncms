package uk.mafu.flex.builders.display
{
	import flash.display.DisplayObjectContainer;
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.containers.BoxDirection;
	import mx.containers.HBox;
	import mx.containers.HDividedBox;
	import mx.controls.Alert;
	import mx.controls.Button;
	import mx.controls.HorizontalList;
	import mx.controls.Label;
	import mx.core.Application;
	import mx.core.ScrollPolicy;
	import mx.core.UIComponent;
	import mx.events.DragEvent;
	import mx.events.ListEvent;
	import mx.managers.DragManager;
	import mx.managers.PopUpManager;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.flex.components.LoonHorizontalList;
	import uk.mafu.flex.meta.ClassMeta;
	import uk.mafu.flex.meta.ROW;
	import uk.mafu.flex.util.ReflectionUtil;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.events.EditImageEvent;
	import uk.mafu.loon.events.EditOneToManyImagesEvent;
	import uk.mafu.loon.events.EntitySavedEvent;
	import uk.mafu.loon.events.OneToManyLoadedEvent;
	import uk.mafu.loon.events.RemoveImageEvent;
	import uk.mafu.loon.events.image.ImageEditEvent;
	import uk.mafu.loon.events.image.UploadImageEvent;

	public class ImageGallery extends EntityContainer
	{
		private var row:ROW;
		private var to_clazz_meta:ClassMeta ;
		private var labelCol:String ;
		private var relationship_name:String ;
		//Components
		private var children:LoonHorizontalList;
		private var editButton:Button;
		private var uploadButton:Button;
		private var deleteButton:Button;
		
		override protected function reload(evt:EntitySavedEvent):void {
			if(this.to_clazz_meta.clazz == Util.getClass(evt.entity)){
				updateList(children,evt.entity);
			}
		}
		
		private function updateList(list:HorizontalList,o:Object):void {
			var provider:ArrayCollection = (list.dataProvider as ArrayCollection);
			for each ( var item:Object in provider) {
				if(Util.getPrimaryKey(item) == Util.getPrimaryKey(o)) {
					var originalIndex:Number = provider.getItemIndex(item); 
					provider.removeItemAt(originalIndex);
					provider.addItemAt(o,originalIndex);
					return;
				}
			}
		}
		
		private function createMutuallyExclusiveSelectionBehavior(options:HorizontalList,children:HorizontalList):void{
				options.addEventListener(ListEvent.CHANGE,function(evt:ListEvent):void {
					children.selectedIndex = -1;
					editButton.enabled = false;
					deleteButton.enabled = false;
				});
				children.addEventListener(ListEvent.CHANGE,function(evt:ListEvent):void {
					options.selectedIndex = -1;
				});
		}
		
		private function addDoubleClickListener(list:HorizontalList):void{
			list.addEventListener(ListEvent.ITEM_DOUBLE_CLICK,function (e:ListEvent):void {
				var o:Object = (e.target as LoonHorizontalList).selectedItem;
				Util.debug("double clicked on item" + Util.getPrimaryKey(o));
				var listChild:ImageLink = ImageLink(LoonHorizontalList(e.currentTarget).selectedItem);
				Swiz.dispatchEvent(new EditImageEvent(listChild));
			});
		}
		
		public function ImageGallery(service:IService,entityParent:Object,row:ROW,parentContainer:UIComponent){
			super(service,row.relationship.to_clazz.clazz,row.relationship.from_clazz.clazz);
			this.entityParent = entityParent;
			this.row = row;
			parentContainer.addChild(this);
			this.to_clazz_meta   = ReflectionUtil.extractClassMeta(row.relationship.to_clazz.clazz);
			this.labelCol  = to_clazz_meta.displayMeta.chooserSettings.labelColumn;
			this.relationship_name  = row.relationship.name;
			addEventListeners();
			render();
		}
		
		public function __handle__EditOneToManyImagesEvent(e:EditOneToManyImagesEvent):void{
			if(e.parentId == Util.getPrimaryKey(entityParent) 
			&& e.relationship == relationship_name) {
				addToExecutionQueue(e);
			}
		}
		
		override protected function removeEventListeners():void{
			Swiz.removeEventListener(OneToManyLoadedEvent.NAME,__handle__OneToManyLoadedEvent);
			Swiz.removeEventListener(EditOneToManyImagesEvent.NAME,__handle__EditOneToManyImagesEvent);
			Swiz.removeEventListener(EntitySavedEvent.NAME,__handle__link_saved_event);
			//
			removeEventListener(UploadImageEvent.NAME,__handle__UploadImageEvent);
			removeEventListener(EditImageEvent.NAME,__handle__EditImageEvent);
			removeEventListener(RemoveImageEvent.NAME,__handle__RemoveImageEvent);
		}
		
		protected function addEventListeners():void{
			Swiz.addEventListener(OneToManyLoadedEvent.NAME,__handle__OneToManyLoadedEvent,false,0,true);
			Swiz.addEventListener(EditOneToManyImagesEvent.NAME,__handle__EditOneToManyImagesEvent,false,0,true);
			Swiz.addEventListener(EntitySavedEvent.NAME,__handle__link_saved_event,false,0,true);
			//
			addEventListener(UploadImageEvent.NAME,__handle__UploadImageEvent,false,0,true);
			addEventListener(EditImageEvent.NAME,__handle__EditImageEvent,false,0,true);
			addEventListener(RemoveImageEvent.NAME,__handle__RemoveImageEvent,false,0,true);
			//
		}

		protected function __handle__link_saved_event(evt:EntitySavedEvent):void{
			if(evt.entity is ImageLink){
				service.loadOneToMany(
				row.relationship.from_clazz.clazz,
				row.relationship.to_clazz.clazz,
				row.relationship.name,
				Util.getPrimaryKey(entityParent),
				["pk","excite","title","caption","text","width","height","imageId"]
				);
			}
		}
		
		protected function __handle__UploadImageEvent(evt:UploadImageEvent):void {
			var castable:Boolean = (new evt.parent_clazz as parent_clazz != null) ;
			var matchingIds:Boolean = evt.parentId  == Util.getPrimaryKey(entityParent) ;
			var sameName:Boolean = evt.relationship == relationship_name;
			if(castable	&& matchingIds	&& sameName) {
				//Alert.show("__handle__UploadImageEvent");
				var window:ImageUploadWindow= new ImageUploadWindow(service);
				window.addEventListener(ImageEditEvent.IMAGE_CREATED,function(e:ImageEditEvent):void {
					var link:ImageLink = new ImageLink();
					link.width = e.loonImage.width;
					link.height = e.loonImage.height;
					link.imageId = e.loonImage.pk;
					children.addItem(link);
					//Put the event into the queue
					Swiz.dispatchEvent(new EditOneToManyImagesEvent( 
						children.dataProvider as ArrayCollection,
						relationship_name,
						parent_clazz,			
						Util.getPrimaryKey(entityParent)
						)
				);  
				});
				PopUpManager.addPopUp(window,Application.application as Application,true);
				PopUpManager.centerPopUp(window);
			}		
		}
		
		protected function __handle__EditImageEvent(evt:EditImageEvent):void {
			var window:ImagePropertyEditorWindow = new ImagePropertyEditorWindow(evt.imageLink);
			window.addEventListener(ImageEditEvent.PROPERTIES_MODIFIED,
			function(e:ImageEditEvent):void {
				Util.debug("and i handled the change, all hail the transitioner!!!");
				Swiz.dispatchEvent(new EditOneToManyImagesEvent( 
						children.dataProvider as ArrayCollection,
						relationship_name,
						parent_clazz,			
						Util.getPrimaryKey(entityParent)
						)
				);  
			});
				PopUpManager.addPopUp(window,this,true);
				PopUpManager.bringToFront(window);
				PopUpManager.centerPopUp(window);
		}
		
		protected function __handle__RemoveImageEvent(evt:RemoveImageEvent):void {
			Alert.show("__handle__RemoveImageEvent" + evt.imagelink);
			children.removeItem(evt.imagelink);
			Swiz.dispatchEvent(new EditOneToManyImagesEvent( 
						children.dataProvider as ArrayCollection,
						relationship_name,
						parent_clazz,			
						Util.getPrimaryKey(entityParent)
						)
				);  
		}
		
		override protected function removeWidgets():void{
			removeAllChildren();
		}
		 
		public function __handle__OneToManyLoadedEvent(e:OneToManyLoadedEvent):void{
			Util.debug("__handle__OneToManyLoadedEvent:" + Util.getClass(this));			
			if(e.result != null) {
				if(e.result.relationship  == this.relationship_name
				&& e.result.parentPk == Util.getPrimaryKey(entityParent)
				){
					Util.debug("matched, going to handle the event.");
					children.dataProvider = e.result.data;
				}
			}
			else{
					Util.debug("no match,not going to handle the event.");
			}
		}
		
		private function drag_complete_listener(e:DragEvent):void {
			//var dragSource:List = e.dragInitiator as List;
			var selectedItem:Object = (e.dragInitiator as LoonHorizontalList).selectedItem;
			if(e.target === children){
				Swiz.dispatchEvent(new EditOneToManyImagesEvent( 
					children.dataProvider as ArrayCollection,
					relationship_name,
					parent_clazz,			
					Util.getPrimaryKey(entityParent)));  
			}
			Util.debug("this.children.addEventListener(DragEvent.DRAG_COMPLETE");
		}

		private function drag_allow_listener(e:DragEvent):void {
			e.action = DragManager.MOVE;
			//Make sure that the drag drop operation is between related, allowed lists, i.e don't drag Dog onto Vehicles list... 
			if(e.dragInitiator !== children) {
				e.stopImmediatePropagation();
				e.stopPropagation();
			}
			if(isDuplicate(
					(e.dragInitiator as HorizontalList).selectedItem,
					e.dragInitiator as HorizontalList,
					e.target as HorizontalList))	{
				e.stopImmediatePropagation();
				e.stopPropagation();
				return;
			}
			editButton.enabled = false;
			deleteButton.enabled = false;
			Util.debug("this.options.addEventListener(DragEvent.DRAG_DROP");
		}
		
		/**
		 * When moving an item into another list, only allow this to happen if the other list doesn't allready 
		 * contain an item with the same id.
		 */
		private function isDuplicate(item:Object,source:HorizontalList,target:HorizontalList):Boolean{
			//If it's drag,drop within the same component..... 
			if(source === target) {
				return false;
			}
			for each(var obj:Object in (target.dataProvider as ArrayCollection)) {
				if(Util.getPrimaryKey(obj) == Util.getPrimaryKey(item)) {
					return true;
				}
			} 
			return false;
		}
		
		public override function render():void{
			
			direction = BoxDirection.VERTICAL;
			this.height = 500;
			this.verticalScrollPolicy = ScrollPolicy.OFF;
			this.horizontalScrollPolicy = ScrollPolicy.OFF;
			this.styleName = "imageGallery"; 
			//Add a resize to 100% event listener first....
			this.parent.addEventListener(Event.RESIZE,function():void{
				if(parent != null){
					width = parent.width -1 ;
				//	height = parent.height -1;
				} 
			});
			
			var label:Label = new Label();
			label.text = row.name;
			this.addChild(label);
			
			var buttonGroup:UIComponent  = createButtonGroup();
			addChild(buttonGroup);
			
			var dividedBox:HDividedBox = new HDividedBox();
			addChild(dividedBox);
			
			dividedBox.percentHeight = 80;
			dividedBox.percentWidth = 95;
			
			addEventListener(Event.RESIZE,function():void{
					dividedBox.width = width ;
			});
			
			children = new LoonHorizontalList(service);
			dividedBox.addChild(children);
			children.dragEnabled = true;
			children.dragMoveEnabled = true;
			children.dropEnabled = true;
			
			children.percentHeight = 99;
			children.percentWidth = 99;
			
			children.columnWidth = 60;
			children.rowHeight = 60;
			
			children.addEventListener(DragEvent.DRAG_COMPLETE,drag_complete_listener);
			children.addEventListener(DragEvent.DRAG_DROP,drag_allow_listener);
			children.addEventListener(DragEvent.DRAG_ENTER,drag_allow_listener);
			children.addEventListener(DragEvent.DRAG_OVER,drag_allow_listener);
			addDoubleClickListener(this.children);
			addSelectionDeselectionListeners(this.children);
			if(Util.isTransient(this.entityParent)){
				this.enabled = false;			
			} 
			else{
				service.loadOneToMany(
					row.relationship.from_clazz.clazz,
					row.relationship.to_clazz.clazz,
					row.relationship.name,
					Util.getPrimaryKey(entityParent),
					["pk","excite","title","caption","text","meta","width","height","imageId"]
					);
			}
 		} 
 		
 		private function getUltimateParent(c:DisplayObjectContainer):DisplayObjectContainer {
 			if(c is LoonHorizontalList) {
 				return c as LoonHorizontalList;
 			}
 			if(c.parent is LoonHorizontalList) {
 				return c.parent as LoonHorizontalList;
 			}
 			else {
 				return getUltimateParent(c.parent as DisplayObjectContainer);
 			}
 		}
		
		private function addSelectionDeselectionListeners(list:LoonHorizontalList):void {
			list.addEventListener(ListEvent.CHANGE,function (o:ListEvent):void {
				Util.debug("options selection changed");
				if((o.target as LoonHorizontalList).selectedItem != null) {
					editButton.enabled = true; 
 					deleteButton.enabled = true;
 				}
				else{
					editButton.enabled = false; 
 					deleteButton.enabled = false;
				}
			});
		}
		
		private function createButtonGroup():UIComponent {
			var vbox:HBox = new HBox();
			editButton = new Button();
			editButton.label = "Edit Image Properties";
			editButton.addEventListener(MouseEvent.CLICK,
			function ():void {
				var selected:Object = (children as LoonHorizontalList).selectedItem;
				dispatchEvent(new EditImageEvent(ImageLink(selected))
				);
			});
			editButton.enabled = false;
			vbox.addChild(editButton);
			//-----------------
			uploadButton = new Button();
			uploadButton.label = "Upload Image";
			uploadButton.addEventListener(MouseEvent.CLICK,
			function ():void {
				dispatchEvent(new UploadImageEvent(relationship_name,entityParent));
			});
			vbox.addChild(uploadButton);
			//-----------						
			deleteButton = new Button();
			deleteButton.label = "Delete Image";
			deleteButton.addEventListener(MouseEvent.CLICK,
			function ():void {
				var selected:ImageLink = ImageLink((children as LoonHorizontalList).selectedItem);
				dispatchEvent(new RemoveImageEvent(selected));
			});
			deleteButton.enabled = false;
			vbox.addChild(deleteButton);
			return vbox;
		}
	}
}