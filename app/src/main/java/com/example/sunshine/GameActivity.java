package com.example.sunshine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class GameActivity extends AppCompatActivity {
	private String theWord;
	private char[] answerChars;
	private int failsRemaining = 7;
	private TextView failsRemainingText;
	private boolean gameOver = false;

	private Button selectedLetter = null;

	// Purpose: Here's code to run upon the creation of the activity.
	// Arguments: Bundle savedInstanceState
	// Return: -
	@SuppressLint("SetTextI18n")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_game);

		// Set up the top app bar:
		Objects.requireNonNull(this.getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
		this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_wb_sunny_24);

		this.theWord = MainActivity.pickWord();

		// Save the elements:
		this.failsRemainingText = (TextView) this.findViewById(R.id.fails_remaining_text);

		// Draw the dashes:
		this.answerChars = new char[this.theWord.length()];
		for (int i = 0; i < this.theWord.length(); i++) {
			this.answerChars[i] = '-';
		}
		TextView answerText = (TextView) this.findViewById(R.id.answer_text);
		answerText.setText(new String(this.answerChars));

		// Show fails remaining:
		this.failsRemainingText.setText(this.getString(R.string.global_fails_remaining) + ": " + this.failsRemaining);
	}

	// Purpose: Load the options menu - The about item.
	// Arguments: Menu menu
	// Return: boolean
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.menu_actions, menu);
		menu.findItem(R.id.global_new_game).setVisible(false);

		return super.onCreateOptionsMenu(menu);
	}

	// Purpose: Handle the options menu selection.
	// Arguments: @NonNull MenuItem item
	// Return: boolean
	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.global_about:
				Intent intent = new Intent(this, AboutActivity.class);
				this.startActivity(intent);
				return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void clickLetter(View view) {
		if (this.gameOver) {
			return;
		}

		Button clickedLetter = (Button) view;

//		Log.d("lgt", String.valueOf(view.getId()));
//		Log.d("lgt", view.getId() == R.id.button_a ? "true" : "false");

		if (this.selectedLetter != null) {
			this.selectedLetter.setBackgroundColor(getResources().getColor(R.color.peanut));
		}

		this.selectedLetter = clickedLetter;

		clickedLetter.setBackgroundColor(getResources().getColor(R.color.brunette));

		this.findViewById(R.id.button_guess).setEnabled(true);
	}

	// Purpose: This happens when the guess button is clicked or any Enter (except hardware numpad) is pressed.
	// Arguments: View view
	// Return: -
	@SuppressLint("SetTextI18n")
	public void clickGuess(View view) {
		// If the game is over, clicking this will start ResultActivity.
		if (this.gameOver) {
			Intent intent = new Intent(this, ResultActivity.class);
			intent.putExtra("SUCCESS", this.failsRemaining >= 0);
			intent.putExtra("THE_WORD", this.theWord);
			intent.putExtra("FAILS_REMAINING", (Math.max(this.failsRemaining, 0))); // Don't display -1.
			this.startActivity(intent);
			this.finish();
			return;
		}

		char guess = this.selectedLetter.getText().charAt(0);

		this.selectedLetter.setTextColor(getResources().getColor(R.color.peanut));
		this.selectedLetter.setBackgroundColor(0x00000000);
		this.selectedLetter.setEnabled(false);
		this.selectedLetter = null;

//		// The letter is already used.
//		if (this.guessedChars.contains(guess)) {
//			Tools.toast(this, "'" + guess + "' " + this.getString(R.string.game_already_used));
//			return;
//		}

		// Loop through the selected word to find any matches. Deal with hits:
		boolean letterFound = false;
		for (int i = 0; i < this.theWord.length(); i++) {
			if (this.theWord.charAt(i) == guess) {
				letterFound = true;
				this.answerChars[i] = guess;
			}
		}

		// Deal with a miss:
		if (!letterFound) {
			((Art) this.findViewById(R.id.sunshine_art)).progress();
			this.failsRemaining--;
			this.failsRemainingText.setText(this.getString(R.string.global_fails_remaining) + ": " + (Math.max(this.failsRemaining, 0))); // Don't display -1.
		} else { // On at least one hit, update the displayed guessed word:
			TextView answerText = (TextView) this.findViewById(R.id.answer_text);
			answerText.setText(new String(this.answerChars));
			this.gameOver = true;
			for (char answerChar : this.answerChars) {
				if (answerChar == '-') {
					this.gameOver = false;
					break;
				}
			}
		}

		// If it's over, disable the guess edit and change the guess button text. (see the beginning of this method)
		if (this.failsRemaining < 0 || this.gameOver) {
			this.gameOver = true;
			((Button) this.findViewById(R.id.button_guess)).setText(R.string.game_over);
		} else {
			view.setEnabled(false);
		}
	}
}

