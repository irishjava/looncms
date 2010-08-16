package uk.mafu.flex.builders
{
	import flash.events.Event;
	
	import mx.containers.Accordion;
	import mx.containers.Canvas;
	import mx.containers.HDividedBox;
	import mx.containers.Panel;
	import mx.core.ScrollPolicy;
	
	import uk.mafu.flex.view.EntityListPanel;
	import uk.mafu.loon.IPluginLoader;
	
	public class LoonApplication extends Panel
	{
		private var loader:IPluginLoader;
		private var rend:ScreenRenderer;
		private var c:Canvas = new Canvas();
		private var h:HDividedBox = new HDividedBox();
		private var sidebar:TitleLessPanel = new TitleLessPanel();
		private var mainbar:TitleLessPanel = new TitleLessPanel();
		private var accordion:Accordion = new Accordion();
	
		public function LoonApplication(pluginLoader:IPluginLoader){
			loader = pluginLoader;
			title = pluginLoader.getTitle();
			percentWidth = 100;
			percentHeight = 100;
			activeState();
		}
		
		private function initialState():void {
		}
		
		private function activeState():void {
			//
			c.horizontalScrollPolicy = ScrollPolicy.OFF;
			c.verticalScrollPolicy = ScrollPolicy.OFF;
			c.percentWidth = 100;
			c.percentHeight = 100;
			c.setStyle("padding","2");
			//
			addChild(c);
			//
			c.addChild(h);
			//
			h.percentHeight  = 100;
			h.percentWidth = 100;
			h.setStyle("padding","2");
			//
			h.addChild(sidebar);
			//
			sidebar.percentWidth = 40;
			sidebar.percentHeight = 100;
			sidebar.horizontalScrollPolicy = ScrollPolicy.OFF;
			sidebar.verticalScrollPolicy = ScrollPolicy.OFF;
			
			//
			h.addChild(mainbar);
			//
			mainbar.percentWidth = 60;
			mainbar.percentHeight = 100;
			mainbar.horizontalScrollPolicy = ScrollPolicy.OFF;
			mainbar.verticalScrollPolicy = ScrollPolicy.OFF;
			//
			mainbar.addChild(accordion);
			accordion.percentWidth = 100;
			c.addEventListener(Event.RESIZE,function():void {
				accordion.height = c.height ;
			});
			accordion.horizontalScrollPolicy = ScrollPolicy.OFF;
			accordion.verticalScrollPolicy = ScrollPolicy.OFF;
			//
			rend = new ScreenRenderer(loader,accordion);
			new EntityListPanel(loader,sidebar);
		}
	}
}