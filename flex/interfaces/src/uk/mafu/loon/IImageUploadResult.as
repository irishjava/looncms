package uk.mafu.loon
{
	public interface IImageUploadResult
	{
		function get status():Boolean; 
		function get pk():Number;
		function get width():Number;
		function get height():Number;
		function get mimetype():String;
		function get filename():String;
	}
}