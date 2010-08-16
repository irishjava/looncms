package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	
	public class OneToOneSavedEvent extends Event 
	{
		public static const NAME:String = Util.getFullClassName(OneToOneSavedEvent);
		
		private var parent:Class;
		private var child:Class;
		private var parent_pk:Object;
		private var child_pk:Object;
		private var relationship:String;	
		
	 	public function OneToOneSavedEvent(parent:Class,
												child:Class,
												parent_pk:Object,
												child_pk:Object,
												relationship:String){
			super(NAME,true,true);
			this.parent = parent;
			this.child = child;
			this.parent_pk  = parent_pk;
			this.child_pk = child_pk;
			this.relationship= relationship;
		}
		
    	/**
     	*  @private
     	*/
    	override public function clone():Event {
       		return Util.cloneObject(this) as Event;
    	}
   	}
}