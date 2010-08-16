package uk.mafu.flex.builders.display
{
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.collections.IViewCursor;
	import mx.containers.Box;
	import mx.containers.BoxDirection;
	import mx.containers.HBox;
	import mx.controls.Button;
	import mx.controls.ComboBox;
	import mx.controls.Label;
	import mx.core.UIComponent;
	import mx.events.ListEvent;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.flex.meta.ClassMeta;
	import uk.mafu.flex.meta.ROW;
	import uk.mafu.flex.util.ReflectionUtil;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.events.CreateOneToOneRelationshipEvent;
	import uk.mafu.loon.events.EditOneToOneRelationshipChildEvent;
	import uk.mafu.loon.events.EditOneToOneRelationshipEvent;
	import uk.mafu.loon.events.EntitySavedEvent;
	import uk.mafu.loon.events.OneToOneRelationshipLoadedEvent;

	public class One2OneGroup extends EntityContainer
	{
		private var parentEntity:Object;
		private var row:ROW;
		private var dropdown:ComboBox;
		private var to_clazz_meta:ClassMeta ;
		private var labelCol:String ;
		private var relationship_name:String ;
		
		public function One2OneGroup(service:IService,parentEntity:Object,row:ROW,parentContainer:UIComponent){
			super(service,row.relationship.to_clazz.clazz,row.relationship.from_clazz.clazz);
			this.parentEntity = parentEntity;
			this.row = row;
			this.relationship_name  = row.relationship.name;
			parentContainer.addChild(this);
			Swiz.addEventListener(OneToOneRelationshipLoadedEvent.NAME,__handle__OneToOneRelationshipLoadedEvent,false,0,true);
			Swiz.addEventListener(EntitySavedEvent.NAME,
				__handle__EntitySavedEvent
				,false,0,true);
			Swiz.addEventListener(EditOneToOneRelationshipEvent.NAME,__handle__EditOneToOneRelationshipEvent,false,0,true);
			render();
		}
		
		 
		override protected function removeEventListeners():void{
			Swiz.removeEventListener(OneToOneRelationshipLoadedEvent.NAME,__handle__OneToOneRelationshipLoadedEvent);
			Swiz.removeEventListener(OneToOneRelationshipLoadedEvent.NAME,__handle__EntitySavedEvent);
			Swiz.removeEventListener(EditOneToOneRelationshipEvent.NAME,__handle__EditOneToOneRelationshipEvent);
		}
		
		override protected function removeWidgets():void{
			removeAllChildren();
		}
		
		
		public function __handle__EntitySavedEvent(e:EntitySavedEvent):void{
			if(this.row.relationship.to_clazz.clazz == Util.getClass(e.entity)){
				service.loadOneToOne(
					row.relationship.from_clazz.clazz,
					row.relationship.to_clazz.clazz,
					row.relationship.name,
					Util.getPrimaryKey(parentEntity),
					["pk",
						ReflectionUtil.extractClassMeta(
						row.relationship.to_clazz.clazz)
						.displayMeta.chooserSettings.labelColumn]
					);
			}
		}
		
		protected function __handle__EditOneToOneRelationshipEvent(e:EditOneToOneRelationshipEvent):void {
			if(e.parentId == Util.getPrimaryKey(this.parentEntity) 
			&& e.relationship == this.relationship_name 
			&& Util.getPrimaryKey(e.child) == Util.getPrimaryKey(this.dropdown.data)) {
				this.addToExecutionQueue(e);
			}
		}
		
		public function __handle__OneToOneRelationshipLoadedEvent(e:OneToOneRelationshipLoadedEvent):void{
			if(
				(e.parent_clazz == parent_clazz) 
				&& (e.relationship == relationship_name)
				&& (e.parentPk == Util.getPrimaryKey(parentEntity) ) 
			)
			{
				Util.debug("__handle__OneToOneRelationshipLoadedEvent");
				if(e.result != null) {
					if(e.result.options !=null){
						//Add one,empty,null id'd, instance of the class to the collection.......
						var copy:ArrayCollection  = new ArrayCollection(e.result.options);
						copy.addItem(new this.clazz);
						
						//Recursive relationships are a BIG no no .... we can't permit that to happen ....
						for each(var o:Object in copy){
							//If this relationship contains objects of the same class as the parentEntity
							if(parentEntity is Util.getClass(o)) { 
								if(Util.getPrimaryKey(o) == Util.getPrimaryKey(parentEntity)){
									copy.removeItemAt(copy.getItemIndex(o));	
								}
							}
						}
						

					var copy2:Array  = copy.toArray(); 
					copy2.sort(function (a:Object,b:Object):Number{
						if(a[labelCol] == null) {
							return -1;
						}
						if(b[labelCol] == null) {
							return 1;
						}
						
						else {
							return (a[labelCol] as String).toLocaleLowerCase().localeCompare(
								(b[labelCol] as String).toLocaleLowerCase()
							);
						}
					});
						
						this.dropdown.dataProvider = copy2;
					}
					if(e.result.data !=null){
						this.data = Util.cloneObject(e.result.data);
					}
					//Finally make it select the selected item.
					setSelected();	
				}
			}
		}
				
		public override function render():void{
			super.direction = BoxDirection.HORIZONTAL;
			this.to_clazz_meta   = ReflectionUtil.extractClassMeta(row.relationship.to_clazz.clazz);
			this.labelCol  = to_clazz_meta.displayMeta.chooserSettings.labelColumn;
			this.relationship_name  = row.relationship.name;
			this.dropdown = new ComboBox();
			this.dropdown.data = this.data;
			dropdown.addEventListener(ListEvent.CHANGE,
				function (fe:ListEvent):void {
					var child:Object = (fe.target as ComboBox).selectedItem;
					dropdown.data = (fe.target as ComboBox).selectedItem;
					Swiz.dispatchEvent(new EditOneToOneRelationshipEvent( 
						dropdown.data,
						relationship_name,
						parent_clazz,			
						Util.getPrimaryKey(parentEntity)
					)); 
			});
			
			this.dropdown.labelField = null;
			this.dropdown.labelFunction = function (obj:Object):String{
				return obj[labelCol];
			};
			
			this.addChild(dropdown);
			
			var box:HBox = new HBox();
			this.addChild(box);
			var label:Label = new Label();
			label.text = row.label;
			box.addChild(label);
			box.addChild(dropdown);
			if(	Util.isTransient(this.parentEntity)) {
				dropdown.enabled = false;
			}
			else{
				service.loadOneToOne(
					row.relationship.from_clazz.clazz,
					row.relationship.to_clazz.clazz,
					row.relationship.name,
					Util.getPrimaryKey(parentEntity),
					["pk",
						ReflectionUtil.extractClassMeta(row.relationship.to_clazz.clazz).displayMeta.chooserSettings.labelColumn]
				);
			}
			
			
		} 
		
		
		private function getItemIndex(o:Object):Number {
			var ret:Number = (dropdown.dataProvider as ArrayCollection).getItemIndex(o);
			return ret;
		}
		
		private function setSelected():void {
			var cursor:IViewCursor = (dropdown.dataProvider as ArrayCollection).createCursor();	
			while (!cursor.afterLast){
				if(this.data !=null && cursor.current.pk == data.pk) {
					dropdown.selectedIndex = getItemIndex(cursor.current);
					return;
				}
				//Set the empty option as selected.
				if(this.data ==null && Util.isTransient(cursor.current)) {
					dropdown.selectedIndex = getItemIndex(cursor.current);;
					return;
				}
				cursor.moveNext();
			}
			//If nothing is selected, set selected index to -1( try to get an empty cell).
			dropdown.selectedIndex = -1;
			Util.debug("finished setting the selected item");
		}
		
		
		
		private function createButtonGroup():Box {
			var vbox:HBox = new HBox();
			var addNewButton:Button = new Button();
			addNewButton.label = "Create new";
			addNewButton.addEventListener(MouseEvent.CLICK, 
			function ():void {
				Swiz.dispatchEvent(new CreateOneToOneRelationshipEvent(row.relationship.to_clazz.clazz,row.relationship.name,parentEntity));
			});
			vbox.addChild(addNewButton);
			var editButton:Button = new Button();
			editButton.label = "Edit";
			editButton.addEventListener(MouseEvent.CLICK,
			function ():void {
				Swiz.dispatchEvent(new EditOneToOneRelationshipChildEvent(
				dropdown.selectedItem,
				row.relationship.name,
				Util.getClass(parentEntity),
				Util.getPrimaryKey(parentEntity)));
			});
			vbox.addChild(editButton);
			if(	Util.isTransient(this.parentEntity)) {
				addNewButton.enabled = false;
				editButton.enabled = false;
			}

			return vbox;
		}
		
		override protected function reload(evt:EntitySavedEvent):void {
			if(Util.getClass(evt.entity) == Util.getClass(this.clazz)){
				service.loadOneToOne(
					row.relationship.from_clazz.clazz,
					row.relationship.to_clazz.clazz,
					row.relationship.name,
					Util.getPrimaryKey(parentEntity),
					["pk",
						ReflectionUtil.extractClassMeta(row.relationship.to_clazz.clazz).displayMeta.chooserSettings.labelColumn]
				);
			}
		}	
	}
}