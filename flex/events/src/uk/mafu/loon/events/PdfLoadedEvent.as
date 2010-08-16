package uk.mafu.loon.events
{
	import flash.events.Event;
	
	import uk.mafu.flex.util.Util;
	import uk.mafu.loon.domain.data.LoonPdf;
	
   	public class PdfLoadedEvent extends Event
	{
		public static const NAME:String = Util.getFullClassName(PdfLoadedEvent);
		public var pdf:LoonPdf;
		public var callee:Object;
		
		public function PdfLoadedEvent(pdf:LoonPdf,callee:Object) {
        	super(NAME, true,true);
        	Util.debug("created new:" + Util.getClassname(this) );
        	pdf = pdf;
        	this.callee = callee;
        }

    	override public function clone():Event{
       		return Util.cloneObject(this) as Event;
    	}
	}
}