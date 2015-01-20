package com.github.maxpower47.tagviewenhanced.library.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class UIUtils {
	public static int dipToPixels(float dipValue, Context context) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
	}
}
