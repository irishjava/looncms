package uk.mafu.flex.meta
{
	public class OrderSettings
	{
		private var _column:String;
		private var _ascending:Boolean;
		
		public function OrderSettings(){
		}

		public function get column():String{
			return this._column;
		}

		public function set column(c:String):void{
			this._column = c;
		}

		public function get ascending():Boolean{
			return this._ascending; 
		}

		public function set ascending(b:Boolean):void{
			this._ascending = b;
		}
	}
}