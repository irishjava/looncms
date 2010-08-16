package uk.mafu.loon.dto
{
	import flash.utils.ByteArray;
	import uk.mafu.loon.domain.data.ImageLink;
	import uk.mafu.loon.domain.data.LoonImage;
	
	[RemoteClass(alias="uk.mafu.loon.dto.ImageThumb")]
	[Bindable]
	public class ImageThumb
	{
		public var data:ByteArray;
		public var mimetype:String;
		public var linkPk:Number;
	}
}