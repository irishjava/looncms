package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	
	public class OneToManySavedEvent extends Event 
	{
		public static const NAME:String = Util.getFullClassName(OneToManySavedEvent);
		private var parent_clazz:Class;
		private var parentId:Object;
		private var relationship:String;
		private var children:Array;
		
	 	public function OneToManySavedEvent(
	 							parent_clazz:Class,
								parentId:Object,
								relationship:String,
								children:Array)
		{
	 		super(NAME,true,true);
	 		this.parent_clazz = parent_clazz ;
	 		this.parentId = parentId ;
	 		this.relationship = relationship;
	 		this.children = children;
		}

    	/**
     	*  @private
     	*/
    	override public function clone():Event {
       		return Util.cloneObject(this) as Event;
    	}
   	}
}