package uk.mafu.loon.responder
{
	import org.swizframework.Swiz;
	
	import uk.mafu.flex.util.Assert;
	import uk.mafu.loon.events.VideoRemovedEvent;

	public class RemoveVideoResponder extends AbstractResponder
	{
		public var pk:Object
		
	 	public function RemoveVideoResponder(pk:Object){
	 		Assert.notNull(pk,"pk");
	 		this.pk = pk;
		}

		override public function result(data:Object):void{
			Swiz.dispatchEvent(new VideoRemovedEvent(pk));
		}
	}
}