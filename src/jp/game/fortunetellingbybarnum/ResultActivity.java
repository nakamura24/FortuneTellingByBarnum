/* Copyright (C) 2013 M.Nakamura
 *
 * This software is licensed under a Creative Commons
 * Attribution-NonCommercial-ShareAlike 2.1 Japan License.
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 		http://creativecommons.org/licenses/by-nc-sa/2.1/jp/legalcode
 */
package jp.game.fortunetellingbybarnum;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends Activity {
	private static final String TAG = "ResultActivity";
	private static final String[] result1 = {
		"あなたは他人から好かれたい、ほめて欲しいと思ってますが、それにかかわらず自分を批判する傾向にあります。",
		"あなたは外向的・社交的で愛想がよいときもありますが、その一方で内向的で用心深く遠慮がちなときもあります。",
		"あなたはある程度の変化や多様性を好み、制約や制限があるときには不満を抱きます。",
		"あなたの願望にはやや非現実的な傾向のものもあります。",
		"あなたは使われず生かしきれていない才能をかなり持っています。", };
	private static final String[] result2 = {
		"はたから見た場合、規律正しく自制的ですが、内心ではくよくよしたり不安になる傾向があります。",
		"正しい判断や正しい行動をしたのかどうか真剣な疑問を持つときがあります。",
		"そのうえ、あなたは独自の考えを持っていることを誇りに思い、十分な根拠もない他人の意見を聞き入れることはありません。",
		"しかし、あなたは他人に自分のことをさらけ出しすぎるのも賢明でないことにも気付いています。",
		"また、あなたは凹むようなことがあっても、それを克服することができます。", };
	private static Random randam = new Random();
	public static final int ACTIVITY_HELP = 0x0003;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			Log.i(TAG, "onCreate");
			setContentView(R.layout.activity_result);

			String result = "";
			int randold = -1;
			for (int i = 0; i < 2;) {
				int rand = randam.nextInt(5);
				if(randold != rand){
					result += result1[rand];
					randold = rand;
					i++;
				}
			}
			randold = -1;
			for (int i = 0; i < 2;) {
				int rand = randam.nextInt(5);
				if(randold != rand){
					result += result2[rand];
					randold = rand;
					i++;
				}
			}
			TextView textView_result = (TextView) findViewById(R.id.textView_result);
			textView_result.setText(result);
			SharedPreferences sharedPreferences = PreferenceManager
					.getDefaultSharedPreferences(this.getApplicationContext());
			int total = sharedPreferences.getInt("Total", 0);
			Editor editer = sharedPreferences.edit();
			editer.putInt("Total", ++total);
			editer.commit();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i(TAG, "onActivityResult");
		Intent intent = new Intent();
		setResult(RESULT_OK, intent);
		finish();
	}

	public void onClickTrueButton(View view) {
		try {
			Log.i(TAG, "onClickTrueButton");
			Intent intent = new Intent(this, HelpActivity.class);
			startActivityForResult(intent, ACTIVITY_HELP);
			SharedPreferences sharedPreferences = PreferenceManager
					.getDefaultSharedPreferences(this.getApplicationContext());
			int believed = sharedPreferences.getInt("Believed", 0);
			Editor editer = sharedPreferences.edit();
			editer.putInt("Believed", ++believed);
			editer.commit();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	public void onClickFalseButton(View view) {
		try {
			Log.i(TAG, "onClickFalseButton");
			Intent intent = new Intent();
			setResult(RESULT_OK, intent);
			finish();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}
}
