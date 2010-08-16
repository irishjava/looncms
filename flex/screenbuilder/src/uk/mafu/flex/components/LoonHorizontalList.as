package uk.mafu.flex.components
{
	import mx.collections.ArrayCollection;
	import mx.controls.HorizontalList;
	
	import uk.mafu.loon.IConfiguration;
	import uk.mafu.loon.IConfigurationProvider;
	import uk.mafu.loon.IService;	
	public class LoonHorizontalList extends HorizontalList implements IConfigurationProvider
	{ 
		private var _service:IService;
		
		public function LoonHorizontalList(service:IService){
			super();
			this.minWidth = 300;
			this.maxWidth = 600;
			this.itemRenderer = new ImageItemRenderer(service);
		}
		
		public function get configuration():IConfiguration{
			return this._service.configuration;
		}
		
		public function addItem(item:*):void {
			var provider:ArrayCollection = ArrayCollection(this.dataProvider);
			provider.addItem(item);
		}
		
		public function removeItem(item:*):void {
			var provider:ArrayCollection = ArrayCollection(this.dataProvider);
			provider.removeItemAt(provider.getItemIndex(item));
		}
		
		
	}
}