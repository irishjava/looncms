package uk.mafu.domain.gillespies
{
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.PdfLink;
	import uk.mafu.loon.domain.data.VideoLink;
	
	[RemoteClass(alias="uk.mafu.domain.gillespies.Project")]
	[Tab(title="Text",order=1,field=title,field=header,field=content,field=client,field=designTeam,field=capitalValue,field=photographer,field=excite,field=photomontage)]
	[Tab(title="People",order=2,field=assignments)]
	[Tab(title="Images",order=3,field=images)]
	[Tab(title="Thumbnail",order=4,field=thumbnail)]
 	[Tab(title="Video",order=5,field=video,field=videoThumbnail)]
	[Tab(title="Pdf",order=6,field=pdf)]
	[Tab(title="Front Page Image",order=4,field=frontPageImage)]
 	[Order(col="pk",asc="false")]
	[Columns(col="pk",col="title",col="client")]
	[Chooser(label="title")]
	[Bindable]
	public class Project
	{
		public var pk:Number =-1;
		public var permalink:String;
		
		[Display(label="Title")]
		public var title:String;
		
		[Display(label="Header")]
		public var header:String;
	
		[Display(label="Content",lines=15,widget=RichTextWidget)]
		public var content:String;
		
		[Display(label="Client")]
		public var client:String;
		
		[Display(label="Design Team")]
		public var designTeam:String;
		
		[Display(label="Capital Value")]
		public var capitalValue:String;
		
		[Display(label="Photographer")]
		public var photographer:String;
		
		[Display(label="Excite",lines=4)]
		public var excite:String;

		[Display(label="Photomontage")]
		public var photomontage:String;
		
		[Display(label="Thumbnail",widget=SingleImage)]
		public var thumbnail:ImageLink;
		
		[Display(label="Front Page Image",widget=SingleImage)]
		public var frontPageImage:ImageLink;
		
		[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
		[Display(label="Project Images",widget=ImageGallery)]
	  	public var images:Array = new Array();
	  	
	  	[Display(label="PDF",widget=SinglePdf)]
		public var pdf:PdfLink;
		
		[Display(label="Video",widget=SingleVideo)]
		public var video:VideoLink;
		
		[Display(label="Video Thumbnail",widget=SingleImage)]
		public var videoThumbnail:ImageLink;
	  	
	  	[Relationship(end="uk.mafu.domain.gillespies::Assignment",type="MANY_TO_MANY")]
		[Order(col="pk",asc="false")]
		[Display(label="Assignments")]
		public var assignments:Array = new Array();
	}
}