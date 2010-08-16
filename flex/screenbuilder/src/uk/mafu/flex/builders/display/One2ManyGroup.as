package uk.mafu.flex.builders.display
{
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.containers.Box;
	import mx.containers.BoxDirection;
	import mx.containers.VBox;
	import mx.controls.Button;
	import mx.controls.Label;
	import mx.controls.List;
	import mx.core.UIComponent;
	import mx.events.DragEvent;
	import mx.events.ListEvent;
	import mx.managers.DragManager;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.flex.meta.ClassMeta;
	import uk.mafu.flex.meta.ROW;
	import uk.mafu.flex.util.ReflectionUtil;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.events.CreateOneToOneRelationshipEvent;
	import uk.mafu.loon.events.EditOneToManyChildEvent;
	import uk.mafu.loon.events.EditOneToManyEvent;
	import uk.mafu.loon.events.EntitySavedEvent;
	import uk.mafu.loon.events.OneToManyLoadedEvent;

	public class One2ManyGroup extends EntityContainer
	{
		private var row:ROW;
		private var to_clazz_meta:ClassMeta;
		private var labelCol:String ;
		private var relationship_name:String ;
		//Components
		private var children:List;
		private var options:List ;
		
		override protected function reload(evt:EntitySavedEvent):void {
			if(this.to_clazz_meta.clazz == Util.getClass(evt.entity)){
				service.loadOneToMany(
					row.relationship.from_clazz.clazz,
					row.relationship.to_clazz.clazz,
					row.relationship.name,
					Util.getPrimaryKey(entityParent),
					["pk",
					ReflectionUtil.extractClassMeta(
					row.relationship.to_clazz.clazz).displayMeta.chooserSettings.labelColumn
					]
					);
			}
		}
		
		private function createMutuallyExclusiveSelectionBehavior(list1:List,list2:List):void{
				list1.addEventListener(ListEvent.CHANGE,function(evt:ListEvent):void {
					list2.selectedIndex = -1;
				});
				list2.addEventListener(ListEvent.CHANGE,function(evt:ListEvent):void {
					list1.selectedIndex = -1;
				});
		}
		
		private function addDoubleClickListener(list:List):void{
			list.addEventListener(ListEvent.ITEM_DOUBLE_CLICK,function (e:ListEvent):void {
				Util.debug("double clicked on item" + Util.getPrimaryKey((e.target as List).selectedItem));
				var listChild:Object = (e.currentTarget as List).selectedItem;
				Swiz.dispatchEvent(new EditOneToManyChildEvent(
						listChild ,relationship_name,entityParent)
				);
			});
		}
		
		public function One2ManyGroup(service:IService,entityParent:Object,row:ROW,parentContainer:UIComponent){
			super(service,row.relationship.to_clazz.clazz,row.relationship.from_clazz.clazz);
			this.entityParent = entityParent;
			this.row = row;
			parentContainer.addChild(this);
			this.to_clazz_meta   = ReflectionUtil.extractClassMeta(row.relationship.to_clazz.clazz);
			this.labelCol  = to_clazz_meta.displayMeta.chooserSettings.labelColumn;
			this.relationship_name  = row.relationship.name;
			Swiz.addEventListener(OneToManyLoadedEvent.NAME,__handle__OneToManyLoadedEvent,false,0,true);
			Swiz.addEventListener(EditOneToManyEvent.NAME,__handle__EditOneToManyEvent,false,0,true);
			render();
			if(Util.isTransient(this.entityParent)) {
				this.children.enabled = false;
			}
			else {
			service.loadOneToMany(
				row.relationship.from_clazz.clazz,
				row.relationship.to_clazz.clazz,
				row.relationship.name,
				Util.getPrimaryKey(entityParent),
					["pk",
					ReflectionUtil.extractClassMeta(row.relationship.to_clazz.clazz)
					.displayMeta
					.chooserSettings
					.labelColumn
					]
				);
			}
			
		}
		
		public function __handle__EditOneToManyEvent(e:EditOneToManyEvent):void{
			if(e.parentId == Util.getPrimaryKey(entityParent) 
			&& e.relationship == relationship_name) {
				addToExecutionQueue(e);
			}
		}
		
		public function __handle__EntitySavedEvent(e:EntitySavedEvent):void{
			if(Util.getClass(e.entity) == this.clazz) {
				this.reload(e);
			}
		}
		
		override protected function removeEventListeners():void{
			Swiz.removeEventListener(OneToManyLoadedEvent.NAME,__handle__OneToManyLoadedEvent);
			Swiz.removeEventListener(EditOneToManyEvent.NAME,__handle__EditOneToManyEvent);
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
					options.dataProvider= prepareOptionsList(e.result.options);
				}
			}
			else{
					Util.debug("no match,not going to handle the event.");
			}
		}
		
		private function prepareOptionsList(options:Array):Array {
			//Add one,empty,null id'd, instance of the class to the collection.......
			var copy:ArrayCollection  = new ArrayCollection(options);
			//copy.addItem(new this.clazz);
			//Recursive relationships are a BIG no no .... we can't permit that to happen ....
			for each(var o:Object in copy){
				//If this relationship contains objects of the same class as the parentEntity
				if(entityParent is Util.getClass(o)) { 
					if(Util.getPrimaryKey(o) == Util.getPrimaryKey(entityParent)){
						copy.removeItemAt(copy.getItemIndex(o));	
					}
				}
			}
			
			var sort:Sort = new Sort();
    		// There is only one sort field, so use a <code>null</code> first parameter.
     		sort.fields = [new SortField(labelCol ,true)];
     		copy.sort = sort;
      		copy.refresh();
 			return copy.toArray();
		}
		
		private function label_function (obj:Object):String{
			var ret:String = obj[labelCol];
			if(ret == null){
				return ret;
			}
			var limit:Number = 20;
			var trailing:Number = 3;
			if(ret.length > limit) {
				ret = ret.substr(0,limit - trailing);
				for(var i:Number = 0;i< trailing;i++){
					ret = ret.concat(new String("."));
				}
			}
			return ret;	
		}
		
		private function drag_complete_listener(e:DragEvent):void {
			var dragSource:List = e.dragInitiator as List;
			var selectedItem:Object= dragSource.selectedItem;
			if( (e.target === children) || (e.target === options) )
			{
				Swiz.dispatchEvent(new EditOneToManyEvent( 
						children.dataProvider as ArrayCollection,
						relationship_name,
						parent_clazz,			
						Util.getPrimaryKey(entityParent)
					));  
			}
			Util.debug("	this.children.addEventListener(DragEvent.DRAG_COMPLETE");
		}

		private function drag_allow_listener(e:DragEvent):void {
			e.action = DragManager.MOVE;
			//Make sure that the drag drop operation is between related, allowed lists, i.e don't drag Dog onto Vehicles list... 
			if(((e.dragInitiator !== children) && (e.dragInitiator !== options))) {
				e.stopImmediatePropagation();
				e.stopPropagation();
			}
			if(isDuplicate(
					(e.dragInitiator as List).selectedItem,
					e.dragInitiator as List,
					e.target as List))	{
							
				e.stopImmediatePropagation();
				e.stopPropagation();
				return;
			}
			Util.debug("this.options.addEventListener(DragEvent.DRAG_DROP");
		}
		
		/**
		 * When moving an item into another list, only allow this to happen if the other list doesn't allready 
		 * contain an item with the same id.
		 */
		private function isDuplicate(item:Object,source:List,target:List):Boolean{
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
			super.direction = BoxDirection.HORIZONTAL;
			this.height = 250;
			var label:Label = new Label();
			label.text = row.name;
			this.addChild(label);
			
			var childrenBox:VBox = new VBox();
			this.addChild(childrenBox);
			var attachedLabel:Label = new Label();
			attachedLabel.text = "Attached Items";
			childrenBox.addChild(attachedLabel);
			
			this.children = new List();
			childrenBox.addChild(children);
			this.children.dragEnabled = true;
			this.children.dragMoveEnabled = true;
			this.children.dropEnabled = true;
			
			this.children.height = 180;
			this.children.width = 160;
			this.children.labelFunction = label_function;
			this.children.addEventListener(DragEvent.DRAG_COMPLETE,drag_complete_listener);
			this.children.addEventListener(DragEvent.DRAG_DROP,drag_allow_listener);
			this.children.addEventListener(DragEvent.DRAG_ENTER,drag_allow_listener);
			this.children.addEventListener(DragEvent.DRAG_OVER,drag_allow_listener);
			//
			var optionsBox:VBox = new VBox();	
			this.addChild(optionsBox);
			//
			var label2:Label = new Label();
			label2.text = "Available Items";
			optionsBox.addChild(label2);
			//
			this.options = new List();
			optionsBox.addChild(options);
			
			this.options.dragEnabled = true;
			this.options.dragMoveEnabled = true;
			this.options.dropEnabled = true;
			this.options.height = 180;
			this.options.width = 160;
			this.options.labelFunction = label_function;
			
			this.options.addEventListener(DragEvent.DRAG_COMPLETE,drag_complete_listener);
			this.options.addEventListener(DragEvent.DRAG_DROP,drag_allow_listener);
			this.options.addEventListener(DragEvent.DRAG_ENTER,drag_allow_listener);
			this.options.addEventListener(DragEvent.DRAG_OVER,drag_allow_listener);
			this.options.addEventListener(DragEvent.DRAG_START,drag_allow_listener);
			
			/**
			 * Add the event listeners
			 */
			addDoubleClickListener(this.options);
			addDoubleClickListener(this.children);
			createMutuallyExclusiveSelectionBehavior(options,children);
			
		} 
		
		private function createButtonGroup():Box {
			var vbox:VBox = new VBox();
			var addNewButton:Button = new Button();
			addNewButton.label = "Create new";
			addNewButton.addEventListener(MouseEvent.CLICK, 
			function ():void {
				Swiz.dispatchEvent(
						new CreateOneToOneRelationshipEvent(row.relationship.to_clazz.clazz,
							row.relationship.name,
							entityParent));
			});
			vbox.addChild(addNewButton);
			var editButton:Button = new Button();
			editButton.label = "Edit Selected";
			editButton.addEventListener(MouseEvent.CLICK,
			function ():void {
				var selectedChild:Object = (children as List).selectedItem;
				var selectedOption:Object = (options as List).selectedItem;
				var selected:Object; 
				if(selectedChild == null) {
					selected = selectedOption;
				}
				else {
					selected = selectedChild;
				}
				Swiz.dispatchEvent(new EditOneToManyChildEvent(
						selected ,relationship_name,entityParent)
				);
			});
			vbox.addChild(editButton);
			return vbox;
		}
	}
}