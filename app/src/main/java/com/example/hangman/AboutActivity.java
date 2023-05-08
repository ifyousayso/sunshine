package com.example.hangman;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
	// Purpose: Here's code to run upon the creation of the activity.
	// Arguments: Bundle savedInstanceState
	// Return: -
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_about);

		// Set up the top app bar:
		this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		this.getSupportActionBar().setTitle(this.getString(R.string.app_name) + ": " + this.getString(R.string.about_title));
	}

	// Purpose: When the only options item is selected, close the activity.
	// Arguments: @NonNull MenuItem item
	// Return: boolean
	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		this.finish();
		return true;
	}
}

