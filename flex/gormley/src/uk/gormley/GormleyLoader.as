package uk.gormley
{
 	import uk.gormley.domain.Art;
 	import uk.gormley.domain.ArtGroup;
 	import uk.gormley.domain.AudioResource;
 	import uk.gormley.domain.Catalogue;
 	import uk.gormley.domain.ImageResource;
 	import uk.gormley.domain.NewsItem;
 	import uk.gormley.domain.PressItem;
 	import uk.gormley.domain.Show;
 	import uk.gormley.domain.Slide;
 	import uk.gormley.domain.StudioView;
 	import uk.gormley.domain.TextResource;
 	import uk.gormley.domain.VideoResource;
 	import uk.gormley.domain.Website;
 	import uk.mafu.flex.util.ContextUtil;
 	import uk.mafu.loon.AbstractPluginLoader;
 	import uk.mafu.loon.IConfiguration;
 	import uk.mafu.loon.IService;
 	import uk.mafu.loon.domain.data.AudioLink;
 	import uk.mafu.loon.domain.data.ImageLink;
 	import uk.mafu.loon.domain.data.LoonAudio;
 	import uk.mafu.loon.domain.data.LoonImage;
 	import uk.mafu.loon.domain.data.LoonPdf;
 	import uk.mafu.loon.domain.data.LoonVideo;
 	import uk.mafu.loon.domain.data.PdfLink;
 	import uk.mafu.loon.domain.data.VideoLink;
 	import uk.mafu.loon.service.LoonService;
 	import uk.mafu.loon.tree.Branch;
 	import uk.mafu.loon.tree.Leaf;
	
	public class GormleyLoader extends AbstractPluginLoader 
	{
		override public function getTitle():String{
			return "GORMLEY CMS";
		}
		
 		public function GormleyLoader(){
			for each( var clazz:Class in getClasses() ) {
				new clazz;
			}
		}
		
		override public function getConfiguration():IConfiguration{
			if(ContextUtil.isLocal()){
				return new LocalConfiguration();
			}
			else {
				return new RemoteConfiguration();
			}
		}
		
		override public function getClasses():Array {
			return [
				Art,
				ArtGroup,
				Catalogue,
				NewsItem,
				PressItem,
				Show,
				Slide,
				StudioView,
				TextResource,
				ImageResource,
				VideoResource,
				AudioResource,
				Website,
				LoonAudio,
				AudioLink,
				LoonImage,
				ImageLink,
				LoonPdf,
				PdfLink,
				LoonVideo,
				VideoLink];
		}
	 
		override public function getSidebarTree():Array{
			var root:Array = new Array();
			var homepage:Branch	= new Branch("Home page");
			root.push(homepage);
			homepage.addItem(new Leaf("Slide",Slide));	
			
			var now:Branch = new Branch("Now");
			root.push(now);
			now.addItem(new Leaf("News Item",NewsItem));
			now.addItem(new Leaf("Studio View",StudioView));
			
			var drawSculp:Branch = new Branch("Drawings/Sculptures");
			root.push(drawSculp);
			drawSculp.addItem(new Leaf("Art Item",Art));
			drawSculp.addItem(new Leaf("Art Group",ArtGroup));
			
			var shows:Branch = new Branch("Shows");
			root.push(shows);
			shows.addItem(new Leaf("Show",Show));
			
			var resources:Branch = new Branch("Resources");
			root.push(resources);
			resources.addItem(new Leaf("Text",TextResource));
			resources.addItem(new Leaf("Image",ImageResource));
			resources.addItem(new Leaf("Video",VideoResource));
			resources.addItem(new Leaf("Audio",AudioResource));
			
			var biblio:Branch = new Branch("Biography");
			root.push(biblio);
			biblio.addItem(new Leaf("Catalogue",Catalogue));
			biblio.addItem(new Leaf("Press Item",PressItem));
			
			root.push(new Leaf("Website",Website));
			return root;
		}
	
		override public function getRemoteService():IService{
			return new LoonService(getConfiguration());
		}
 	}
}
