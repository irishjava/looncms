package uk.mafu.loon.config.saatchi
{
 
	import uk.mafu.domain.saatchi.ArticleItem;
	import uk.mafu.domain.saatchi.AttitudeItem;
	import uk.mafu.domain.saatchi.Brand;
	import uk.mafu.domain.saatchi.BrandStrategyGroup;
	import uk.mafu.domain.saatchi.BrandStrategyItem;
	import uk.mafu.domain.saatchi.NewsItem;
	import uk.mafu.domain.saatchi.PhilosophyItem;
	import uk.mafu.domain.saatchi.Product;
	import uk.mafu.domain.saatchi.PurposeItem;
	import uk.mafu.domain.saatchi.ViewItem;
	import uk.mafu.domain.saatchi.Website;
	import uk.mafu.flex.util.ContextUtil;
	import uk.mafu.loon.AbstractPluginLoader;
	import uk.mafu.loon.IConfiguration;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.LoonImage;
	import uk.mafu.loon.domain.data.LoonPdf;
	import uk.mafu.loon.domain.data.LoonVideo;
	import uk.mafu.loon.domain.data.PdfLink;
	import uk.mafu.loon.domain.data.VideoLink;
	import uk.mafu.loon.service.LoonService;
	import uk.mafu.loon.tree.Branch;
	import uk.mafu.loon.tree.Leaf;
	
	public class SaatchiLoader extends AbstractPluginLoader 
	{
		
		override public function getTitle():String{
			return "SAATCHI CMS";
		}
		
 		public function SaatchiLoader(){
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
			ArticleItem,
			AttitudeItem,
			Brand,
			BrandStrategyGroup,
			BrandStrategyItem,
			NewsItem,
			PhilosophyItem,
			Product,
			PurposeItem,
			ViewItem,
			Website,
			LoonImage,
			ImageLink,
			LoonPdf,
			PdfLink,
			LoonVideo,
			VideoLink];
		}
	 
		override public function getSidebarTree():Array{
			var root:Array = new Array();
			var newsMedia:Branch = new Branch("News Media");
			root.push(newsMedia);
			
			newsMedia.addItem(new Leaf("Articles",ArticleItem));
			newsMedia.addItem(new Leaf("News Items",NewsItem));
			
			var branding:Branch = new Branch("Branding");
			root.push(branding);
			branding.addItem(new Leaf("Brands",Brand));
			branding.addItem(new Leaf("Brand Strategy Groups",BrandStrategyGroup));
			branding.addItem(new Leaf("Brand Strategy Items",BrandStrategyItem));
			
			root.push(new Leaf("Philosophy",PhilosophyItem));
			root.push(new Leaf("Purpose",PurposeItem));
			
			root.push(new Leaf("Products",Product));
			root.push(new Leaf("View Items",ViewItem));
			root.push(new Leaf("Website",Website));
			root.push(new Leaf("Attitude",AttitudeItem));
			
 			return root;
		}
	
		override public function getRemoteService():IService{
			return new LoonService(getConfiguration());
		}
 	}
}