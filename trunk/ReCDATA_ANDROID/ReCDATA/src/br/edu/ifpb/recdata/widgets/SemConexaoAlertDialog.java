package br.edu.ifpb.recdata.widgets;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;
import br.edu.ifpb.R.drawable;
import br.edu.ifpb.recdata.telas.TelaAbertura;



public class SemConexaoAlertDialog {
	
	private Activity activity;
	

	public SemConexaoAlertDialog(Activity activity){
		this.activity = activity;
	}
	

	public  void showDialog(){
		
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				this.activity);

		alertDialog.setTitle("Problema na conexão");
		alertDialog.setIcon(drawable.icon_errodefault);
		alertDialog.setMessage("Deseja tentar novamente?");
		alertDialog.setPositiveButton("Sim",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(activity,
						TelaAbertura.class);
				activity.startActivity(intent);
				activity.finish();
			}
		});
		
		
		alertDialog.setNegativeButton("Não",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
		
				Toast.makeText(activity, "Saindo da aplicação",
						Toast.LENGTH_SHORT).show();
				dialog.cancel();
				
				activity.finish();
			}
		});
		
		alertDialog.show();
	}

	


}
