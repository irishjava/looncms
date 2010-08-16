package uk.mafu.flex.builders.display
{
	import flash.events.Event;
	
	import mx.binding.utils.BindingUtils;
	import mx.containers.HBox;
	import mx.controls.Label;
	import mx.controls.RichTextEditor;
	import mx.core.UIComponent;
	import uk.mafu.flex.util.Util;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.flex.meta.ROW;
	import uk.mafu.loon.events.EntityModifiedEvent;

	public class RichTextWidget extends HBox
	{
		
		private var textLabel:Label;
		private var textArea:RichTextEditor;
		
		public function RichTextWidget(entity:Object,metaRow:ROW,parentContainer:UIComponent)
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

			textArea = new RichTextEditor();
			
			textArea.percentWidth = 65;
			textArea.height = 20 * metaRow.lines;
			textArea.styleName = "textGroupTextArea";
			
			//Bind from widget to the data
			BindingUtils.bindProperty(textArea, "htmlText",data, metaRow.name);
			//Bind from data to the widget
			BindingUtils.bindProperty(data, metaRow.name ,textArea, "htmlText");
			
			textArea.addEventListener(Event.CHANGE,function (evt:Event):void{
				Util.debug("richtext.event.change: data='" + data.toString()  + "'");
				Swiz.dispatchEvent(new EntityModifiedEvent(data));
			});
			addChild(textArea);
		}
	}
}