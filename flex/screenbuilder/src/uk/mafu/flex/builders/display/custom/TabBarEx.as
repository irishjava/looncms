package uk.mafu.flex.builders.display.custom
{


      import flash.events.MouseEvent;
      import mx.controls.Button;
      import mx.controls.TabBar;
      import mx.events.ItemClickEvent;

      public class TabBarEx extends TabBar
      {
		private var _finalized:Boolean           = false;
	    private var _tabRows:int                 = 1;
	    private var _tabRowHeight:int            = 0;
		
		// Constructor -------------------------------------------------------------

        public function TabBarEx(){
			super();
			addEventListener(ItemClickEvent.ITEM_CLICK, itemClickHandler);
			addEventListener(MouseEvent.MOUSE_UP, mouseHandler, true);
		}

        // Public -------------------------------------------------------------------
		/** @inheritDoc */
        public function finalize():void{
        	_finalized = true;
            _tabRows = 1;
            removeEventListener(ItemClickEvent.ITEM_CLICK, itemClickHandler);
            removeEventListener(MouseEvent.MOUSE_UP, mouseHandler, true);
		}

        public function invalidateTabsWidth():void{
        	resetNavItems();
            callLater(invalidateDisplayList);
		}

        /** @inheritDoc */
        public function get isFinalized():Boolean{
        	return _finalized;
		}

        // Protected -----------------------------------------------------------------
		/**
        * @private
        */
		protected override function measure():void{
			super.measure();
			if( _tabRowHeight <= 0 ){
				_tabRowHeight = measuredHeight;
			}
			if( _tabRows > 1 ){
				measuredHeight = (_tabRowHeight - 1) * _tabRows + 1;
			}
		}

        /**
        * @private
        */
        protected override function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void{
			var i:int;
			var xPos:int;
			var yPos:int;
            var buttonWidth:int;
			var tabRowWidth:int;
			var tabButton:Button;
			var lineNumber:int;
			var line:TabBarExLine;
			var prevLine:TabBarExLine;
			var lines:Array;
			xPos = 0;
			line = new TabBarExLine()
			lines = [line];
			lineNumber = 0;
			for( i = 0; i < numChildren; i++ ){
				tabButton = getChildAt(i) as Button;
				if( tabButton ){
                	buttonWidth = tabButton.measuredWidth - 1;
                    	if( xPos + buttonWidth > unscaledWidth && line.buttons.length > 0 ){
                        	xPos = 0;
                            lineNumber++;
                            line = new TabBarExLine();
                            lines.push(line);
						}
                             line.buttonWidth += buttonWidth;
                             line.buttons.push(tabButton);
                             xPos += buttonWidth;
					}
				}
				if( _tabRows != lineNumber + 1 ){
                	_tabRows = lineNumber + 1;
                    unscaledHeight = measuredHeight = 
                    measuredMinHeight = (_tabRowHeight - 1) * _tabRows + 1;
                    invalidateSize();
				}
                super.updateDisplayList(unscaledWidth, unscaledHeight);
                if( _tabRows > 1 ){
					lineNumber = _tabRows - 1;
					while( lineNumber > 0 ){
						line = TabBarExLine(lines[lineNumber]);
						prevLine = TabBarExLine(lines[lineNumber-1]);
						buttonWidth = Button(prevLine.buttons[prevLine.buttons.length - 1]).measuredWidth - 1;
						if( prevLine.buttonWidth - line.buttonWidth > buttonWidth ){
							line.buttons.unshift(prevLine.buttons.pop());
                            prevLine.buttonWidth -= buttonWidth;
                            line.buttonWidth += buttonWidth;
                            lineNumber = _tabRows - 1;
						}
                        else{
							lineNumber--;
						}
					}
					tabRowWidth = unscaledWidth * 0.8;
					for( i = 0; i < lines.length; i++ ){
                    	tabRowWidth = Math.max(tabRowWidth, TabBarExLine(lines[i]).buttonWidth);
                    }                      
					tabRowWidth = Math.min(unscaledWidth - 5, tabRowWidth);
					i = selectedIndex;
					while( i >= 0 ){
						line = TabBarExLine(lines[lineNumber]);
						lines.push(lines.shift());
						i -= line.buttons.length;
					}
                    // Buttons
                    for( lineNumber = 0; lineNumber < _tabRows; lineNumber++ ){
						xPos = 3;
						yPos = lineNumber * (_tabRowHeight - 1);
						if( lineNumber == _tabRows - 1 ){
                        	xPos = 0;
							tabRowWidth += 5;
						}
						line = TabBarExLine(lines[lineNumber]);
						for( i = 0; i < line.buttons.length; i++ ){
							tabButton = Button(line.buttons[i]);
							if( i < line.buttons.length - 1 ){
								buttonWidth = tabButton.measuredWidth * tabRowWidth / line.buttonWidth;
                        	}
							else{
								buttonWidth = tabRowWidth - xPos;
							}
							tabButton.move(xPos, yPos);
							tabButton.setActualSize(buttonWidth, _tabRowHeight);
							xPos += (buttonWidth - 1);
						}
					}
				}
            }

            // Eventlistener ------------------------------------------------------------

		private function itemClickHandler(event:ItemClickEvent):void{
        	if( event && event.index < numChildren ){
            	var button:Button = getChildAt(event.index) as Button;
                if( button.y != (_tabRows - 1) * _tabRowHeight ){
                	invalidateDisplayList();
				}
			}
		}

		private function mouseHandler(event:MouseEvent):void{
        	event.stopImmediatePropagation();
		}
	}
}