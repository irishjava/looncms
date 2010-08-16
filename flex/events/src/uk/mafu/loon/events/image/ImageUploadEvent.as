package uk.mafu.loon.events.image
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IImageUploadResult;
	
   	public class ImageUploadEvent extends Event
	{
		public static const IMAGE_UPLOAD_SUCCESS:String = "imageUploadSuccess";
		public static const IMAGE_UPLOAD_FAILURE:String = "imageUploadFailure";
		public var msg:Object;
		public var result:IImageUploadResult;
		
		public function ImageUploadEvent(type:String,result:IImageUploadResult){
        	super(type, false, false);
        	this.msg = msg;
        	this.result = result;
         	Util.debug("created new:" + Util.getClassname(this) + " ' type='" + type + "'");
    	}

	 	override public function clone():Event
    	{
       		return Util.cloneObject(this) as Event;
    	}
	}
}