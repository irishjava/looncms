package uk.mafu.flex.builders.display
{
	import flash.events.MouseEvent;
	import mx.containers.HBox;
	import mx.controls.Button;
	import org.swizframework.Swiz;
	import uk.mafu.loon.events.DiscardEntityEvent;
	import uk.mafu.loon.events.SaveEntityEvent;

	public class SaveCancelGroup extends HBox
	{
		
		private	var saveButton:Button;
		private var discardButton:Button;
		
		public function SaveCancelGroup(entity:Object)
		{
			super();
			this.data = entity;
			
			this.percentWidth = 30;
			
			saveButton = new Button();
			this.addChild(saveButton);
			saveButton.label = "save";
			saveButton.enabled = false;
			saveButton.addEventListener(MouseEvent.CLICK,function (e:*):void{
				Swiz.dispatchEvent(new SaveEntityEvent(entity));
			});
			
		    discardButton = new Button();
			this.addChild(discardButton);
			discardButton.label = "discard";
			discardButton.addEventListener(MouseEvent.CLICK,function (e:*):void{
				Swiz.dispatchEvent(new DiscardEntityEvent(entity));
			});
		}
		
		public function handleEntityChanged():void {
			saveButton.enabled = true;
		}
		
		
		
	}
}