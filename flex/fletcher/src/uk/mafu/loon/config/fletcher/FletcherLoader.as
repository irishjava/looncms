package uk.mafu.loon.config.fletcher
{
	import uk.mafu.domain.fletcher.Press;
	import uk.mafu.domain.fletcher.Project;
	import uk.mafu.domain.fletcher.ProjectCategory;
	import uk.mafu.domain.fletcher.Slide;
	import uk.mafu.domain.fletcher.Website;
	import uk.mafu.flex.util.ContextUtil;
	import uk.mafu.loon.AbstractPluginLoader;
	import uk.mafu.loon.IConfiguration;
	import uk.mafu.loon.INode;
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
	
	public class FletcherLoader extends AbstractPluginLoader
	{
		override public function getTitle():String {
			return "FLETCHER CMS";
		}
		
 		public function FletcherLoader(){
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
			Press,Project,ProjectCategory,Slide,Website,
			 LoonImage,ImageLink,LoonPdf,PdfLink,LoonVideo,VideoLink];
		}
	 
		override public function getSidebarTree():Array{
			var root:Array = new Array();
			root.push(new Leaf("Press",Press));
			var project:Branch = new Branch("Project");
			root.push(project);
			project.addItem(new Leaf("Project",Project));
			project.addItem(new Leaf("Categories",ProjectCategory));
			var config:Branch = new Branch("Config");
			root.push(config);
			config.addItem(new Leaf("Website",Website));
			config.addItem(new Leaf("Slides",Slide));
			return root;
		}
	
		override public function getRemoteService():IService{
			return new LoonService(getConfiguration());
		}
 	}
}