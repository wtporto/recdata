package br.edu.ifpb.recdata.leitorCodigoqr;

import recdata.leitoqr.LerQrActivity;
import android.app.Activity;
import android.os.Bundle;

public class QrCode extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        new LerQrActivity();
		
	}

}

/*
 * <style name="AppBaseTheme" parent="Theme.AppCompat.Light"> <!-- Theme
 * customizations available in newer API levels can go in
 * res/values-vXX/styles.xml, while customizations related to
 * backward-compatibility can go here. --> </style>
 * 
 * <!-- Application theme. --> <style name="AppTheme" parent="AppBaseTheme">
 * <!-- All customizations that are NOT specific to a particular API-level can
 * go here. --> </style>
 * 
 * <string name="app_name">ReCDATA</string> <string name="hello_world">Hello
 * world!</string>
 */
