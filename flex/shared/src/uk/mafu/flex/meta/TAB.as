package uk.mafu.flex.meta
{
	import uk.mafu.flex.util.Assert;
	
	public class TAB
	{
		public var order:Number;
		public var title:String;
		private var _rows:Array = new Array();
		private var _fieldNames:Array = new Array();
		
		public function TAB(){
		}

		public function addROW(r:ROW):void {
			//Assert.instanceOf(r,ROW);
			this._rows.push(r);
		}
		
		public function deleteROW(r:ROW):void {
			if(this._rows.indexOf(r) != -1){
				this._rows[this._rows.indexOf(r)] == null;
			}
		}
		
		public function popROW():ROW{
			return this._rows.pop();	
		}
		
		public function addFieldName(s:String):void {
			//Assert.instanceOf(r,ROW);
			this._fieldNames.push(s);
		}
		
		public function deleteFieldName(s:String):void {
			if(this._fieldNames.indexOf(s) != -1){
				this._fieldNames[this._fieldNames.indexOf(s)] == null;
			}
		}
		
		public function popFieldName():ROW{
			return this._fieldNames.pop();	
		}
		
		public function get fieldNames():Array {
			return this._fieldNames;
		}
		
	}
}