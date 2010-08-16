package uk.mafu.flex.builders
{
	import mx.collections.ArrayCollection;
	import mx.containers.Accordion;
	import mx.controls.Alert;
	import mx.events.CloseEvent;
	import org.swizframework.Swiz;
	import uk.mafu.flex.builders.display.EntityContainer;
	import uk.mafu.flex.builders.display.EntityPage;
	import uk.mafu.flex.builders.display.SearchPage;
	import uk.mafu.flex.util.Assert;
	import uk.mafu.loon.IConfiguration;
	import uk.mafu.loon.IPluginLoader;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.events.ApplicationErrorEvent;
	import uk.mafu.loon.events.CreateEntityEvent;
	import uk.mafu.loon.events.DeleteEntityEvent;
	import uk.mafu.loon.events.EntityLoadedEvent;
	import uk.mafu.loon.events.LoadEntityEvent;
	import uk.mafu.loon.events.UpdatePanelEvent;

	public class ScreenRenderer
	{
		private var service:IService;
		private var configuration:IConfiguration;
		
		private var __executionQueue:ArrayCollection = new ArrayCollection(new Array());
		private var accordian:Accordion;

		public function ScreenRenderer(loader:IPluginLoader,accordion:Accordion){
			this.accordian = accordion;
			this.service = loader.getRemoteService();
			this.configuration = loader.getConfiguration();
			Assert.notNull(this.accordian,"accordian");
			Assert.notNull(this.service,"service");
			Swiz.addEventListener(LoadEntityEvent.NAME,__handle__LoadEntityEvent);
			Swiz.addEventListener(EntityLoadedEvent.NAME,__handle__EntityLoadedEvent);
			Swiz.addEventListener(UpdatePanelEvent.NAME,__handle__UpdatePanelEvent);
			Swiz.addEventListener(CreateEntityEvent.NAME,__handle__CreateEntityEvent);
			Swiz.addEventListener(DeleteEntityEvent.NAME,__handle__DeleteEntityEvent);
			Swiz.addEventListener(ApplicationErrorEvent.NAME,__handleApplicationErrorEvent);
		}

		public function buildEditScreen(data:Object):void {
			var page:EntityPage = new EntityPage(service,configuration,data,accordian);
			this.accordian.addChild(page);
			this.accordian.selectedChild = page;
			page.render();
		}
		
		public function buildSearchScreen(clazz:Class):void {
			
			//Carry out a check to see if there is any unsaved entity in the current editor.
			var cancelled:Boolean = false;
			for each (var test:EntityContainer in accordian.getChildren()) {
				if(test.hasExecutions()){
					Alert.show("You have made changes but not saved them. Are you sure you want to continue?","Discard Current Entity",(Alert.YES|Alert.NO),null,function(c:CloseEvent):void{
						if(c.detail == Alert.YES) {
							cancelled = true;
						}
					});
				}	
			}
			if(cancelled == true) {
				return;
			}
			//Ok, so we are commited to displaying the search screen, even if it means discarding the current entity...			
			if(cancelled == false) {
				var page:SearchPage = new SearchPage(service,clazz,accordian);
				for each (var container:EntityContainer in accordian.getChildren()) {
					container.destroy();	
				}
				this.accordian.removeAllChildren();
				this.accordian.addChild(page);
				this.accordian.selectedChild = page;
				page.render();
			}
		}
		
		private function __handleApplicationErrorEvent(evt:ApplicationErrorEvent):void{
			Alert.show(evt.faultType.toString());
		}
		
		private function __handle__LoadEntityEvent(evt:LoadEntityEvent):void{
			this.service.load(evt.clazz,evt.pk);		
			evt.stopPropagation();
		}
		
		private function __handle__EntityLoadedEvent(evt:EntityLoadedEvent):void {
			buildEditScreen(evt.result);
			evt.stopPropagation();
		}
		
		private function __handle__UpdatePanelEvent(evt:UpdatePanelEvent):void {
			buildSearchScreen(evt.clazz);
			evt.stopPropagation();
		}
		
		private function __handle__CreateEntityEvent(evt:CreateEntityEvent):void {
			buildEditScreen(new evt.clazz);
			evt.stopPropagation();
		}
		
		private function __handle__DeleteEntityEvent(evt:DeleteEntityEvent):void {
			this.service.remove(evt.clazz,evt.pk);
		}
	}
}