package com.github.maxpower47.tagviewenhanced.example;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.github.maxpower47.tagviewenhanced.library.color.RandomColorGenerator;
import com.github.maxpower47.tagviewenhanced.library.model.Tag;
import com.github.maxpower47.tagviewenhanced.library.ui.TagClickListener;
import com.github.maxpower47.tagviewenhanced.library.ui.TagView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TagView tv = (TagView) findViewById(R.id.tags_1);

		tv.setColorGenerator(RandomColorGenerator.DEFAULT);

		tv.setListener(new TagClickListener() {
			@Override
			public void onTagClick(Tag tag) {
				Toast.makeText(MainActivity.this, tag.getText() + " clicked", Toast.LENGTH_SHORT).show();
			}
		});

		List<Tag> tags = new ArrayList<>();
		tags.add(new Tag("Lorem", "#000000"));
		tags.add(new Tag("ipsum"));
		tags.add(new Tag("dolor"));
		tags.add(new Tag("sit"));
		tags.add(new Tag("amet"));
		tags.add(new Tag("consectetur"));
		tags.add(new Tag("adipiscing"));
		tags.add(new Tag("elit"));
		tags.add(new Tag("Cras"));
		tags.add(new Tag("et"));

		tv.setTags(tags, " ");
	}

}
