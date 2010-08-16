package uk.gormley.domain
{

import uk.mafu.loon.domain.data.PdfLink;

[RemoteClass(alias="uk.gormley.domain.AbstractPdfItem")]
[Bindable]
public class AbstractPdfItem extends AbstractLinkItem {
	
	[Display(label="Pdf 1",widget=SinglePdf)]
	public var pdf1:PdfLink;
	
	[Display(label="Pdf 2",widget=SinglePdf)]
	public var pdf2:PdfLink;
	
	[Display(label="Pdf 3",widget=SinglePdf)]
	public var pdf3:PdfLink;
	}
} 