package com.github.maxpower47.tagviewenhanced.library.model;

import android.graphics.Color;

public class Tag {
	private String text;
	private Integer color;

	public Tag(String text) {
		this.text = text;
	}

	public Tag(String text, int color) {
		this.text = text;
		this.color = color;
	}

	public Tag(String text, String color) {
		this.text = text;
		this.color = Color.parseColor(color);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}
