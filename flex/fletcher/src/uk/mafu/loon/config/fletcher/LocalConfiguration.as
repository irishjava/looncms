package uk.mafu.loon.config.fletcher
{
	import uk.mafu.loon.AbstractConfiguration;
	
	public class LocalConfiguration extends AbstractConfiguration
	{
		override public function getRootContext():String{
			return "http://mafunet2010.amp-london.com/fletcher-server-0.0.1-SNAPSHOT";
		} 
	}
}