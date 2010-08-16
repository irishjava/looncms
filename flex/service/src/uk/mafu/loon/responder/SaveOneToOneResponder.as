package uk.mafu.loon.responder
{
	import mx.rpc.events.ResultEvent;
	import uk.mafu.flex.util.Util;
	import org.swizframework.Swiz;
	import uk.mafu.loon.events.OneToOneSavedEvent;
	import uk.mafu.flex.util.Util;

	public class SaveOneToOneResponder extends AbstractResponder
	{
		private var parent:Class;
		private var child:Class;
		private var parent_pk:Object;
		private var child_pk:Object;
		private var relationship:String;	
		
	 	public function SaveOneToOneResponder(parent:Class,
												child:Class,
												parent_pk:Object,
												child_pk:Object,
												relationship:String){
			this.parent = parent;
			this.child = child;
			this.parent_pk  = parent_pk;
			this.child_pk = child_pk;
			this.relationship= relationship;
		}

		override public function result(data:Object):void{
			Util.debug((data as ResultEvent).result);
			Swiz.dispatchEvent(
				new OneToOneSavedEvent(
								parent,
								child,
								parent_pk,
								child_pk,
								relationship)
			);
		}
	}
}