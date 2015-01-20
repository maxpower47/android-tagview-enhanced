package com.github.maxpower47.tagviewenhanced.library.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.github.maxpower47.tagviewenhanced.library.R;
import com.github.maxpower47.tagviewenhanced.library.color.ColorGenerator;
import com.github.maxpower47.tagviewenhanced.library.model.Tag;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TagView extends TextView {

	private static final int DEFAULT_PADDING = 8;
	private static final int DEFAULT_CORNER_RADIUS = 6;
	private static final boolean DEFAULT_UPPERCASE = true;

	private int tagPadding;
	private int tagCornerRadius;
	private boolean uppercaseTags = DEFAULT_UPPERCASE;

	private TagClickListener listener;
	private ColorGenerator colorGenerator;

	@SuppressWarnings("UnusedDeclaration")
	public TagView(Context context) {
		this(context, null);
	}

	public TagView(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.tagViewStyle);
	}

	public TagView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		if (attrs != null) {
			TypedArray attributesArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TagView, defStyle, R.style.Widget_TagView);
			tagPadding = attributesArray.getDimensionPixelSize(R.styleable.TagView_tagPadding, dipToPixels(DEFAULT_PADDING));
			tagCornerRadius = attributesArray.getDimensionPixelSize(R.styleable.TagView_tagCornerRadius, dipToPixels(DEFAULT_CORNER_RADIUS));
			uppercaseTags = attributesArray.getBoolean(R.styleable.TagView_tagUppercase, DEFAULT_UPPERCASE);
			attributesArray.recycle();
		} else {
			tagPadding = dipToPixels(DEFAULT_PADDING);
			tagCornerRadius = dipToPixels(DEFAULT_CORNER_RADIUS);
		}

		this.setMovementMethod(LinkMovementMethod.getInstance());
	}

	private int dipToPixels(float dipValue) {
		DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
	}

	public void setTags(Tag[] tags, String separator) {
		setTags(Arrays.asList(tags), separator);
	}

	public void setTags(List<? extends Tag> tags, String separator) {
		if (tags.isEmpty()) {
			this.setVisibility(View.GONE);
			return;
		}

		final SpannableStringBuilder sb = new SpannableStringBuilder();
		Iterator<? extends Tag> it = tags.iterator();
		while (it.hasNext()) {
			Tag tag = it.next();
			String tagContent = uppercaseTags ? tag.getText().toUpperCase() : tag.getText();
			sb.append(tagContent);
			sb.setSpan(createSpan(tagContent, colorGenerator.getColor(tag)),
					sb.length() - tagContent.length(),
					sb.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			sb.setSpan(new ClickableTagSpan(tagContent, listener),
					sb.length() - tagContent.length(),
					sb.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			if (it.hasNext()) {
				sb.append(separator);
			}
		}
		sb.append(" ");  // hack to fix incorrect line spacing with orphaned span on new line
		setText(sb);
	}

	private ImageTagSpan createSpan(String text, int color) {
		return new ImageTagSpan(
				text,
				tagPadding,
				getTextSize(),
				getTypeface() == Typeface.DEFAULT_BOLD,
				getCurrentTextColor(),
				color,
				tagCornerRadius
		);
	}

	public int getTagPadding() {
		return tagPadding;
	}

	public void setTagPadding(int tagPadding) {
		this.tagPadding = tagPadding;
	}

	public int getTagCornerRadius() {
		return tagCornerRadius;
	}

	public void setTagCornerRadius(int tagCornerRadius) {
		this.tagCornerRadius = tagCornerRadius;
	}

	public boolean isUppercaseTags() {
		return uppercaseTags;
	}

	public void setUppercaseTags(boolean uppercaseTags) {
		this.uppercaseTags = uppercaseTags;
	}

	public TagClickListener getListener() {
		return listener;
	}

	public void setListener(TagClickListener listener) {
		this.listener = listener;
	}


	public ColorGenerator getColorGenerator() {
		return colorGenerator;
	}

	public void setColorGenerator(ColorGenerator colorGenerator) {
		this.colorGenerator = colorGenerator;
	}


}
