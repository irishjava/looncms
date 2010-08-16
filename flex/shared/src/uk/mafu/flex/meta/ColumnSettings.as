package uk.mafu.flex.meta
{
	public class ColumnSettings
	{
		
		private var _columns:Array = new Array(); 
		
		public function ColumnSettings(){
		}
		
		/**
		 * Add a column 
		 */
		public function addColumn(c:String):void{
			this._columns.push(c);
		}
		
		/**
		 * @return an array of column names 
		 */
		public function get columns():Array{
			return  this._columns ;
		}

	}
}