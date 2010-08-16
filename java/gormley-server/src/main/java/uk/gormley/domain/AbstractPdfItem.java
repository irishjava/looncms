package uk.gormley.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import uk.mafu.loon.domain.data.PdfLink;

@Entity
@Indexed
@Analyzer(impl=StandardAnalyzer.class)
public class AbstractPdfItem extends AbstractLinkItem  implements Serializable{

	private static final long serialVersionUID = 1L;

	@OneToOne(optional = true)
	@JoinColumn(name="pdf1_fk")
	private PdfLink pdf1;

	@OneToOne(optional = true)
	@JoinColumn(name="pdf2_fk")
	private PdfLink pdf2;

	@OneToOne(optional = true)
	@JoinColumn(name="pdf3_fk")
	private PdfLink pdf3;

	public PdfLink getPdf1() {
		return this.pdf1;
	}

	public PdfLink getPdf2() {
		return this.pdf2;
	}

	public PdfLink getPdf3() {
		return this.pdf3;
	}

	public void setPdf1(PdfLink pdf1) {
		this.pdf1 = pdf1;
	}

	public void setPdf2(PdfLink pdf2) {
		this.pdf2 = pdf2;
	}

	public void setPdf3(PdfLink pdf3) {
		this.pdf3 = pdf3;
	}
}