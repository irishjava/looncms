package uk.mafu.loon.responder
{
	
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	import org.swizframework.Swiz;
	import uk.mafu.loon.dto.OneToManyResult;
	import uk.mafu.loon.events.OneToManyLoadedEvent;

	public class LoadOneToManyResponder  extends AbstractResponder
	{
		private var parent_clazz:Class,pk:Object,relationship:String;
		
		public function LoadOneToManyResponder(parent_clazz:Class,pk:Object,relationship:String){
			this.parent_clazz = parent_clazz;
			this.pk = pk;
			this.relationship = relationship;
		}

		override public function result(data:Object):void{
			var res:OneToManyResult = ((data as ResultEvent).result as OneToManyResult);
			Swiz.dispatchEvent(new OneToManyLoadedEvent(res as OneToManyResult ));
		}
	}
}