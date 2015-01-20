package com.github.maxpower47.tagviewenhanced.library.ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class TagDrawable extends Drawable {
	private static final int MAGIC_PADDING_LEFT = 0;
	private static final int MAGIC_PADDING_BOTTOM = 6;
	private final String text;
	private final float roundCornersFactor;
	private final Paint textContentPain;
	private final Paint backgroundPaint;
	private final RectF fBounds;
	private Rect backgroundPadding;

	public TagDrawable(String text, int tagPadding, float textSize, boolean bold, int textColor, int tagColor, float roundCornersFactor) {
		this.backgroundPadding = new Rect(tagPadding, tagPadding, tagPadding, tagPadding);
		this.text = text;
		this.roundCornersFactor = roundCornersFactor;
		this.textContentPain = new Paint();
		textContentPain.setColor(textColor);
		textContentPain.setTextSize(textSize);
		textContentPain.setAntiAlias(true);
		textContentPain.setFakeBoldText(bold);
		textContentPain.setStyle(Paint.Style.FILL);
		textContentPain.setTextAlign(Paint.Align.LEFT);

		this.backgroundPaint = new Paint();
		backgroundPaint.setColor(tagColor);
		backgroundPaint.setStyle(Paint.Style.FILL);
		backgroundPaint.setAntiAlias(true);

		setBounds(0, 0,
				(int) textContentPain.measureText(text) + backgroundPadding.left + backgroundPadding.right,
				(int) (textContentPain.getTextSize() + backgroundPadding.top + backgroundPadding.bottom)
		);
		fBounds = new RectF(getBounds());
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRoundRect(fBounds, roundCornersFactor, roundCornersFactor, backgroundPaint);
		canvas.drawText(text, backgroundPadding.left + MAGIC_PADDING_LEFT, textContentPain.getTextSize() + backgroundPadding.top - MAGIC_PADDING_BOTTOM, textContentPain);
	}

	@Override
	public void setAlpha(int alpha) {
		textContentPain.setAlpha(alpha);
		backgroundPaint.setAlpha(alpha);
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		textContentPain.setColorFilter(cf);
	}

	@Override
	public int getOpacity() {
		return PixelFormat.TRANSLUCENT;
	}
}
