package br.edu.ifpb.recdata.telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import br.edu.ifpb.R;

public class TelaQrCode extends Activity implements OnClickListener {
	public static final int REQUEST_CODE = 0;
	
	private TextView txResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ler_qr);
		
		txResult = (TextView) findViewById(R.id.txResult);
		Button Reserva = (Button) findViewById(R.id.reservaQr);
		Reserva.setOnClickListener(this);
		
		
	}
	
	
	public void callZXing(View view){
		Intent it = new Intent(TelaQrCode.this, com.google.zxing.client.android.CaptureActivity.class);
		startActivityForResult(it, REQUEST_CODE);
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
			txResult.setText("Item: "+data.getStringExtra("SCAN_RESULT")+" ("+data.getStringExtra("SCAN_FORMAT")+")");
	
		}
	}
/*
 	





*/


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}

/*
 
 
 
 */
