package uk.mafu.flex.util
{
	import flash.errors.IllegalOperationError;
	import flash.utils.ByteArray;
	import flash.utils.getDefinitionByName;
	import flash.utils.getQualifiedClassName;
	
	public class Util
	{
		public static function isTransient(o1:Object,field:Object="pk"):Boolean {
			var primaryKey:Object = o1[field];
			var notNull:Boolean = primaryKey != null;
			if(primaryKey is String && notNull){
				return false;
			}
			else if(primaryKey is Number && Number(primaryKey) > 0){
				return false;
			}
			else {
				return true;
			} 
			throw new IllegalOperationError("Illegal State"); 
		}
		
		public static function getClassname(obj:Object):String{
			var full:String = getQualifiedClassName(obj);
			return full.substr(full.lastIndexOf(":")+1,full.length);
		}
		
		public static function debug(val:*):void{
			trace(val);
		}
				
		public static function getClassnameByClass(clazz:Class):String{
			var full:String = getQualifiedClassName(new clazz);
			return full.substr(full.lastIndexOf(":")+1,full.length);
		}
		
		public static function getFriendlyClassnameByClass(clazz:Class):String{
			var full:String = getQualifiedClassName(new clazz);
			var camelCase:String = full.substr(full.lastIndexOf(":")+1,full.length);
			var ret:String = "";
			for(var i:Number=0;i< camelCase.length;i++){
				if(camelCase.charAt(i).match(/([A-Z]+)/)){
					if(i == 0){
						ret = ret + camelCase.charAt(i);
					}
					else{
						ret = ret + " " + camelCase.charAt(i).toLowerCase();
					}
				}
				else{
					ret  = ret + camelCase.charAt(i);
				}
			}
			return ret;	
		}
		
		public static function makeNull(object:Object):void{
			object = null;
		}
		
		/**
		 * Get the fully qualified class name.
		 */
		public static function getFullClassName(obj:Object):String{
			return getQualifiedClassName(obj);
		}
		
		public static function getJavaClassname(obj:Object):String{
			var full:String = getQualifiedClassName(obj);
			return full.replace("::",".");
		}
		
		public static function getJavaClassnameByClass(clazz:Class):String{
			var full:String = getQualifiedClassName(new clazz);
			return full.replace("::",".");
		}
		
		public static function getClass(obj:Object):Class{
			if(obj == null){
				return null;
			}
			var c:Class = Class(getDefinitionByName(getQualifiedClassName(obj)));
			return c;
		}
		
		public static function getClassByName(className:String):Class{
			return Class(getDefinitionByName(className));
		}
		
		public static function cloneObject(o:Object):Object{
			var bytes:ByteArray = new ByteArray();
       		bytes.writeObject(o);
       		bytes.position = 0;
       		return bytes.readObject();
    	}
    	
    	public static function getPrimaryKey(o:Object):Object{
    		return o['pk'];
    	}
    	
    	/**
    	 * Has this entity been saved or does it still have a -1 primary key
    	 */
    	public static function isUnattachedEntity(o:Object):Boolean{
    		return isTransient(o);
    	}
    	
    	public static function isIdentical(obj1:Object,obj2:Object):Boolean{
			var buffer1:ByteArray = new ByteArray();
    		buffer1.writeObject(obj1);
    		var buffer2:ByteArray = new ByteArray();
    		buffer2.writeObject(obj2);
    		// compare the lengths
    		var size:uint = buffer1.length;
    		if (buffer1.length == buffer2.length) {
        		buffer1.position = 0;
        		buffer2.position = 0;
 				// then the bits
        		while (buffer1.position < size) {
            		var v1:int = buffer1.readByte();
            		if (v1 != buffer2.readByte()) {
                		return false;
            		}
        		}    
        		return true;                        
    		}
    		return false;	
		}
		
		/**
		 * Return true if the two Objects are of the same class and share the same primary key.
		 */
		public static function isSameEntity(obj1:Object,obj2:Object):Boolean {
			//In the case where the object doesn't have a set primary key. 
			if(obj1 === obj2){
				return true;
			}
			if(	(Util.getClass(obj1) == Util.getClass(obj2) ) && 
				(Util.getPrimaryKey(obj1) ==  Util.getPrimaryKey(obj2)) ){
				return true;
			}
			return false;
		}
		
		
		
		public static function join(... value:Array):Array {
			var ret:Array = new Array()
			for each ( var arr:Array in value ) {
				for each ( var obj:Object in arr ) {
					var i:Number = ret.indexOf(obj)
					if(i == -1) {
						ret.push(obj);
					}				
				}
			}
			return ret;
		}
		
		public static function copyArray(src:Array):Array {
			var ret:Array = new Array();
			for each ( var obj:Object in src ) {
				ret.push(obj);				
			}
			return ret;
		}
	}
}