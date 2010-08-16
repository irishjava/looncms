package uk.mafu.loon.common;

import org.apache.commons.lang.StringUtils;

public enum ContentType {
	JPEG("image/jpeg", new String[] { "jpeg", "jpg" }, ContentGroup.IMAGE),
	GIF("image/gif", new String[] { "gif" }, ContentGroup.IMAGE),
	
	PNG("image/png", new String[] { "png" }, ContentGroup.IMAGE),
	PDF("image/pdf", new String[] { "pdf" }, ContentGroup.DOCUMENT),
	AVI("video/avi", new String[] { "avi" }, ContentGroup.VIDEO);
	public static final ContentType getInstanceByContentType(String contentType) {
		if (contentType == null) {
			throw new UnsupportedOperationException("null parameter, contentType");
		}
		for (ContentType type : values()) {
			if (type.getContentType().toLowerCase().equals(contentType.toLowerCase())) {
				return type;
			}
		}
		throw new UnsupportedOperationException("unknown ContentType :'" + contentType + "'");
	}
	public static final ContentType getInstanceByFilename(String filename) {
		if (filename == null) {
			throw new UnsupportedOperationException("bad filename:'" + filename + "'");
		}
		// Snip the filename suffix
		String suffix = StringUtils.substringAfterLast(filename, ".");
		if (suffix == null) {
			throw new UnsupportedOperationException("can't get suffix from :'" + filename + "'");
		}
		String suffixLC = suffix.toLowerCase();
		for (ContentType type : values()) {
			for (String ext : type.fileExtensions) {
				if (ext.equals(suffixLC)) {
					return type;
				}
			}
		}
		throw new UnsupportedOperationException("can't deduce type from filename :'" + filename + "'");
	}
	private final String contentType;

	private final String[] fileExtensions;

	private final ContentGroup group;

	ContentType(String contentType, String[] fileExtensions, ContentGroup group) {
		this.contentType = contentType;
		this.fileExtensions = fileExtensions;
		this.group = group;
	}

	public String getContentType() {
		return contentType;
	}

	public String[] getFileExtensions() {
		return fileExtensions;
	}

	public ContentGroup getGroup() {
		return group;
	}

	public final boolean isContentType(String type) {
		if (contentType.equals(type)) {
			return true;
		}
		return false;
	}

	public boolean isFileType(String extension) {
		for (int i = 0; i < fileExtensions.length; i++) {
			if (extension.toLowerCase().equals(fileExtensions[i].toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}
