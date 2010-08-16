package uk.mafu.flex.builders
{
	
	import flash.utils.Dictionary;
	
	import uk.mafu.flex.util.ReflectionUtil;
	
	public class RelationshipBuilder extends AbstractBuilder
	{
		
		private var processedClasses:Dictionary  = new Dictionary();
		
		public function RelationshipBuilder():void{
		}
		
		public function build(clazz:Class):Array{
			//Relationships
			var relationships:Array = ReflectionUtil.extractRelationships(clazz);
			//Util.debug(o);
			return relationships;
		}
			
		
	}
}