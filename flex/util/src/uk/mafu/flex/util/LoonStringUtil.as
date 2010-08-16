package uk.mafu.flex.util
{
	import flash.utils.ByteArray;
	import flash.utils.getDefinitionByName;
	import flash.utils.getQualifiedClassName;
	
	public class LoonStringUtil
	{
		public static function pluralize(src:String):String {
			var ret:String = "";
			ret = ret.concat(src);
			if(ret.charAt(src.length -1) == 'y') {
					ret = ret.substr(0,ret.length -1);
					ret = ret.concat("ies");
			}
			else {
				ret = ret.concat("s");
			}
			return ret;
		}
		
		
		public static function decamelize( str:String, separater:String = " " ):String {
            return str.replace( /(([^.\d])(\d)|[A-Z])/g,
            	function(...$):String {
                	if($[2]) return $[2] + separater + $[3].toLowerCase();
                    if($[4]==0) return $[0].toLowerCase();
                    return separater + $[0].toLowerCase();
                });
        }
	}
}