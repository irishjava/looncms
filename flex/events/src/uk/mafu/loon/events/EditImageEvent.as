package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Assert;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.domain.data.ImageLink;
	
	/**
	 * 
	 */
	public class EditImageEvent extends Event
	{
		public var imageLink:ImageLink;
		
		
		public static const NAME:String = Util.getFullClassName(EditImageEvent);
		
		public function EditImageEvent(imageLink:ImageLink) {
        	super(NAME, true,true);
        	Assert.notNull(imageLink);
        	this.imageLink = imageLink;
        	Util.debug("created new:" + Util.getClassname(this) );
        }

    	/**
     	*  @private
     	*/
    	override public function clone():Event	{
       		return Util.cloneObject(this) as Event;
    	}
	}
}