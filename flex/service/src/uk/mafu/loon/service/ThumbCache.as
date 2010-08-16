package uk.mafu.loon.service
{
	import flash.utils.Dictionary;
	
	import uk.mafu.loon.dto.ImageThumb;
	
	public class ThumbCache
	{
		private static var cache:Dictionary = new Dictionary(true);
		private static const IDENTIFIER:String = "linkPk";
	
		public static function addThumb(thumb:ImageThumb):void{
			cache[thumb[IDENTIFIER]] = thumb;
		}
		
		public static function hasThumb(pk:Object):Boolean{
//			var cursor:IViewCursor =  cache.createCursor();
//			while (!cursor.afterLast){
//				//This is where we gotta do something, if, we gotta do something.
//				if(cursor.current[IDENTIFIER] == pk) {
//					return true;
//				}
//				else{
//					cursor.moveNext();
//				}
//      		}
//      		return false;

				return cache[pk] == null ? false : true;
		}
		
		public static function remove(pk:Number):void{
//			var cursor:IViewCursor =  cache.createCursor();
//			while (!cursor.afterLast){
//				//This is where we gotta do something, if, we gotta do something.
//				if(cursor.current[IDENTIFIER] == pk) {
//					cursor.remove();
//					return;
//				}
//      		}
			cache[pk] = null;
		}
		
		public static function getThumb(pk:Object):ImageThumb{
//			var cursor:IViewCursor =  cache.createCursor();
//			while (!cursor.afterLast){
//				//This is where we gotta do something, if, we gotta do something.
//				if(cursor.current[IDENTIFIER] == pk) {
//					return cursor.current;
//				}
//				else{
//					cursor.moveNext();
//				}
//      		}
			var ret:*;
			ret = cache[pk];
			if(ret == null){			
      			throw new Error("Item with pk: '" + pk + "' not found in cache");
   			}
   			else {
 				return ret;  			
   			}
		}
		
	}
}