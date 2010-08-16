package uk.mafu.flex.view
{
	import flash.errors.IllegalOperationError;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ICollectionView;
	import mx.controls.treeClasses.ITreeDataDescriptor;
	
	import uk.mafu.loon.IBranch;
	
	public class TreeDataDescriptor implements ITreeDataDescriptor
	{
		
	    /**
	     *  Provides access to a node's children, returning a collection
	     *  view of children if they exist.
		 *  A node can return any object in the collection as its children;
		 *  children need not be nested.
		 *  It is best-practice to return the same collection view for a
		 *  given node.
	     *
		 *  @param node The node object currently being evaluated.
		 *
	     *  @param model The entire collection that this node is a part of.
		 *
	     *  @return An collection view containing the child nodes.
	     */
	    public function getChildren(node:Object, model:Object = null):ICollectionView{
	    	var children:Array = (node as IBranch).getChildren();
	    	var arrayCollection:ArrayCollection = new ArrayCollection(children);
	    	return arrayCollection;
	    }
	
	    /**
	     *  Tests for the existence of children in a non-terminating node.
	     */
	    public function hasChildren(node:Object, model:Object = null):Boolean {
	    	if((node is IBranch) && (node as IBranch).getChildren().length > 0) {
	    		return true;
	    	} 
	    	return false;
	    }
	
	    /**
	     *  Tests a node for termination.
		 *  Branches are non-terminating but are not required
		 *  to have any leaf nodes.
	     *
	     *  @param node The node object currently being evaluated.
		 *
	     *  @param model The entire collection that this node is a part of.
		 *
	     *  @return A Boolean indicating if this node is non-terminating.
	     */
	    public function isBranch(node:Object, model:Object = null):Boolean {
	    	if(node is IBranch) {
	    		return true;
	    	}
	    	return false;
	    }
	
		/**
	     *  Gets the data from a node.
	     *
	     *  @param node The node object from which to get the data.
		 *
	     *  @param model The collection that contains the node.
		 *
	     *  @return Object The requested data.
	     */
		 public function getData(node:Object, model:Object = null):Object {
		 	throw new IllegalOperationError("not supported");	
		 }
	
	    /**
	     *  Adds a child node to a node at the specified index.
	     *
		 *  @param node The node object that will parent the child.
		 *
		 *  @param child The node object that will be parented by the node.
		 *
		 *  @param index The 0-based index of where to put the child node.
		 *
	     *  @param model The entire collection that this node is a part of
	     *  @return true if successful.
	     */
	    public function addChildAt(parent:Object, newChild:Object,
							index:int, model:Object = null):Boolean{
			throw new IllegalOperationError("not supported");					
		}
	
	    /**
	     *  Removes a child node to a node at the specified index.
	     *
		 *  @param node The node object that is the parent of the child.
		 *
		 *  @param child The node object that will be removed.
		 *
		 *  @param index The 0-based index of the soon to be deleted node.
		 *
	     *  @param model The entire collection that this node is a part of
	     *  @return true if successful.
	     */
	    public function removeChildAt(parent:Object, child:Object,
							   index:int, model:Object = null):Boolean {
			throw new IllegalOperationError("not supported");
		}
	}

}
