package uk.mafu.loon.tree
{
	import uk.mafu.loon.IAction;
	import uk.mafu.loon.IActionNode;
	
	public class Action implements IActionNode
	{
		private var _title:String;
		private var _action:IAction;
		private var _confirmation:String;
		
		public function Action(
				title:String,confirmation:String,action:IAction){
			this._title = title;
			this._action = action;
			this._confirmation = confirmation;
		}
		
		public function get confirmation():String {
			return _confirmation;
		}
		
		public function get title():String {
			return _title;
		}
		
		public function get action():IAction {
			return _action;
		}
	}
}