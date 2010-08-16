package uk.mafu.kwa.config
{
	import uk.mafu.flex.util.ContextUtil;
	import uk.mafu.kwa.domain.Award;
	import uk.mafu.kwa.domain.Client;
	import uk.mafu.kwa.domain.Excite;
	import uk.mafu.kwa.domain.HomePageSlide;
	import uk.mafu.kwa.domain.JobPosting;
	import uk.mafu.kwa.domain.News;
	import uk.mafu.kwa.domain.Person;
	import uk.mafu.kwa.domain.Press;
	import uk.mafu.kwa.domain.Project;
	import uk.mafu.kwa.domain.ProjectCategory;
	import uk.mafu.kwa.domain.ProjectSlide;
	import uk.mafu.kwa.domain.Website;
	import uk.mafu.loon.AbstractPluginLoader;
	import uk.mafu.loon.IConfiguration;
	import uk.mafu.loon.INode;
	import uk.mafu.loon.IService;
	import uk.mafu.loon.service.LoonService;
	import uk.mafu.loon.tree.Branch;
	import uk.mafu.loon.tree.Leaf;
	
	public class KwaLoader extends AbstractPluginLoader
	{
		override public function getTitle():String {
			return "KWA CMS";
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
			return [Award,Client,Excite,JobPosting,Person,Press,News,Project,HomePageSlide,ProjectCategory,ProjectSlide,Website];
		}
	 
		override public function getSidebarTree():Array{
			var root:Array = new Array();
			
			var projectsRoot:Branch = new Branch("Projects");
		
			root.push(projectsRoot);
			projectsRoot.addItem(new Leaf("Project",Project));
			projectsRoot.addItem(new Leaf("ProjectCategory",ProjectCategory));
			projectsRoot.addItem(new Leaf("Project Slides",ProjectSlide));
			projectsRoot.addItem(new Leaf("Homepage Slides",HomePageSlide ));
			
			var practiceRoot:Branch = new Branch("Practice");
			root.push(practiceRoot);
			
			practiceRoot.addItem(new Leaf("People",Person));
			practiceRoot.addItem(new Leaf("Award",Award));
			practiceRoot.addItem(new Leaf("Client",Client));
			practiceRoot.addItem(new Leaf("Excite",Excite));
			practiceRoot.addItem(new Leaf("JobPosting",JobPosting));
			practiceRoot.addItem(new Leaf("Press",Press));
			practiceRoot.addItem(new Leaf("News",News));
			
			root.push(new Leaf("Website",Website));
			return root;
		}
	
		override public function getRemoteService():IService{
			return new LoonService(getConfiguration());
		}
 	}
}