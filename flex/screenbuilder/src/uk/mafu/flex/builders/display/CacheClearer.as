package uk.mafu.flex.builders.display
{
	import flash.events.Event;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	
	import mx.controls.Alert;
	
	import uk.mafu.loon.IAction;
	
	public class CacheClearer implements IAction
	{
		private var _url:String;	
		private var _enabled:Boolean;
		
		public function get enabled():Boolean {
			return this._enabled;
		}
	
		public function CacheClearer(url:String,enabled:Boolean = false){
			this._url = url;
			this._enabled = enabled;
		}
		
		public function doAction():void {
			var req:URLRequest = new URLRequest(_url);
			var loader:URLLoader = new URLLoader(req);
			loader.addEventListener(Event.COMPLETE,function (e:*):void {
				Alert.show("Server cache has been cleared");
			});
		}
	}
}