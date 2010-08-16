package uk.mafu.flex.builders.display
{
	import flash.display.DisplayObject;
	import flash.errors.IllegalOperationError;
	
	import mx.containers.Accordion;
	import mx.containers.Box;
	import mx.containers.BoxDirection;
	import mx.containers.VBox;
	import mx.controls.Spacer;
	import mx.core.Container;
	import mx.core.ScrollPolicy;
	import mx.core.UIComponent;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.flex.builders.display.custom.MultiRowTabNavigator;
	import uk.mafu.flex.meta.ClassMeta;
	import uk.mafu.flex.meta.ROW;
	import uk.mafu.flex.meta.RelationshipMeta;
	import uk.mafu.flex.meta.TAB;
	import uk.mafu.flex.util.ReflectionUtil;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IConfiguration;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.domain.data.AudioLink;
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.PdfLink;
	import uk.mafu.loon.domain.data.VideoLink;
	import uk.mafu.loon.events.CreateOneToOneRelationshipEvent;
	import uk.mafu.loon.events.DiscardEntityEvent;
	import uk.mafu.loon.events.EditManyToManyChildEvent;
	import uk.mafu.loon.events.EditManyToManyEvent;
	import uk.mafu.loon.events.EditOneToManyChildEvent;
	import uk.mafu.loon.events.EditOneToManyEvent;
	import uk.mafu.loon.events.EditOneToManyImagesEvent;
	import uk.mafu.loon.events.EditOneToOneRelationshipChildEvent;
	import uk.mafu.loon.events.EditOneToOneRelationshipEvent;
	import uk.mafu.loon.events.EntityModifiedEvent;
	import uk.mafu.loon.events.EntitySavedEvent;
	import uk.mafu.loon.events.SaveEntityEvent;
	import uk.mafu.loon.events.SaveOneToOneRelationshipEvent;
	import uk.mafu.loon.events.image.DeleteSingleImageEvent;
	import uk.mafu.loon.events.image.EditSingleImageEvent;

	public class EntityPage extends EntityContainer
	{
		
		private var saveCancelGroup:SaveCancelGroup;
		
		public function EntityPage(service:IService,configuration:IConfiguration,entity:Object, 
			entityParent:Object=null,selfParentRelationship:String=null) {
			super(service,Util.getClass(entity),Util.getClass(entityParent));
			this.data = entity;
			this.entityParent = entityParent; 
			addEventListeners();
		}
		
		public function __handle__CreateOneToOneRelationshipEvent(e:CreateOneToOneRelationshipEvent):void{
			Util.debug("__handle__CreateOneToOneRelationshipEvent");
			var accordion:Accordion = this.findAccordian();
			var childEntityPage:EntityPage = new EntityPage(this.service,this.configuration,new e.clazz,e.parent,e.relationship);
			accordion.addChild(childEntityPage);
			accordion.selectedChild = childEntityPage;
			///childEntityPage.addEventListener(EntitySavedEvent.NAME,reload);
			childEntityPage.render();
		}
		
		public function __handle__EditOneToOneRelationshipChildEvent(e:EditOneToOneRelationshipChildEvent):void{
			Util.debug("__handle__EditOneToOneRelationshipEvent");
			var accordion:Accordion = this.findAccordian();
			var childEntityPage:EntityPage = new EntityPage(this.service,this.configuration,e.child,e.parent,e.relationship);
			accordion.addChild(childEntityPage);
			//childEntityPage.addEventListener(EntitySavedEvent.NAME,reload);
			accordion.selectedChild = childEntityPage;
			//childEntityPage.addEventListener(EntitySavedEvent.NAME,reload);
			childEntityPage.render();
		}
		
		public function __handle__EditOneToManyChildEvent(e:EditOneToManyChildEvent):void{
			e.stopPropagation();
//			trace("__handle__EditOneToManyChildEvent");
//			var accordion:Accordion = this.findAccordian();
//			var childEntityPage:EntityPage = new EntityPage(this.service,this.configuration,e.child,e.parent,e.relationship);
//			accordion.addChild(childEntityPage);
//			accordion.selectedChild = childEntityPage;
//			childEntityPage.render();
			this.service.load(e.child_clazz, Util.getPrimaryKey(e.child) );		
		}
		
		
		public function __handle__EditManyToManyChildEvent(e:EditManyToManyChildEvent):void{
			e.stopPropagation();
//			trace("__handle__EditOneToManyChildEvent");
//			var accordion:Accordion = this.findAccordian();
//			var childEntityPage:EntityPage = new EntityPage(this.service,this.configuration,e.child,e.parent,e.relationship);
//			accordion.addChild(childEntityPage);
//			accordion.selectedChild = childEntityPage;
//			childEntityPage.render();
			this.service.load(e.child_clazz, Util.getPrimaryKey(e.child) );		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		private function __handle__SaveOneToOneRelationshipEvent(evt:SaveOneToOneRelationshipEvent):void{
			if(evt.parentEntity == this.data) {
				addToExecutionQueue(evt);
			}
		}
		
		protected function __handle__DiscardEntityEvent(evt:DiscardEntityEvent):void{
			Util.debug("received a discard entity event");
			if(Util.isSameEntity(evt.entity,data)){
				Util.debug("executing the save entity event");
				//Get all the children, if the child is an EntityContainer ( top level object or relationship ),
				//then call it's handle executions method.
				for each (var o:Object in this.getChildren()){
					//If it's tabs, go into it..... nuff said...
					if(o is MultiRowTabNavigator) {
							for each (var tabChild:Object in (o as MultiRowTabNavigator).getChildren()){
								if(tabChild is Box) {
									for each (var boxChild:Object in tabChild.getChildren()){
										if(boxChild is EntityContainer) {
											Util.debug("tabChild" + tabChild);
											(boxChild as EntityContainer).destroy();
											Util.makeNull(boxChild);			
										}
								}
							}
						}
					}
				}
				removeAllChildren();
				clearExecutionQueue();
				removeEventListeners();
				findAccordian().removeChild(this);
				Util.makeNull(this);
			}
		}
		
		override protected function reload(evt:EntitySavedEvent):void {
			if((Util.isSameEntity(evt.entity,this.data))){
				if(parent != null) {
					this.data = evt.entity;
					this.render();
				}
				//Assert.notNull(parent,"Something is wrong, asked an EntityPage" + 
				//" not attached to the Accordion to reload itself, need better cleanup!!!");
			} 
		}	
		
		protected function __handle__SaveEntityEvent(evt:SaveEntityEvent):void{
			Util.debug("received a save entity event");
			if(Util.isSameEntity(evt.entity,data)){
				Util.debug("handling the save entity event");
				//Get all the children, if the child is an EntityContainer ( top level object or relationship ),
				//then call it's handle executions method.
				for each (var o:Object in this.getChildren()){
					//If it's tabs, go into it..... nuff said...
					if(o is MultiRowTabNavigator) {
							for each (var tabChild:Object in (o as MultiRowTabNavigator).getChildren()){
								//TODO:Add code to handle if this is not a tabbed pannel
								if(tabChild is Box) {
									for each (var boxChild:Object in tabChild.getChildren()){
										if(boxChild is EntityContainer) {
											Util.debug("tabChild" + tabChild);
											(boxChild as EntityContainer).handleExecutions();
											(boxChild as EntityContainer).destroy();
											Util.makeNull(boxChild);			
										}
								}
							}
						}
					}
				}
				handleExecutions();
				destroy();
				if(this.parent != null) {
					(this.parent as Accordion).removeChild(this); 
				}
				delete this;
//				this.parent.
//				findAccordian().removeChild(this);
				//Util.makeNull(this);
			}
		}
		
		protected override function removeEventListeners():void{
			Swiz.removeEventListener(CreateOneToOneRelationshipEvent.NAME,__handle__CreateOneToOneRelationshipEvent);
			Swiz.removeEventListener(EditOneToOneRelationshipChildEvent.NAME,__handle__EditOneToOneRelationshipChildEvent);
			Swiz.removeEventListener(DiscardEntityEvent.NAME,__handle__DiscardEntityEvent);
			Swiz.removeEventListener(SaveEntityEvent.NAME,__handle__SaveEntityEvent);
			Swiz.removeEventListener(EntityModifiedEvent.NAME,__handle__EntityModifiedEvent);
			
			Swiz.removeEventListener(EditOneToManyChildEvent.NAME,__handle__EditOneToManyChildEvent);
			Swiz.removeEventListener(EditOneToManyEvent.NAME,__handle__EditOneToManyEvent);
			
			Swiz.removeEventListener(EditManyToManyChildEvent.NAME,__handle__EditManyToManyChildEvent);
			Swiz.removeEventListener(EditManyToManyEvent.NAME,__handle__EditManyToManyEvent);
			
			Swiz.removeEventListener(EditOneToManyImagesEvent.NAME,__handle__EditOneToManyImagesEvent);
			Swiz.removeEventListener(EditOneToOneRelationshipEvent.NAME,__handle__EditOneToOneRelationshipEvent);
			Swiz.removeEventListener(EditSingleImageEvent.NAME,__handle__EditSingleImageEvent);
			Swiz.removeEventListener(DeleteSingleImageEvent.NAME,__handle__DeleteSingleImageEvent);
			
		}
	
		protected override function removeWidgets():void {
			removeWidgetsRecursive(this);		
		}
		
		private function removeWidgetsRecursive(comp:DisplayObject):void {
			if(comp is Container) {
				for each(var o:DisplayObject in (comp as Container).getChildren()) {
					if(o is EntityContainer ) {
						(o as EntityContainer).destroy();
						(comp as Container).removeChild(o as DisplayObject);
						o = null;
					}
					else {
						removeWidgetsRecursive(o);
					}
					//After recursion it should un-ravel through this final cleanup solution.. watch out for null pointers!!
					removeAllChildren();
				}
			}
		}
	
		protected function addEventListeners():void{
			Swiz.addEventListener(CreateOneToOneRelationshipEvent.NAME,__handle__CreateOneToOneRelationshipEvent,false,0,true);
			Swiz.addEventListener(EditOneToOneRelationshipChildEvent.NAME,__handle__EditOneToOneRelationshipChildEvent,false,0,true);
			Swiz.addEventListener(DiscardEntityEvent.NAME,__handle__DiscardEntityEvent,false,0,true);
			Swiz.addEventListener(SaveEntityEvent.NAME,__handle__SaveEntityEvent,false,0,true);
			Swiz.addEventListener(EntityModifiedEvent.NAME,__handle__EntityModifiedEvent,false,0,true);
			
			Swiz.addEventListener(EditOneToManyChildEvent.NAME,__handle__EditOneToManyChildEvent,false,0,true);
			Swiz.addEventListener(EditOneToManyEvent.NAME,__handle__EditOneToManyEvent,false,0,true);
			
			Swiz.addEventListener(EditManyToManyChildEvent.NAME,__handle__EditManyToManyChildEvent,false,0,true);
			Swiz.addEventListener(EditManyToManyEvent.NAME,__handle__EditManyToManyEvent,false,0,true);
			
			Swiz.addEventListener(EditOneToManyImagesEvent.NAME,__handle__EditOneToManyImagesEvent,false,0,true);
			Swiz.addEventListener(EditOneToOneRelationshipEvent.NAME,__handle__EditOneToOneRelationshipEvent,false,0,true);
			Swiz.addEventListener(EditSingleImageEvent.NAME,__handle__EditSingleImageEvent,false,0,true);
			Swiz.addEventListener(DeleteSingleImageEvent.NAME,__handle__DeleteSingleImageEvent,false,0,true);
		}
		
		
		private function __handle__DeleteSingleImageEvent(evt:DeleteSingleImageEvent):void {
			if(evt.entityParent === data){
				saveCancelGroup.handleEntityChanged();
			}
		}
		
		private function __handle__EditSingleImageEvent(evt:EditSingleImageEvent):void{
			if(evt.parent === data) {
				saveCancelGroup.handleEntityChanged();
			}
		}
		
		private function __handle__EditOneToOneRelationshipEvent(evt:EditOneToOneRelationshipEvent):void{
			var castable:Boolean = (data as evt.parent != null);
			if(castable &&(evt.parentId == Util.getPrimaryKey(data))) {
				saveCancelGroup.handleEntityChanged();
			}
		}
		
		private function __handle__EditOneToManyEvent(evt:EditOneToManyEvent):void{
			var castable:Boolean = (data as evt.parent != null);
			if(castable &&(evt.parentId == Util.getPrimaryKey(data))) {
				saveCancelGroup.handleEntityChanged();
			}
		}
		
		private function __handle__EditManyToManyEvent(evt:EditManyToManyEvent):void{
			var castable:Boolean = (data as evt.parent != null);
			if(castable &&(evt.parentId == Util.getPrimaryKey(data))) {
				saveCancelGroup.handleEntityChanged();
			}
		}
		
		private function __handle__EditOneToManyImagesEvent(evt:EditOneToManyImagesEvent):void{
			var castable:Boolean = (data as evt.parent != null);
			if(castable && (evt.parentId == Util.getPrimaryKey(data))) {
				saveCancelGroup.handleEntityChanged();
			}
		}
		
		private function __handle__EntityModifiedEvent(evt:EntityModifiedEvent):void{
			if(Util.isSameEntity( evt.entity,data)) {	
			 	Util.debug("executing the modify entity event");
				saveCancelGroup.handleEntityChanged();
				addToExecutionQueue(evt);	
			}
		}
		
		public function getThis():UIComponent {
			return this;
		}
		
		public override function render():void{
			direction  = BoxDirection.VERTICAL;
			//Set the header on the accoridon....
			setHeader();
			var classMeta:ClassMeta = ReflectionUtil.extractClassMeta(this.clazz);
			//Clear the page of all child components
			removeAllChildren();
			this.percentWidth = 95;
			this.percentHeight = 95;
			this.setStyle("bgcolor","red");
			
			this.verticalScrollPolicy = ScrollPolicy.AUTO;
			this.horizontalScrollPolicy = ScrollPolicy.AUTO;
//			this.parent.addEventListener(Event.RESIZE,function():void{
//				if(getThis().parent != null){
//					width = getThis().parent.width -1;
//					height = getThis().parent.height -1;
//				} 
//			});
			
			if(classMeta.displayMeta.isTabbed()){
				buildWithTabs();
			}
			else{
				buildWithoutTabs();
			}
		}
 
 		private function buildWithTabs():void{
 			
 			var totalHeight:Number = 0;
			var clazz:Class = Util.getClass(this.data);
			var def:ClassMeta = ReflectionUtil.extractClassMeta(clazz);
			saveCancelGroup  = new SaveCancelGroup(this.data);
			addChild(saveCancelGroup);
					
			var tabNavigator:MultiRowTabNavigator = new MultiRowTabNavigator();
			this.addChild(tabNavigator);
			tabNavigator.percentHeight = 100;
			tabNavigator.percentWidth = 100;
			tabNavigator.resizeToContent = true;
			tabNavigator.verticalScrollPolicy = ScrollPolicy.OFF;
			tabNavigator.horizontalScrollPolicy = ScrollPolicy.OFF;
			
//			tabNavigator.parent.addEventListener(Event.RESIZE,function():void{
//					tabNavigator.width = tabNavigator.parent.width;
//					tabNavigator.height = tabNavigator.parent.height;
//			});
			
			for each(var tab:TAB in def.displayMeta.tabs){
				var vbox:VBox = new VBox();
				vbox.verticalScrollPolicy = ScrollPolicy.OFF;
				vbox.horizontalScrollPolicy = ScrollPolicy.OFF;
				vbox.clipContent = false;
				vbox.label = tab.title.toUpperCase();
				tabNavigator.addChild(vbox);
				for each (var n:String in tab.fieldNames){
					for each (var r:ROW in def.displayMeta.rows) {
						if(n === r.name){
							createRow(r,vbox);
							var s:Spacer = new Spacer();
							s.percentWidth = 100;
							s.height = 5;
							vbox.addChild(s);
						}
					}
				}
				var ender:Spacer = new Spacer();
				ender.percentWidth = 100;
				ender.height = 5;
				vbox.addChild(s);
			}
		}
		
		private function createRow(metaRow:ROW,parentContainer:UIComponent):void{
			switch (metaRow.type){
				case String:
					if(metaRow.widgetDef == null  || metaRow.widgetDef.clazz == null){
						new TextGroup(data,metaRow,parentContainer);
					}
					else if(metaRow.widgetDef.clazz == RichTextWidget) {
						new RichTextWidget(data,metaRow,parentContainer);
					}
					else if(metaRow.widgetDef.clazz == EnumWidget) {
						new EnumWidget(data,metaRow,parentContainer);
					}
					else {
						throw new IllegalOperationError("unsupported state");
					}
				break;
				
				case Number:
					new NumericInput(data,metaRow,parentContainer);
				break;
				
				case Boolean:
					new BooleanInput(data,metaRow,parentContainer);
				break;
				
				case Date:
					new DateInput(data,metaRow,parentContainer);
				break;
				
				case Array:
					if(metaRow.widgetDef == null  || metaRow.widgetDef.clazz == null){
						if(metaRow.relationship.type == RelationshipMeta.ONE_TO_MANY){
							new One2ManyGroup(service,data,metaRow,parentContainer);
						}
						else if (metaRow.relationship.type == RelationshipMeta.MANY_TO_MANY){
							new Many2ManyGroup(service,data,metaRow,parentContainer);
						}
						else {
							throw new IllegalOperationError("unknown meta type");
						}
					}
					else if(metaRow.widgetDef.clazz == ImageGallery) {
						new ImageGallery(service,data,metaRow,parentContainer);
					}
//					else if(metaRow.widgetDef.clazz == VideoGallery) {
//						new VideoGallery(service,data,metaRow,parentContainer);
//					}
//					else if(metaRow.widgetDef.clazz == PdfGallery) {
//						new PdfGallery(service,data,metaRow,parentContainer);
//					}
					else {
						throw new IllegalOperationError("unknown widget type");
					}
				break;
				
				case ImageLink:
				 	new SingleImage(service,data,metaRow,parentContainer);
					Util.debug("handle a:" + Util.getClassnameByClass(ImageLink));
				break;
				
				case PdfLink:
					new SinglePdf(service,data,metaRow,parentContainer);
					Util.debug("handle a:" + Util.getClassnameByClass(PdfLink));
				break;
				
				case VideoLink:
				 	new SingleVideo(service,data,metaRow,parentContainer);
					Util.debug("handle a:" + Util.getClassnameByClass(VideoLink));
				break;
				
				case AudioLink:
				 	new SingleAudio(service,data,metaRow,parentContainer);
					Util.debug("handle a:" + Util.getClassnameByClass(AudioLink));
				break;
				
				default:
					if(metaRow.relationship.type == RelationshipMeta.ONE_TO_ONE){
						new One2OneGroup(service,data,metaRow,parentContainer);
						break;
					}
					else {
						throw new IllegalOperationError();
					}
				break;
			}
		}
		
		private function buildWithoutTabs():void{
			throw new IllegalOperationError("Method has not been implemented for building non-tabbed screen");
		}
 
		private function buildImageLinkInput(r:ROW):UIComponent{
			var t:UIComponent = new r.widgetDef.clazz;
			return t;
		}
	}
}