package uk.mafu.flex.meta
{
	public dynamic class BadDefinitionError extends Error
	{
		public function BadDefinitionError(start:String,end:String)
		{
			super(generateMessage(start,end));
		}
		
		public function generateMessage(start:String,end:String):String {
			return "Bad class definition '" + end +"' referenced from '" + start + "'"; 
		}
	}
}