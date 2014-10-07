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

public class ModelAdapter extends BaseAdapter {

	
	private Context context;
	private ArrayList<Model> lista;
	
	public ModelAdapter (Context context, ArrayList<Model>lista){
		this.context=context;
		this.lista=lista;
		
		
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
	
		Model item =lista.get(position);
		View layout;
		
		if (convertView == null){
			LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = inflater.inflate(R.layout.listactivity_list_view_personalizada,null);
		}
		else{
			layout = convertView;
		}
		TextView nome = (TextView) layout.findViewById(R.id.text_view_item_nome);
		nome.setText(item.getNome());
		
		ImageView imagem = (ImageView) layout.findViewById(R.id.image_view_icon_item_lista);
		imagem.setImageResource(item.getImagem(position));
		
		return layout;
	}

}
