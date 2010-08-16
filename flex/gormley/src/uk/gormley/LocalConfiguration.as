package uk.gormley
{
	import uk.mafu.loon.AbstractConfiguration;
	
	public class LocalConfiguration extends AbstractConfiguration
	{
		override public function getRootContext():String{
			//return "http://127.0.0.1:8666/gormley-server";
            return "http://mafunet2010.amp-london.com/gormley-server-0.0.1-SNAPSHOT";
		} 
	}
}