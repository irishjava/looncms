package uk.mafu.flex.meta
{
	import flash.errors.IllegalOperationError;
	
	import uk.mafu.flex.util.Util;
	
	public class WidgetDef
	{
		private var _clazz:String;
		
		public function WidgetDef(parentClass:Class,val:String){
			switch (val){
				case "RichTextWidget":
					this._clazz = "uk.mafu.flex.builders.display.RichTextWidget";
					break;
				
				case "SingleImage":
					this._clazz = "uk.mafu.flex.builders.display.SingleImage";
					break;
				
				case "EnumWidget":
					this._clazz = "uk.mafu.flex.builders.display.EnumWidget";
					break;
					
				case "SinglePdf":
					this._clazz = "uk.mafu.flex.builders.display.SinglePdf";
					break;
					
				case "SingleVideo":
					this._clazz = "uk.mafu.flex.builders.display.SingleVideo";
					break;
					
				case "SingleAudio":
					this._clazz = "uk.mafu.flex.builders.display.SingleAudio";
					break;
				
				case "ImageGallery":
					this._clazz = "uk.mafu.flex.builders.display.ImageGallery";
					break;
						
				case "VideoGallery":
					this._clazz = "uk.mafu.flex.builders.display.VideoGallery";
					break;
				
				case "PdfGallery":
					this._clazz = "uk.mafu.flex.builders.display.PdfGallery";
					break;
					
				default:
					throw new IllegalOperationError("Unknown widget type:'" 
									+ val 
									+ "' found within class :'" 
									+ Util.getClassnameByClass(parentClass) 
									+ "'"  );
			}
		}
		
		public function get clazz():Class{
			return Util.getClassByName(this._clazz);
		} 
	}
}