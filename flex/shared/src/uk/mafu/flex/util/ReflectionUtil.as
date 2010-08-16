package uk.mafu.flex.util
{
	import flash.utils.Dictionary;
	import flash.utils.describeType;
	import flash.utils.getDefinitionByName;
	
	import mx.collections.IViewCursor;
	
	import uk.mafu.flex.meta.BadDefinitionError;
	import uk.mafu.flex.meta.ChooserSettings;
	import uk.mafu.flex.meta.ClassMeta;
	import uk.mafu.flex.meta.ColumnSettings;
	import uk.mafu.flex.meta.DisplayMeta;
	import uk.mafu.flex.meta.MetaData;
	import uk.mafu.flex.meta.OrderSettings;
	import uk.mafu.flex.meta.ROW;
	import uk.mafu.flex.meta.RelationshipMeta;
	import uk.mafu.flex.meta.TAB;
	import uk.mafu.flex.meta.WidgetDef;
	
	public class ReflectionUtil
	{
		
		private static function handleMeta(m:XML):MetaData{
			var meta:MetaData = new MetaData();	
			var name:String = String(m.@name);    
			trace("field metadata:name '" + name + "'"); 
			meta.name = name;
			for each (var arg:XML in m.*){
				var key:String = String(arg.@key);
				var value:String = String(arg.@value);
					trace("arg:key '" + key + 
					 "' arg:value '" + value + 
					 "'"  );
					 meta.dict[key] = value;
			}
		return meta;
		}
		


		private static var relationshipMap:Dictionary = new Dictionary(true);

		/**
		 * @return an @see{Array} containing RelationshipMeta objects.
		 */
		public static function extractRelationships(clazz:Class):Array{
			
			if(relationshipMap[clazz] == null) {
					trace("relationships not in cache:" +clazz );
					trace("putting relationships into cache :" +clazz );
					var x:XML = describeType(clazz);
				var rels:Array = new Array();
				for each (var meta:XML in x..metadata){
					if(String(meta.@name) ==="Relationship"){
						var metaData:MetaData = handleMeta(meta);
						trace("meta" + meta);
						trace("meta.parent" + meta.parent());
						var name:String = String(meta.parent().@name);
						var start:String = String(meta.parent().@declaredBy);
						var from_clazz:Class = Class(getDefinitionByName(start));
						var end:String = String(metaData.dict['end']);
						var to_clazz:Class;
						try {
						 to_clazz = Class(getDefinitionByName(end)); 
						}
						catch(e:ReferenceError){
							throw new BadDefinitionError(start,end); 
						}
						
						var type:String =  metaData.dict['type'];
						var r:RelationshipMeta = new RelationshipMeta(name,new ClassMeta(from_clazz),new ClassMeta(to_clazz),type);
						rels.push(r);
					}
				}
				relationshipMap[clazz] = rels;
				return relationshipMap[clazz];
			}
			else {
				trace("returning relationships from cache :" +clazz );
				return relationshipMap[clazz];
			}
		}
		
		private static var classMetaMap:Dictionary = new Dictionary(true);
		
		private static var displayMetaMap:Dictionary = new Dictionary(true);
		
		/**
		 * @return an @see{Array} containing RelationshipMeta objects.
		 */
		public static function extractClassMeta(clazz:Class):ClassMeta{
			if(classMetaMap[clazz] == null) {
					trace("ClassMeta not in cache:" +clazz );
					trace("putting ClassMeta into cache :" +clazz );
				var x:XML = describeType(clazz);
				var name:String = String(x.@name);
				var classMeta:ClassMeta = new ClassMeta(clazz,name);
				var r:Array = extractRelationships(clazz);
				classMeta.displayMeta = extractDisplayMeta(clazz);
				for each( var rel:RelationshipMeta in r) {
					trace("rel" + rel);
					classMeta.relationships[rel.name] = rel;
				}
				classMetaMap[clazz] = classMeta;
				return classMetaMap[clazz];
			}
			else {
				trace("returning ClassMeta from cache :" +clazz );
				return classMetaMap[clazz];
			}
		}
		
		public static function extractDisplayMeta(clazz:Class):DisplayMeta{
			if(displayMetaMap[clazz] == null) {
				trace("DisplayMeta not in cache:" +clazz );
				trace("putting DisplayMeta into cache :" +clazz );
				var displayMeta:DisplayMeta = new DisplayMeta(clazz);
				var xml:XML = describeType(clazz);
				trace("xml" + xml);
				var accessors:XMLList =xml..accessor;
				for each (var x2:XML in accessors){
					if(String(x2.@access) === "readwrite"){
						displayMeta.addROW(handleROW(x2));
					}
				}
				var metas:XMLList =xml..metadata; 
				for each (var x:XML in metas){
					switch (String(x.@name)){
					case "Tab":
					displayMeta.addTAB(handleTab(x));
					break;
					case "Columns":
					displayMeta.columnSettings = handleColumn(x,displayMeta);
					break;
					case "Order":
					displayMeta.orderSettings = handleOrder(x);
					break;
					case "Chooser":
					displayMeta.chooserSettings = handleChooser(x);
					break;
					default:
					trace("not handling '" + String(x.@name) + "'");  
					//Do nothing ...throw new IllegalOperationError("unhandled field type" + String(x.@name));
				}
			}
			
				displayMetaMap[clazz] = displayMeta;
				return displayMetaMap[clazz];
			}
			else {
				trace("returning ClassMeta from cache :" +clazz );
				return displayMetaMap[clazz];
			}
		}
		
		private static function handleColumn(x:XML,displayMeta:DisplayMeta):ColumnSettings{
			trace(String(x));
			var columnSettings:ColumnSettings = new ColumnSettings();
			for each (var arg:XML in x..arg){
				trace(String(arg.@key) + ":" + String(arg.@value));
				var colname:String = String(arg.@value);
				var cursor:IViewCursor = displayMeta.rows.createCursor()  
				var foundInvalid:Boolean = true;
				while (!cursor.afterLast){
					var row:ROW = (cursor.current as ROW);
					if(row.name == colname) {
						foundInvalid = false;
						break;	
					}
					cursor.moveNext();
          		}				
				if(foundInvalid == true) {
					throw new Error("invalid column definition in class: " +
							  Util.getFriendlyClassnameByClass(displayMeta.clazz));
				}
				columnSettings.addColumn(colname);
			}
			return columnSettings;
		}
		
		private static function handleOrder(x:XML):OrderSettings{
			trace(String(x));
			var o:OrderSettings = new OrderSettings();
			for each (var arg:XML in x..arg){
				trace(String(arg.@key) + ":" + String(arg.@value));
				if(String(arg.@key) ==="col"){
					o.column = String(arg.@value);		
				}
				if(String(arg.@key) ==="asc"){
					o.ascending = Boolean(arg.@value);		
				}
				
			}
			return o;
		}
		
		private static function handleChooser(x:XML):ChooserSettings{
			trace(String(x));
			var ch:ChooserSettings = new ChooserSettings();
			for each (var arg:XML in x..arg){
				trace(String(arg.@key) + ":" + String(arg.@value));
				if(String(arg.@key) ==="label"){
					ch.labelColumn = String(arg.@value);	
				}
				break;
			}
			return ch;
		}
		
		private static function handleROW(x:XML):ROW{
			var row:ROW = new ROW();
			trace(String(x.@name));
			row.name = String(x.@name);
			var c:Class = Class(getDefinitionByName(String(x.@type)));
			var parentClass:Class = Class(getDefinitionByName(String(x.@declaredBy)))
			
			row.type = c;
			trace("////////////\nwe have a Field");
			trace("arg:name '" + String(x.@name) +
				  "' arg:type '" + String(x.@type) + 
				  "'");
			for each (var meta:XML in x..metadata){
				var metaData:MetaData = handleMeta(meta);
				if(metaData.name === "Relationship"){
					var name:String = String(meta.parent().@name);
					var from_clazz:Class = Class(getDefinitionByName(meta.parent().@declaredBy));
					var to_clazz:Class = Class(getDefinitionByName(metaData.dict['end']));
					var type:String =  metaData.dict['type'];
					row.relationship = new RelationshipMeta(name,new ClassMeta(from_clazz),new ClassMeta(to_clazz),type);
				}
				if(metaData.name === "Display"){
					if(metaData.dict['widget'] !=null){
						row.widgetDef = new WidgetDef(parentClass,metaData.dict['widget']);
					}
					if(metaData.dict['label'] !=null){
						row.label = metaData.dict['label'];
					}
					if(metaData.dict['lines'] !=null){
						row.lines =  metaData.dict['lines'];
					}
					if(metaData.dict['enums'] !=null){
						row.enums =  metaData.dict['enums'].toString().split(',');
					}
					if(metaData.dict['defaultValue'] !=null){
						row.defaultValue =  metaData.dict['defaultValue'].toString();
					}
					
	
				}
			}
			return row;
		}
		
		private static function handleTab(x:XML):TAB{
			var tab:TAB = new TAB();
			trace("////////////\nwe have a Tab");
			var tabOrder:Number;
			for each (var arg:XML in x.*){
					trace("arg:key '" + String(arg.@key) + 
					 "' arg:value '" + String(arg.@value) + "'"  );
					 if(String(arg.@key) === "title"){
						tab.title =String(arg.@value); 					 
					 } 
					 if(String(arg.@key) === "order"){
						tab.order =Number(arg.@value); 					 
					 }
					 if(String(arg.@key) === "field"){
						tab.addFieldName(String(arg.@value)); 					 
					 }
			}			 
			return tab;
		}
		}

	}
