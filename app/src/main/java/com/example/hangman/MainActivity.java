package com.example.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
	private static String[] wordsSimple;
	private static String[] wordsTricky;
	public static Difficulty difficulty;

	private enum Difficulty { SIMPLE, TRICKY, IMPOSSIBLE };

	// Purpose: Here's code to run upon the creation of the activity.
	// Arguments: Bundle savedInstanceState
	// Return: -
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_main);

		// Hide the top app bar.
		Objects.requireNonNull(this.getSupportActionBar()).hide();

		// Load and store all words once.
		MainActivity.wordsSimple = Tools.loadTextAsset(this, "en-us-simple.txt");
		MainActivity.wordsTricky = Tools.loadTextAsset(this, "en-us-tricky.txt");
	}

	// Purpose: Pick a word from the stored array and return it.
	// Arguments: -
	// Return: String
	public static String pickWord() {
		switch (MainActivity.difficulty) {
			case SIMPLE:
				return MainActivity.wordsSimple[(int) Math.floor(Math.random() * MainActivity.wordsSimple.length)];
			case TRICKY:
				return MainActivity.wordsTricky[(int) Math.floor(Math.random() * MainActivity.wordsTricky.length)];
		}

		return "KWYJIBO";
	}

	// Purpose: This button selects a word from the dictionary and starts GameActivity with it.
	// Arguments: View view
	// Return: -
	public void clickNewGameSimple(View view) {
		MainActivity.difficulty = Difficulty.SIMPLE;

		Intent intent = new Intent(this, GameActivity.class);
		this.startActivity(intent);
	}

	public void clickNewGameTricky(View view) {
		MainActivity.difficulty = Difficulty.TRICKY;

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

