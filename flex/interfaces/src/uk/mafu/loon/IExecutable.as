package uk.mafu.loon
{
	import uk.mafu.loon.IService;
	public interface IExecutable {
		function execute(service:IService,entityParent:Object=null):void;
		function isDuplicate(other:IExecutable):Boolean;
	}
}