package uk.mafu.flex.meta
{
	public class ROW
	{
		//Display name
		private var _name:String;
		
		//Label
		private var _label:String;
		
		//Label
		private var _lines:Number = 1;
		
		private var _enums:Array = [];
		public var defaultValue:String = "";
		
		
		
		private var _maxChars:Number = -1;
				
		
		//Name of the element to which the the widget will be bound.
		private var _binding:String;

		//The Class of the widget that will be used to hold the data e.g TextArea;		
		private var _widgetDef:WidgetDef;
		
		//The type that the row represents e.g. Array, String, Number; whatever ...
		private var _type:Class;
		
		private var _relationshipDef:RelationshipMeta;
		
		public function ROW(){
		}
		
		public function set name(_name:String):void{
			this._name = _name;
		}
		
		public function get name():String{
			return this._name;
		}
		
		public function set label(_label:String):void{
			this._label = _label;
		}
		
		public function get label():String{
			return this._label;
		}
		
		public function set maxChars(maxChars:Number):void{
			this._maxChars = maxChars;
		}
		
		public function get maxChars(  ):Number{
			return this._maxChars;
		}
		
		public function set lines(lines:Number):void{
			this._lines = lines;
		}
		
		public function get lines(  ):Number{
			return this._lines;
		}
		
		public function set enums(e:Array):void{
			this._enums = e;
		}
		
		public function get enums():Array{
			return this._enums;
		}
		
		public function set binding(_binding:String):void{
			this._binding = _binding;
		}
		
		public function get binding():String{
			return this._binding;
		}
		
		public function set widgetDef(_widgetDef:WidgetDef):void{
			this._widgetDef = _widgetDef;
		}
		
		public function get widgetDef():WidgetDef{
			return this._widgetDef;
		}
		
		public function set type(_type:Class):void{
			this._type = _type;
		}
		
		public function get type():Class{
			return this._type;
		}
		
		public function set relationship(_relationship:RelationshipMeta):void{
			this._relationshipDef = _relationship;
		}
		
		public function get relationship():RelationshipMeta{
			return this._relationshipDef;
		}
	}
}