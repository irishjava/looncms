package uk.gormley
{
	import uk.mafu.loon.AbstractConfiguration;
	
	public class RemoteConfiguration extends AbstractConfiguration
	{
		override public function getRootContext():String{
			return "http://mafunet2010.amp-london.com/gormley-server-0.0.1-SNAPSHOT";
		} 
	}
}