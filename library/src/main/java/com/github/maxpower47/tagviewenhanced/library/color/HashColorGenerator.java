package com.github.maxpower47.tagviewenhanced.library.color;

import com.github.maxpower47.tagviewenhanced.library.model.Tag;

import java.util.Arrays;
import java.util.List;

public class HashColorGenerator implements ColorGenerator {
	public static ColorGenerator DEFAULT;

	static {
		DEFAULT = create(Arrays.asList(
				0xfff16364,
				0xfff58559,
				0xfff9a43e,
				0xffe4c62e,
				0xff67bf74,
				0xff59a2be,
				0xff2093cd,
				0xffad62a7,
				0xff805781
		));
	}

	private final List<Integer> mColors;

	public static ColorGenerator create(List<Integer> colorList) {
		return new HashColorGenerator(colorList);
	}

	private HashColorGenerator(List<Integer> colorList) {
		mColors = colorList;
	}

	public int getColor(Tag tag) {
		return mColors.get(Math.abs(tag.getText().hashCode()) % mColors.size());
	}
}
