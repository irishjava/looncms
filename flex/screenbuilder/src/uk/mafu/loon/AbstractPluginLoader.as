package uk.mafu.loon
{
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.LoonImage;
	import uk.mafu.loon.domain.data.LoonPdf;
	import uk.mafu.loon.domain.data.LoonVideo;
	import uk.mafu.loon.domain.data.PdfLink;
	import uk.mafu.loon.domain.data.VideoLink;
	
	public class AbstractPluginLoader implements IPluginLoader
	{
		
		public function getTitle():String{
			throw new Error("Implement in subclass");
		}
		
		public function AbstractPluginLoader(){
			var clazz:Class;
			for each( clazz in getBaseClasses() ) {
				new clazz;
			}
			for each( clazz in getClasses() ) {
				new clazz;
			}	
		}
	
		private function getBaseClasses():Array {
			return [LoonImage,ImageLink,LoonPdf,PdfLink,LoonVideo,VideoLink];
		}

		public function getClasses():Array
		{
			throw new Error("Implement in subclass");
		}
		
		/**
		 * @return an Array, containing INode objects 
		 */
		public function getSidebarTree():Array
		{
			throw new Error("Implement in subclass");
		}
		
		public function getRemoteService():IService
		{
			throw new Error("Implement in subclass");
		}
		
		public function getConfiguration():IConfiguration
		{
			throw new Error("Implement in subclass");
		}
		
		public function getIntroText():String {
			throw new Error("Implement in subclass");
		}
	}
}