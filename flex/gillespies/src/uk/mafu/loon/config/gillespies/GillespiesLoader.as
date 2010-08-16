package uk.mafu.loon.config.gillespies
{
	import uk.mafu.domain.gillespies.Assignment;
	import uk.mafu.domain.gillespies.Award;
	import uk.mafu.domain.gillespies.BlogEntry;
	import uk.mafu.domain.gillespies.Client;
	import uk.mafu.domain.gillespies.CommunityWork;
	import uk.mafu.domain.gillespies.Job;
	import uk.mafu.domain.gillespies.LectureAndTalk;
	import uk.mafu.domain.gillespies.Link;
	import uk.mafu.domain.gillespies.News;
	import uk.mafu.domain.gillespies.Office;
	import uk.mafu.domain.gillespies.OfficeLocation;
	import uk.mafu.domain.gillespies.Person;
	import uk.mafu.domain.gillespies.PressRelease;
	import uk.mafu.domain.gillespies.Profile;
	import uk.mafu.domain.gillespies.Project;
	import uk.mafu.domain.gillespies.ProjectCategory;
	import uk.mafu.domain.gillespies.ProjectLink;
	import uk.mafu.domain.gillespies.ProjectLocation;
	import uk.mafu.domain.gillespies.Publication;
	import uk.mafu.domain.gillespies.Role;
	import uk.mafu.domain.gillespies.Sustainability;
	import uk.mafu.domain.gillespies.Testimonial;
	import uk.mafu.domain.gillespies.Website;
	import uk.mafu.domain.gillespies.Working;
	import uk.mafu.domain.gillespies.YearOut;
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
	import uk.mafu.loon.tree.Action;
	import uk.mafu.loon.tree.Branch;
	import uk.mafu.loon.tree.Leaf;
	
	public class GillespiesLoader extends AbstractPluginLoader
	{
		override public function getTitle():String {
			return "GILLESPIES CMS";
		}
		
 		public function GillespiesLoader (){
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
				Assignment,
				Award,
				BlogEntry,
				Client,
				CommunityWork,
				Job,
				Working,
				LectureAndTalk,
				Link,
				News, 
				Office, 
				OfficeLocation,
				Person,
				PressRelease,
				Profile,
				Project,
				ProjectLink,
				ProjectCategory,
				ProjectLocation,
				Publication,
				Role,
				Sustainability,
				Testimonial,
				Website,
				YearOut,
				LoonImage,
				ImageLink,
				LoonPdf,
				PdfLink,
				LoonVideo,
				VideoLink
			];
			return null;
		}
		
		override public function getSidebarTree():Array {
			var ret:Array = new Array();
			//Website
			var sitewide:Branch = new Branch("Sitewide");
			ret.push(sitewide);
			sitewide.addItem(new Leaf("Website",Website));
 			//SHOWCASE		
			var projectsBranch:Branch = new Branch("Showcase (Projects)");
			ret.push(projectsBranch);
			projectsBranch.addItem(new Leaf("Projects",Project));
			projectsBranch.addItem(new Leaf("Project Categories",ProjectCategory));
			projectsBranch.addItem(new Leaf("Project Locations",ProjectLocation));
			projectsBranch.addItem(new Leaf("Home Page Images",ProjectLink));
			//PRACTICE
			var practiceBranch:Branch = new Branch("Practice");
			ret.push(practiceBranch);
			practiceBranch.addItem(new Leaf("Profile/Philosophy",Profile));
			practiceBranch.addItem(new Leaf("Sustainability",Sustainability));
			practiceBranch.addItem(new Leaf("People",Person));
			practiceBranch.addItem(new Leaf("Assignments",Assignment));
			practiceBranch.addItem(new Leaf("Roles",Role));
			practiceBranch.addItem(new Leaf("Join Us",Job));
			practiceBranch.addItem(new Leaf("Working here",Working));
			practiceBranch.addItem(new Leaf("Year Out",YearOut));
			practiceBranch.addItem(new Leaf("Community Work",CommunityWork));
			practiceBranch.addItem(new Leaf("Links",Link));
			//CLIENTS
			var clientsBranch:Branch = new Branch("Clients");
			ret.push(clientsBranch);
			clientsBranch.addItem(new Leaf("Testimonials",Testimonial));
			clientsBranch.addItem(new Leaf("Client List",Client));
			//NEWS
			var newsBranch:Branch = new Branch("News");
			ret.push(newsBranch);
			newsBranch.addItem(new Leaf("News",News));
			newsBranch.addItem(new Leaf("Blog",BlogEntry));
			//PRESS
			var pressBranch:Branch = new Branch("Press");
			ret.push(pressBranch);
			pressBranch.addItem(new Leaf("Press",PressRelease));
			pressBranch.addItem(new Leaf("Awards",Award));
			pressBranch.addItem(new Leaf("Publications",Publication));
			pressBranch.addItem(new Leaf("Lectures/Talks",LectureAndTalk));
			//CONTACT
			var officesBranch:Branch = new Branch("Contact");
			ret.push(officesBranch);
			officesBranch.addItem(new Leaf("Offices",Office));
			officesBranch.addItem(new Leaf("Office Locations",OfficeLocation));
			//
			var action:Action = new Action("Clear the server cache","Please confirm, that you wish to cleare the server cache.",
				getConfiguration().getCacheClearer()
				)
			ret.push(action);
			return ret;
		}
		
		override public function getRemoteService():IService{
			return new LoonService(getConfiguration());
		}
 	}
}