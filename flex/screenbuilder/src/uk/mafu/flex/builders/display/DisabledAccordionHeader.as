package uk.mafu.flex.builders.display
{
	
	import mx.containers.accordionClasses.AccordionHeader;
	import mx.core.IFactory;

    public class DisabledAccordionHeader extends AccordionHeader { //implements IFactory  {
        public function DisabledAccordionHeader() {
            super();
            mouseEnabled = false;
        }
        
        public function newInstance():*{
//        	return new DisabledAccordionHeader();
			return this;
        }
    }
}