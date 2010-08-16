package uk.mafu.flex.builders
{
	import uk.mafu.flex.meta.DisplayMeta;
	import uk.mafu.flex.util.ReflectionUtil;
	
	public class ScreenBuilder extends AbstractBuilder
	{
		public function ScreenBuilder(){
		}
	
		public static function build(clazz:Class):DisplayMeta{
			return ReflectionUtil.extractDisplayMeta(clazz);
		}		
	}
}