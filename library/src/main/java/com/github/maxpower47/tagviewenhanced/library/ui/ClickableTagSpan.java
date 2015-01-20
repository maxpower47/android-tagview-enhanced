package com.github.maxpower47.tagviewenhanced.library.ui;

import android.text.style.ClickableSpan;
import android.view.View;

import com.github.maxpower47.tagviewenhanced.library.model.Tag;

public class ClickableTagSpan extends ClickableSpan {
	private final Tag mTag;
	private TagClickListener mTagClickListener;

	public ClickableTagSpan(Tag tag, TagClickListener listener) {
		super();
		if (tag == null) {
			throw new NullPointerException();
		}
		mTag = tag;
		mTagClickListener = listener;
	}

	@Override
	public void onClick(View widget) {
		if (mTagClickListener != null) {
			mTagClickListener.onTagClick(mTag);
		}
	}
}
