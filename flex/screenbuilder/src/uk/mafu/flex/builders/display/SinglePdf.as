package uk.mafu.flex.builders.display
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.FileFilter;
	import flash.net.FileReference;
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	import flash.utils.ByteArray;
	
	import mx.binding.utils.BindingUtils;
	import mx.containers.BoxDirection;
	import mx.containers.ControlBar;
	import mx.containers.Grid;
	import mx.containers.GridItem;
	import mx.containers.GridRow;
	import mx.containers.HBox;
	import mx.containers.Panel;
	import mx.containers.VBox;
	import mx.controls.Button;
	import mx.controls.Label;
	import mx.controls.LinkButton;
	import mx.controls.TextArea;
	import mx.controls.TextInput;
	import mx.core.UIComponent;
	
	import org.swizframework.Swiz;
	
	import uk.mafu.flex.meta.ROW;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.IUploader;
	import uk.mafu.loon.domain.data.LoonPdf;
	import uk.mafu.loon.domain.data.PdfLink;
	import uk.mafu.loon.events.EditOneToOneRelationshipEvent;
	import uk.mafu.loon.events.EntitySavedEvent;
	import uk.mafu.loon.events.OneToOneRelationshipLoadedEvent;
	import uk.mafu.loon.executions.EditSinglePdfExecution;
	

	public class SinglePdf extends EntityContainer implements IUploader
	{
		private var panel:Panel;
		private var relationship_name:String;
		private var row:ROW;
		private var textArea:TextArea;
		
		//DISPLAY ITEMS...
		private var outerHbox:HBox;
		private var nameLabel:Label;
		private var innerVbox:VBox;
		private var labelVbox:VBox;
		
		//PropsGrid 
		private var propsGrid:Grid;
		
		//File Title 
		private var titleRow:GridRow;
		private var titleGridItem1:GridItem;
		private var titleLabel:Label;
		private var titleGridItem2:GridItem;
		private var titleValueText:TextInput;
	
		//Download link 
		private var downloadRow:GridRow;
		private var downloadGridItem1:GridItem;
		//			
		private var downloadLabel:Label;
		private var downloadGridItem2:GridItem;
		private var downloadValueLabel:LinkButton;
		//
		private var buttonBar:ControlBar;
		private var addButton:Button;
		//
		private var deleteButton:Button;
		
		private function __handle__OneToOneLoadedEvent(evt:OneToOneRelationshipLoadedEvent):void {
			if(Util.isSameEntity(evt.parent_clazz,Util.getClass(entityParent)) && 
				(evt.relationship == relationship_name)) {
					if(evt.result.data != null){
						PdfLink(data).pk =  PdfLink(evt.result.data).pk;
						PdfLink(data).pdfId =  PdfLink(evt.result.data).pdfId;
						PdfLink(data).title =  PdfLink(evt.result.data).title;
//						pdfLink.pk = res.pk;
//						pdfLink.title = res.title;
//						pdfLink.pdfId = res.pdfId;
						titleValueText.enabled =  true;
						downloadValueLabel.enabled =  true;
						clearExecutionQueue();
					}
				}
		}
					
		public function SinglePdf(service:IService,entityParent:Object,row:ROW,parentContainer:UIComponent){
			super(service,row.type,Util.getClass(entityParent),row.name);
			this.row = row;
			this.relationship_name = row.name;
			this.entityParent = entityParent;
			parentContainer.addChild(this);
			this.relationship_name  = row.name;
			Swiz.addEventListener(OneToOneRelationshipLoadedEvent.NAME,__handle__OneToOneLoadedEvent,false,0,true);
			this.data = new PdfLink;
			render();
			//disable if parent is not already persisted...
			if(Util.isTransient(this.entityParent)) {
				enabled = false;
			}
			else{
			service.loadOneToOne(
							parent_clazz,
							clazz,
							relationship_name,
							Util.getPrimaryKey(entityParent),
							["pk","title","pdfId"],false
							);
			}
			//ChangeWatcher.watch(data,"title",function (e:*):void { trace("text changed"); });
		}
		
		private function get pdfLink():PdfLink {
			if(this.data == null)
			{
				this.data = new PdfLink();
			}
			return this.data as PdfLink;
		}
		
		//Upload complete....
		public function uploadCompleted(pdfId:Object):void {
			pdfLink.pdfId  = pdfId;
			titleValueText.enabled = true;
			downloadValueLabel.enabled = true;
			addToExecutionQueue(new EditSinglePdfExecution(data as PdfLink,relationship_name));
				Swiz.dispatchEvent(
					new EditOneToOneRelationshipEvent(
							data,relationship_name,Util.getClass(entityParent),Util.getPrimaryKey(entityParent)
						)
				);
		}
		
		public override function render():void {
			//
			direction = BoxDirection.HORIZONTAL;
			//
			outerHbox = new HBox();
			nameLabel = new Label()
			nameLabel.text = row.label;
			outerHbox.addChild(nameLabel);
			//
			innerVbox = new VBox();
			outerHbox.addChild(innerVbox);
			//
			labelVbox = new VBox();
			
			//PropsGrid 
			propsGrid = new Grid();
			
			//File Title 
			titleRow = new GridRow();
			titleGridItem1 = new GridItem();
			titleRow.addChild(titleGridItem1);
			
			titleLabel = new Label();
			titleGridItem1.addChild(titleLabel);
			titleLabel.text = "Title:";
			
			titleGridItem2 = new GridItem();
			titleRow.addChild(titleGridItem2);
			
			titleValueText = new TextInput();
			titleGridItem2.addChild(titleValueText);
			titleValueText.width = 150;
			titleValueText.enabled = false;
			propsGrid.addChild(titleRow);

			//End File Title 
			
			//Bind from widget to the data
			BindingUtils.bindProperty(titleValueText,"text",data,"title" );
			//Bind from data to the widget
			BindingUtils.bindProperty(data,"title",titleValueText, "text");
			titleValueText.addEventListener(Event.CHANGE,function (evt:Event):void{
				Util.debug("single pdf:event.change data='" + data + "'");
				addToExecutionQueue(new EditSinglePdfExecution(data,relationship_name));
				Swiz.dispatchEvent(
					new EditOneToOneRelationshipEvent(
							data,relationship_name,Util.getClass(entityParent),Util.getPrimaryKey(entityParent)
						)
				);
			});

			//Download link 
			downloadRow  = new GridRow();
			downloadGridItem1 = new GridItem();
			downloadRow.addChild(downloadGridItem1);
			//			
			downloadLabel = new Label();
			downloadGridItem1.addChild(downloadLabel);
			downloadLabel.text = "download file:";
			//			
			downloadGridItem2 = new GridItem();
			downloadRow.addChild(downloadGridItem2);
			
			downloadValueLabel = new LinkButton();
			downloadGridItem2.addChild(downloadValueLabel);
			downloadValueLabel.label  = "Click to view";
			downloadValueLabel.enabled  = false;
			
			downloadValueLabel.addEventListener(MouseEvent.CLICK,function (e:*):void {
				var urlrequst:URLRequest = new URLRequest(service.configuration.getPdfPreviewUrl()  + pdfLink.pdfId);
				navigateToURL(urlrequst,"_self");
			});
			
			propsGrid.addChild(downloadRow);
			//End File Size 
			//End PropsGrid
			innerVbox.addChild(propsGrid);
			//
			buttonBar = new ControlBar();
			addButton = new Button();
			addButton.label = "Add";
			addButton.addEventListener(MouseEvent.CLICK ,addButtonClicked);
			//
			deleteButton = new Button();
			deleteButton.label = "Delete";
			deleteButton.addEventListener(MouseEvent.CLICK,deleteButtonClicked);
			//
			buttonBar.addChild(addButton);
			buttonBar.addChild(deleteButton);
			//
			innerVbox.addChild(buttonBar);
			//
			addChild(outerHbox);
			
			
		}
		
		
		override protected function reload(evt:EntitySavedEvent):void {
			
		}
		
		private function getThis():SinglePdf {
			return this;
		}
		
		private function addButtonClicked(evt:*):void {
			var f:FileReference = new FileReference();
			f.addEventListener(Event.COMPLETE,function (e:Event):void {
				var byteArray:ByteArray =new ByteArray();
				Util.debug("addButtonClicked" + f.size.toString());
				Util.debug("addButtonClicked" + f.name);
				byteArray.writeBytes(f.data,0);//,f.data.length -1
				Util.debug("addButtonClicked" + byteArray.length);
				titleValueText.enabled = true;
				var vid:LoonPdf = new LoonPdf();
				vid.filename= f.name;
				vid.data = byteArray;
				service.uploadPdf(vid,getThis());
			});
			f.addEventListener(Event.SELECT,function (e:Event):void {
				f.load();
				
			});
			f.browse([new FileFilter("Pdf Files (*.pdf)", "*.pdf", "PDF")]);	
		}
			
		private function deleteButtonClicked(evt:*):void {
			pdfLink.pdfId = -1;
			titleValueText.text = "";
			titleValueText.enabled = false;
			downloadValueLabel.enabled = false;
			addToExecutionQueue(new EditSinglePdfExecution(data,relationship_name,true));
				Swiz.dispatchEvent(
					new EditOneToOneRelationshipEvent(
							data,relationship_name,Util.getClass(entityParent),Util.getPrimaryKey(entityParent)
						)
				);
		}
		
		override protected function removeEventListeners():void {
			Swiz.removeEventListener(OneToOneRelationshipLoadedEvent.NAME,__handle__OneToOneLoadedEvent);
		}
		
		override protected function removeWidgets():void{
			this.removeAllChildren();
		}
	}
}