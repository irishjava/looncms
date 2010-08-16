package uk.gormley.domain
{
[RemoteClass(alias="uk.gormley.domain.ArtGroup")]
[Tab(title="Main",order=1,field=title,field=body,field=date,field=url)]
[Tab(title="Arts",order=2,field=arts)]
[Tab(title="Art Type",order=3,field=artType)]
[Tab(title="Art SubType",order=4,field=artSubType)]
[Tab(title="Start/End",order=5,field=start,field=end)]
[Tab(title="Info",order=6,field=infoImage,field=infoText)]
[Tab(title="Progress Images",order=7,field=progressImages)]
[Tab(title="Video 1",order=8,field=video1,field=videoThumb1)]
[Tab(title="Video 2",order=9,field=video2,field=videoThumb2)]
[Tab(title="Video 3",order=10,field=video3,field=videoThumb3)]
[Tab(title="Audio",order=11,field=audio1,field=audio2,field=audio3)]
[Tab(title="Pdfs",order=12,field=pdf1,field=pdf2,field=pdf3)]
[Tab(title="URLs",order=4,field=url1,field=url2,field=url3,field=url4,field=url5,field=url6)]
[Order(col="title",asc="false")]
[Columns(col="pk",col="title",col="artType",col="artSubType")]
[Chooser(label="title")]
[Bindable]
public class ArtGroup extends AbstractStripItem {
	
	[Relationship(end="uk.gormley.domain::Art",type="MANY_TO_MANY")]
	[Order(col="pk",asc="false")]
	[Display(label="Arts")]
	public var arts:Array;
	
	[Display(label="Art Type",widget=EnumWidget,
	enums="sculpture-series,sculpture-project,drawing-series,drawing-workbook,drawing-prints"
	,defaultValue="sculpture-series"
	)]
	public var artType:String;
	
	[Display(label="Art Sub-Type")]
	public var artSubType:String;
	
	[Display(label="Start Date")]
	public var start:Date;
	
	[Display(label="End Date")]
	public var end:Date;
	}
}
