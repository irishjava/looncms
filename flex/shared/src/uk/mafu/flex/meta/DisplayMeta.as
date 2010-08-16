package uk.mafu.flex.meta
{
	import mx.collections.ArrayCollection;
	
	import uk.mafu.flex.util.Assert;
	
	
	
	public class DisplayMeta
	{
		
		public var clazz:Class;
		
		//In the situation whereby there are no tabs, the raw row data. 
		private var _rows:ArrayCollection = new ArrayCollection(new Array());
		//The tabs that will each hold a row, in the case where this is a tabbed form.
		private var _tabs:ArrayCollection = new ArrayCollection(new Array());
		//This stuff is related to the search/list screen.
		//Define the which column stuff is sorted upon.
		private var _orderSettings:OrderSettings ;
		//Define what columns are displayed in search/list screen.
		private var _columnSettings:ColumnSettings ;
		//
		private var _chooserSettings:ChooserSettings = new ChooserSettings();
		
		public function DisplayMeta(clazz:Class){		
			Assert.notNull(clazz);
			this.clazz = clazz;
		}
		
		public function addTAB(t:TAB):void{
			this._tabs.addItem(t);
		}
		
		public function addROW(r:ROW):void{	
			this._rows.addItem(r);
		}
		
		public function isTabbed():Boolean {
			return this._tabs.length > 0;
		}
		
		public function get rows():ArrayCollection {
			return this._rows;
		}
		
		public function get tabs():ArrayCollection {
			var ret:Array = this._tabs.toArray();
			ret.sort(function sort(a:Object,b:Object):Number{
				if(a.order < b.order) {
					return -1;
				}
				if(a.order > b.order) {
					return 1;
				}
				return 0;
			});
			return new ArrayCollection(ret);
		}
		
		public function get orderSettings():OrderSettings {
			return this._orderSettings;
		}
		
		public function get columnSettings():ColumnSettings {
			return this._columnSettings;
		}
		
		public function set columnSettings(c:ColumnSettings):void{
			//bit of a hack, prevent definitions further within the class from screwing up the initial settings..
			if(	this._columnSettings == null){
				this._columnSettings = c;
			}
		}
		
		public function set orderSettings(o:OrderSettings):void{	
			this._orderSettings = o;
		}
		
		public function get chooserSettings():ChooserSettings {
			return this._chooserSettings;
		}
		
		public function set chooserSettings(c:ChooserSettings):void{	
			this._chooserSettings = c;
		}
	}
}