package uk.mafu.flex.meta
{
	import flash.utils.Dictionary;

	public class ClassMeta {
		public var clazz:Class;
		public var name:String;
		public var relationships:Dictionary = new Dictionary(); 
		private var _displayMeta:DisplayMeta;
		
		public function ClassMeta(clazz:Class, name:String = null,relationships:Dictionary=null) {
			this.clazz = clazz;
			this.name = name;
			if(relationships != null){
				this.relationships = relationships;		
			}
		}
		
		public function set displayMeta(_displayMeta:DisplayMeta):void {
			this._displayMeta = _displayMeta;
		}
		
		public function get displayMeta():DisplayMeta {
			return this._displayMeta;
		}
		
	}
}