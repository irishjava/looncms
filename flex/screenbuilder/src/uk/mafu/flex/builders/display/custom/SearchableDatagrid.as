package uk.mafu.flex.builders.display.custom
{
import flash.events.Event;
import flash.events.TimerEvent;
import flash.utils.Timer;

import mx.collections.ArrayCollection;
import mx.collections.IViewCursor;
import mx.containers.ApplicationControlBar;
import mx.containers.Canvas;
import mx.containers.VBox;
import mx.controls.DataGrid;
import mx.controls.Label;
import mx.controls.TextInput;
import mx.controls.dataGridClasses.DataGridColumn;
import mx.core.Application;
import mx.events.ListEvent;

import uk.mafu.flex.util.Util;

/**
 	 *  Dispatched when the user double-clicks on an item in the control.
 	 *
 	 *  @eventType mx.events.ListEvent.ITEM_DOUBLE_CLICK
 	*/
	[Event(name="itemDoubleClick", type="mx.events.ListEvent")]
	
	/**
 	 *  Dispatched when the user clicks on an item in the control.
 	 *
 	 *  @eventType mx.events.ListEvent.ITEM_CLICK
 	*/
	[Event(name="itemClick", type="mx.events.ListEvent")]
	
	/**
 	 *  Dispatched when the user clicks on an item in the control.
 	 *
 	 *  @eventType mx.events.ListEvent.ITEM_CLICK
 	*/
	[Event(name="change", type="mx.events.ListEvent")]
	public class SearchableDatagrid extends Canvas {

		private var _searchableFieldNames:ArrayCollection = new ArrayCollection(new Array());
		private var _columns:ArrayCollection = new ArrayCollection(new Array());
		private var textInput:TextInput;
		private var dg:DataGrid = new DataGrid();
		private var timer:Timer;
		private var filterEventQueue:ArrayCollection = new ArrayCollection();		
		private const FILTER_TIMER_CHECK_INTERVAL:int = 1500;
		public var filterText:String="";
		
		public function cleanup():void {
			trace("removing timer");
			timer.stop();
			timer = null;
			removeAllChildren();
		}
		
		public function get selectedItems():Array{
			return dg.selectedItems;
		}
		
		public function get selectedIndex():int{
			return dg.selectedIndex;
		}
		
		public function get selectedIndices():Array{
			return dg.selectedIndices;
		}
		
		public function get selectedItem():Object{
			return dg.selectedItem;
		}
		
		public function set allowMultipleSelection(val:Boolean):void {
			dg.allowMultipleSelection = val;
		}
		
		public function SearchableDatagrid()
		{
			super();
			var vbox:VBox = new VBox();
			vbox.percentHeight = 100;
			vbox.percentWidth = 100;
			addChild(vbox);
		
			var applicationControlBar:ApplicationControlBar = new ApplicationControlBar();
			applicationControlBar.percentWidth = 100;
			vbox.addChild(applicationControlBar);
			
			var label:Label = new Label();
			label.text = "Filter text:";
			applicationControlBar.addChild(label);
			
			textInput = new TextInput();
			textInput.width = 121;
			textInput.addEventListener(Event.CHANGE ,textInputChangeHandler);
			applicationControlBar.addChild(textInput);
			
			dg = new DataGrid();
			dg.percentHeight = 100;
			dg.percentWidth = 100;
			
			if((doubleClickEnabled ==true) || 
				(Application.application as Application).doubleClickEnabled == true){
				dg.addEventListener(ListEvent.ITEM_DOUBLE_CLICK,function(e:*):void{
					trace("data grid double clicked");
					dispatchEvent(e);
				});
			}
			
			dg.addEventListener(ListEvent.ITEM_CLICK,function(e:*):void{
				trace("data grid clicked");
				dispatchEvent(e);
			});
			
			dg.addEventListener(ListEvent.CHANGE,function(e:*):void{
				trace("data grid selection changed");
				dispatchEvent(e);
			});
			
			vbox.addChild(dg);
			setupFilterEventTimer();
		}
		
		public function setupFilterEventTimer():void {
			timer = new Timer(FILTER_TIMER_CHECK_INTERVAL,3);
			timer.addEventListener(TimerEvent.TIMER, function (e:*):void{
					trace("Timer fired " + timer.currentCount + " times.");
					if(filterEventQueue.length > 0) {
						filterEventQueue.removeAll();
						trace("filtering list");
						dg.dataProvider.refresh();
					}
			});
			//Don't start the timer.. only start it when the textfield receives a change event.
		}
		
		public function set dataProvider(provider:ArrayCollection):void {
			trace("setting data provider");
			if(dg.dataProvider != null) {
				if(dg.selectedItem != null) {
					var selected:Object = dg.selectedItem ;// Util.getPrimaryKey(dg.selectedItem);
					var newSelection:Object = null;
					
					var c:IViewCursor =  provider.createCursor();
					while(!c.afterLast){
						var current:Object = c.current; //Util.getPrimaryKey(c.current);
						if(Util.isSameEntity(current,selected)){
							newSelection = current;
							break;
						}
						c.moveNext();
					}
					dg.dataProvider = provider;
					dg.selectedIndex =  (dg.dataProvider as ArrayCollection).getItemIndex(newSelection);
					dg.dataProvider.filterFunction = filterFunction;
					return;
				}
			
			}
			dg.dataProvider = provider;
			dg.dataProvider.filterFunction = filterFunction;
		}
		
		public function set searchableFieldNames(names:Array):void {
			_searchableFieldNames.source = names;
		}
		
		public function get searchableFieldNames():Array  {
			return _searchableFieldNames.length == 0 ? columns : _searchableFieldNames.source;
		}
		
		public function set columns(names:Array):void {
			_columns.source = names;
			names.forEach(function(name:String,pos:Number,arr:Array):void {
				var tmp:Array = dg.columns;
				var dgCol:DataGridColumn = new DataGridColumn();
				dgCol.headerText = name;
				dgCol.dataField = name;
				tmp.push(dgCol);
				dg.columns = tmp;
			});
		}
		
		public function get columns():Array  {
			return _columns.source;
		}
		
		private function filterFunction(item:Object):Boolean{
			for each(var field:String in searchableFieldNames) {
				if(item[field] is String){
					if((item[field] as String).toLowerCase().indexOf(textInput.text.toLowerCase()) > -1){
						return true;
					}
				}			
			}
			return false;
		}
		
		public function textInputChangeHandler(evt:*):void {
			trace("text input text changed");
			filterEventQueue.addItem(evt);
			if(!timer.running){
				timer.reset();
				timer.start();
			}
		}
	}
}