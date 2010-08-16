package uk.gormley.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;
import uk.mafu.loon.domain.data.PdfLink;
import uk.mafu.loon.domain.data.VideoLink;

@Entity
public class Website implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(length = 2147483647)
	private String groupExhibition;

	@Id
	private String pk = UUID.randomUUID().toString();

	@Column(length = 2147483647)
	private String publicExhibition;

	@ManyToMany(fetch = FetchType.EAGER)
	@IndexColumn(name = "position", base = 0)
	public List<Slide> slides = new ArrayList<Slide>();

	@Column(length = 2147483647)
	private String soloExhibition;

	@Column(length = 2147483647)
	private String text1;

	@Column(length = 2147483647)
	private String text2;

	@Column(length = 2147483647)
	private String text3;

    /**
     * Solo Exhibition Pdf's
     */
    @OneToOne(optional = true)
	@JoinColumn(name = "soloexpdf1")
	private PdfLink soloexpdf1;

    @OneToOne(optional = true)
	@JoinColumn(name = "soloexpdf2")
	private PdfLink soloexpdf2;

    @OneToOne(optional = true)
	@JoinColumn(name = "soloexpdf3")
	private PdfLink soloexpdf3;

    /**
     * Group Exhibtion Pdf's
     */
    @OneToOne(optional = true)
	@JoinColumn(name = "groupexpdf1")
	private PdfLink groupexpdf1;

    @OneToOne(optional = true)
	@JoinColumn(name = "groupexpdf2")
	private PdfLink groupexpdf2;

    @OneToOne(optional = true)
	@JoinColumn(name = "groupexpdf3")
	private PdfLink groupexpdf3;

    /**
     * Public Exhibtion Pdf's
     */
    @OneToOne(optional = true)
	@JoinColumn(name = "publicexpdf1")
	private PdfLink publicexpdf1;

    @OneToOne(optional = true)
	@JoinColumn(name = "publicexpdf2")
	private PdfLink publicexpdf2;

    public PdfLink getGroupcatpdf3() {
        return groupcatpdf3;
    }

    public void setGroupcatpdf3(PdfLink groupcatpdf3) {
        this.groupcatpdf3 = groupcatpdf3;
    }

    public PdfLink getSoloexpdf1() {
        return soloexpdf1;
    }

    public void setSoloexpdf1(PdfLink soloexpdf1) {
        this.soloexpdf1 = soloexpdf1;
    }

    public PdfLink getSoloexpdf2() {
        return soloexpdf2;
    }

    public void setSoloexpdf2(PdfLink soloexpdf2) {
        this.soloexpdf2 = soloexpdf2;
    }

    public PdfLink getSoloexpdf3() {
        return soloexpdf3;
    }

    public void setSoloexpdf3(PdfLink soloexpdf3) {
        this.soloexpdf3 = soloexpdf3;
    }

    public PdfLink getGroupexpdf1() {
        return groupexpdf1;
    }

    public void setGroupexpdf1(PdfLink groupexpdf1) {
        this.groupexpdf1 = groupexpdf1;
    }

    public PdfLink getGroupexpdf2() {
        return groupexpdf2;
    }

    public void setGroupexpdf2(PdfLink groupexpdf2) {
        this.groupexpdf2 = groupexpdf2;
    }

    public PdfLink getGroupexpdf3() {
        return groupexpdf3;
    }

    public void setGroupexpdf3(PdfLink groupexpdf3) {
        this.groupexpdf3 = groupexpdf3;
    }

    public PdfLink getPublicexpdf1() {
        return publicexpdf1;
    }

    public void setPublicexpdf1(PdfLink publicexpdf1) {
        this.publicexpdf1 = publicexpdf1;
    }

    public PdfLink getPublicexpdf2() {
        return publicexpdf2;
    }

    public void setPublicexpdf2(PdfLink publicexpdf2) {
        this.publicexpdf2 = publicexpdf2;
    }

    public PdfLink getPublicexpdf3() {
        return publicexpdf3;
    }

    public void setPublicexpdf3(PdfLink publicexpdf3) {
        this.publicexpdf3 = publicexpdf3;
    }

    public PdfLink getSolocatpdf1() {
        return solocatpdf1;
    }

    public void setSolocatpdf1(PdfLink solocatpdf1) {
        this.solocatpdf1 = solocatpdf1;
    }

    public PdfLink getSolocatpdf2() {
        return solocatpdf2;
    }

    public void setSolocatpdf2(PdfLink solocatpdf2) {
        this.solocatpdf2 = solocatpdf2;
    }

    public PdfLink getSolocatpdf3() {
        return solocatpdf3;
    }

    public void setSolocatpdf3(PdfLink solocatpdf3) {
        this.solocatpdf3 = solocatpdf3;
    }

    public PdfLink getGroupcatpdf1() {
        return groupcatpdf1;
    }

    public void setGroupcatpdf1(PdfLink groupcatpdf1) {
        this.groupcatpdf1 = groupcatpdf1;
    }

    public PdfLink getGroupcatpdf2() {
        return groupcatpdf2;
    }

    public void setGroupcatpdf2(PdfLink groupcatpdf2) {
        this.groupcatpdf2 = groupcatpdf2;
    }

    @OneToOne(optional = true)

	@JoinColumn(name = "publicexpdf3")
	private PdfLink publicexpdf3;

    /**
     * Solo Catalogue Pdf's
     */
    @OneToOne(optional = true)
	@JoinColumn(name = "solocatpdf1")
	private PdfLink solocatpdf1;

    @OneToOne(optional = true)
	@JoinColumn(name = "solocatpdf2")
	private PdfLink solocatpdf2;

    @OneToOne(optional = true)
	@JoinColumn(name = "solocatpdf3")
	private PdfLink solocatpdf3;

    /**
     * Group Catalogue Pdf's
     */
    @OneToOne(optional = true)
	@JoinColumn(name = "groupcatpdf1")
	private PdfLink groupcatpdf1;

    @OneToOne(optional = true)
	@JoinColumn(name = "groupcatpdf2")
	private PdfLink groupcatpdf2;

    @OneToOne(optional = true)
	@JoinColumn(name = "groupcatpdf3")
	private PdfLink groupcatpdf3;
    
	@Column
	private String identifier;

	public String getGroupExhibition() {
		return this.groupExhibition;
	}

	public String getPk() {
		return this.pk;
	}

	public String getPublicExhibition() {
		return this.publicExhibition;
	}

	public List<Slide> getSlides() {

		return this.slides;
	}

	public String getSoloExhibition() {
		return this.soloExhibition;
	}

	public String getText1() {
		return this.text1;
	}

	public String getText2() {
		return this.text2;
	}

	public String getText3() {
		return this.text3;
	}

	public void setGroupExhibition(String groupExhibition) {
		this.groupExhibition = groupExhibition;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public void setPublicExhibition(String publicExhibition) {
		this.publicExhibition = publicExhibition;
	}

	public void setSlides(List<Slide> slides) {
		this.slides = slides;
	}

	public void setSoloExhibition(String soloExhibition) {
		this.soloExhibition = soloExhibition;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public void setText3(String text3) {
		this.text3 = text3;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

}
