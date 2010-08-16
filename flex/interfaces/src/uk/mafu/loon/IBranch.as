package uk.mafu.loon
{
	import uk.mafu.loon.INode;
	
	public interface IBranch extends INode
	{
		function getChildren():Array;
		function addItem(node:INode):void;
	}
}