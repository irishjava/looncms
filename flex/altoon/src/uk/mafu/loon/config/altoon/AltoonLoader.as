package uk.mafu.loon.config.altoon
{
	import uk.mafu.domain.altoon.Award;
	import uk.mafu.domain.altoon.Bibliography;
	import uk.mafu.domain.altoon.Location;
	import uk.mafu.domain.altoon.News;
	import uk.mafu.domain.altoon.Office;
	import uk.mafu.domain.altoon.Person;
	import uk.mafu.domain.altoon.Project;
	import uk.mafu.domain.altoon.ProjectCategory;
	import uk.mafu.domain.altoon.ProjectLink;
	import uk.mafu.domain.altoon.Region;
	import uk.mafu.domain.altoon.Role;
	import uk.mafu.domain.altoon.Service;
	import uk.mafu.domain.altoon.Website;
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
	
	public class AltoonLoader extends AbstractPluginLoader
	{
		
		override public function getTitle():String {
			return "Altoon CMS";
		}
		
 		public function AltoonLoader(){
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
			return [Award,Bibliography,Location,News,Office,Person,
			Project,ProjectCategory,ProjectLink,Role,Region,Service,Website,
			 LoonImage,ImageLink,LoonPdf,PdfLink,LoonVideo,VideoLink];
		}
	 
		override public function getSidebarTree():Array{
			var root:Array = new Array();
			
			var news:Branch = new Branch("News");
			root.push(news);
			news.addItem(new Leaf("Award",Award));
			news.addItem(new Leaf("News",News));
			
			var project:Branch = new Branch("Project");
			root.push(project);
			project.addItem(new Leaf("Project",Project));
			project.addItem(new Leaf("Project Links",ProjectLink));
			project.addItem(new Leaf("Categories",ProjectCategory));
			
			var people:Branch = new Branch("People");
			root.push(people);
			people.addItem(new Leaf("Person",Person));
			people.addItem(new Leaf("Roles",Role));
			
			var location:Branch = new Branch("Location");
			root.push(location);
			location.addItem(new Leaf("Location",Location));
			location.addItem(new Leaf("Region",Region));
			
			var offices:Branch = new Branch("Offices");
			root.push(offices);
			offices.addItem(new Leaf("Office",Office));

			var config:Branch = new Branch("Config");
			root.push(config);
			config.addItem(new Leaf("Bibliography",Bibliography));
			config.addItem(new Leaf("Services",Service));
			config.addItem(new Leaf("Website",Website));
			return root;
		}
	
		override public function getRemoteService():IService{
			return new LoonService(getConfiguration());
		}
 	}
}