package com.example.hangman;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tools {
	private static Toast mToast;

	// Purpose: Constructor!
	// Arguments: -
	private Tools() {}

	// Purpose: If it exists, cancel an old toast and display a new one with fixed settings.
	// Arguments: Context context, String message
	// Returns: -
	public static void toast(Context context, String message) {
		// If a the toast is already active, cancel it.
		if (Tools.mToast != null) {
			Tools.mToast.cancel();
		}

		Tools.mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		Tools.mToast.show();
	}

	// Purpose: Load the contents of a file and return the lines as a String[].
	// Arguments: Context context, String fileName
	// Returns: String[]
	public static String[] loadTextAsset(Context context, String fileName) {
		String[] strLines;
		ArrayList<String> arliLines = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));

			String line;
			while ((line = reader.readLine()) != null) {
				arliLines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		strLines = new String[arliLines.size()];

		return arliLines.toArray(strLines);
	}
}

