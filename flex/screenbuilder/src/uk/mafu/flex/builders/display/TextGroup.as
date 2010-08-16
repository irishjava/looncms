package uk.mafu.flex.builders.display
{
	import flash.events.Event;
	
	import mx.binding.utils.BindingUtils;
	import mx.containers.HBox;
	import mx.controls.Alert;
	import mx.controls.Label;
	import mx.controls.TextArea;
	import mx.core.UIComponent;
	import uk.mafu.flex.util.Util;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.flex.meta.ROW;
	import uk.mafu.loon.events.EntityModifiedEvent;

	public class TextGroup extends HBox
	{
		
		private var textLabel:Label;
		private var textArea:TextArea;
		
		public function TextGroup(entity:Object,metaRow:ROW,parentContainer:UIComponent)
		{
			super();
			parentContainer.addChild(this);
			this.styleName = "textGroup";
			
			//Add a resize to 100% event listener first....
			this.parent.addEventListener(Event.RESIZE,function():void{
				if(parent != null){
					width = parent.width ;
				} 
			});
			
			this.data = entity;
			    
			textLabel = new Label();
			textLabel.styleName = "textGroupLabel" ; 
			
			textLabel.percentWidth = 30; 
			textLabel.height = 20 ;
			textLabel.truncateToFit = true;
			textLabel.text = metaRow.label;
			addChild(textLabel);

			textArea = new TextArea();
			textArea.percentWidth = 65;
			textArea.height = 20 * metaRow.lines;
			textArea.styleName = "textGroupTextArea";
			
			if(metaRow.maxChars != -1) {
				textArea.maxChars = 250;
			}
			else{
				if(metaRow.lines == 1) {
					textArea.maxChars = 250;
				}
			}
			
			//Bind from widget to the data
			BindingUtils.bindProperty(textArea, "text",data, metaRow.name);
			//Bind from data to the widget
			BindingUtils.bindProperty(data, metaRow.name ,textArea, "text");
			textArea.addEventListener(Event.CHANGE,function (evt:Event):void{
				Util.debug("textgroup.event.change data = " + data + "'");
				Swiz.dispatchEvent(new EntityModifiedEvent(data));
			});
			addChild(textArea);
			
			
//			addEventListener(Event.RESIZE,
//			function ():void {
//				label.width = (width * 0.3);
//				textArea.width = (width * 0.5);
//				textArea.percentWidth
//			},false,0,true);
		}
	}
}