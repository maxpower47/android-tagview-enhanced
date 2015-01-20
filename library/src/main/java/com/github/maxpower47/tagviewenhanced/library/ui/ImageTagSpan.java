package com.github.maxpower47.tagviewenhanced.library.ui;

import android.text.style.ImageSpan;

public class ImageTagSpan extends ImageSpan {
	public ImageTagSpan(String text, int tagPadding, float textSize, boolean bold, int textColor, int tagColor, float roundCornersFactor) {
		super(new TagDrawable(text, tagPadding, textSize, bold, textColor, tagColor, roundCornersFactor));
	}
}
