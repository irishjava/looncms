package uk.mafu.flex.builders.display
{
	import flash.events.Event;
	
	import mx.binding.utils.BindingUtils;
	import mx.binding.utils.ChangeWatcher;
	import mx.containers.HBox;
	import mx.controls.Alert;
	import mx.controls.DateField;
	import mx.controls.Label;
	import mx.controls.TextInput;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import uk.mafu.flex.meta.ROW;
	import org.swizframework.Swiz;
	
	
	import uk.mafu.loon.events.EntityModifiedEvent;
	
	public class DateInput extends HBox
	{
		private var dateLabel:Label  ;
		private var dateField:DateField  ;
		private var dateHolder:Date;
	
		public function DateInput(entity:Object,metaRow:ROW,parentContainer:UIComponent)
		{
			super();
			parentContainer.addChild(this);
			this.data = entity;
			
			dateLabel = new Label();
			dateLabel.percentWidth = 30; 
			dateLabel.height = 30 ;
			dateLabel.truncateToFit = true;
			dateLabel.text = metaRow.label;
			
			addChild(dateLabel);
//			addEventListener(Event.RESIZE,
//			function ():void {
//				dateLabel.width = (width * 0.3);
//			});

			var fieldName:String  = metaRow.name;
			
			dateField  = new DateField();
			dateField.selectedDate = data[fieldName] != null ? data[fieldName] : new Date();
			dateField.formatString = "DD/MM/YY";
			dateField.yearNavigationEnabled = true; 
			BindingUtils.bindProperty(data, fieldName , dateField, "selectedDate");
			
			ChangeWatcher.watch(data,fieldName,function (evt:*):void {
				Swiz.dispatchEvent(new EntityModifiedEvent(entity));
			});
			
			addChild(dateField);
		}
	}
}