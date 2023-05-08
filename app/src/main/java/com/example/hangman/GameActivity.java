package com.example.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
	private Art art;
	private String theWord;
	private char[] answerChars;
	private int failsRemaining = 7;
	private ArrayList<Character> guessedChars = new ArrayList<Character>();
	private TextView answerText;
	private TextView failsRemainingText;
	private TextView lettersText;
	private EditText guessEdit;
	private boolean gameOver = false;

	private String charsToString(char[] chars) {
		String result = "";
		for (int i = 0; i < chars.length; i++) {
			result += chars[i];
		}
		return result;
	}

	// Purpose: Here's code to run upon the creation of the activity.
	// Arguments: Bundle savedInstanceState
	// Return: -
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_game);

		// Set up the top app bar:
		this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_sentiment_very_dissatisfied_24);

		this.theWord = MainActivity.pickWord();

		// Save the elements:
		this.art = (Art) this.findViewById(R.id.hangman_art);
		this.answerText = (TextView) this.findViewById(R.id.answer_text);
		this.failsRemainingText = (TextView) this.findViewById(R.id.fails_remaining_text);
		this.lettersText = (TextView) this.findViewById(R.id.missed_text);
		this.guessEdit = (EditText) this.findViewById(R.id.guess_edit);

		// Draw the dashes:
		this.answerChars = new char[this.theWord.length()];
		for (int i = 0; i < this.theWord.length(); i++) {
			this.answerChars[i] = '-';
		}
		this.answerText.setText(this.charsToString(this.answerChars));

		// Show fails remaining:
		this.failsRemainingText.setText(this.getString(R.string.game_fails_remaining) + ": " + this.failsRemaining);

		// Listen to various Enter keys and click the guess button:
		this.guessEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
				// (actionId != 0 && event == null) || (actionId == 0 && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == 66)
				// EditorInfo.IME_ACTION_DONE = 6 (android:imeOptions)
				// EditorInfo.IME_ACTION_GO = 2 (android:imeOptions)
				// KeyEvent.ACTION_DOWN = 0
				// KeyEvent.ACTION_UP = 1
				// KeyEvent.KEYCODE_ENTER = 66

				// Soft Enter || Hard Enter (main) down -- filter out hard Enter (main) up.
				if (actionId != 0 || event.getAction() == KeyEvent.ACTION_DOWN) {
					clickGuess(findViewById(R.id.guess_button));
					return true; // The event has been handled.
				}

				return false;
		}
		});
	}

	// Purpose: Load the options menu - The about item.
	// Arguments: Menu menu
	// Return: boolean
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.menu_actions, menu);
		menu.findItem(R.id.action_new_game).setVisible(false);

		return super.onCreateOptionsMenu(menu);
	}

	// Purpose: Handle the options menu selection.
	// Arguments: @NonNull MenuItem item
	// Return: boolean
	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_about:
				Intent intent = new Intent(this, AboutActivity.class);
				this.startActivity(intent);
				return true;
		}

		return super.onOptionsItemSelected(item);
	}

	// Purpose: This happens when the guess button is clicked or any Enter (except hardware numpad) is pressed.
	// Arguments: View view
	// Return: -
	public void clickGuess(View view) {
		// If the game is over, clicking this will start ResultActivity.
		if (this.gameOver) {
			Intent intent = new Intent(this, ResultActivity.class);
			intent.putExtra("SUCCESS", this.failsRemaining >= 0);
			intent.putExtra("THE_WORD", this.theWord);
			intent.putExtra("FAILS_REMAINING", (this.failsRemaining >= 0 ? this.failsRemaining : 0)); // Don't display -1.
			this.startActivity(intent);
			this.finish();
			return;
		}

		// There's no letter in the EditText.
		if (this.guessEdit.getText().length() != 1) {
			Tools.toast(this, this.getString(R.string.game_no_letter));
			return;
		}

		// Read the letter and reset the field:
		char guess = this.guessEdit.getText().charAt(0);
		this.guessEdit.setText("");

		// The letter is already used.
		if (this.guessedChars.contains(guess)) {
			Tools.toast(this, "'" + guess + "' " + this.getString(R.string.game_already_used));
			return;
		}

		// Add the letter to the list.
		this.guessedChars.add(guess);

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
			this.art.progress();
			this.failsRemaining--;
			this.failsRemainingText.setText(this.getString(R.string.game_fails_remaining) + ": " + (this.failsRemaining >= 0 ? this.failsRemaining : 0)); // Don't display -1.
			this.lettersText.setText(this.lettersText.getText() + (this.lettersText.getText().length() == 0 ? "" : ", ") + guess);
		} else { // On at least one hit, update the displayed guessed word:
			this.answerText.setText(this.charsToString(this.answerChars));
			this.gameOver = true;
			for (int i = 0; i < this.answerChars.length; i++) {
				if (this.answerChars[i] == '-') {
					this.gameOver = false;
					break;
				}
			}
		}

		// If it's over, disable the guess edit and change the guess button text. (see the beginning of this method)
		if (this.failsRemaining < 0 || this.gameOver) {
			this.gameOver = true;
			this.guessEdit.setEnabled(false);
			this.guessEdit.setVisibility(View.INVISIBLE);
			((Button) this.findViewById(R.id.guess_button)).setText(R.string.game_over);
		}
	}
}

