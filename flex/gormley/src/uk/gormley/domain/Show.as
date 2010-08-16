package uk.gormley.domain
{
import uk.mafu.loon.domain.data.ImageLink;

[RemoteClass(alias="uk.gormley.domain.Show")]
[Tab(title="Main",order=1,field=title,field=date,field=url,field=type,field=permanent)]
[Tab(title="Body",order=2,field=text,field=body)]
[Tab(title="Start/End",order=3,field=startDate,field=endDate)]
[Tab(title="Images",order=4,field=images)]
[Tab(title="Info",order=5,field=infoImage,field=infoText)]
[Tab(title="Progress Images",order=6,field=progressImages)]
[Tab(title="Video 1",order=7,field=video1,field=videoThumb1)]
[Tab(title="Video 2",order=8,field=video2,field=videoThumb2)]
[Tab(title="Video 3",order=9,field=video3,field=videoThumb3)]
[Tab(title="Audio",order=10,field=audio1,field=audio2,field=audio3)]
[Tab(title="Pdfs",order=11,field=pdf1,field=pdf2,field=pdf3)]
[Tab(title="URLs",order=12,field=url1,field=url2,field=url3,field=url4,field=url5,field=url6)]
[Order(col="title",asc="false")]
[Columns(col="pk",col="title",col="type",col="permanent",col="startDate",col="endDate")]
[Chooser(label="title")]
[Bindable]
public class Show extends AbstractStripItem {

	[Display(label="Type",widget=EnumWidget,enums="solo,group"
	,defaultValue="solo")]
	public var type:String;
	
	[Display(label="Permanent")]
	public var permanent:Boolean;
	
	[Display(label="Start Date",lines=10)]
	public var startDate:Date;
	
	[Display(label="End Date",lines=10)]
	public var endDate:Date;
	
	[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
	[Display(label="Project Images",widget="ImageGallery")]
	public var images:Array;
	}
}
