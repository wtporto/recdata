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

public class ItemAdapter extends BaseAdapter {

	
	private Context context;
	private ArrayList<Item> lista;
	
	
	public ItemAdapter(Context busca, ArrayList<Item> itens) {
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
	
		Item item =lista.get(position);
		View layout;
		
		if (convertView == null){
			LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = inflater.inflate(R.layout.listaactivity_resultados,null);
		}
		else{
			layout = convertView;
		}
		
		TextView descricao = (TextView) layout.findViewById(R.id.text_view_item_descricao);
 		descricao.setText("Descrição: "+item.getDescricaoItem().toString());
		
 		// não sei pq só pega assim :(
 		String idItem="Id.Item: ";

		TextView  iditem  = (TextView) layout.findViewById(R.id.text_view_item_id);
		iditem.setText(idItem+String.valueOf(item.getIdItem()));
		
		TextView categoria = (TextView) layout.findViewById(R.id.text_view_item_categoria);
		categoria.setText("Categoria: "+item.getDescricaoCategoria().toString());
	
		String idCat="Id.Categoria: ";
		
		TextView idcategoria = (TextView) layout.findViewById(R.id.text_view_item_idcategoria);
		idcategoria.setText(idCat+String.valueOf(item.getIdCategoria()));
		
		//int idcat = Integer.parseInt((String) idcategoria.getText());
	
		
		ImageView imagem = (ImageView) layout.findViewById(R.id.image_view_icon_item_lista_result);
		imagem.setImageResource(item.getImagem(item.getIdCategoria()));
		
		return layout;
	}

}