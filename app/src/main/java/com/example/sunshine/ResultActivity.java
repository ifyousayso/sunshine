package com.example.sunshine;

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

		TextView hyperlinkTFD = (TextView) this.findViewById(R.id.text_hyperlink_tfd);
		String theWord = bundle.getString("THE_WORD").toLowerCase();
		String anchorTFD = "<a href=\"http://www.thefreedictionary.com/" + theWord + "\">" +
				this.getString(R.string.result_tfd) + "</a> (" +
				this.getString(R.string.result_internet) + ")";
		hyperlinkTFD.setText(Html.fromHtml(anchorTFD));
		hyperlinkTFD.setMovementMethod(LinkMovementMethod.getInstance());
		TextView hyperlinkDict = (TextView) this.findViewById(R.id.text_hyperlink_dict);
		String anchorDict = "<a href=\"https://www.dictionary.com/browse/" + theWord + "\">" +
				this.getString(R.string.result_dict) + "</a> (" +
				this.getString(R.string.result_internet) + ")";
		hyperlinkDict.setText(Html.fromHtml(anchorDict));
		hyperlinkDict.setMovementMethod(LinkMovementMethod.getInstance());
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

