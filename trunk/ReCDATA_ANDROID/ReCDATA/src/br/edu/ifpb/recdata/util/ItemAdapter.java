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
 		descricao.setText("Descrição: "+item.getDescricao().toString());
	
		TextView categoria = (TextView) layout.findViewById(R.id.text_view_item_categoria);
		categoria.setText("Categoria: "+item.getCategoria().getDescricao().toString());
	
		TextView regiao = (TextView) layout.findViewById(R.id.text_view_item_regiao);
		regiao.setText("Região: "+item.getRegiao().getNome().toString());
		
		ImageView imagem = (ImageView) layout.findViewById(R.id.image_view_icon_item_lista_result);
		imagem.setImageResource(item.getImagem(item.getCategoria().getId()));
		
		return layout;
	}

}