package uk.mafu.loon.events.image
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.LoonImage;
	
   	public class ImageEditEvent extends Event
	{
		public static const EDIT_COMPLETE:String = "imageEditComplete";
		public static const IMAGE_CREATED:String = "imageCreated";
		public static const PROPERTIES_MODIFIED:String = "propertiesModified";
		public static const EDIT_CANCELED:String = "imageEditCancelled";
		
		private var _loonImage:LoonImage;
		private var _imageLink:ImageLink;
		
		public function ImageEditEvent(type:String,loonImage:LoonImage=null,imageLink:ImageLink=null){
        	super(type, false, false);
         	Util.debug("created new:" + Util.getClassname(this) + " ' type='" + type + "'");
         	this._loonImage = loonImage;
         	this._imageLink = imageLink;
    	}

	 	override public function clone():Event{
       		return Util.cloneObject(this) as Event;
    	}
    	
    	public function get loonImage():LoonImage {
    		return _loonImage;
    	}
    	
    	public function get imageLink():ImageLink {
    		return _imageLink;
    	}
	}
}