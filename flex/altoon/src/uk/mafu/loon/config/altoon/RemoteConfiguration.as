package uk.mafu.loon.config.altoon
{
	import uk.mafu.loon.AbstractConfiguration;
	
	public class RemoteConfiguration extends AbstractConfiguration 
	{
		public override function getRootContext():String {
			return "http://mafunet2010.amp-london.com/altoon-server-0.0.1-SNAPSHOT";
		}
	}
}