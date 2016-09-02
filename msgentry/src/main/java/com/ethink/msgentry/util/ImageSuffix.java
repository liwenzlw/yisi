package com.ethink.msgentry.util;

public enum ImageSuffix {

	PNG("image/png"), JPG("image/jpg"), JPEG("image/jpeg"), GIF("image/gif"), BMP("image/bmp");
	
	private String suffix;

	private ImageSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	@Override
	public String toString() {
		return this.suffix;
	}

}
