package com.github.maxpower47.tagviewenhanced.library.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
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
import com.github.maxpower47.tagviewenhanced.library.util.UIUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TagView extends TextView {

	private static final int DEFAULT_PADDING = 8;
	private static final int DEFAULT_CORNER_RADIUS = 6;
	private static final boolean DEFAULT_UPPERCASE = true;

	private static final int DEFAULT_COLOR = 0xfff16364;

	private int tagPadding;
	private int tagCornerRadius;
	private boolean uppercaseTags = DEFAULT_UPPERCASE;
	private int tagColor = DEFAULT_COLOR;

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
			tagPadding = attributesArray.getDimensionPixelSize(R.styleable.TagView_tagPadding, UIUtils.dipToPixels(DEFAULT_PADDING, getContext()));
			tagCornerRadius = attributesArray.getDimensionPixelSize(R.styleable.TagView_tagCornerRadius, UIUtils.dipToPixels(DEFAULT_CORNER_RADIUS, getContext()));
			uppercaseTags = attributesArray.getBoolean(R.styleable.TagView_tagUppercase, DEFAULT_UPPERCASE);
			tagColor = attributesArray.getColor(R.styleable.TagView_tagColor, DEFAULT_COLOR);
			attributesArray.recycle();
		} else {
			tagPadding = UIUtils.dipToPixels(DEFAULT_PADDING, getContext());
			tagCornerRadius = UIUtils.dipToPixels(DEFAULT_CORNER_RADIUS, getContext());
		}

		setMovementMethod(LinkMovementMethod.getInstance());
		setHighlightColor(Color.TRANSPARENT);
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

			int color = tag.getColor() != null ? tag.getColor() : colorGenerator != null ? colorGenerator.getColor(tag) : tagColor;

			String tagContent = uppercaseTags ? tag.getText().toUpperCase() : tag.getText();
			sb.append(tagContent);
			sb.setSpan(createSpan(tagContent, color),
					sb.length() - tagContent.length(),
					sb.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			sb.setSpan(new ClickableTagSpan(tag, listener),
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
