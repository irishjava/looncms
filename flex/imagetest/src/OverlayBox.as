package
{
	import flash.display.Bitmap;
	
	import mx.containers.Canvas;
	import mx.controls.Image;
	import mx.controls.Label;
	
	public class OverlayBox
	{
		public function OverlayBox(image:Image){
			var bmap:Bitmap = image.data as Bitmap;
			var imgW:Number = bmap.width;
			var imgH:Number = bmap.height;
				
			trace(imgW);
			trace(imgH);
				
			var left:LeftCanvas = new LeftCanvas();
			//left.set Style("backgroundColor","#EC3737");
			left.width  = imgW / 5;
			left.height = imgH;
			left.x = image.x;
			left.y = image.y;
			left.alpha = 0.5;
			image.parent.addChild(left);
			
			var right:RightCanvas = new RightCanvas();
			//right.set Style("backgroundColor","#EC3737");
			right.width  = imgW / 5;
			right.height = imgH;
			right.x = image.x + imgW - imgW/5;
			right.y = image.y;
			right.alpha = 0.5;
			image.parent.addChild(right);
			
			var top:TopCanvas = new TopCanvas();
			top.id = "topCanvas";
			//top.set Style("backgroundColor","#EC3737");
			top.width  = (imgW / 5 ) * 3;
			top.height = (imgH /5);
			top.x = image.x + imgW/5;
			top.y = image.y;
			top.alpha = 0.5;
			image.parent.addChild(top);
			
			var bottom:BottomCanvas = new BottomCanvas();
			//bottom.set Style("backgroundColor","#EC3737");
			bottom.width  = (imgW / 5 ) * 3;
			bottom.height = (imgH /5);
			bottom.x = image.x + imgW/5;
			bottom.y = imgH - (imgH /5);
			bottom.alpha = 0.5;
			image.parent.addChild(bottom);
		}
	}
}