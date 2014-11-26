package br.edu.ifpb.recdata.util;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.entity.Item;
import br.edu.ifpb.recdata.entity.ReservaItem;

public class ReservaAdapter extends BaseAdapter {

	
	private Context context;
	private ArrayList<ReservaItem> lista;
	
	
	public ReservaAdapter(Context busca, ArrayList<ReservaItem> itens) {
		this.context=busca;
		this.lista=  itens;
		
	}


	@Override
	public int getCount() {
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	
		ReservaItem reserva =lista.get(position);
		View layout;
		
		if (convertView == null){
			LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = inflater.inflate(R.layout.listaactivity_reserva,null);
		}
		else{
			layout = convertView;
		}
		
		TextView descricao = (TextView) layout.findViewById(R.id.text_view_horaDataInicio);
 		descricao.setText("Hora/Data Inicio : "+reserva.getHoraDataInicio().toString());
	
		TextView categoria = (TextView) layout.findViewById(R.id.text_view_horaDataFim);
		categoria.setText("Hora/Data Fim: "+reserva.getHoraDataFim().toString());
	
		
		ImageView imagem = (ImageView) layout.findViewById(R.id.image_view_icon_item_lista_result);
		imagem.setImageResource(reserva.getImagem(1));
		
		return layout;
	}

}