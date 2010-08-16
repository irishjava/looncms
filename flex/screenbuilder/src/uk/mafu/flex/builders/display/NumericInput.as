package uk.mafu.flex.builders.display
{
	import flash.events.Event;
	
	import mx.binding.utils.BindingUtils;
	import mx.containers.HBox;
	import mx.controls.Label;
	import mx.controls.TextArea;
	import mx.core.UIComponent;
	import mx.validators.NumberValidator;
	import uk.mafu.flex.meta.ROW;
	import org.swizframework.Swiz;
	import uk.mafu.flex.util.Util;
	
 
	import uk.mafu.loon.events.EntityModifiedEvent;
	
	public class NumericInput extends HBox
	{
		public function NumericInput(entity:Object,metaRow:ROW,parentContainer:UIComponent)
		{
			super();
			parentContainer.addChild(this);
			this.data = entity;
			
			var label:Label = new Label();
			label.width = 95; 
			label.height = 20 ;
			label.truncateToFit = true;
			label.text = metaRow.label;
			
			addChild(label);
			addEventListener(Event.RESIZE,
			function ():void {
				label.width = (width * 0.3);
			});

			var textArea:TextArea = new TextArea();
			textArea.height = 20 * metaRow.lines;
			
			var validator:NumberValidator = new NumberValidator();
			validator.source= textArea;
			validator.property = "text";	
			//Bind from widget to the data
			BindingUtils.bindProperty(textArea, "text",data, metaRow.name);
			//Bind from data to the widget
			BindingUtils.bindProperty(data, metaRow.name ,textArea, "text");
			if(textArea.text == "NaN") {
				textArea.text = "";
			}
			textArea.addEventListener(Event.CHANGE,function (evt:Event):void{
				Util.debug("textarea:change:data is" + data.toString());
				Swiz.dispatchEvent(new EntityModifiedEvent(data));
			},false,0,true);
			addChild(textArea);
		}
	}
}