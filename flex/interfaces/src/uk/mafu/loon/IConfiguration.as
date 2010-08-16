package uk.mafu.loon
{
	public interface IConfiguration
	{
		function getCacheClearer():IAction;
		function getRootContext():String;
		function getImageUploadUrl():String;  
		function getImageViewUrl():String;
		function getThumbnailViewUrl():String;
		function getPdfViewUrl():String;
		function getPdfPreviewUrl():String;
		function getAudioUrl():String;
		function getVideoViewUrl():String;
		function getServiceUrl():String;
	}
}