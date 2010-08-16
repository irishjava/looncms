package uk.gormley.domain
{

import uk.mafu.loon.domain.data.PdfLink;

[RemoteClass(alias="uk.gormley.domain.Website")]
[Tab(title="Main",order=1,field=identifier,field=title,field=text1,field=text2,field=text3)]
[Tab(title="Slides",order=2,field=slides)]
[Tab(title="Solo Exhibition",order=3,field=soloExhibition,field=soloexpdf1,field=soloexpdf2,field=soloexpdf3)]
[Tab(title="Group Exhibition",order=4,field=groupExhibition,field=groupexpdf1,field=groupexpdf2,field=groupexpdf3)]
[Tab(title="Public Exhibition",order=5,field=publicExhibition,field=publicexpdf1,field=publicexpdf2,field=publicexpdf3)]
[Tab(title="Solo Catalogue",order=6,field=solocatpdf1,field=solocatpdf2,field=solocatpdf3)]
[Tab(title="Group Catalogue",order=7,field=groupcatpdf1,field=groupcatpdf2,field=groupcatpdf3)]
[Order(col="identifier",asc="false")]
[Columns(col="pk",col="identifier")]
[Chooser(label="title")]
[Bindable]
public class Website {

    public var pk:Object;

    [Display(label="Identifier")]
    public var identifier:String;

    [Display(label="Bio Title")]
    public var text1:String;

    [Display(label="Bio Caption",lines=15)]
    public var text2:String;

    [Display(label="Bio Text",lines=15)]
    public var text3:String;

    [Relationship(end="uk.gormley.domain::Slide",type="MANY_TO_MANY")]
    [Order(col="pk",asc="false")]
    [Display(label="Slides")]
    public var slides:Array;

    [Display(label="Solo Exhibition (Text Separated by Year in form yyyy)",lines=60)]
    public var soloExhibition:String;

    [Display(label="Group Exhibition (Text Separated by Year in form yyyy)",lines=60)]
    public var groupExhibition:String;

    [Display(label="Public Exhibition (Text Separated by Year in form yyyy)",lines=60)]
    public var publicExhibition:String;

    [Display(label="Solo Exhibition 1")]
    public var soloexpdf1:PdfLink;

    [Display(label="Solo Exhibition 2")]
    public var soloexpdf2:PdfLink;

    [Display(label="Solo Exhibition 3")]
    public var soloexpdf3:PdfLink;

    [Display(label="Group Exhibition 1")]
    public var groupexpdf1:PdfLink;

    [Display(label="Group Exhibition 2")]
    public var groupexpdf2:PdfLink;

    [Display(label="Group Exhibition 3")]
    public var groupexpdf3:PdfLink;

    [Display(label="Public Exhibition 1")]
    public var publicexpdf1:PdfLink;

    [Display(label="Public Exhibition 2")]
    public var publicexpdf2:PdfLink;

    [Display(label="Public Exhibition 3")]
    public var publicexpdf3:PdfLink;

    [Display(label="Solo Catalogue 1")]
    public var solocatpdf1:PdfLink;

    [Display(label="Solo Catalogue 2")]
    public var solocatpdf2:PdfLink;

    [Display(label="Solo Catalogue 3")]
    public var solocatpdf3:PdfLink;

    [Display(label="Group Catalogue 1")]
    public var groupcatpdf1:PdfLink;

    [Display(label="Group Catalogue 2")]
    public var groupcatpdf2:PdfLink;

    [Display(label="Group Catalogue 3")]
    public var groupcatpdf3:PdfLink;
}
}
