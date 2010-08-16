package uk.gormley.domain
{
import uk.mafu.loon.domain.data.AudioLink;
import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.VideoLink;

[RemoteClass(alias="uk.gormley.domain.AbstractStripItem")]
[Bindable]
public class AbstractStripItem extends AbstractPdfItem {
	
	[Display(label="Image",widget=SingleImage)]	
	public var infoImage:ImageLink;
	
	[Display(label="Text",lines=10)]
	public var infoText:String;
	
	[Relationship(end="uk.mafu.loon.domain.data::ImageLink",type="ONE_TO_MANY")]
	[Display(label="Images",widget=ImageGallery)]
	public var progressImages:Array;
	
	[Display(label="Video",widget=SingleVideo)]
	public var video1:VideoLink;
	
	[Display(label="Video",widget=SingleVideo)]
	public var video2:VideoLink;
	
	[Display(label="Video",widget=SingleVideo)]
	public var video3:VideoLink;
	
	[Display(label="Audio 1",widget=SingleVideo)]
	public var audio1:AudioLink;
	
	[Display(label="Audio 2",widget=SingleVideo)]
	public var audio2:AudioLink;
	
	[Display(label="Audio 3",widget=SingleVideo)]
	public var audio3:AudioLink;
	
	[Display(label="Video Thumb",widget=SingleVideo)]
	public var videoThumb1:ImageLink;
	
	[Display(label="Video Thumb",widget=SingleVideo)]
	public var videoThumb2:ImageLink;
	
	[Display(label="Video Thumb",widget=SingleVideo)]
	public var videoThumb3:ImageLink;
	}
}