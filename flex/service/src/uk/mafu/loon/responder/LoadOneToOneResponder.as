package uk.mafu.loon.responder
{
	import mx.rpc.events.ResultEvent;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.loon.dto.OneToOneResult;
	import uk.mafu.loon.events.OneToOneRelationshipLoadedEvent;

	public class LoadOneToOneResponder extends AbstractResponder
	{
		private var parent_clazz:Class,pk:Object,relationship:String;
		
		public function LoadOneToOneResponder(parent_clazz:Class,pk:Object,relationship:String){
			this.parent_clazz = parent_clazz;
			this.pk = pk;
			this.relationship = relationship;
		}

		override public function result(data:Object):void{
			Swiz.dispatchEvent(
				new OneToOneRelationshipLoadedEvent(parent_clazz,pk,relationship,(data as ResultEvent).result as OneToOneResult ));
		}
		
		 
	}
}