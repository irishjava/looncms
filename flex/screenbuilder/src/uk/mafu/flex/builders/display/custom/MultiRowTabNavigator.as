package uk.mafu.flex.builders.display.custom{

import mx.containers.TabNavigator;
	public class MultiRowTabNavigator extends TabNavigator {
		protected override function createChildren():void {
			if(!tabBar){
				tabBar = new TabBarEx();
				tabBar.name = "tabBar";
				tabBar.focusEnabled = false;
				tabBar.styleName = this;
				tabBar.setStyle("borderStyle", "none");
				tabBar.setStyle("paddingTop", 0);
				tabBar.setStyle("paddingBottom", 0);
				rawChildren.addChild(tabBar);
            }
        	super.createChildren();
    	} 
	}
}