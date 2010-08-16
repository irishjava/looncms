package uk.mafu.domain.saatchi;

import java.io.Serializable;

public class BrandStrategyDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private String text;
	private String quoteBody;
	private String quoteReference;
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
	
	
}
