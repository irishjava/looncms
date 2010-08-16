package uk.mafu.loon.config.maverick
{
	import uk.mafu.domain.maverick.ArchiveItem;
	import uk.mafu.domain.maverick.Award;
	import uk.mafu.domain.maverick.Campaign;
	import uk.mafu.domain.maverick.News;
	import uk.mafu.domain.maverick.Person;
	import uk.mafu.domain.maverick.Press;
	import uk.mafu.domain.maverick.Project;
	import uk.mafu.domain.maverick.ProjectCategory;
	import uk.mafu.domain.maverick.Role;
	import uk.mafu.domain.maverick.Showreel;
	import uk.mafu.domain.maverick.Website;
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
	
	public class MaverickLoader extends AbstractPluginLoader
	{
		override public function getTitle():String{
			return "MAVERICK CMS";
		}
		
 		public function MaverickLoader(){
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
			ArchiveItem,Award,Campaign,News,Person,Role,Press,Project,ProjectCategory,Showreel,Website
			,LoonImage,ImageLink,LoonPdf,PdfLink,LoonVideo,VideoLink];
		}
	 
		override public function getSidebarTree():Array{
			var root:Array = new Array();
			root.push(new Leaf("Showreels",Showreel));
			root.push(new Leaf("Projects",Project));
			root.push(new Leaf("Project Categories",ProjectCategory));
			root.push(new Leaf("Campaigns",Campaign));
			root.push(new Leaf("News",News));
			root.push(new Leaf("Press",Press));
			root.push(new Leaf("Awards",Award));
			root.push(new Leaf("People",Person));
			root.push(new Leaf("Roles",Role));
			root.push(new Leaf("Archive Items",ArchiveItem));
			root.push(new Leaf("Website",Website));
 			return root;
		}
	
		override public function getRemoteService():IService{
			return new LoonService(getConfiguration());
		}
 	}
}