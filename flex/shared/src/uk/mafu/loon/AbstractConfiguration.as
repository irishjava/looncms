package uk.mafu.loon
{
	public class AbstractConfiguration implements IConfiguration
	{
		public function AbstractConfiguration(){
		}

		public function getRootContext():String{
			throw new Error("Must implement this method in subclass");
		}
		
		public function getCacheClearer():IAction{
			throw new Error("Must implement this method in subclass");
		}
		
		public function getImageUploadUrl():String {
			return getRootContext() + "/loon/imageUploadController";
		}
		
		public function getImageViewUrl():String {
			return getRootContext() + "/loon/imageViewController?pk=";
		}
		
		public function getThumbnailViewUrl():String {
			return getRootContext() + "/loon/thumbnailViewController?pk=";
		}
		
		public function getPdfViewUrl():String {
			return getRootContext() + "/loon/pdfViewController?pk=";
		}
		
		public function getPdfPreviewUrl():String {
			return getRootContext() + "/loon/pdfPreviewController?pdfId=";
		}
		
		public function getVideoViewUrl():String {
			return getRootContext() + "/loon/videoViewController?pk=";
		}
		
		public function getAudioUrl():String {
			return getRootContext() + "/loon/audioViewController?pk=";
		}
		
		public function getServiceUrl():String {
			return getRootContext() + "/loon/adminService";	
		}
		
	}
}