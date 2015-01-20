package com.github.maxpower47.tagviewenhanced.library.color;

import com.github.maxpower47.tagviewenhanced.library.model.Tag;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomColorGenerator implements ColorGenerator {
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
	private final Random mRandom;

	public static ColorGenerator create(List<Integer> colorList) {
		return new RandomColorGenerator(colorList);
	}

	private RandomColorGenerator(List<Integer> colorList) {
		mColors = colorList;
		mRandom = new Random(System.currentTimeMillis());
	}

	public int getColor(Tag tag) {
		return mColors.get(mRandom.nextInt(mColors.size()));
	}
}
