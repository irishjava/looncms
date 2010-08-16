package uk.mafu.flex.util
{
	import flash.errors.IllegalOperationError;
	
	public class Assert
	{
		public function Assert(){
			throw new IllegalOperationError("Class should only be accessed in a static fashion");
		}
		
		public static function notNull(obj:*,msg:String=""):void {
			if(obj == null) {
				throw new UninitializedError(msg); 
			}
		}
		
		public static function instanceOf(obj:*,cls:Class):void {
			if(obj==null){
				throw new IllegalOperationError("Instance is null");
			}
			if(!(obj is cls)){
				throw new IllegalOperationError("Object is not instance of " + Util.getJavaClassnameByClass(cls));
			} 
		}
		
		public static function collectionOnlyContains(collection:Array,allowedClasses:Array):void {
			collection.every(function (a:*,b:*,c:*):void{
				for(var i:Number = 0; i < allowedClasses.length; i++) {
					if(a is allowedClasses[i]){
						return;	
					}
				}
				throw new IllegalOperationError("Item is not one of " + function(allowedClasses:Array):String{
					var s:String = "";
					for(var i:Number = 0; i < allowedClasses.length; i++) {
						if(i  == allowedClasses.length -1){
							s.concat(Util.getClassnameByClass(allowedClasses[i] as Class));
						}
						else{
							s.concat(Util.getClassnameByClass(allowedClasses[i] as Class));
						}
					}
					return s;
				});
			});
		}
	}
}