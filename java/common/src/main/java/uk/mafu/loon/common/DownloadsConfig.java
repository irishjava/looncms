package uk.mafu.loon.common;

import java.io.Serializable;

public class DownloadsConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	private String imageUrl;
	private String imageThumbUrl;
	private String videoUrl;
	private String audioUrl;
	private String pdfUrl;

	public DownloadsConfig() {
		super();
	}

	public DownloadsConfig(String imageUrl, String videoUrl, String pdfUrl) {
		super();
		this.imageUrl = imageUrl;
		this.videoUrl = videoUrl;
		this.pdfUrl = pdfUrl;
	}

	public DownloadsConfig(String imageUrl, String videoUrl, String pdfUrl,
			String audioUrl) {
		super();
		this.imageUrl = imageUrl;
		this.videoUrl = videoUrl;
		this.pdfUrl = pdfUrl;
		this.audioUrl = audioUrl;
	}

	public DownloadsConfig(String imageUrl, String imageThumbUrl,
			String videoUrl, String pdfUrl, String audioUrl) {
		super();
		this.imageUrl = imageUrl;
		this.imageThumbUrl = imageThumbUrl;
		this.videoUrl = videoUrl;
		this.pdfUrl = pdfUrl;
		this.audioUrl = audioUrl;
	}

	public String getImageThumbUrl() {
		return imageThumbUrl;
	}

	public void setImageThumbUrl(String imageThumbUrl) {
		this.imageThumbUrl = imageThumbUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public String getPdfUrl() {
		return pdfUrl;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

}
