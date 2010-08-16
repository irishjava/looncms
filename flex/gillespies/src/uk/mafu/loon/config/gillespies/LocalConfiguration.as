package uk.mafu.loon.config.gillespies
{
	import uk.mafu.flex.builders.display.CacheClearer;
	import uk.mafu.loon.AbstractConfiguration;
	import uk.mafu.loon.IAction;
	
	public class LocalConfiguration extends AbstractConfiguration
	{
		public override function getRootContext():String {
			return "http://127.0.0.1:8666/gillespies-server";
		}
		
		override public function getCacheClearer():IAction{
			return new CacheClearer("http://www.mafuservices.com/amfphp/services/gillespies/resetCache.php");
		}		
	}
}
