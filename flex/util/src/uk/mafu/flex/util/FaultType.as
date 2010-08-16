package uk.mafu.flex.util
{
	import flash.errors.IllegalOperationError;
	
	public class FaultType
	{
		public static const CONSTRAINT_VIOLATION:String="CONSTRAINT_VIOLATION";
		
		private var _type:String;
		private var _faultString:String;
		
		public function FaultType(type:String,faultString:String=""){
			Assert.notNull(type,"FaultType.type may not be null");
			this._type = type;
			this._faultString = faultString;
		}
		
		public function get faultString():String {return _faultString;}
		public function get type():String {return _type;}
		
		public function toString():String {
			if(type == CONSTRAINT_VIOLATION) {
				return faultString ;
			}
			throw new IllegalOperationError("unknown type");
		}
	}
}