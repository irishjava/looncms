package uk.mafu.flex.meta
{
	import flash.errors.IllegalOperationError;
	
	public class RelationshipMeta
	{
		private var _name:String;
		private var _from_clazz:ClassMeta;
		private var _to_clazz:ClassMeta;
		private var _type:String;
		
		public static const ONE_TO_ONE:String = "ONE_TO_ONE";
		public static const ONE_TO_MANY:String = "ONE_TO_MANY";
		public static const MANY_TO_MANY:String = "MANY_TO_MANY";
		
		//Expects classname in the following format ... entity="uk.mafu.domain.architecht::ImageLink"
		public function RelationshipMeta(name:String,from_clazz:ClassMeta,to_clazz:ClassMeta,type:String){
			this._name = name;
			switch (type){
				case ONE_TO_ONE: 
				this._type = type;
				break;
				case ONE_TO_MANY: 
				this._type = type;
				break;
				case MANY_TO_MANY: 
				this._type = type;
				break;
				default:
					throw new IllegalOperationError("unsupported relationship type:'" + type + "' name:" + name + "' from class:" +from_clazz +"'" );
			} 
			this._from_clazz = from_clazz;
			this._to_clazz = to_clazz;
		}
		
		public function get name():String{
			return this._name;
		}
		
		public function get from_clazz():ClassMeta{
			return this._from_clazz;
		}
		
		public function get to_clazz():ClassMeta{
			return this._to_clazz;
		}
		
		public function get type():String{
			return this._type;
		}
	}
}