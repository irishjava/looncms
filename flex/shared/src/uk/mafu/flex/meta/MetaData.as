package uk.mafu.flex.meta
{
	import flash.utils.Dictionary;
	
	public class MetaData
	{
		public var name:String;
		public var dict:Dictionary = new Dictionary(); 
		
		public function MetaData(name:String = null,dict:Dictionary=null) {
			this.name = name;
			if(dict != null){
				this.dict = dict;		
			}	
		}
	}
}