package com.emakmafu.controls 
{
	import flash.display.BitmapData;
	import flash.display.BlendMode;
	import flash.display.DisplayObject;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.geom.Matrix;
	
	/**
	 * Dispatched when the background is resized
	 */
	[Event(name="resize", type="flash.events.Event")]
	
	
	/**
	 * @author emakmafu
	 */
	public class Overlay extends Sprite 
	{
		
		protected var _bgWidth : Number;
		protected var _bgHeight : Number;
		protected var _bgAlpha : Number;
		protected var _bgColor : uint;
		protected var _background : Sprite;
		protected var _holes:Array;
		
		public function Overlay(width : Number, height : Number, color:uint = 0xFFFFFF, alpha : Number = 1) 
		{
			_holes = [];
			_bgWidth = width;
			_bgHeight = height;
			_bgColor = color;
			_background = new Sprite;
			_background.mouseEnabled = _background.mouseChildren = false;
			addChild(_background);
			updateBackground();
			backgroundAlpha = alpha;
		}

		public function updateBackground():void
		{
			_drawFlatBackground();	
			if(hasHoles)
			{
				_drawHoleyBackground();
			}
			dispatchEvent(new Event(Event.RESIZE));
		}
		
		protected function _drawFlatBackground():void
		{
			with (_background.graphics)
			{
				clear();
				beginFill(_bgColor);
				drawRect(0, 0, _bgWidth, _bgHeight);
				endFill();	
			}
		}
		
		protected function _drawHoleyBackground():void
		{
			var bitmapData : BitmapData = new BitmapData(_bgWidth, _bgHeight, true);
			bitmapData.draw(_background);
			
			_appendBitmapData(_holes, bitmapData);
			
			with(_background.graphics)
			{
				clear();
				beginBitmapFill(bitmapData);
				drawRect(0, 0, _bgWidth, _bgHeight);
				endFill();		
			}
				
		}
		
		protected function _appendBitmapData(drawableObjects:Array, bitmapData:BitmapData):void
		{
			for each(var source:DisplayObject in drawableObjects)
			{
				var matrix:Matrix = new Matrix(1,0,0,1, source.x, source.y);
				bitmapData.draw(source, matrix, null, BlendMode.ERASE);
			}
		}
		
		public function resize(width:Number, height:Number):void
		{
			_bgWidth = width;
			_bgHeight = height;
			updateBackground();
		}

		override public function set width(value : Number) : void
		{
			_bgWidth = value;
			updateBackground();
		}
		
		override public function set height(value:Number):void
		{
			_bgHeight = value;
			updateBackground();	
		}
		
		public function get backgroundAlpha() : Number
		{
			return _bgAlpha;
		}
		
		public function set backgroundAlpha(alpha : Number) : void
		{
			_background.alpha = _bgAlpha = alpha;
		}

		public function get backgroundColor() : uint
		{
			return _bgColor;
		}
		
		public function set backgroundColor(color : uint) : void
		{
			_bgColor = color;
			updateBackground();
		}
		
		public function get hasHoles() : Boolean
		{
			return _holes.length > 0 ? true : false;
		}
		
		public function addHole(hole:DisplayObject) : void
		{
			_holes.push(hole);
			updateBackground();	
		}
		
		public function addHoleAt(index:int, hole : DisplayObject):void
		{
			_holes.splice(index, 0, hole);
			updateBackground();
		}

		public function removeHole(hole : DisplayObject) : DisplayObject
		{
			var index : int = getHoleIndex(hole);
			return removeHoleAt(index);
		}
		
		public function removeHoleAt(index : int) : DisplayObject
		{
			var result : DisplayObject = _holes.splice(index, 1)[0];
			updateBackground();
			return result; 
		}
		
		public function getHoleAt(index : int) : DisplayObject
		{
			return _holes[index];	
		}
		
		public function getHoleIndex(hole : DisplayObject) : int
		{
			return _holes.indexOf(hole);	
		}
		
	}
}
