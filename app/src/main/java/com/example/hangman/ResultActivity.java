package com.example.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
	// Purpose: Here's code to run upon the creation of the activity.
	// Arguments: Bundle savedInstanceState
	// Return: -
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_result);

		// Set up the top app bar:
		this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_sentiment_very_dissatisfied_24);
		this.getSupportActionBar().setTitle(this.getString(R.string.app_name) + ": " + this.getString(R.string.result_title));

		// Receive the outcome data:
		Bundle bundle = this.getIntent().getExtras();
		((TextView) this.findViewById(R.id.outcome_text)).setText(bundle.getBoolean("SUCCESS") ? R.string.result_won : R.string.result_lost);
		((TextView) this.findViewById(R.id.the_word_text)).setText(this.getString(R.string.result_the_word) + " " + bundle.getString("THE_WORD"));
		((TextView) this.findViewById(R.id.fails_remaining_text)).setText(this.getString(R.string.result_fails_remaining) + ": " + String.valueOf(bundle.getInt("FAILS_REMAINING")));
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
			case R.id.action_new_game:
				// Start GameActivity and finish this one:
				intent = new Intent(this, GameActivity.class);
				this.startActivity(intent);
				this.finish();
				return true;
			case R.id.action_about:
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

