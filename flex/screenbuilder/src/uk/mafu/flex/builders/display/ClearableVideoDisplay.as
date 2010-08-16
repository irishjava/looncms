package uk.mafu.flex.builders.display
{
	import mx.controls.VideoDisplay;
	import mx.core.mx_internal;

	public class ClearableVideoDisplay extends VideoDisplay
	{
		public function ClearableVideoDisplay()
		{
			super();
			maintainAspectRatio = true;
			bufferTime = 1;
		}
		
		public function clear():void {
			if(playing == true) {
				stop();
			}
            mx_internal::videoPlayer.clear();
		}
		
	}
}