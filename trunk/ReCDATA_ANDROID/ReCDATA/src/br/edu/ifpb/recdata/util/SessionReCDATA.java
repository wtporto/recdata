package br.edu.ifpb.recdata.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import br.edu.ifpb.recdata.telas.TelaListaFuncionalidades;
import br.edu.ifpb.recdata.telas.TelaLogin;


public class SessionReCDATA {

	SharedPreferences sharedPreferences;
	Editor editor;
	Activity activity;
	int PRIVATE_MODE = 0;

	private static final String SHAREDPREFERENCES_NAME = "SessionUserReCDATA";
	private static final String IS_LOGIN = "IsLoggedIn";
	public static final String KEY_ID = "id";

	public SessionReCDATA(Activity activity) {
		this.activity = activity;
		sharedPreferences = activity.getSharedPreferences(
				SHAREDPREFERENCES_NAME, PRIVATE_MODE);
		editor = sharedPreferences.edit();
	}

	public void createLoginSession(int id) {

		editor.putBoolean(IS_LOGIN, true);
		editor.putInt(KEY_ID, id);
		editor.commit();
	}

	public int getUserDetails() {
		int user;
		// user id
		user = sharedPreferences.getInt(KEY_ID, 0);

		return user;
	}

	public void logoutUser() {
		editor.clear();
		editor.commit();

		Intent i = new Intent(activity, TelaListaFuncionalidades.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		// Adicionar nova Flag para iniciar nova Activity
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		activity.startActivity(i);
		activity.finish();
	}

	public boolean checkLogin() {
		return sharedPreferences.getBoolean(IS_LOGIN, false);
	}

	@Override
	public String toString() {
		return "SessionReCDATA [sharedPreferences=" + sharedPreferences
				+ ", editor=" + editor + ", activity=" + activity
				+ ", PRIVATE_MODE=" + PRIVATE_MODE + "]";
	}
	
	
}