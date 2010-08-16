package uk.mafu.loon.domain.data
{
	[RemoteClass(alias="uk.mafu.loon.domain.data.PdfLink")]
	[Bindable]
	public dynamic class PdfLink {
		public var pk:Number;
		public var title:String;
		public var caption:String;
		public var pdfId:Object;
	}
}
