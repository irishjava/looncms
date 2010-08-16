package uk.mafu.flex.builders.display
{
	import flash.errors.IllegalOperationError;
	import flash.utils.getQualifiedClassName;
	
	import mx.collections.ArrayCollection;
	import mx.collections.IViewCursor;
	import mx.containers.Accordion;
	import mx.containers.Box;
	import mx.core.UIComponent;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IConfiguration;
	import uk.mafu.loon.IEntityContainer;
	import uk.mafu.loon.IExecutable;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.events.EntitySavedEvent;
	
	public class EntityContainer extends Box implements IEntityContainer
	{
		protected var service:IService;
		protected var configuration:IConfiguration;
		
		protected var clazz:Class;
		protected var parent_clazz:Class;
		protected var entityParent:Object ;
		private var __executionQueue:ArrayCollection = new ArrayCollection(new Array());
		
		public function EntityContainer(service:IService, clazz:Class,
			parent_clazz:Class=null,relationship:String=null){
			this.service = service;
			this.configuration = service.configuration;
			this.clazz = clazz;
			this.parent_clazz = parent_clazz;
			Swiz.addEventListener(EntitySavedEvent.NAME,reload,false,0,true);
		}
		
		public function internalRemoveEventListeners():void {
			Swiz.removeEventListener(EntitySavedEvent.NAME,reload);
		}
		
	 	//Abstract
		public function render():void{
			throw new IllegalOperationError("Implement in child class!!" + Util.getClassname(this));
		}
	
		protected function reload(evt:EntitySavedEvent):void {
			throw new IllegalOperationError("Implement in child class!!" + Util.getClassname(this));
		}	
		
		protected function removeEventListeners():void {
			throw new IllegalOperationError("Implement in child class!!" + Util.getClassname(this));
		}
		
		protected function removeWidgets():void{
			throw new IllegalOperationError("Implement in child class!!" + Util.getClassname(this));
		}
		
		public function destroy():void{
			internalRemoveEventListeners();
			clearExecutionQueue();
			removeEventListeners();
			removeWidgets();
		}

		protected function addToExecutionQueue(change:IExecutable):void {
			var cursor:IViewCursor =  __executionQueue.createCursor();
			var pos:Number = -1;
			//Remove any duplicates
			while (!cursor.afterLast){
				var current:IExecutable = (cursor.current as IExecutable);
				if(current.isDuplicate(change)){
					cursor.remove();
				}
				cursor.moveNext();
          	}
          	//Finally, add the item
			__executionQueue.addItem(change);
		}
		
		public function clearExecutionQueue():void {
			__executionQueue.removeAll();	
		}
		
		public function handleExecutions():void{
			var cursor:IViewCursor =  __executionQueue.createCursor();
			while (!cursor.afterLast){
				//This is where we gotta do something, if, we gotta do something.
				(cursor.current as IExecutable).execute(service,this.entityParent);
				cursor.moveNext();
      		}
      		__executionQueue.removeAll();
		} 
		
		public function hasExecutions():Boolean{
			if (__executionQueue.length > 0) {
				return true;
			}
			return false;
		} 
		
		public override function get data():Object {
			return super.data; // Util.cloneObject(super.data);
		}
		
		public override function set data(data:Object):void {
			super.data = data; //Util.cloneObject(data);
		}
		
		protected function getParentAccordian(t:UIComponent,depth:Number=0):Accordion {
			Util.debug("entityContainer.getParentAccordian" + getQualifiedClassName(t));
			if(depth > 10){
				throw new IllegalOperationError("recursion overflow");
			}
			if(getQualifiedClassName(t) === getQualifiedClassName(Accordion)){
				return t as Accordion;
			}
			else{
				return getParentAccordian(t.parent as UIComponent,++depth);
			}
		}
		
		protected function setHeader():void{
			var accordion:Accordion = findAccordian();
			accordion.selectedChild=this;
			var children:Array= accordion.getChildren();
			accordion.getHeaderAt(accordion.getChildIndex(this)).label =  Util.getFriendlyClassnameByClass(this.clazz);
		}
		
		protected function findAccordian():Accordion{
			return getParentAccordian(this);
		}
	}
}