package uk.mafu.loon.tree
{
	import uk.mafu.flex.util.Assert;
	import uk.mafu.loon.ILeaf;
	
	public class Leaf implements ILeaf
	{
		private var _title:String;
		private var clazz:Class;
		
		public function Leaf(title:String,clazz:Class){
			Assert.notNull(title,"title");
			Assert.notNull(clazz,"clazz");	
			this._title = title;
			this.clazz = clazz;
		}
		
		public function get title():String{
			return this._title;
		}
		
		public function getClass():Class{
			return this.clazz;
		}
	}
}