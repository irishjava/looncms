package uk.mafu.loon.responder
{
	
	import mx.rpc.events.ResultEvent;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.loon.dto.ManyToManyResult;
	import uk.mafu.loon.events.ManyToManyLoadedEvent;

	public class LoadManyToManyResponder  extends AbstractResponder
	{
		private var parent_clazz:Class,pk:Object,relationship:String;
		
		public function LoadManyToManyResponder(parent_clazz:Class,pk:Object,relationship:String){
			this.parent_clazz = parent_clazz;
			this.pk = pk;
			this.relationship = relationship;
		}

		override public function result(data:Object):void{
			var res:Object = (data as ResultEvent).result;
			Swiz.dispatchEvent(
				new ManyToManyLoadedEvent(res as ManyToManyResult ));
		}
	}
}