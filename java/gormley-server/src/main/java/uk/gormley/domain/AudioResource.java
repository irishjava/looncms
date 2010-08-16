package uk.gormley.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import uk.mafu.loon.domain.data.AudioLink;

@Entity
@Indexed
@Analyzer(impl=StandardAnalyzer.class)
public class AudioResource extends AbstractPdfItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private Date date;
	
	@OneToOne(optional=true)
	@JoinColumn(name="aud_res_audio_link")
	private AudioLink audio;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AudioLink getAudio() {
		return audio;
	}

	public void setAudio(AudioLink audio) {
		this.audio = audio;
	}

}
