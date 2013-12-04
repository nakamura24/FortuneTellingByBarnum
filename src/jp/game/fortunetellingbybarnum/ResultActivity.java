/* Copyright (C) 2013 M.Nakamura
 *
 * This software is licensed under a Creative Commons
 * Attribution-NonCommercial-ShareAlike 2.1 Japan License.
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 		http://creativecommons.org/licenses/by-nc-sa/2.1/jp/legalcode
 */
package jp.game.fortunetellingbybarnum;

import static jp.game.fortunetellingbybarnum.Constant.*;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends Activity {
	private static final String TAG = "ResultActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.activity_result);

			Resources resource = getResources();
			String[] results = resource.getStringArray(R.array.results);
			String[] astrology = resource.getStringArray(R.array.astrology);
			String[] bloodtytes = resource.getStringArray(R.array.bloodtytes);
			String message = resource.getString(R.string.message);
			boolean[] useds = new boolean[results.length];
			String result = "";
			Random randam = new Random();
			for (int i = 0; i < 5;) {
				int rand = randam.nextInt(results.length);
				if (!useds[rand]) {
					result += results[rand];
					useds[rand] = true;
					i++;
				}
			}
			result += astrology[randam.nextInt(astrology.length)]
					+ String.format(message,
							bloodtytes[randam.nextInt(bloodtytes.length)]);
			TextView textView_result = (TextView) findViewById(R.id.textView_result);
			textView_result.setText(result);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i(TAG, "onActivityResult");
		try {
			Intent intent = new Intent();
			setResult(RESULT_OK, intent);
			finish();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	public void onClickTrueButton(View view) {
		Log.i(TAG, "onClickTrueButton");
		try {
			SharedPreferences sharedPreferences = PreferenceManager
					.getDefaultSharedPreferences(this.getApplicationContext());
			int total = sharedPreferences.getInt("Total", 0);
			int believed = sharedPreferences.getInt("Believed", 0);
			Editor editer = sharedPreferences.edit();
			editer.putInt("Total", ++total);
			editer.putInt("Believed", ++believed);
			editer.commit();
			Intent intent = new Intent(this, HelpActivity.class);
			startActivityForResult(intent, ACTIVITY_HELP);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	public void onClickFalseButton(View view) {
		Log.i(TAG, "onClickFalseButton");
		try {
			SharedPreferences sharedPreferences = PreferenceManager
					.getDefaultSharedPreferences(this.getApplicationContext());
			int total = sharedPreferences.getInt("Total", 0);
			Editor editer = sharedPreferences.edit();
			editer.putInt("Total", ++total);
			editer.commit();
			Intent intent = new Intent();
			setResult(RESULT_OK, intent);
			finish();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}
}
