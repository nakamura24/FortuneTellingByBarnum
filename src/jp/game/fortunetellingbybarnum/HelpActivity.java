/* Copyright (C) 2013 M.Nakamura
 *
 * This software is licensed under a Creative Commons
 * Attribution-NonCommercial-ShareAlike 2.1 Japan License.
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 		http://creativecommons.org/licenses/by-nc-sa/2.1/jp/legalcode
 */
package jp.game.fortunetellingbybarnum;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class HelpActivity extends Activity {
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			Log.i(TAG, "onCreate");
			setContentView(R.layout.activity_help);
			
			SharedPreferences sharedPreferences = PreferenceManager
					.getDefaultSharedPreferences(this.getApplicationContext());
			int total = sharedPreferences.getInt("Total", 0);
			int believed = sharedPreferences.getInt("Believed", 0);
			Resources resource = getResources();
			String percent = resource
					.getString(R.string.percent);
			TextView textView_percent = (TextView) findViewById(R.id.textView_percent);
			textView_percent.setText(String.format(percent, believed * 100 / total));
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	public void onClickOkButton(View view) {
		try {
			Log.i(TAG, "onClickOkButton");
			// 画面の終了
			Intent intent = new Intent();
			setResult(RESULT_OK, intent);
			finish();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

}
