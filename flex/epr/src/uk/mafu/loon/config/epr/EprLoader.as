package uk.mafu.loon.config.epr
{
	import uk.mafu.domain.epr.Award;
	import uk.mafu.domain.epr.News;
	import uk.mafu.domain.epr.Person;
	import uk.mafu.domain.epr.Project;
	import uk.mafu.domain.epr.ProjectCategory;
	import uk.mafu.domain.epr.ProjectLink;
	import uk.mafu.domain.epr.Website;
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
	import uk.mafu.loon.dto.ImageThumb;
	import uk.mafu.loon.service.LoonService;
	import uk.mafu.loon.tree.Branch;
	import uk.mafu.loon.tree.Leaf;
	
	public class EprLoader extends AbstractPluginLoader
	{
		
		override public function getTitle():String {
			return "EPR CMS";
		}
		
 		override public function EprLoader(){
			for each( var clazz:Class in getClasses()) {
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
				Award,News,Person,
				Project,ProjectCategory,
				ProjectLink,Website,
				ImageThumb,
				LoonImage,ImageLink,
				LoonPdf,PdfLink,
				LoonVideo,VideoLink
			];
		}
	 
		override public function getSidebarTree():Array{
			var root:Array = new Array();
			root.push(new Leaf("Award",Award));
			root.push(new Leaf("Project Links",ProjectLink));
			root.push(new Leaf("News",News));
			var project:Branch = new Branch("Project");
			root.push(project);
			project.addItem(new Leaf("Projects",Project));
			project.addItem(new Leaf("Categories",ProjectCategory));
			root.push(new Leaf("People",Person));
			root.push(new Leaf("Website",Website));
			return root;
		}
		
		override public function getRemoteService():IService{
			return new LoonService(getConfiguration());
		}
 	}
}