package uk.mafu.loon
{
	import mx.rpc.AbstractService;
	
	public interface IPluginLoader
	{
		function getClasses():Array;
		function getSidebarTree():Array;
		function getRemoteService():IService;
		function getConfiguration():IConfiguration;
		function getIntroText():String;
		function getTitle():String;
	}
}