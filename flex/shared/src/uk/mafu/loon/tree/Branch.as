package uk.mafu.loon.tree
{
	import uk.mafu.flex.util.Assert;
	import uk.mafu.loon.IActionNode;
	import uk.mafu.loon.IBranch;
	import uk.mafu.loon.INode;
	
	public class Branch implements IBranch
	{
		private var _title:String;
		private var _children:Array = new Array;
		
		public function Branch(title:String,children:Array=null){
			if(children != null){
				Assert.collectionOnlyContains(children,[INode,IActionNode]);
				this._children = children;
			}
			this._title = title;
		}
		
		public function get title():String {
			return _title;
		}
		/**
		 * Return an array of INode's .
		 */
		public function getChildren():Array {
			return _children;
		}
		
		public function addItem(node:INode):void {
			Assert.instanceOf(node,INode);
			this._children.push(node);
		}
	}
}