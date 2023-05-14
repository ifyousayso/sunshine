package com.example.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ResultActivity extends AppCompatActivity {
	// Purpose: Here's code to run upon the creation of the activity.
	// Arguments: Bundle savedInstanceState
	// Return: -
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_result);

		// Set up the top app bar:
		Objects.requireNonNull(this.getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
		this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_wb_sunny_24);
		this.getSupportActionBar().setTitle(this.getString(R.string.app_name) + ": " + this.getString(R.string.result_title));

		// Receive the outcome data:
		Bundle bundle = this.getIntent().getExtras();
		TextView outcomeTextView = (TextView) this.findViewById(R.id.outcome_text);
		String outcomeText;
		if (bundle.getBoolean("SUCCESS")) {
			outcomeText = this.getString(R.string.result_won) + "!";
		} else {
			outcomeText = this.getString(R.string.result_lost) + ".";
		}
		outcomeTextView.setText(outcomeText);
		((TextView) this.findViewById(R.id.the_word_text)).setText(this.getString(R.string.result_the_word) + ": " + bundle.getString("THE_WORD"));
		((TextView) this.findViewById(R.id.fails_remaining_text)).setText(this.getString(R.string.global_fails_remaining) + ": " + String.valueOf(bundle.getInt("FAILS_REMAINING")));

		TextView hyperlinkView = (TextView) findViewById(R.id.text_hyperlink);
		String theWord = bundle.getString("THE_WORD").toLowerCase();
		String urlTFD = "http://www.thefreedictionary.com/" + theWord;
		String urlDictionary = "https://www.dictionary.com/browse/" + theWord;
		String hyperlinkText = "Are you curious about the dictionary entry?<br />" +
				"<a href='" + urlTFD + "'>TheFreeDictionary</a> (Internet)<br />" +
				"<a href='" + urlDictionary + "'>dictionary.com</a> (Internet)";
		hyperlinkView.setText(Html.fromHtml(hyperlinkText));
		hyperlinkView.setMovementMethod(LinkMovementMethod.getInstance());
	}

	// Purpose: Load the options menu: The about item.
	// Arguments: Menu menu
	// Return: boolean
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.menu_actions, menu);

		return super.onCreateOptionsMenu(menu);
	}

	// Purpose: Handle the options menu selection.
	// Arguments: @NonNull MenuItem item
	// Return: boolean
	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
			case R.id.global_new_game:
				// Start GameActivity and finish this one:
				intent = new Intent(this, GameActivity.class);
				this.startActivity(intent);
				this.finish();
				return true;
			case R.id.global_about:
				// Start AboutActivity:
				intent = new Intent(this, AboutActivity.class);
				this.startActivity(intent);
				return true;
		}

		return super.onOptionsItemSelected(item);
	}

	// Purpose: Clicking the return button finishes this activity, returning to MainActivity.
	// Arguments: View view
	// Return: -
	public void clickReturn(View view) {
		this.finish();
	}
}

