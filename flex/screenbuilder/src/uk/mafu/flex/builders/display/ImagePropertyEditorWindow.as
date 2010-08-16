package uk.mafu.flex.builders.display
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import uk.mafu.flex.util.Util;
	import mx.binding.utils.BindingUtils;
	import mx.binding.utils.ChangeWatcher;
	import mx.containers.BoxDirection;
	import mx.containers.HBox;
	import mx.containers.Panel;
	import mx.containers.VBox;
	import mx.controls.Button;
	import mx.controls.CheckBox;
	import mx.controls.Label;
	import mx.controls.RichTextEditor;
	import mx.controls.TextArea;
	import mx.events.PropertyChangeEvent;
	import mx.managers.PopUpManager;
	
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.events.image.ImageEditEvent;
	[Event(name="propertiesModified", type="uk.mafu.loon.events.image.ImageEditEvent")]
	public class ImagePropertyEditorWindow extends Panel
	{
		private var buttonBox:Panel;
		private var saveButton:Button;
		private var closeButton:Button;
		private var fieldsBox:VBox;
		private var imageLink:ImageLink;
		
		public function ImagePropertyEditorWindow(imageLink:ImageLink) {
			title = "Edit image properties";
			alpha = 255;
			this.imageLink = imageLink;
			ChangeWatcher.watch(imageLink,"title",handle_changed);
			ChangeWatcher.watch(imageLink,"text",handle_changed);
			ChangeWatcher.watch(imageLink,"caption",handle_changed);
			ChangeWatcher.watch(imageLink,"excite",handle_changed);
			ChangeWatcher.watch(imageLink,"meta",handle_changed);
			alpha = 1;
			addFields();
			addButtonBox();
		}
	
		private function handle_changed(evt:PropertyChangeEvent):void {
			dispatchEvent(new ImageEditEvent(ImageEditEvent.PROPERTIES_MODIFIED,null,imageLink));
			Util.debug("something changed on imagelink");
		}
	
		private function addFields():void{
			fieldsBox = new VBox();
			fieldsBox.addChild(createTitleRow());
			fieldsBox.addChild(createTextRow());
			fieldsBox.addChild(createCaptionRow());
			fieldsBox.addChild(createExciteRow());
			fieldsBox.addChild(createMetaRow());
			addChild(fieldsBox);
		}
 	
		private function  createTitleRow():HBox {
			var titleRow:HBox = new HBox();
			var label:Label = new Label();
			label.text = "Title";
			titleRow.addChild(label);
			var text:TextArea = new TextArea();
			BindingUtils.bindProperty(text,"text",imageLink,"title");
			BindingUtils.bindProperty(imageLink,"title",text,"text");
			titleRow.addChild(text);
			return titleRow;
		}
		
		private function  createMetaRow():HBox {
			var titleRow:HBox = new HBox();
			var label:Label = new Label();
			label.text = "Meta";
			titleRow.addChild(label);
			var metaText:TextArea = new TextArea();
			BindingUtils.bindProperty(metaText,"text",imageLink,"meta");
			BindingUtils.bindProperty(imageLink,"meta",metaText,"text");
			titleRow.addChild(metaText);
			return titleRow;
		}
		
		private function createCaptionRow():HBox {
			var titleRow:HBox = new HBox();
			var label:Label = new Label();
			label.text = "Caption";
			titleRow.addChild(label);
			var metaText:TextArea = new TextArea();
			BindingUtils.bindProperty(metaText,"text",imageLink,"caption");
			BindingUtils.bindProperty(imageLink,"caption",metaText,"text");
			titleRow.addChild(metaText);
			return titleRow;
		}
		
		private function  createTextRow():HBox {
			var textRow:HBox = new HBox();
			var label:Label = new Label();
			label.text = "Text";
			textRow.addChild(label);
			var text:TextArea = new TextArea();
			BindingUtils.bindProperty(text,"text",imageLink,"text");
			BindingUtils.bindProperty(imageLink,"text",text,"text");
			textRow.addChild(text);
			return textRow;
		} 
		
		private function  createExciteRow():HBox {
			var exciteRow:HBox = new HBox();
			var label:Label = new Label();
			label.text = "Excite Image";
			exciteRow.addChild(label);
			var checkbox:CheckBox = new CheckBox();
			checkbox.selected = imageLink.excite;
			checkbox.addEventListener(Event.CHANGE,function(e:*):void {
				imageLink.excite = !(imageLink.excite);			
			});
			exciteRow.addChild(checkbox);
			return exciteRow;
		} 
	
		private function save_handler(e:*):void{
			
		}
		
		private function doClose(e:*):void{
			PopUpManager.removePopUp(this);
		}
	
		private function addButtonBox():void{
			buttonBox = new Panel();
			buttonBox.layout = "horizontal";
			buttonBox.percentWidth = 100;
			closeButton = new Button();
			closeButton.addEventListener(MouseEvent.CLICK,doClose);
			closeButton.label = "Close";
			buttonBox.addChild(closeButton);	
			addChild(buttonBox);
		}
	}
}