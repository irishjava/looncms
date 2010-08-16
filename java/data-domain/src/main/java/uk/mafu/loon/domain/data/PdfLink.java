package uk.mafu.loon.domain.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PdfLink implements Serializable, Link {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pk;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "PDF_ID", nullable = true)
	private int pdfId = -1;

         @Column
         private String caption;

         public void setCaption(String caption ){
                 this.caption = caption;
         }

         public String getCaption(){
                 return this.caption;
         }


	public int getPk() {
		return pk;
	}

	public String getTitle() {
		return title;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPdfId() {
		return pdfId;
	}

	public void setPdfId(int pdfId) {
		this.pdfId = pdfId;
	}
}
