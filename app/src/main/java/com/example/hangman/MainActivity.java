package com.example.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
	private static String[] words;

	// Purpose: Here's code to run upon the creation of the activity.
	// Arguments: Bundle savedInstanceState
	// Return: -
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_main);

		// Hide the top app bar.
		this.getSupportActionBar().hide();

		// Load and store all words once.
		MainActivity.words = Tools.loadTextAsset(this, "dictionary.txt");
	}

	// Purpose: Pick a word from the stored array and return it.
	// Arguments: -
	// Return: String
	public static String pickWord() {
		return MainActivity.words[(int) Math.floor(Math.random() * MainActivity.words.length)];
	}

	// Purpose: This button selects a word from the dictionary and starts GameActivity with it.
	// Arguments: View view
	// Return: -
	public void clickNewGame(View view) {
		Intent intent = new Intent(this, GameActivity.class);
		this.startActivity(intent);
	}

	// Purpose: This button starts AboutActivity.
	// Arguments: View view
	// Return: -
	public void clickAbout(View view) {
		Intent intent = new Intent(this, AboutActivity.class);
		this.startActivity(intent);
	}
}

