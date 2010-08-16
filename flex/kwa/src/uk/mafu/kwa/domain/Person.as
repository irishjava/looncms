package uk.mafu.kwa.domain{
import uk.mafu.loon.domain.data.ImageLink;
[RemoteClass(alias="uk.kwa.domain.Person")]
[Tab(title="Main",order=1,
	field=name,
	field=image,
	field=texture,
	field=bio,
	field=suffix,
	field=permalink,
	field=shortName
)]
[Tab(title="Excite",order=2,
	field=excite
)]

[Order(col="pk",asc="false")]
[Columns(col="pk",col="name")]
[Chooser(label="name")]
[Bindable]
public class Person {
		public var pk:Number  = -1 ;
		
		[Display(label="Name",lines=1)]
		public var name:String;
		
		[Display(label="Suffix",lines=1)]
		public var suffix:String;
		
		[Display(label="Short Name",lines=1)]
		public var shortName:String;
		
		[Display(label="Directors Excite")]
		[Relationship(end="uk.mafu.kwa.domain::Excite",type="ONE_TO_ONE")]
		public var excite:Excite;
	
		[Display(label="Image",widget=SingleImage)]	
		public var image:ImageLink;
		
		[Display(label="Texture",widget=SingleImage)]
		public var texture:ImageLink;
		
		[Display(label="Bio",lines=5)]
		public var bio:String;
		
		public var permalink:String;
	}
}