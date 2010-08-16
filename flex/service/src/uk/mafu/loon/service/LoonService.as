package uk.mafu.loon.service
{
	import flash.events.HTTPStatusEvent;
	import flash.events.MouseEvent;
	import flash.events.ProgressEvent;
	
	import hessian.client.HessianOperation;
	import hessian.mxml.HessianService;
	
	import mx.containers.Box;
	import mx.containers.Canvas;
	import mx.controls.Alert;
	import mx.controls.LinkButton;
	import mx.core.Application;
	import mx.core.IDataRenderer;
	import mx.core.IUIComponent;
	import mx.managers.PopUpManager;
	import mx.rpc.AbstractService;
	import mx.rpc.AsyncToken;
	
	import uk.mafu.flex.util.Assert;
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.IConfiguration;
	import uk.mafu.loon.ISaveListener;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.IUploader;
	import uk.mafu.loon.domain.data.LoonAudio;
	import uk.mafu.loon.domain.data.LoonPdf;
	import uk.mafu.loon.domain.data.LoonVideo;
	import uk.mafu.loon.events.image.ImageUploadEvent;
	import uk.mafu.loon.responder.DeleteSingleLinkResponder;
	import uk.mafu.loon.responder.GetAllResponder;
	import uk.mafu.loon.responder.LoadImageResponder;
	import uk.mafu.loon.responder.LoadImageThumbResponder;
	import uk.mafu.loon.responder.LoadManyToManyResponder;
	import uk.mafu.loon.responder.LoadOneToManyResponder;
	import uk.mafu.loon.responder.LoadOneToOneResponder;
	import uk.mafu.loon.responder.LoadResponder;
	import uk.mafu.loon.responder.RemoveImageResponder;
	import uk.mafu.loon.responder.RemoveResponder;
	import uk.mafu.loon.responder.SaveManyToManyResponder;
	import uk.mafu.loon.responder.SaveOneToManyImagesResponder;
	import uk.mafu.loon.responder.SaveOneToManyResponder;
	import uk.mafu.loon.responder.SaveOneToOneResponder;
	import uk.mafu.loon.responder.SaveResponder;
	import uk.mafu.loon.responder.SaveSingleLinkResponder;
	import uk.mafu.loon.responder.UploadResponder;
	
	public class LoonService  extends AbstractService implements IService  
	{
		[Bindable]
		private var remoteUrl:String;
		
		[Bindable]
		private var config:IConfiguration;
		
		public function LoonService(config:IConfiguration):void {
			this.config = config;
		}
	
		public function get configuration():IConfiguration {
			return IConfiguration(config);
		}
		
		public function getAll(clazz:Class,constraint:Array):void{
            Util.debug("getAll " + clazz + " constraint " + constraint);
            var class_name:String = Util.getJavaClassnameByClass(clazz);
			var operation:HessianOperation  = HessianOperation(getProxy().getOperation("getAll"));
			operation.addEventListener(ProgressEvent.PROGRESS,function(e:ProgressEvent):void {
				Util.debug("loaded " + e.bytesLoaded + " out of total " + e.bytesTotal);
			});
			var tok:AsyncToken =  operation.send(class_name,constraint);
			tok.addResponder(new GetAllResponder(clazz));
		}
		 
		public function save(target:Object,listener:ISaveListener = null):void{
			Util.debug("save(target:Object=" + target +  "");
			var tok:AsyncToken =  getProxy().getOperation("save").send(target);
			tok.addResponder(new SaveResponder(listener));
		}
			
		public function remove(clazz:Class,pk:Object):void{
			var tok:AsyncToken =  getProxy().getOperation("remove").send(
			Util.getJavaClassnameByClass(clazz),pk);
			tok.addResponder(new RemoveResponder(clazz,pk));
		}
		
		
		public function removeImage(pk:Object):void{
			var tok:AsyncToken =  getProxy().getOperation("removeImage").send(pk);
			tok.addResponder(new RemoveImageResponder(pk));
		}
			
		public function removeVideo(pk:Object):void{
		}
		
		public function removePdf(pk:Object):void{}
		
		public function saveOneToOneRelationship(
			parent:Class,
			child:Class,
			parent_pk:Object,
			child_pk:Object,
			relationship:String):void {
			
			var tok:AsyncToken =  getProxy().getOperation("saveOneToOne").
			send(Util.getJavaClassname(parent),
				Util.getJavaClassname(child),
				parent_pk,
				child_pk,
				relationship);
				
			tok.addResponder(new SaveOneToOneResponder(
													parent,
													child,
													parent_pk,
													child_pk,
													relationship)
													);
		}
		
		public function saveOneToMany(
				parent_clazz:Class,
				parentId:Object,
				relationship:String,
				children:Array
				):void {
			
			var tok:AsyncToken =  getProxy().getOperation("saveOneToMany").
				send(
				Util.getJavaClassnameByClass(parent_clazz),
				parentId,
				relationship,
				children);	
							
			tok.addResponder(new SaveOneToManyResponder(parent_clazz,
				parentId,
				relationship,
				children));
		}
		
		
		public function saveManyToMany(
				parent_clazz:Class,
				parentId:Object,
				relationship:String,
				children:Array
				):void {
			
			var tok:AsyncToken =  getProxy().getOperation("saveManyToMany").
				send(
				Util.getJavaClassnameByClass(parent_clazz),
				parentId,
				relationship,
				children);	
							
			tok.addResponder(new SaveManyToManyResponder(parent_clazz,
				parentId,
				relationship,
				children));
		}
		
		public function saveOneToManyImages(
				parent_clazz:Class,
				parentId:Object,
				relationship:String,
				children:Array
				):void {
			
			var tok:AsyncToken =  getProxy().getOperation("saveOneToManyImages").
				send(
				Util.getJavaClassnameByClass(parent_clazz),
				parentId,
				relationship,
				children);	
							
			tok.addResponder(new SaveOneToManyImagesResponder(parent_clazz,
				parentId,
				relationship,
				children));
		}
		
		public function loadOneToOne(parent_clazz:Class,
			child_clazz:Class,
			relationship:String,
			parentId:Object,fields:Array,withOptions:Boolean = true):void{
            Util.debug("loadOneToOne, parent_clazz" + parent_clazz + " child_clazz " + child_clazz);
			var tok:AsyncToken =  getProxy().getOperation("loadOneToOne").send(
			Util.getJavaClassnameByClass(
			parent_clazz),
			Util.getJavaClassnameByClass(
			child_clazz),
			relationship,parentId,fields,withOptions);
			Util.debug("sent the loadOneToOne request to the server");
			tok.addResponder(new LoadOneToOneResponder(parent_clazz,parentId,relationship) );
		}
		
		public function loadOneToMany(
			parent_clazz:Class,
			child_clazz:Class,
			relationship:String,
			parentId:Object,fields:Array):void{
	        Util.debug("loadOneToMany, parent_clazz" + parent_clazz + " child_clazz " + child_clazz);
			var tok:AsyncToken = getProxy().getOperation("loadOneToMany").send(
				Util.getJavaClassnameByClass(
				parent_clazz),
				Util.getJavaClassnameByClass(child_clazz),
				relationship,parentId,fields);
			Util.debug("sent the loadOneToMany request to the server");
			tok.addResponder(new LoadOneToManyResponder(parent_clazz,parentId,relationship) );
		}
		
		
		
		
		public function loadManyToMany(
			parent_clazz:Class,
			child_clazz:Class,
			relationship:String,
			parentId:Object,fields:Array):void{
	        Util.debug("loadManyToMany, parent_clazz" + parent_clazz + " child_clazz " + child_clazz);
			var tok:AsyncToken = getProxy().getOperation("loadManyToMany").send(
			//var tok:AsyncToken = this.service.getOperation("loadOneToMany").send(
			Util.getJavaClassnameByClass(
			parent_clazz),
			Util.getJavaClassnameByClass(
			child_clazz),
			relationship,parentId,fields);
			Util.debug("sent the loadManyToMany request to the server");
			tok.addResponder(new LoadManyToManyResponder(parent_clazz,parentId,relationship) );
		}
		
		private function getProxy():HessianService {
			var localService:HessianService = new HessianService();  //new HessianService(this.config.getServiceUrl());
			localService.destination  = this.config.getServiceUrl();
			localService.showBusyCursor = true;
			return localService; 
		}
		
		public function load(clazz:Class,id:Object):void {
			Util.debug("load(clazz:Class,=" + clazz+"id:Object=" + id +"");
			getProxy().load.send(Util.getJavaClassnameByClass(clazz),id).addResponder(new LoadResponder());
		}
		
		public function loadImage(pk:Object,caller:Object = null):void {
			Util.debug("loadImage(pk=" + pk +"");
			getProxy().loadImage.send(pk).addResponder(new LoadImageResponder(caller));
		}
		
		public function loadImageThumb(pk:Object,caller:IDataRenderer):void {
			if(ThumbCache.hasThumb(pk)) {
				caller.data = ThumbCache.getThumb(pk).data;
			}
			else{
				Util.debug("loadImage pk='" + pk +"'");
				getProxy().loadImageThumb.send(pk).addResponder(new LoadImageThumbResponder(caller));
			}
		}

		public function createImage(caller:Object):void{
			var uploader:ImageUploader = new ImageUploader(this.configuration,this);
			uploader.addEventListener(ImageUploadEvent.IMAGE_UPLOAD_FAILURE,
				function(e:ImageUploadEvent):void {
					Alert.show("Error uploading image");
				});
			uploader.addEventListener(ImageUploadEvent.IMAGE_UPLOAD_SUCCESS,
				function(evt:ImageUploadEvent):void{
					getProxy().loadImage.send(evt.result.pk).addResponder(new LoadImageResponder(caller));
				}
			);
			uploader.selectFile();
		}
		
		/**
		 * Child should be one of the "link's", imageLink,videoLink or pdfLink..
		 */
		public function saveSingleLink(child:Object,parent:Object,relationship:String):void{
			getProxy().saveSingleLink.send(
				child,
				Util.getJavaClassname(child),
				Util.getJavaClassname(parent),
				Util.getPrimaryKey(parent),
				relationship).addResponder(new SaveSingleLinkResponder());
		}
		
		public function deleteSingleLink(parent:Object,relationship:String):void {
			Assert.instanceOf(parent,Object);
			Assert.instanceOf(relationship,String);
			getProxy().deleteSingleLink.send(
				Util.getJavaClassname(parent),
				Util.getPrimaryKey(parent),
				relationship).addResponder(new DeleteSingleLinkResponder());
		}
		
		public function uploadPdf(loonPdf:Object,caller:IUploader):void {
			Assert.instanceOf(loonPdf,LoonPdf);
			var operation:HessianOperation  = HessianOperation(getProxy().getOperation("uploadPdf"));
			operation.addEventListener(ProgressEvent.PROGRESS,function(e:ProgressEvent):void {
				Util.debug("loaded " + e.bytesLoaded + " out of total " + e.bytesTotal);
			});
			
			operation.addEventListener(HTTPStatusEvent.HTTP_STATUS ,function(e:HTTPStatusEvent):void {
				Util.debug("status :" + e.toString());
			});
		
		
			var popup:IUIComponent = createPopupCanvas(operation); 
			var tok:AsyncToken =  operation.send(loonPdf as LoonPdf);
			tok.addResponder(new UploadResponder(caller,popup));
		}
		
		public function createPopupCanvas(operation:HessianOperation):IUIComponent {
			var c:Canvas = new Canvas();
			c.percentWidth = 100;
			c.percentHeight = 100;
			
				
			var vbox:Box = new Box();
			vbox.setStyle("horizontalAlign","center");
			vbox.setStyle("verticalAlign","middle");
			vbox.percentWidth = 100;
			vbox.percentHeight = 100;
			
			c.addChild(vbox);
			
			var cancelButton:LinkButton = new LinkButton();
			cancelButton.label = "Cancel upload";
			vbox.addChild(cancelButton);
		
			PopUpManager.addPopUp(c,Application(Application.application),true);
			PopUpManager.bringToFront(c);
			
			cancelButton.addEventListener(MouseEvent.CLICK,function (e:*):void {
				PopUpManager.removePopUp(c);
				operation.cancel();
			});
			
			return c;
		}
		
		
		public function uploadVideo(loonVideo:Object,caller:IUploader):void {
			Assert.instanceOf(loonVideo,LoonVideo);
			var operation:HessianOperation  = HessianOperation(getProxy().getOperation("uploadVideo"));
			operation.addEventListener(ProgressEvent.PROGRESS,function(e:ProgressEvent):void {
				Util.debug("loaded " + e.bytesLoaded + " out of total " + e.bytesTotal);
			});
			operation.addEventListener(HTTPStatusEvent.HTTP_STATUS ,function(e:HTTPStatusEvent):void {
				Util.debug("status :" + e.toString());
			});
			
			var popup:IUIComponent = createPopupCanvas(operation); 
			var tok:AsyncToken =  operation.send(loonVideo as LoonVideo);
			tok.addResponder(new UploadResponder(caller,popup));
		}
		
		public function uploadAudio(loonAudio:Object,caller:IUploader):void {
			Assert.instanceOf(loonAudio,LoonAudio);
			var operation:HessianOperation  = HessianOperation(getProxy().getOperation("uploadAudio"));
			
			operation.addEventListener(ProgressEvent.PROGRESS,function(e:ProgressEvent):void {
				Util.debug("loaded " + e.bytesLoaded + " out of total " + e.bytesTotal);
			});
			operation.addEventListener(HTTPStatusEvent.HTTP_STATUS ,function(e:HTTPStatusEvent):void {
				Util.debug("status :" + e.toString());
			});
			
			var popup:IUIComponent = createPopupCanvas(operation); 
			var tok:AsyncToken =  operation.send(loonAudio as LoonAudio);
			tok.addResponder(new UploadResponder(caller,popup));
		}
	}
}