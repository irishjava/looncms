package uk.mafu.loon.service
{
	import flash.net.FileFilter;

	public class ImageUploaderParam
	{
		public var target:String;
		public var fileParamName:String ="data";
		public static var fileFilter:FileFilter = new FileFilter ("Images (*.jpg, *.jpeg, *.gif, *.png)", "*.jpg; *.jpeg; *.gif; *.png");
		public var title:String;
		
		public static function getImageFilters():Array{
			var a:Array = new Array();
			a[0] = fileFilter;
			return a;
		}
	}
}