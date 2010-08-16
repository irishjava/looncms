package uk.mafu.loon.service
{
	import flash.utils.Dictionary;
	import flash.xml.XMLDocument;
	import flash.xml.XMLNode;
	import mx.utils.XMLUtil;
	import uk.mafu.loon.IImageUploadResult;
	import uk.mafu.flex.util.Util;
		
	public class ImageUploaderResult implements IImageUploadResult{
		
		private var _status:Boolean;
		private var _pk:Number;
		private var _width:Number;
		private var _height:Number;
		private var _mimetype:String;
		private var _filename:String;
		
		
		public function get status():Boolean{
			return _status;
		} 
		public function get pk():Number{
			return _pk;
		}
		
		public function get width():Number{
			return _width;
		}
		public function get height():Number{
			return _height;
		}
		
		public function get mimetype():String{
			return _mimetype;
		}
		
		public function get filename():String{
			return _filename;
		}
				
		public function ImageUploaderResult(msg:String){
			var doc:XMLDocument = XMLUtil.createXMLDocument(msg);
			var dict:Dictionary = new Dictionary(true);
			for each (var node:XMLNode in doc.firstChild.childNodes) {
				Util.debug("name:" + node.nodeName + " value:" +  getValOfNode(node));
				dict[node.nodeName] = getValOfNode(node);
			}
			this._pk = dict['pk'];
			dict['status'] === "success" ? this._status = true : this._status = false;
			this._width = dict['width'];
			this._height = dict['height'];
			this._mimetype = dict['mimetype'];
			this._filename = dict['filename'];
		}
		
		public function toString():String{
			var ret:String = new String();
			ret += "status:";
			ret += this.status;
			ret += ":pk:";
			ret += this.pk;
		 	ret += ":width:";
			ret += this.width;
			ret += ":height:";
			ret += this.height;
			ret += ":filename:";
			ret += this.filename;
			ret += ":mimetype:";
			ret += this.mimetype;
			return ret; 
		}
		
		public function getValOfNode(node:XMLNode):String{
			if(node.childNodes != null && node.childNodes.length > 0) {
				return (node.childNodes[0] as XMLNode).nodeValue;
			}
			else {
				return null;
			}
		}
	}
}