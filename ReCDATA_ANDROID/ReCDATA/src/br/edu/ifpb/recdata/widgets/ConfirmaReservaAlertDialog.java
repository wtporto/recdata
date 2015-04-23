package br.edu.ifpb.recdata.widgets;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;
import br.edu.ifpb.R.drawable;
import br.edu.ifpb.recdata.telas.TelaLogin;



public class ConfirmaReservaAlertDialog {
	
	private Activity activity;
	

	public ConfirmaReservaAlertDialog(Activity activity){
		this.activity = activity;
	}
	

	public  void showDialog(){
		
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				this.activity);

		alertDialog.setTitle("Reserva do Item");//TODO: troca este valor por constantes.
		alertDialog.setIcon(drawable.icon_pergunta);
		alertDialog.setMessage("Deseja Confirma sua reserva neste Horário?");
		alertDialog.setPositiveButton("Sim",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(activity,
						TelaLogin.class);//nova activity
				activity.startActivity(intent);
				activity.finish();
			}
		});
		
		
		alertDialog.setNegativeButton("Não",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
		
				Toast.makeText(activity, "Informe Um novo horário!",
						Toast.LENGTH_SHORT).show();
				dialog.cancel();
				
			
			}
		});
		
		alertDialog.show();
	}

	


}

