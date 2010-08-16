package uk.mafu.domain.saatchi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BrandDesignDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private String text;
	private String quoteBody;
	private String quoteReference;
	private List<Brand> brands = new ArrayList<Brand>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public String getQuoteBody() {
		return quoteBody;
	}

	public void setQuoteBody(String quoteBody) {
		this.quoteBody = quoteBody;
	}

	public String getQuoteReference() {
		return quoteReference;
	}

	public void setQuoteReference(String quoteReference) {
		this.quoteReference = quoteReference;
	}
}
