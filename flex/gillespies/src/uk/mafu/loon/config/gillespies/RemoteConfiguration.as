package uk.mafu.loon.config.gillespies
{
	import uk.mafu.flex.builders.display.CacheClearer;
	import uk.mafu.loon.AbstractConfiguration;
	import uk.mafu.loon.IAction;
	
	public class RemoteConfiguration extends AbstractConfiguration 
	{
		public override function getRootContext():String {
			return  "http://mafunet2010.amp-london.com/gillespies-server-0.0.1-SNAPSHOT";	
		}
		
		override public function getCacheClearer():IAction{
			return new CacheClearer("http://www.mafuservices.com/amfphp/services/gillespies/resetCache.php");
		}
	}
}