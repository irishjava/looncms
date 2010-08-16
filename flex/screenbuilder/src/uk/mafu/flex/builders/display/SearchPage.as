package uk.mafu.flex.builders.display
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.containers.Accordion;
	import mx.containers.BoxDirection;
	import mx.containers.HBox;
	import mx.controls.Button;
	import mx.core.UIComponent;
	import mx.events.ListEvent;
	import mx.events.ResizeEvent;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.flex.builders.display.custom.SearchableDatagrid;
	import uk.mafu.flex.meta.ClassMeta;
	import uk.mafu.flex.util.Assert;
	import uk.mafu.flex.util.LoonStringUtil;
	import uk.mafu.flex.util.ReflectionUtil;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IConfiguration;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.events.CreateEntityEvent;
	import uk.mafu.loon.events.DeleteEntityEvent;
	import uk.mafu.loon.events.EntitiesLoadedEvent;
	import uk.mafu.loon.events.EntityRemovedEvent;
	import uk.mafu.loon.events.EntitySavedEvent;
	import uk.mafu.loon.events.LoadEntityEvent;
	
	public class SearchPage extends EntityContainer
	{
		private var dg:SearchableDatagrid;
		private var h:HBox;
		private var del:Button;
		private var add:Button;
		
		protected override function removeEventListeners():void {
			Swiz.removeEventListener(EntitiesLoadedEvent.NAME,__handle__EntitiesLoadedEvent);
			Swiz.removeEventListener(EntityRemovedEvent.NAME,__handle__EntityRemovedEvent);
			dg.cleanup();
		}
		
		protected override function removeWidgets():void {
			removeAllChildren();			
		}
		
		public function SearchPage(service:IService,clazz:Class,
			accordion:Accordion){
			super(service,clazz,null,null);
			Assert.instanceOf(service,IService);
			Assert.instanceOf(configuration,IConfiguration);
			Assert.instanceOf(clazz,Class);
			Swiz.addEventListener(EntitiesLoadedEvent.NAME,__handle__EntitiesLoadedEvent);
			Swiz.addEventListener(EntityRemovedEvent.NAME,__handle__EntityRemovedEvent);
		}
	
		private function __handle__EntityRemovedEvent(evt:EntityRemovedEvent):void{
			if(evt.clazz == this.clazz) {
				this.retrieveData();
			}
		}
	
		override protected function reload(evt:EntitySavedEvent):void {
			if( Util.getClass(evt.entity) == this.clazz ) {
				this.data = evt.entity;
				this.retrieveData();
			} 
		}
		
		private function __handle__EntitiesLoadedEvent(evt:EntitiesLoadedEvent):void{
			if(evt.clazz == this.clazz) {
				if(this.dg != null){
					Util.debug("received:" + Util.getClassname(evt));
					this.dg.dataProvider  = new ArrayCollection(evt.result);
					evt.stopPropagation();
				}
			}
		}
		
		public override function render():void{
			setHeader();
			this.direction = BoxDirection.VERTICAL;
			var def:ClassMeta = ReflectionUtil.extractClassMeta(clazz);
			//loader.addChild(vbox);
			this.dg  = new SearchableDatagrid();
			dg.allowMultipleSelection = true;
			dg.doubleClickEnabled = true;
			this.addChild(dg);
			
			this.addEventListener(Event.RESIZE,
			function (evt:ResizeEvent):void {
				dg.width = (evt.target as UIComponent).width  -1 ;
				dg.height = (evt.target as UIComponent).height -50;
			});
			
			this.width = parent.width -1;
			this.height = parent.height -1;
			this.parent.addEventListener(Event.RESIZE,parent_render);
			function parent_render(evt:ResizeEvent):void {
				width = (evt.target as UIComponent).width  -1 ;
				height = (evt.target as UIComponent).height -1;
			}

			dg.columns = def.displayMeta.columnSettings.columns;

			dg.addEventListener(ListEvent.ITEM_DOUBLE_CLICK,function (evt:ListEvent):void{
				Util.debug("double clicked on item" + 
							Util.getPrimaryKey(SearchableDatagrid(evt.target).selectedItem)
							);	
				var listChild:Object = SearchableDatagrid(evt.currentTarget).selectedItem;
				Swiz.dispatchEvent(
					new LoadEntityEvent(Util.getClass(listChild),Util.getPrimaryKey(listChild))
				);	
			});
			
			dg.addEventListener(ListEvent.CHANGE,function (e:ListEvent):void {
				Util.debug("list change event");
				var dataGrid:SearchableDatagrid =  SearchableDatagrid(e.target);
				if(dataGrid.selectedItems !=null) {
					if(dataGrid.selectedItems.length == 0) {
						del.enabled = false;
					}
					if(dataGrid.selectedItems.length == 1) {
						del.enabled = true;
						del.label = "Delete selected " + Util.getClassname(clazz) ;
					}
					if(dataGrid.selectedItems.length > 1) {
						del.enabled = true;
						del.label = "Delete selected " + LoonStringUtil.pluralize(Util.getClassname(clazz));
					}
				}
			});
			
			//Add the button group 
			this.h = new HBox();
			this.addChild(h);
			this.add  = new Button();
	
			add.label = "Add new " + Util.getClassname(clazz);
			add.addEventListener(MouseEvent.CLICK,function(e:*):void {
				Swiz.dispatchEvent(new CreateEntityEvent(clazz));
			});
			h.addChild(add);
			this.del = new Button();
			
			del.label = "Delete selected " + Util.getClassname(clazz);
			del.enabled = false;
			
			dg.addEventListener(ListEvent.CHANGE,function (e:ListEvent):void {
				Util.debug("list change event");
				var dataGrid:SearchableDatagrid =  SearchableDatagrid(e.target);
				if(dataGrid.selectedItems !=null) {
					if(dataGrid.selectedItems.length == 0) {
						del.enabled = false;
					}
					if(dataGrid.selectedItems.length == 1) {
						del.enabled = true;
						del.label = "Delete selected " + Util.getClassname(clazz) ;
					}
					if(dataGrid.selectedItems.length > 1) {
						del.enabled = true;
						del.label = "Delete selected " + LoonStringUtil.pluralize(Util.getClassname(clazz));
					}
				}
			});
			
			del.addEventListener(MouseEvent.CLICK,function(e:*):void{
				if(dg.selectedItems !=null){
					for each(var item:Object in dg.selectedItems){
						Swiz.dispatchEvent(
							new DeleteEntityEvent(clazz,Util.getPrimaryKey(item))
						);
					}
				}
			});
			h.addChild(del);
			//End add the button group
			retrieveData();
		}
	
		protected function retrieveData():void{
			service.getAll(  this.clazz, ReflectionUtil.extractDisplayMeta(this.clazz).columnSettings.columns);
		}
	}
}