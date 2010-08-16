package uk.mafu.flex.view
{
	import flash.errors.IllegalOperationError;
	
	import mx.containers.Canvas;
	import mx.controls.Alert;
	import mx.controls.Tree;
	import mx.core.ScrollPolicy;
	import mx.core.UIComponent;
	import mx.events.CloseEvent;
	import mx.events.ListEvent;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.loon.IActionNode;
	import uk.mafu.loon.IBranch;
	import uk.mafu.loon.ILeaf;
	import uk.mafu.loon.INode;
	import uk.mafu.loon.IPluginLoader;
	import uk.mafu.loon.events.UpdatePanelEvent;
	import uk.mafu.loon.tree.Leaf;

	public class EntityListPanel extends Canvas
	{
		
		public function EntityListPanel(loader:IPluginLoader,parent:UIComponent)
		{
			this.verticalScrollPolicy = ScrollPolicy.OFF;
			
			var tree:Tree = new Tree();
			tree.verticalScrollPolicy = ScrollPolicy.OFF;
			tree.percentHeight = 100;
			tree.percentWidth = 100;
			this.addChild(tree);
			this.percentHeight = 100;
			this.percentWidth = 100;
			parent.addChild(this);
			tree.dataProvider = loader.getSidebarTree()
			tree.labelFunction = function(e:Object):String {
				if(e is INode) {
					return (e as INode).title;
				}
				else {
					throw new IllegalOperationError();
				}
			}
			tree.dataDescriptor = new TreeDataDescriptor();
	 		tree.addEventListener(ListEvent.ITEM_CLICK,itemSelectedHandler);
		}
		
		private function itemSelectedHandler(e:ListEvent):void {
				var o:Object = (e.target as Tree).selectedItem;
				var t:Tree  = (e.target as Tree);
				if(o is ILeaf){
					Swiz.dispatchEvent(new UpdatePanelEvent( 
						(o as Leaf ).getClass()  
						));
				}
				else if(o is IBranch){
					if(!t.isItemOpen(o)) {
						t.expandItem(o ,true);
					}
					else{
						//do nothing
					}
				}
				else if(o is IActionNode){
					var actionNode:IActionNode = o as IActionNode;
					Alert.show(actionNode.confirmation,actionNode.title,
					Alert.OK | Alert.CANCEL,
					null,
					function (e:*):void{
						trace(e);
						if(CloseEvent(e).detail == Alert.OK){
							actionNode.action.doAction();
						}
					},
					null,
					Alert.CANCEL);
				}
				else {
					throw new IllegalOperationError("should not get here");				
				}
			}
	}
}