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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.activity_main);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i(TAG, "onActivityResult");
		try {
			EditText editText_year = (EditText) findViewById(R.id.editText_year);
			EditText editText_month = (EditText) findViewById(R.id.editText_month);
			EditText editText_day = (EditText) findViewById(R.id.editText_day);
			editText_year.getText().clear();
			editText_month.getText().clear();
			editText_day.getText().clear();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	public void onClickOkButton(View view) {
		Log.i(TAG, "onClickOkButton");
		try {
			EditText editText_year = (EditText) findViewById(R.id.editText_year);
			EditText editText_month = (EditText) findViewById(R.id.editText_month);
			EditText editText_day = (EditText) findViewById(R.id.editText_day);
			if (editText_year.getText().length() <= 0
					|| editText_month.getText().length() <= 0
					|| editText_day.getText().length() <= 0) {
				alertDialog();
				return;
			}
			Intent intent = new Intent(this, ResultActivity.class);
			startActivityForResult(intent, ACTIVITY_RESULT);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	// 警告ダイアログ
	private void alertDialog() {
		Log.i(TAG, "alertDialog");
		try {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this);
			// アラートダイアログのタイトルを設定します
			alertDialogBuilder.setTitle(R.string.alert_title);
			alertDialogBuilder.setMessage(R.string.alert_message);
			// アラートダイアログの肯定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
			alertDialogBuilder.setPositiveButton(R.string.button_ok, null);
			// アラートダイアログを表示します
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}
}
