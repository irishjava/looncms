package uk.mafu.loon
{
	public interface IActionNode extends INode
	{
		function get confirmation():String;
		function get action():IAction;
	}
}