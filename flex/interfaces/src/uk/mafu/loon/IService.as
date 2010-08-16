package uk.mafu.loon
{
	import mx.core.IDataRenderer;
	
	public interface IService
	{
		
		function get configuration():IConfiguration;
		
		function saveOneToOneRelationship(
			parent:Class,
			child:Class,
			parent_pk:Object,
			child_pk:Object,
			relationship_name:String):void ;
		
		function loadOneToOne(
			parent_clazz:Class,
			child_clazz:Class,
			relationship_name:String,
			parentId:Object,fields:Array,withOptions:Boolean = true):void;
			
		function loadOneToMany(		
				parent_clazz:Class,
				child_clazz:Class,
				relationship_name:String,
				parentId:Object,fields:Array):void;
				
		function loadManyToMany(		
				parent_clazz:Class,
				child_clazz:Class,
				relationship_name:String,
				parentId:Object,fields:Array):void;
				
		function saveOneToMany(
				parent_clazz:Class,
				parentId:Object,
				relationship:String,
				children:Array
				):void;
				
		function saveManyToMany(
				parent_clazz:Class,
				parentId:Object,
				relationship:String,
				children:Array
				):void;
				
		/**
		* The children are imageLinks..
		*/
		function saveOneToManyImages(
				parent_clazz:Class,
				parentId:Object,
				relationship:String,
				children:Array
				):void;
				
	
		function load(clazz:Class,pk:Object):void;
		function loadImage(pk:Object,caller:Object =null):void;
		
		function loadImageThumb(pk:Object,caller:IDataRenderer):void ;
		
//		function loadPdf(pk:Object,caller:Object =null):void;
//		function loadVideo(pk:Object,caller:Object =null):void;

		/**
		 * The array of fields are the field names that you want to be loaded in the returned objects.
		 */
		function getAll(clazz:Class,fields:Array):void;

		function save(target:Object,listener:ISaveListener = null):void;
		
		function remove(clazz:Class,pk:Object):void;
	
		function removeImage(pk:Object):void;
			
		function removeVideo(pk:Object):void;
		
		function removePdf(pk:Object):void;
		
		function createImage(caller:Object):void;
		
		function saveSingleLink(child:Object,parent:Object,relationship:String):void;
		
		function deleteSingleLink(parent:Object,relationship:String):void;
		
		function uploadVideo(loonVideo:Object,caller:IUploader):void;
		
		function uploadAudio(loonAudio:Object,caller:IUploader):void;
		
		function uploadPdf(loonPdf:Object,caller:IUploader):void;
		
	}
}